package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Funcionario;

public class FuncionarioDAO {

	private static FuncionarioDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectidpes;
	private PreparedStatement sqlselectnotassigned;
	
	public static FuncionarioDAO getInstance() {
		
		if (instance == null) 
			instance = new FuncionarioDAO();
		
		return instance;
	}
	
	private FuncionarioDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into funcionarios (id_func, salario, ctps, turno, id_pes) values (default, ? , ?, ?, ?)");
			sqlupdate = conn.prepareStatement("update funcionarios set salario = ?, ctps = ?, turno = ? where id_func = ?");
			sqldelete = conn.prepareStatement("delete from funcionarios where id_func = ?");
			sqlselect = conn.prepareStatement("select * from funcionarios where id_func = ?");
			sqlall = conn.prepareStatement("select id_func from funcionarios");
			sqlselectidpes = conn.prepareStatement("select * from funcionarios where id_pes = ?");
			sqlselectnotassigned = conn.prepareStatement("select id_func from funcionarios where id_func not in (select id_func from bibliotecarios) and id_func not in (select id_func from assistentes)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Funcionario funcionario) {
		try {
			sqlinsert.setDouble(1, funcionario.getSalario());
			sqlinsert.setString(2, funcionario.getCtps());
			sqlinsert.setString(3, String.valueOf(funcionario.getTurno()));
			sqlinsert.setInt(4, funcionario.getIdPes());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Funcionario funcionario) {
		try {
			sqlupdate.setDouble(1, funcionario.getSalario());
			sqlupdate.setString(2,  funcionario.getCtps());
			sqlupdate.setString(3, String.valueOf(funcionario.getTurno()));
			sqlupdate.setInt(4, funcionario.getIdFunc());
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
	
	public Funcionario select(int id) {
		ResultSet rs;
		Funcionario funcionario = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				funcionario = new Funcionario();
				funcionario.setIdFunc(rs.getInt("id_func"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setCtps(rs.getString("ctps"));
				funcionario.setTurno(rs.getString("turno").toCharArray()[0]);
				
				funcionario.setIdPes(rs.getInt("id_pes"));
				funcionario.setNome(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getNome());
				funcionario.setCpf(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getCpf());
				funcionario.setDtNasc(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getDtNasc());
				funcionario.setEndereco(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getEndereco());
				funcionario.setSexo(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getSexo());				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return funcionario;
	}
	
	public ArrayList<Funcionario> all() {
		ResultSet rs;
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				funcionarios.add(select(rs.getInt("id_func")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return funcionarios;
	}
	
	public Funcionario selectIdPes(int id) {
		ResultSet rs;
		Funcionario funcionario = null;
		
		try {
			sqlselectidpes.setInt(1,id);
			rs = sqlselectidpes.executeQuery();
			if(rs.next()) {
				funcionario = new Funcionario();
				funcionario.setIdFunc(rs.getInt("id_func"));
				funcionario.setSalario(rs.getDouble("salario"));
				funcionario.setCtps(rs.getString("ctps"));
				funcionario.setTurno(rs.getString("turno").toCharArray()[0]);
				
				funcionario.setIdPes(rs.getInt("id_pes"));
				funcionario.setNome(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getNome());
				funcionario.setCpf(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getCpf());
				funcionario.setDtNasc(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getDtNasc());
				funcionario.setEndereco(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getEndereco());
				funcionario.setSexo(FuncionarioDAO.getInstance().select(rs.getInt("id_func")).getSexo());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return funcionario;
	}
	
	public ArrayList<Funcionario> selectFuncNotAssign() {
		ResultSet rs;
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			rs = sqlselectnotassigned.executeQuery();
			while(rs.next()) {
				funcionarios.add(select(rs.getInt("id_func")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return funcionarios;
	}
}
