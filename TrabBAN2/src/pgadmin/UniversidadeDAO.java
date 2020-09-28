package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Universidade;

public class UniversidadeDAO {

	private static UniversidadeDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static UniversidadeDAO getInstance() {
		
		if (instance == null) 
			instance = new UniversidadeDAO();
		
		return instance;
	}
	
	private UniversidadeDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into universidade (id_uni, nome, sigla) values (default, ? , ?)");
			sqlupdate = conn.prepareStatement("update universidade set nome = ?, sigla = ? where id_uni = ?");
			sqldelete = conn.prepareStatement("delete from universidade where id_uni = ?");
			sqlselect = conn.prepareStatement("select * from universidade where id_uni = ?");
			sqlall = conn.prepareStatement("select id_uni from universidade");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Universidade universidade) {
		try {
			sqlinsert.setString(1, universidade.getNome());
			sqlinsert.setString(2, universidade.getSigla());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Universidade universidade) {
		try {
			sqlupdate.setString(1, universidade.getNome());
			sqlupdate.setString(2, universidade.getSigla());
			sqlupdate.setInt(3, universidade.getIdUni());
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
	
	public Universidade select(int id) {
		ResultSet rs;
		Universidade universidade = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				universidade = new Universidade();
				universidade.setIdUni(rs.getInt("id_uni"));
				universidade.setNome(rs.getString("nome"));
				universidade.setSigla(rs.getString("sigla"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return universidade;
	}
	
	public ArrayList<Universidade> all() {
		ResultSet rs;
		ArrayList<Universidade> universidade = new ArrayList<Universidade>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				universidade.add(select(rs.getInt("id_uni")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return universidade;
	}
	
}
