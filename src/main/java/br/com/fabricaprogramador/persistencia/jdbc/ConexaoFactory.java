package br.com.fabricaprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnectionn() {
		
		
			try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/fabricaweb", "jonas","Jon-1136");
			} catch (SQLException e) {
				
				throw new RuntimeException(e);
			}	
		

	}
}
