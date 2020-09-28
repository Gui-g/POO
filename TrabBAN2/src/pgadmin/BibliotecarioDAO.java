package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Bibliotecario;

public class BibliotecarioDAO {

	private static BibliotecarioDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectfunc;
	
	public static BibliotecarioDAO getInstance() {
		
		if (instance == null) 
			instance = new BibliotecarioDAO();
		
		return instance;
	}
	
	private BibliotecarioDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into bibliotecarios (id_bib, id_func) values (default, ?)");
			sqldelete = conn.prepareStatement("delete from bibliotecarios where id_bib = ?");
			sqlselect = conn.prepareStatement("select * from bibliotecarios where id_bib = ?");
			sqlall = conn.prepareStatement("select id_bib from bibliotecarios");
			sqlselectfunc = conn.prepareStatement("select * from bibliotecarios where id_func = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Bibliotecario bibliotecario) {
		try {
			sqlinsert.setInt(1, bibliotecario.getIdFunc());
			sqlinsert.executeUpdate();
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
	
	public Bibliotecario select(int id) {
		ResultSet rs;
		Bibliotecario bibliotecario = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				bibliotecario = new Bibliotecario();
				bibliotecario.setIdBib(rs.getInt("id_bib"));
				
				bibliotecario.setIdFunc(rs.getInt("id_func"));
				bibliotecario.setSalario(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getSalario());
				bibliotecario.setCtps(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getCtps());
				bibliotecario.setTurno(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getTurno());
				
				bibliotecario.setIdPes(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getIdPes());
				bibliotecario.setNome(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getNome());
				bibliotecario.setCpf(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getCpf());
				bibliotecario.setDtNasc(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getDtNasc());
				bibliotecario.setEndereco(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getEndereco());
				bibliotecario.setSexo(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getSexo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bibliotecario;
	}
	
	public Bibliotecario selectIdFunc(int id) {
		ResultSet rs;
		Bibliotecario bibliotecario = null;
		
		try {
			sqlselectfunc.setInt(1,id);
			rs = sqlselectfunc.executeQuery();
			if(rs.next()) {
				bibliotecario = new Bibliotecario();
				bibliotecario.setIdBib(rs.getInt("id_bib"));
				
				bibliotecario.setIdFunc(rs.getInt("id_func"));
				bibliotecario.setSalario(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getSalario());
				bibliotecario.setCtps(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getCtps());
				bibliotecario.setTurno(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getTurno());
				
				bibliotecario.setIdPes(rs.getInt("id_pes"));
				bibliotecario.setNome(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getNome());
				bibliotecario.setCpf(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getCpf());
				bibliotecario.setDtNasc(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getDtNasc());
				bibliotecario.setEndereco(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getEndereco());
				bibliotecario.setSexo(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getSexo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bibliotecario;
	}
	
	public ArrayList<Bibliotecario> all() {
		ResultSet rs;
		ArrayList<Bibliotecario> bibliotecarios = new ArrayList<Bibliotecario>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				bibliotecarios.add(select(rs.getInt("id_bib")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bibliotecarios;
	}
	
}
