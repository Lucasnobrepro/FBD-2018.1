package JDBC;
import java.sql.*;


public class ConnectionFactory {
	private final String ip = "localhost";
	private final int port = 5432;
	private final String user = "postgres";
	private final String password = "mundo2015";
	private final String database = "iSportfai";
			
			public Connection getConnection(){
		try{
			return DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port +"/"+ database, user, password);	
		}catch(SQLException e){
			throw new RuntimeException(e); 
		}
	}
			
}
