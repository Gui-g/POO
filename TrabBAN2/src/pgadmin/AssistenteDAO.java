package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Assistente;

public class AssistenteDAO {

	private static AssistenteDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectfunc;
	
	public static AssistenteDAO getInstance() {
		
		if (instance == null) 
			instance = new AssistenteDAO();
		
		return instance;
	}
	
	private AssistenteDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into assistentes (id_assist, id_func, supervisor) values (default, ?, ?)");
			sqlupdate = conn.prepareStatement("update assistentes set supervisor = ? where id_assist = ?");
			sqldelete = conn.prepareStatement("delete from assistentes where id_assist = ?");
			sqlselect = conn.prepareStatement("select * from assistentes where id_assist = ?");
			sqlall = conn.prepareStatement("select id_assist from assistentes");
			sqlselectfunc = conn.prepareStatement("select * from assistentes where id_func = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Assistente assistente) {
		try {
			sqlinsert.setDouble(1, assistente.getIdFunc());
			sqlinsert.setInt(2, assistente.getSupervisor().getIdBib());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Assistente assistente) {
		try {
			sqlupdate.setInt(1, assistente.getSupervisor().getIdBib());
			sqlupdate.setInt(2, assistente.getIdAssist());
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
	
	public Assistente select(int id) {
		ResultSet rs;
		Assistente assistente = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				assistente = new Assistente();
				assistente.setIdAssist(rs.getInt("id_assist"));
				
				assistente.setIdFunc(rs.getInt("id_func"));
				assistente.setSalario(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getSalario());
				assistente.setCtps(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getCtps());
				assistente.setTurno(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getTurno());
				
				assistente.setIdPes(rs.getInt("id_pes"));
				assistente.setNome(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getNome());
				assistente.setCpf(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getCpf());
				assistente.setDtNasc(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getDtNasc());
				assistente.setEndereco(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getEndereco());
				assistente.setSexo(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getSexo());	
				
				assistente.setSupervisor(BibliotecarioDAO.getInstance().select(rs.getInt("supervisor")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return assistente;
	}
	
	public Assistente selectIdFunc(int id) {
		ResultSet rs;
		Assistente assistente = null;
		
		try {
			sqlselectfunc.setInt(1,id);
			rs = sqlselectfunc.executeQuery();
			if(rs.next()) {
				assistente = new Assistente();
				assistente.setIdAssist(rs.getInt("id_assist"));
				
				assistente.setIdFunc(rs.getInt("id_func"));
				assistente.setSalario(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getSalario());
				assistente.setCtps(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getCtps());
				assistente.setTurno(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getTurno());
				
				assistente.setIdPes(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getIdPes());
				assistente.setNome(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getNome());
				assistente.setCpf(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getCpf());
				assistente.setDtNasc(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getDtNasc());
				assistente.setEndereco(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getEndereco());
				assistente.setSexo(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getSexo());	
				
				assistente.setSupervisor(BibliotecarioDAO.getInstance().select(rs.getInt("supervisor")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return assistente;
	}
	
	public ArrayList<Assistente> all() {
		ResultSet rs;
		ArrayList<Assistente> assistentes = new ArrayList<Assistente>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				assistentes.add(select(rs.getInt("id_assist")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return assistentes;
	}
	
}
