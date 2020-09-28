package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Livro;

public class LivroDAO {

	private static LivroDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectisbn;
	
	public static LivroDAO getInstance() {
		
		if (instance == null) 
			instance = new LivroDAO();
		
		return instance;
	}
	
	private LivroDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into livros (id_liv, titulo, autor, isbn) values (default, ?, ?, ?)");
			sqlupdate = conn.prepareStatement("update livros set titulo = ?, autor = ?, isbn = ? where id_liv = ?");
			sqldelete = conn.prepareStatement("delete from livros where id_liv = ?");
			sqlselect = conn.prepareStatement("select * from livros where id_liv = ?");
			sqlall = conn.prepareStatement("select id_liv from livros");
			sqlselectisbn = conn.prepareStatement("select * from livros where isbn = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Livro livro) {
		try {
			sqlinsert.setString(1, livro.getTitulo());
			sqlinsert.setString(2, livro.getAutor());
			sqlinsert.setString(3, livro.getIsbn());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Livro livro) {
		try {
			sqlupdate.setString(1, livro.getTitulo());
			sqlupdate.setString(2, livro.getAutor());
			sqlupdate.setString(3,  livro.getIsbn());
			sqlupdate.setInt(4, livro.getIdLiv());
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
	
	public Livro select(int id) {
		ResultSet rs;
		Livro livro = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				livro = new Livro();
				livro.setIdLiv(rs.getInt("id_liv"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setIsbn(rs.getString("isbn"));
				livro.setEditoras(PublicadoDAO.getInstance().selectEdi(rs.getInt("id_liv")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livro;
	}
	
	public Livro selectIsbn(String isbn) {
		ResultSet rs;
		Livro livro = null;
		
		try {
			sqlselectisbn.setString(1,isbn);
			rs = sqlselectisbn.executeQuery();
			if(rs.next()) {
				livro = new Livro();
				livro.setIdLiv(rs.getInt("id_liv"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setIsbn(rs.getString("isbn"));
				livro.setEditoras(PublicadoDAO.getInstance().selectEdi(rs.getInt("id_liv")));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livro;
	}
	
	public ArrayList<Livro> all() {
		ResultSet rs;
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				livros.add(select(rs.getInt("id_liv")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return livros;
	}
	
}
