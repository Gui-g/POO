package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.postgresql.util.PGInterval;

import dados.Categoria;

public class CategoriaDAO {

	private static CategoriaDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static CategoriaDAO getInstance() {
		
		if (instance == null) 
			instance = new CategoriaDAO();
		
		return instance;
	}
	
	private CategoriaDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into categorias (id_cat, nome, tempo_emprest) values (default, ? , ?)");
			sqlupdate = conn.prepareStatement("update categorias set nome = ?, tempo_emprest = ? where id_cat = ?");
			sqldelete = conn.prepareStatement("delete from categorias where id_cat = ?");
			sqlselect = conn.prepareStatement("select * from categorias where id_cat = ?");
			sqlall = conn.prepareStatement("select id_cat from categorias");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Categoria cat) {
		try {
			sqlinsert.setString(1, cat.getNome());
			sqlinsert.setObject(2, cat.getTempo());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Categoria cat) {
		try {
			sqlupdate.setString(1, cat.getNome());
			sqlupdate.setObject(2,  cat.getTempo());
			sqlupdate.setInt(3, cat.getIdCat());
			sqlupdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id) {
		try {
			sqldelete.setInt(1, id);
			sqldelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Categoria select(int id) {
		ResultSet rs;
		Categoria cat = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				cat = new Categoria();
				cat.setIdCat(rs.getInt("id_cat"));
				cat.setNome(rs.getString("nome"));
				cat.setTempo((PGInterval) rs.getObject("tempo_emprest"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cat;
	}
	
	public ArrayList<Categoria> all() {
		ResultSet rs;
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				categorias.add(select(rs.getInt("id_cat")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categorias;
	}
	
}
