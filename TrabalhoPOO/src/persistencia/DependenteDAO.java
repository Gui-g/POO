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
	private PreparedStatement sqlselectcpf;
	private PreparedStatement sqlupdate;
	
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
			sqlselectcpf = conn.prepareStatement("select * from dependentes where cpf like ?");
			sqlall = conn.prepareStatement("select * from contribuintes");
			sqlupdate = conn.prepareStatement("update contribuintes set nome = ?, endereco = ?, cpf = ?, idade = ? where id = ?");
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
	
	public ArrayList<Dependente> selectCpf(String cpf) {
		ResultSet rs;
		ArrayList<Dependente> dependentes = new ArrayList<Dependente>();
		Dependente pessoa;
		
		try {
			sqlselectcpf.setString(1, String.format("%%%s%%", cpf));
			rs = sqlselectcpf.executeQuery();
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
