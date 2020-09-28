package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Emprestimo;

public class EmprestimoDAO {

	private static EmprestimoDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlalluser;
	private PreparedStatement sqlallliv;
	private PreparedStatement sqlselectiduser;
	private PreparedStatement sqlselectidliv;
	private PreparedStatement sqlallnotent;
	private PreparedStatement sqlallempuser;
	private PreparedStatement sqlallempliv;
	
	public static EmprestimoDAO getInstance() {
		
		if (instance == null) 
			instance = new EmprestimoDAO();
		
		return instance;
	}
	
	private EmprestimoDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into emprestimos (id_emp, id_ex, id_liv, id_user, id_bib, data_emp, renov) values (default, ? , ?, ? , ? , ?, 0)");
			sqlupdate = conn.prepareStatement("update emprestimos set data_ent = ?, renov = ? where id_emp = ?");
			sqldelete = conn.prepareStatement("delete from emprestimos where id_emp = ?");
			sqlselect = conn.prepareStatement("select * from emprestimos where id_emp = ?");
			sqlall = conn.prepareStatement("select id_emp from emprestimos");
			sqlallnotent = conn.prepareCall("select id_emp from emprestimos where data_ent is null");
			sqlalluser = conn.prepareStatement("select distinct id_user from emprestimos where data_ent is null");
			sqlallliv = conn.prepareStatement("select distinct id_liv from emprestimos where data_ent is null");
			sqlselectiduser = conn.prepareStatement("select id_emp from emprestimos where id_user = ? and data_ent is null");
			sqlselectidliv = conn.prepareStatement("select id_emp from emprestimos where id_liv = ? and data_ent is null");
			sqlallempuser = conn.prepareStatement("select id_emp from emprestimos where id_user = ?");
			sqlallempliv = conn.prepareStatement("select id_emp from emprestimos where id_liv = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Emprestimo emprestimo) {
		try {
			sqlinsert.setInt(1, emprestimo.getEx().getIdEx());
			sqlinsert.setInt(2, emprestimo.getEx().getIdLiv());
			sqlinsert.setInt(3, emprestimo.getUser().getIdUser());
			sqlinsert.setInt(4, emprestimo.getBib().getIdBib());
			java.util.Date data = emprestimo.getDiaEmprestimo();
			java.sql.Date sqlDate = new java.sql.Date(data.getTime());
			sqlinsert.setDate(5, sqlDate);
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Exemplar na coleção de reserva");
		}
	}
	
	public void update(Emprestimo emprestimo) {
		try {
			if(emprestimo.getDiaEntrega() == null) {
				sqlupdate.setDate(1, null);
				sqlupdate.setInt(2, emprestimo.getRenov());
				sqlupdate.setInt(3, emprestimo.getIdEmp());
				sqlupdate.executeUpdate();
			} else {
				java.util.Date data = emprestimo.getDiaEntrega();
				java.sql.Date sqlDate = new java.sql.Date(data.getTime());
				sqlupdate.setDate(1, sqlDate);
				sqlupdate.setInt(2, emprestimo.getRenov());
				sqlupdate.setInt(3, emprestimo.getIdEmp());
				sqlupdate.executeUpdate();
			}
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
	
	public Emprestimo select(int id) {
		ResultSet rs;
		Emprestimo emprestimo = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				emprestimo = new Emprestimo();
				emprestimo.setIdEmp(rs.getInt("id_emp"));
				emprestimo.setDiaEmprestimo((java.util.Date) rs.getObject("data_emp"));
				emprestimo.setBib(BibliotecarioDAO.getInstance().select(rs.getInt("id_bib")));
				emprestimo.setEx(ExemplarDAO.getInstance().select(rs.getInt("id_liv"), rs.getInt("id_ex")));
				emprestimo.setUser(UsuarioDAO.getInstance().select(rs.getInt("id_user")));
				emprestimo.setRenov(rs.getInt("renov"));
				if(rs.getObject("data_ent") == null) {
					emprestimo.setDiaEntrega(null);
				} else {
					emprestimo.setDiaEntrega((java.util.Date) rs.getObject("data_ent"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimo;
	}
	
	public ArrayList<Emprestimo> selectIdUser(int id) {
		ResultSet rs;
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			sqlselectiduser.setInt(1,id);
			rs = sqlselectiduser.executeQuery();
			while(rs.next()) {
				emprestimos.add(select(rs.getInt("id_emp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
	public ArrayList<Emprestimo> selectIdLiv(int id) {
		ResultSet rs;
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			sqlselectidliv.setInt(1,id);
			rs = sqlselectidliv.executeQuery();
			while(rs.next()) {
				emprestimos.add(select(rs.getInt("id_emp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
	public ArrayList<Emprestimo> all() {
		ResultSet rs;
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				emprestimos.add(select(rs.getInt("id_emp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
	public ArrayList<Emprestimo> allNotEnt() {
		ResultSet rs;
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			rs = sqlallnotent.executeQuery();
			while(rs.next()) {
				emprestimos.add(select(rs.getInt("id_emp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
	public ArrayList<Integer> selectIdUser() {
		ResultSet rs;
		ArrayList<Integer> emprestimos = new ArrayList<Integer>();
		
		try {
			rs = sqlalluser.executeQuery();
			while(rs.next()) {
				emprestimos.add(rs.getInt("id_user"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
	public ArrayList<Emprestimo> allEmpUser(int id) {
		ResultSet rs;
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			sqlallempuser.setInt(1, id);
			rs = sqlallempuser.executeQuery();
			while(rs.next()) {
				emprestimos.add(select(rs.getInt("id_emp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
	public ArrayList<Emprestimo> allEmpLiv(int id) {
		ResultSet rs;
		ArrayList<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
		
		try {
			sqlallempliv.setInt(1, id);
			rs = sqlallempliv.executeQuery();
			while(rs.next()) {
				emprestimos.add(select(rs.getInt("id_emp")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
	public ArrayList<Integer> selectidLiv() {
		ResultSet rs;
		ArrayList<Integer> emprestimos = new ArrayList<Integer>();
		
		try {
			rs = sqlallliv.executeQuery();
			while(rs.next()) {
				emprestimos.add(rs.getInt("id_liv"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;
	}
	
}
