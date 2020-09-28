package pgadmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Editora;
import dados.Livro;


public class PublicadoDAO {

	private static PublicadoDAO instance = null;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlselectedi;
	
	public static PublicadoDAO getInstance() {
		
		if (instance == null) 
			instance = new PublicadoDAO();
		
		return instance;
	}
	
	private PublicadoDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into publicados (id_liv, id_edit) values (? , ?)");
			sqldelete = conn.prepareStatement("delete from publicados where id_liv = ? and id_edit = ?");
			sqlselectedi = conn.prepareStatement("select id_edit from publicados where id_liv = ?");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(Livro publicado) {
		try {
			for(Editora edi : publicado.getEditoras()) {
				sqlinsert.setInt(1, publicado.getIdLiv());
				sqlinsert.setInt(2, edi.getIdEdi());
				sqlinsert.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(int id_liv, int id_edi) {
		try {
			sqldelete.setInt(1, id_liv);
			sqldelete.setInt(2, id_edi);
			sqldelete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public ArrayList<Editora> selectEdi(int id_liv) {
		ResultSet rs;
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		
		try {
			sqlselectedi.setInt(1, id_liv);
			rs = sqlselectedi.executeQuery();
			while(rs.next()) {
				editoras.add(EditoraDAO.getInstance().select(rs.getInt("id_edit")));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return editoras;
	}
}
