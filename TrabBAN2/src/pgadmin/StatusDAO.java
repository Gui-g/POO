package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Status;

public class StatusDAO {

	private static StatusDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static StatusDAO getInstance() {
		
		if (instance == null) 
			instance = new StatusDAO();
		
		return instance;
	}
	
	private StatusDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into status (id_st, nome) values (default, ?)");
			sqlupdate = conn.prepareStatement("update status set nome = ? where id_st = ?");
			sqldelete = conn.prepareStatement("delete from status where id_st = ?");
			sqlselect = conn.prepareStatement("select * from status where id_st = ?");
			sqlall = conn.prepareStatement("select id_st from status");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Status status) {
		try {
			sqlinsert.setString(1, status.getNome());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Status status) {
		try {
			sqlupdate.setString(1, status.getNome());
			sqlupdate.setInt(2, status.getIdSt());
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
	
	public Status select(int id) {
		ResultSet rs;
		Status status = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				status = new Status();
				status.setIdSt(rs.getInt("id_st"));
				status.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public ArrayList<Status> all() {
		ResultSet rs;
		ArrayList<Status> status = new ArrayList<Status>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				status.add(select(rs.getInt("id_st")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
}
