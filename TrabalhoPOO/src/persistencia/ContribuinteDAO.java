package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Contribuinte;

public class ContribuinteDAO {

	private static ContribuinteDAO instance = null;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectcpf;
	private PreparedStatement sqlupdate;
	
	public static ContribuinteDAO getInstance() {
		if(instance == null) 
			instance = new ContribuinteDAO();
		
		return instance;
	}
	
	private ContribuinteDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into contribuintes (nome, endereco, cpf, idade, conta_bancaria) values (?, ?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from contribuintes where id = ?");
			sqlselect = conn.prepareStatement("select * from contribuintes where id = ?");
			sqlselectcpf = conn.prepareStatement("select * from contribuintes where cpf like ?");
			sqlall = conn.prepareStatement("select * from contribuintes");
			sqlupdate = conn.prepareStatement("update contribuintes set nome = ?, endereco = ?, cpf = ?, idade = ?, conta_bancaria = ? where id = ?");
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
	
	public void update(Contribuinte contribuinte) {
		try {
			sqlupdate.setInt(6, contribuinte.getId());
			sqlupdate.setString(1, contribuinte.getNome());
			sqlupdate.setString(2, contribuinte.getEndereco());
			sqlupdate.setString(3, contribuinte.getCpf());
			sqlupdate.setInt(4, contribuinte.getIdade());
			sqlupdate.setInt(5, contribuinte.getContaBancaria());
			sqlupdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Contribuinte pessoa) {
		try {
			sqlinsert.setString(1, pessoa.getNome());
			sqlinsert.setString(2, pessoa.getEndereco());
			sqlinsert.setString(3, pessoa.getCpf());
			sqlinsert.setInt(4, pessoa.getIdade());
			sqlinsert.setInt(5, pessoa.getContaBancaria());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contribuinte> selectCpf(String cpf) {
		ResultSet rs;
		ArrayList<Contribuinte> contribuintes = new ArrayList<Contribuinte>();
		Contribuinte pessoa;
		
		try {
			sqlselectcpf.setString(1, String.format("%%%s%%", cpf));
			rs = sqlselectcpf.executeQuery();
			while(rs.next()) {
				pessoa = new Contribuinte();
				pessoa.setId(rs.getInt("id"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setContaBancaria(rs.getInt("conta_bancaria"));
				contribuintes.add(pessoa);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return contribuintes;
	}
	
	public Contribuinte select(int id) {
		ResultSet rs;
		Contribuinte pessoa = null;
		
		try {
			sqlselect.setInt(1, id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				pessoa = new Contribuinte();
				pessoa.setId(rs.getInt("id"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setIdade(rs.getInt("idade"));
				pessoa.setContaBancaria(rs.getInt("conta_bancaria"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoa;
	}
	
	public ArrayList<Contribuinte> selectAll() {
		ResultSet rs;
		ArrayList<Contribuinte> pessoas  = new ArrayList<Contribuinte>();
		
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
