package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Pessoa;

public class PessoaDAO {

	private static PessoaDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectcpf;
	private PreparedStatement sqlselectonly;
	private PreparedStatement sqlselectnotuser;
	private PreparedStatement sqlselectnotfunc;
	
	public static PessoaDAO getInstance() {
		
		if (instance == null) 
			instance = new PessoaDAO();
		
		return instance;
	}
	
	private PessoaDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into pessoas (id_pes, cpf, nome, dt_nasc, sexo, endereco) values (default, ? , ?, ? , ? , ?)");
			sqlupdate = conn.prepareStatement("update pessoas set nome = ?, sexo = ?, endereco = ? where id_pes = ?");
			sqldelete = conn.prepareStatement("delete from pessoas where id_pes = ?");
			sqlselect = conn.prepareStatement("select * from pessoas where id_pes = ?");
			sqlall = conn.prepareStatement("select id_pes from pessoas");
			sqlselectcpf = conn.prepareStatement("select * from pessoas where cpf = ?");
			sqlselectonly = conn.prepareStatement("select id_pes from pessoas where id_pes not in (select id_pes from usuarios) and id_pes not in (select id_pes from funcionarios)");
			sqlselectnotuser = conn.prepareStatement("select id_pes from pessoas where id_pes not in (select id_pes from usuarios)");
			sqlselectnotfunc = conn.prepareStatement("select id_pes from pessoas where id_pes not in (select id_pes from funcionarios)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Pessoa pessoa) {
		try {
			sqlinsert.setString(1, pessoa.getCpf());
			sqlinsert.setString(2, pessoa.getNome());
			java.util.Date data = pessoa.getDtNasc();
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			sqlinsert.setDate(3, sqlDate);
			sqlinsert.setString(4, String.valueOf(pessoa.getSexo()));
			sqlinsert.setString(5, pessoa.getEndereco());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Pessoa pessoa) {
		try {
			sqlupdate.setString(1, pessoa.getNome());
			sqlupdate.setString(2, String.valueOf(pessoa.getSexo()));
			sqlupdate.setString(3,  pessoa.getEndereco());
			sqlupdate.setInt(4, pessoa.getIdPes());
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
	
	public Pessoa select(int id) {
		ResultSet rs;
		Pessoa pessoa = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				pessoa = new Pessoa();
				pessoa.setIdPes(rs.getInt("id_pes"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setDtNasc((java.util.Date) rs.getObject("dt_nasc"));
				pessoa.setSexo(rs.getString("sexo").toCharArray()[0]);
				pessoa.setEndereco(rs.getString("endereco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoa;
	}
	
	public ArrayList<Pessoa> all() {
		ResultSet rs;
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				pessoas.add(select(rs.getInt("id_pes")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
	public Pessoa selectcpf(String cpf) {
		ResultSet rs;
		Pessoa pessoa = null;
		
		try {
			sqlselectcpf.setString(1,cpf);
			rs = sqlselectcpf.executeQuery();
			if(rs.next()) {
				pessoa = new Pessoa();
				pessoa.setIdPes(rs.getInt("id_pes"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setDtNasc((java.util.Date) rs.getObject("dt_nasc"));
				pessoa.setSexo(rs.getString("sexo").toCharArray()[0]);
				pessoa.setEndereco(rs.getString("endereco"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoa;
	}
	
	public ArrayList<Pessoa> selectOnly() {
		ResultSet rs;
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			rs = sqlselectonly.executeQuery();
			while(rs.next()) {
				pessoas.add(select(rs.getInt("id_pes")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
	public ArrayList<Pessoa> selectNotUser() {
		ResultSet rs;
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			rs = sqlselectnotuser.executeQuery();
			while(rs.next()) {
				pessoas.add(select(rs.getInt("id_pes")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
	public ArrayList<Pessoa> selectNotFunc() {
		ResultSet rs;
		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		try {
			rs = sqlselectnotfunc.executeQuery();
			while(rs.next()) {
				pessoas.add(select(rs.getInt("id_pes")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pessoas;
	}
	
}
