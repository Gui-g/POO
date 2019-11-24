package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Bem;

public class BemDAO {

	private static BemDAO instance = null;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static BemDAO getInstance() {
		if(instance == null) 
			instance = new BemDAO();
		
		return instance;
	}
	
	private BemDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into bem (id, nome, tipo, valor) values (?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from bem where id = ?");
			sqlselect = conn.prepareStatement("select * from bem where id = ?");
			sqlall = conn.prepareStatement("select id from bem");
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
	
	public void insert(Bem bem) {
		try {
			sqlinsert.setString(2, bem.getNome());
			sqlinsert.setString(3, bem.getTipo());
			sqlinsert.setFloat(4, bem.getValor());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Bem select(int id) {
		ResultSet rs;
		Bem bem = null;
		
		try {
			sqlselect.setInt(1, id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				bem = new Bem();
				bem.setId(rs.getInt("id"));
				bem.setNome(rs.getString("nome"));
				bem.setTipo(rs.getString("tipo"));
				bem.setValor(rs.getFloat("valor"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bem;
	}
	
	public ArrayList<Bem> getBem() {
		ResultSet rs;
		ArrayList<Bem> bens = new ArrayList<Bem>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				bens.add(select(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bens;
	}
}
