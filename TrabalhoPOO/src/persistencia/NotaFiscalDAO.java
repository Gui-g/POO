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
	private PreparedStatement sqlselectid;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqlselectcnpj;
	private PreparedStatement sqlselectcpf;
	private PreparedStatement sqldeletepj;
	private PreparedStatement sqlsum;
	private PreparedStatement sqlsumpj;
	private PreparedStatement sqlselectpj;
	private PreparedStatement sqlrestart;
	
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
			sqlselectid = conn.prepareStatement("select * from nota_fiscal where id_contribuinte = ?");
			sqlselectcnpj = conn.prepareStatement("select id_pj from nota_fiscal where id = ?");
			sqlselectpj = conn.prepareStatement("select * from nota_fiscal where id_pj = ?");
			sqlselectcpf = conn.prepareStatement("select id_contribuinte from nota_fiscal where id = ?");
			sqlall = conn.prepareStatement("select id from nota_fiscal");
			sqlupdate = conn.prepareStatement("update nota_fiscal set num_protocolo = ?, valor = ? where id = ?");
			sqldeletepj = conn.prepareStatement("delete from nota_fiscal where id_pj = ?");
			sqlsum = conn.prepareStatement("select sum(valor) from nota_fiscal where id_contribuinte = ?");
			sqlsumpj = conn.prepareStatement("select sum(valor) from nota_fiscal where id_pj = ?");
			sqlrestart = conn.prepareStatement("alter sequence nota_fiscal_id_seq restart");
			
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
	
	public void restart() {
		try {
			sqlrestart.executeQuery();
		} catch (SQLException e) {
		}
	}
	
	public float soma(int ID) throws Exception {
		ResultSet rs;
		Float sum = 0.0F;
		try {
			sqlsum.setInt(1, ID);
			rs = sqlsum.executeQuery();
			if(rs.next() ) {
				String resultado = rs.getString(1);
				if(resultado.equals(""))
					throw new Exception();			
				sum = Float.parseFloat(resultado);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	
	public float somaPj(int ID) throws Exception {
		ResultSet rs;
		Float sum = 0.0F;
		try {
			sqlsumpj.setInt(1, ID);
			rs = sqlsumpj.executeQuery();
			if(rs.next() ) {
				String resultado = rs.getString(1);
				if(resultado.equals(""))
					throw new Exception();			
				sum = Float.parseFloat(resultado);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	
	public void deletePj(int id) {
		
		try {
			sqldeletepj.setInt(1, id);
			sqldeletepj.executeUpdate();
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
	
	public void update(NotaFiscal nota) {
		try {
			sqlupdate.setInt(3, nota.getId());
			sqlupdate.setInt(1, nota.getNumProtocolo());
			sqlupdate.setFloat(2, nota.getValor());
			sqlupdate.executeUpdate();
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
	
	public ArrayList<NotaFiscal> selectId(int id) {
		ResultSet rs;
		ArrayList<NotaFiscal> notas = new ArrayList<NotaFiscal>();
		NotaFiscal doc;
		
		try {
			sqlselectid.setInt(1, id);
			rs = sqlselectid.executeQuery();
			while(rs.next()) {
				doc = new NotaFiscal();
				doc.setId(rs.getInt("id"));
				doc.setValor(rs.getFloat("valor"));
				doc.setNumProtocolo(rs.getInt("num_protocolo"));
				notas.add(doc);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notas;
	}
	
	public ArrayList<NotaFiscal> selectPj(int id) {
		ResultSet rs;
		ArrayList<NotaFiscal> notas = new ArrayList<NotaFiscal>();
		NotaFiscal doc;
		
		try {
			sqlselectpj.setInt(1, id);
			rs = sqlselectpj.executeQuery();
			while(rs.next()) {
				doc = new NotaFiscal();
				doc.setId(rs.getInt("id"));
				doc.setValor(rs.getFloat("valor"));
				doc.setNumProtocolo(rs.getInt("num_protocolo"));
				notas.add(doc);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notas;
	}
	
	public int selectIdCpf(int id) {
		ResultSet rs;
		int res = -1;
		
		try {
			sqlselectcpf.setInt(1, id);
			rs = sqlselectcpf.executeQuery();
			if(rs.next()) {
				res = rs.getInt("id_contribuinte");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int selectIdCnpj(int id) {
		ResultSet rs;
		int res = -1;
		
		try {
			sqlselectcnpj.setInt(1, id);
			rs = sqlselectcnpj.executeQuery();
			if(rs.next()) {
				res = rs.getInt("id_pj");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public ArrayList<NotaFiscal> selectAll() {
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
