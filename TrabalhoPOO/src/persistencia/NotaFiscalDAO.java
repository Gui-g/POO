package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.NotaFiscal;

public class NotaFiscalDAO {

	private static NotaFiscalDAO instance = null;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	
	public static NotaFiscalDAO getInstance() {
		if(instance == null) 
			instance = new NotaFiscalDAO();
		
		return instance;
	}
	
	private NotaFiscalDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into nota_fiscal (num_protocolo, valor, id_contribuinte, id_pj) values (?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from nota_fiscal where id = ?");
			sqlselect = conn.prepareStatement("select * from nota_fiscal where id = ?");
			sqlall = conn.prepareStatement("select id from nota_fiscal");
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
	
	public void insert(NotaFiscal nota, int id1, int id2) {
		try {
			sqlinsert.setInt(1, nota.getNumProtocolo());
			sqlinsert.setFloat(2, nota.getValor());
			sqlinsert.setInt(3, id1);
			sqlinsert.setInt(4, id2);
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public NotaFiscal select(int id) {
		ResultSet rs;
		NotaFiscal nota = null;
		
		try {
			sqlselect.setInt(1, id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				nota = new NotaFiscal();
				nota.setId(rs.getInt("id"));
				nota.setNumProtocolo(rs.getInt("num_protocolo"));
				nota.setCnpj(rs.getString("cnpj"));
				nota.setValor(rs.getFloat("valor"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nota;
	}
	
	public ArrayList<NotaFiscal> getNotas() {
		ResultSet rs;
		ArrayList<NotaFiscal> notas = new ArrayList<NotaFiscal>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				notas.add(select(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notas;
	}
}
