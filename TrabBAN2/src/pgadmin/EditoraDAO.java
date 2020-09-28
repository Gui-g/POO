package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Editora;

public class EditoraDAO {

	private static EditoraDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static EditoraDAO getInstance() {
		
		if (instance == null) 
			instance = new EditoraDAO();
		
		return instance;
	}
	
	private EditoraDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into editoras (id_edit, nome, cnpj) values (default, ? , ?)");
			sqlupdate = conn.prepareStatement("update editoras set nome = ?, cnpj = ? where id_edit = ?");
			sqldelete = conn.prepareStatement("delete from editoras where id_edit = ?");
			sqlselect = conn.prepareStatement("select * from editoras where id_edit = ?");
			sqlall = conn.prepareStatement("select id_edit from editoras");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Editora editora) {
		try {
			sqlinsert.setString(1, editora.getNome());
			sqlinsert.setString(2, editora.getCnpj());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Editora editora) {
		try {
			sqlupdate.setString(1, editora.getNome());
			sqlupdate.setString(2, editora.getCnpj());
			sqlupdate.setInt(3, editora.getIdEdi());
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
	
	public Editora select(int id) {
		ResultSet rs;
		Editora editora = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				editora = new Editora();
				editora.setIdEdi(rs.getInt("id_edit"));
				editora.setNome(rs.getString("nome"));
				editora.setCnpj(rs.getString("cnpj"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return editora;
	}
	
	public ArrayList<Editora> all() {
		ResultSet rs;
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				editoras.add(select(rs.getInt("id_edit")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return editoras;
	}
	
}
