package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dados.Contracheque;

public class ContrachequeDAO {

	private static ContrachequeDAO instance = null;
	private PreparedStatement sqldelete;
	private PreparedStatement sqlinsert;
	private PreparedStatement sqlselect;
	private PreparedStatement sqlall;
	private PreparedStatement sqlselectid;
	private PreparedStatement sqlselectcnpj;
	private PreparedStatement sqlselectcpf;
	private PreparedStatement sqlupdate;
	private PreparedStatement sqldeletepj;
	private PreparedStatement sqlsum;
	private PreparedStatement sqlsumpj;
	private PreparedStatement sqlselectpj;
	private PreparedStatement sqlrestart;
	
	public static ContrachequeDAO getInstance() {
		if(instance == null) 
			instance = new ContrachequeDAO();
		
		return instance;
	}
	
	private ContrachequeDAO() {
		Connection conn = Conexao.getConexao();
		try {
			sqlinsert = conn.prepareStatement("insert into contracheque (num_protocolo, valor, id_contribuinte, id_pj) values (?, ?, ?, ?)");
			sqldelete = conn.prepareStatement("delete from contracheque where id = ?");
			sqlselect = conn.prepareStatement("select * from contracheque where id = ?");
			sqlselectcnpj = conn.prepareStatement("select id_pj from nota_fiscal where id = ?");
			sqlselectcpf = conn.prepareStatement("select id_contribuinte from nota_fiscal where id = ?");
			sqlselectid = conn.prepareStatement("select * from contracheque where id_contribuinte = ?");
			sqlall = conn.prepareStatement("select id from contracheque");
			sqlupdate = conn.prepareStatement("update contracheque set num_protocolo = ?, valor = ? where id = ?");
			sqldeletepj = conn.prepareStatement("delete from contracheque where id_pj = ?");
			sqlsum = conn.prepareStatement("select sum(valor) from contracheque where id_contribuinte = ?");
			sqlsumpj = conn.prepareStatement("select sum(valor) from contracheque where id_pj = ?");
			sqlselectpj = conn.prepareStatement("select * from contracheque where id_pj = ?");
			sqlrestart = conn.prepareStatement("alter sequence contracheque_id_seq restart");
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
	
	public void deletePj(int id) {
		
		try {
			sqldeletepj.setInt(1, id);
			sqldeletepj.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public void insert(Contracheque cheque, int id1, int id2) {
		try {
			sqlinsert.setInt(1, cheque.getNumProtocolo());
			sqlinsert.setFloat(2, cheque.getValor());
			sqlinsert.setInt(3, id1);
			sqlinsert.setInt(4, id2);
			sqlinsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Contracheque cheque) {
		try {
			sqlupdate.setInt(3, cheque.getId());
			sqlupdate.setInt(1, cheque.getNumProtocolo());
			sqlupdate.setFloat(2, cheque.getValor());
			sqlupdate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Contracheque select(int id) {
		ResultSet rs;
		Contracheque cheque = null;
		
		try {
			sqlselect.setInt(1, id);
			rs = sqlselect.executeQuery();
			if(rs.next()) {
				cheque = new Contracheque();
				cheque.setId(rs.getInt("id"));
				cheque.setNumProtocolo(rs.getInt("num_protocolo"));
				cheque.setCnpj(rs.getString("cnpj"));
				cheque.setValor(rs.getFloat("valor"));
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cheque;
	}
	
	public ArrayList<Contracheque> selectId(int id) {
		ResultSet rs;
		ArrayList<Contracheque> cheques = new ArrayList<Contracheque>();
		Contracheque doc;
		
		try {
			sqlselectid.setInt(1, id);
			rs = sqlselectid.executeQuery();
			while(rs.next()) {
				doc = new Contracheque();
				doc.setId(rs.getInt("id"));
				doc.setValor(rs.getFloat("valor"));
				doc.setNumProtocolo(rs.getInt("num_protocolo"));
				cheques.add(doc);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cheques;
	}
	
	public ArrayList<Contracheque> selectPj(int id) {
		ResultSet rs;
		ArrayList<Contracheque> cheques = new ArrayList<Contracheque>();
		Contracheque doc;
		
		try {
			sqlselectpj.setInt(1, id);
			rs = sqlselectpj.executeQuery();
			while(rs.next()) {
				doc = new Contracheque();
				doc.setId(rs.getInt("id"));
				doc.setValor(rs.getFloat("valor"));
				doc.setNumProtocolo(rs.getInt("num_protocolo"));
				cheques.add(doc);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cheques;
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
	
	public ArrayList<Contracheque> selectAll() {
		ResultSet rs;
		ArrayList<Contracheque> cheques = new ArrayList<Contracheque>();
		
		try {
			rs = sqlall.executeQuery();
			while(rs.next()) {
				cheques.add(select(rs.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cheques;
	}
}
