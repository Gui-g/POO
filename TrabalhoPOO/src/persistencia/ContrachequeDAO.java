package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Contracheque;

public class ContrachequeDAO {

	private static ContrachequeDAO instance = null;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static ContrachequeDAO getInstance() {
		if(instance == null) 
			instance = new ContrachequeDAO();
		
		return instance;
	}
	
	private ContrachequeDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into contracheque (num_protocolo, valor, id_contribuinte, id_pj) values (?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from contracheque where id = ?");
			sqlselect = conn.prepareStatement("select * from contracheque where id = ?");
			sqlall = conn.prepareStatement("select id from contracheque");
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
	
	public void insert(Contracheque cheque, int id1, int id2) {
		try {
			sqlinsert.setInt(1, cheque.getNumProtocolo());
			sqlinsert.setFloat(2, cheque.getValor());
			sqlinsert.setInt(3, id1);
			sqlinsert.setInt(4, id2);
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Contracheque select(int id) {
		ResultSet rs;
		Contracheque cheque = null;
		
		try {
			sqlselect.setInt(1, id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				cheque = new Contracheque();
				cheque.setId(rs.getInt("id"));
				cheque.setNumProtocolo(rs.getInt("num_protocolo"));
				cheque.setCnpj(rs.getString("cnpj"));
				cheque.setValor(rs.getFloat("valor"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cheque;
	}
	
	public ArrayList<Contracheque> getNotas() {
		ResultSet rs;
		ArrayList<Contracheque> cheques = new ArrayList<Contracheque>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				cheques.add(select(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cheques;
	}
}
