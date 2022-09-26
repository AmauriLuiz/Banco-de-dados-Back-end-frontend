
package br.com.crud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static Connection createConnectionSQLServer() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Faz com que a classe seja carregada pela JVM
		
		//Cria conex�o com o banco de dados -------- SEM Senha
		Connection connection = 
				DriverManager.getConnection("jdbc:sqlserver://DESKTOP-JK73023\\SQLEXPRESS;DatabaseName=AgenciaViagensMod4;integratedSecurity=true;");
		
		
		
		//Cria conex�o com o banco de dados -------- COM Senha
//		
//		Connection connection = 
//				DriverManager.getConnection("jdbc:sqlserver: 'servidor' ; 'usuario', 'senha'");
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception{
		//Recupera uma conex�o com o banco de dados
		
		Connection con = createConnectionSQLServer();
		
		// testar se a conex�o � nula
		
		if (con != null) {
			System.out.println("Conex�o obtida com sucesso! "+ con);
			con.close();
		}
		
	}


}