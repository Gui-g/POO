package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Exemplar;

public class ExemplarDAO {

	private static ExemplarDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static ExemplarDAO getInstance() {
		
		if (instance == null) 
			instance = new ExemplarDAO();
		
		return instance;
	}
	
	private ExemplarDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into exemplares (id_ex, id_liv, id_st, id_col) values (default, ? , ?, ?)");
			sqlupdate = conn.prepareStatement("update exemplares set id_st = ?, id_col = ? where id_ex = ?");
			sqldelete = conn.prepareStatement("delete from exemplares where id_ex = ? and id_liv = ?");
			sqlselect = conn.prepareStatement("select * from exemplares where id_ex = ? and id_liv = ?");
			sqlall = conn.prepareStatement("select id_ex, id_liv from exemplares");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Exemplar exemplar) {
		try {
			sqlinsert.setInt(1, exemplar.getIdLiv());
			sqlinsert.setInt(2, exemplar.getStat().getIdSt());
			sqlinsert.setInt(3, exemplar.getCol().getIdCol());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Exemplar exemplar) {
		try {
			sqlupdate.setInt(1, exemplar.getStat().getIdSt());
			sqlupdate.setInt(2, exemplar.getCol().getIdCol());
			sqlupdate.setInt(3, exemplar.getIdEx());
			sqlupdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id_liv, int id_ex) {
		try {
			sqldelete.setInt(1, id_ex);
			sqldelete.setInt(2, id_liv);
			sqldelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Exemplar select(int id_liv, int id_ex) {
		ResultSet rs;
		Exemplar exemplar = null;
		
		try {
			sqlselect.setInt(1, id_ex);
			sqlselect.setInt(2, id_liv);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				exemplar = new Exemplar();
				exemplar.setIdEx(rs.getInt("id_ex"));
				exemplar.setStat(StatusDAO.getInstance().select(rs.getInt("id_st")));
				exemplar.setCol(ColecaoDAO.getInstance().select(rs.getInt("id_col")));
				
				exemplar.setIdLiv(rs.getInt("id_liv"));
				exemplar.setTitulo(LivroDAO.getInstance().select(rs.getInt("id_liv")).getTitulo());
				exemplar.setAutor(LivroDAO.getInstance().select(rs.getInt("id_liv")).getAutor());
				exemplar.setIsbn(LivroDAO.getInstance().select(rs.getInt("id_liv")).getIsbn());
				exemplar.setEditoras(PublicadoDAO.getInstance().selectEdi(rs.getInt("id_liv")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exemplar;
	}
	
	public ArrayList<Exemplar> all() {
		ResultSet rs;
		ArrayList<Exemplar> exemplares = new ArrayList<Exemplar>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				exemplares.add(select(rs.getInt("id_liv"), rs.getInt("id_ex")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exemplares;
	}
	
}
