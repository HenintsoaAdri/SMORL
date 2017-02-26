package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilDB {
	public static Connection conn;

	public static Connection getConnPostgre()throws Exception{
		try{
			Class.forName("org.postgresql.Driver");
			conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/smorl","postgres", "adri");
			conn.setAutoCommit(false);
			if(conn == null){
				throw new Exception("Aucune connexion �tablie");
			}
		}
		catch(ClassNotFoundException e){
			throw new Exception("Veuillez inclure le driver de postgres");
		}
		catch(Exception e){
			throw e;
		}
		return conn;
	}
}
