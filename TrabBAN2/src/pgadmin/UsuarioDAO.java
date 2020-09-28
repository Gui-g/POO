package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Usuario;

public class UsuarioDAO {

	private static UsuarioDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectpes;
	private PreparedStatement sqlselectcat;
	
	public static UsuarioDAO getInstance() {
		
		if (instance == null) 
			instance = new UsuarioDAO();
		
		return instance;
	}
	
	private UsuarioDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into usuarios (id_user, id_pes, id_uni, id_cat) values (default, ? , ?, ?)");
			sqlupdate = conn.prepareStatement("update usuarios set id_uni = ?, id_cat = ? where id_user = ?");
			sqldelete = conn.prepareStatement("delete from usuarios where id_user = ?");
			sqlselect = conn.prepareStatement("select * from usuarios where id_user = ?");
			sqlall = conn.prepareStatement("select id_user from usuarios");
			sqlselectpes = conn.prepareStatement("select * from usuarios where id_pes = ?");
			sqlselectcat = conn.prepareStatement("select id_user from usuarios where id_cat = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Usuario usuario) {
		try {
			sqlinsert.setInt(1, usuario.getIdPes());
			sqlinsert.setInt(2, usuario.getUni().getIdUni());
			sqlinsert.setInt(3, usuario.getCat().getIdCat());
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Usuario usuario) {
		try {
			sqlupdate.setInt(1, usuario.getUni().getIdUni());
			sqlupdate.setInt(2,  usuario.getCat().getIdCat());
			sqlupdate.setInt(3, usuario.getIdUser());
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
	
	public Usuario select(int id) {
		ResultSet rs;
		Usuario usuario = null;
		
		try {
			sqlselect.setInt(1,id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUser(rs.getInt("id_user"));
				usuario.setUni(UniversidadeDAO.getInstance().select(rs.getInt("id_uni")));
				usuario.setCat(CategoriaDAO.getInstance().select(rs.getInt("id_cat")));

				usuario.setIdPes(rs.getInt("id_pes"));
				usuario.setCpf(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getCpf());
				usuario.setNome(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getNome());
				usuario.setDtNasc(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getDtNasc());
				usuario.setSexo(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getSexo());
				usuario.setEndereco(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getEndereco());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public Usuario selectPes(int id) {
		ResultSet rs;
		Usuario usuario = null;
		
		try {
			sqlselectpes.setInt(1,id);
			rs = sqlselectpes.executeQuery();
			if(rs.next()) {
				usuario = new Usuario();
				usuario.setIdUser(rs.getInt("id_user"));
				usuario.setUni(UniversidadeDAO.getInstance().select(rs.getInt("id_uni")));
				usuario.setCat(CategoriaDAO.getInstance().select(rs.getInt("id_cat")));

				usuario.setIdPes(rs.getInt("id_pes"));
				usuario.setCpf(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getCpf());
				usuario.setNome(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getNome());
				usuario.setDtNasc(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getDtNasc());
				usuario.setSexo(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getSexo());
				usuario.setEndereco(PessoaDAO.getInstance().select(rs.getInt("id_pes")).getEndereco());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
	
	public ArrayList<Usuario> all() {
		ResultSet rs;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				usuarios.add(select(rs.getInt("id_user")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
	public ArrayList<Usuario> allCat(int id) {
		ResultSet rs;
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		
		try {
			sqlselectcat.setInt(1, id);
			rs = sqlselectcat.executeQuery();
			while(rs.next()) {
				usuarios.add(select(rs.getInt("id_user")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuarios;
	}
	
}
