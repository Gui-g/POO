package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Colecao;

public class ColecaoDAO {

	private static ColecaoDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static ColecaoDAO getInstance() {
		
		if (instance == null) 
			instance = new ColecaoDAO();
		
		return instance;
	}
	
	private ColecaoDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into colecoes (id_col, nome) values (default, ?)");
			sqlupdate = conn.prepareStatement("update colecoes set nome = ? where id_col = ?");
			sqldelete = conn.prepareStatement("delete from colecoes where id_col = ?");
			sqlselect = conn.prepareStatement("select * from colecoes where id_col = ?");
			sqlall = conn.prepareStatement("select id_col from colecoes");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Colecao colecao) {
		try {
			sqlinsert.setString(1, colecao.getNome());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Colecao colecao) {
		try {
			sqlupdate.setString(1, colecao.getNome());
			sqlupdate.setInt(2, colecao.getIdCol());
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
	
	public Colecao select(int id) {
		ResultSet rs;
		Colecao colecao = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				colecao = new Colecao();
				colecao.setIdCol(rs.getInt("id_col"));
				colecao.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return colecao;
	}
	
	public ArrayList<Colecao> all() {
		ResultSet rs;
		ArrayList<Colecao> colecoes = new ArrayList<Colecao>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				colecoes.add(select(rs.getInt("id_col")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return colecoes;
	}
	
}
