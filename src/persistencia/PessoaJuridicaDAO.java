package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.PessoaJuridica;

public class PessoaJuridicaDAO {

	private static PessoaJuridicaDAO instance = null;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqlselectcnpj;
	private PreparedStatement sqlrestart;
	
	public static PessoaJuridicaDAO getInstance() {
		if(instance == null) 
			instance = new PessoaJuridicaDAO();
		
		return instance;
	}
	
	private PessoaJuridicaDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into pessoa_juridica (cnpj, nomePJ, endereco, num_funcionarios) values (?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from pessoa_juridica where id = ?");
			sqlselect = conn.prepareStatement("select * from pessoa_juridica where id = ?");
			sqlall = conn.prepareStatement("select * from pessoa_juridica");
			sqlselectcnpj = conn.prepareStatement("select * from pessoa_juridica where cnpj like ?");
			sqlupdate = conn.prepareStatement("update pessoa_juridica set cnpj = ?, nomepj = ?, endereco = ?, num_funcionarios = ? where id = ?");
			sqlrestart = conn.prepareStatement("alter sequence pessoa_juridica_id_seq restart");
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
	
	public void update(PessoaJuridica pj) {
		try {
			sqlupdate.setInt(5, pj.getId());
			sqlupdate.setString(2, pj.getNomePJ());
			sqlupdate.setString(3, pj.getEndereco());
			sqlupdate.setString(1, pj.getCnpj());
			sqlupdate.setInt(4, pj.getNumFuncionarios());
			sqlupdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(PessoaJuridica pj) {
		try {
			sqlinsert.setString(1, pj.getCnpj());
			sqlinsert.setString(2, pj.getNomePJ());
			sqlinsert.setString(3, pj.getEndereco());
			sqlinsert.setInt(4, pj.getNumFuncionarios());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public PessoaJuridica select(int id) {
		ResultSet rs;
		PessoaJuridica pj = null;
		
		try {
			sqlselect.setInt(1, id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				pj = new PessoaJuridica();
				pj.setId(rs.getInt("id"));
				pj.setCnpj(rs.getString("cnpj"));
				pj.setNomePJ(rs.getString("nomePJ"));
				pj.setEndereco(rs.getString("endereco"));
				pj.setNumFuncionarios(rs.getInt("num_funcionarios"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pj;
	}
	
	public ArrayList<PessoaJuridica> selectCnpj(String cnpj) {
		ResultSet rs;
		ArrayList<PessoaJuridica> pjs = new ArrayList<PessoaJuridica>();
		PessoaJuridica pessoa;
		
		try {
			sqlselectcnpj.setString(1, String.format("%%%s%%", cnpj));
			rs = sqlselectcnpj.executeQuery();
			while(rs.next()) {
				pessoa = new PessoaJuridica();
				pessoa.setId(rs.getInt("id"));
				pessoa.setCnpj(rs.getString("cnpj"));
				pessoa.setNomePJ(rs.getString("nomepj"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setNumFuncionarios(rs.getInt("num_funcionarios"));
				pjs.add(pessoa);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pjs;
	}
	
	public ArrayList<PessoaJuridica> selectAll() {
		ResultSet rs;
		ArrayList<PessoaJuridica> pjs  = new ArrayList<PessoaJuridica>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				pjs.add(select(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pjs;
	}
}
