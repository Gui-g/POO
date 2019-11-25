package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	static private Connection conexao = null;
	
	public static Connection getConexao() {
		if(conexao == null) {
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String username = "postgres";
			String password = "gui";
			
			try {
				Class.forName("org.postgresql.Driver");
				conexao = DriverManager.getConnection(url,username,password);
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conexao;
	}
	
}
