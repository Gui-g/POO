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
	private PreparedStatement sqlselectid;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqlselectidbem;
	private PreparedStatement sqlsum;
	private PreparedStatement sqlrestart;
	
	public static BemDAO getInstance() {
		if(instance == null) 
			instance = new BemDAO();
		
		return instance;
	}
	
	private BemDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into bens (nome, tipo, valor, id_contribuinte) values (?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from bens where id = ?");
			sqlselect = conn.prepareStatement("select * from bens where id = ?");
			sqlall = conn.prepareStatement("select id from bens");
			sqlselectid = conn.prepareStatement("select id_contribuinte from bens where id = ?");
			sqlupdate = conn.prepareStatement("update bens set nome = ?, tipo = ?, valor = ? where id = ?");
			sqlselectidbem = conn.prepareStatement("select * from bens where id_contribuinte = ?");
			sqlsum = conn.prepareStatement("select sum(valor) from bens where id_contribuinte = ?");
			sqlrestart = conn.prepareStatement("alter sequence bens_id_seq restart");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void restart() {
		try {
			sqlrestart.executeQuery();
		} catch (SQLException e) {
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
	
	public void update(Bem bem) {
		try {
			sqlupdate.setInt(4, bem.getId());
			sqlupdate.setString(1, bem.getNome());
			sqlupdate.setString(2, bem.getTipo());
			sqlupdate.setFloat(3, bem.getValor());
			sqlupdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public float soma(int ID) throws Exception {
		ResultSet rs;
		Float sum = 0.0F;
		try {
			sqlsum.setInt(1, ID);
			rs = sqlsum.executeQuery();
			if(rs.next() ) {
				String resultado = rs.getString(1);
				if(resultado.equals(""))
					throw new Exception();
				sum = Float.parseFloat(resultado);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	
	public ArrayList<Bem> selectId(int id) {
		ResultSet rs;
		ArrayList<Bem> bens = new ArrayList<Bem>();
		Bem bem;
		
		try {
			sqlselectidbem.setInt(1, id);
			rs = sqlselectidbem.executeQuery();
			while(rs.next()) {
				bem = new Bem();
				bem.setId(rs.getInt("id"));
				bem.setNome(rs.getString("nome"));
				bem.setTipo(rs.getString("tipo"));
				bem.setValor(rs.getFloat("valor"));
				bens.add(bem);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bens;
	}
	
	public void insert(Bem bem, int id) {
		try {
			sqlinsert.setString(1, bem.getNome());
			sqlinsert.setString(2, bem.getTipo());
			sqlinsert.setFloat(3, bem.getValor());
			sqlinsert.setInt(4, id);
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
	
	public int selectIdContrib(int id) {
		ResultSet rs;
		int res = -1;
		
		try {
			sqlselectid.setInt(1, id);
			rs = sqlselectid.executeQuery();
			if(rs.next()) {
				res = rs.getInt("id_contribuinte");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public ArrayList<Bem> selectAll() {
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
