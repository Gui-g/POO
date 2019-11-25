package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Dependente;

public class DependenteDAO {

	private static DependenteDAO instance = null;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectid;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqlselectcontrib;
	private PreparedStatement resetid;
	
	public static DependenteDAO getInstance() {
		if(instance == null) 
			instance = new DependenteDAO();
		
		return instance;
	}
	
	private DependenteDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into dependentes (nome, endereco, cpf, idade, id_contribuinte) values (?, ?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from dependentes where id = ?");
			sqlselect = conn.prepareStatement("select * from dependentes where id = ?");
			sqlselectid = conn.prepareStatement("select * from dependentes where id_contribuinte = ?");
			sqlall = conn.prepareStatement("select * from dependentes");
			sqlupdate = conn.prepareStatement("update dependentes set nome = ?, endereco = ?, cpf = ?, idade = ? where id = ?");
			sqlselectcontrib = conn.prepareStatement("select id_contribuinte from dependentes where id = ?");
			resetid = conn.prepareStatement("alter sequence dependentes_id_seq restart with 1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void restart() {
		try {
			resetid.executeQuery();
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
	
	public void update(Dependente dependente) {
		try {
			sqlupdate.setInt(5, dependente.getId());
			sqlupdate.setString(1, dependente.getNome());
			sqlupdate.setString(2, dependente.getEndereco());
			sqlupdate.setString(3, dependente.getCpf());
			sqlupdate.setInt(4, dependente.getIdade());
			sqlupdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Dependente pessoa, int id) {
		try {
			sqlinsert.setString(1, pessoa.getNome());
			sqlinsert.setString(2, pessoa.getEndereco());
			sqlinsert.setString(3, pessoa.getCpf());
			sqlinsert.setInt(4, pessoa.getIdade());
			sqlinsert.setInt(5, id);
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Dependente> selectId(int id) {
		ResultSet rs;
		ArrayList<Dependente> dependentes = new ArrayList<Dependente>();
		Dependente pessoa;
		
		try {
			sqlselectid.setInt(1, id);
			rs = sqlselectid.executeQuery();
			while(rs.next()) {
				pessoa = new Dependente();
				pessoa.setId(rs.getInt("id"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setIdade(rs.getInt("idade"));
				dependentes.add(pessoa);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dependentes;
	}
	
	public Dependente select(int id) {
		ResultSet rs;
		Dependente pessoa = null;
		
		try {
			sqlselect.setInt(1, id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				pessoa = new Dependente();
				pessoa.setId(rs.getInt("id"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setIdade(rs.getInt("idade"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoa;
	}
	
	public int selectIdContrib(int id) {
		ResultSet rs;
		int res = -1;
		
		try {
			sqlselectcontrib.setInt(1, id);
			rs = sqlselectcontrib.executeQuery();
			if(rs.next()) {
				res = rs.getInt("id_contribuinte");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public ArrayList<Dependente> selectAll() {
		ResultSet rs;
		ArrayList<Dependente> pessoas  = new ArrayList<Dependente>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				pessoas.add(select(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
}
