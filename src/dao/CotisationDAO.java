package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Cotisation;

public class CotisationDAO {

	public CotisationDAO() {
		// TODO Auto-generated constructor stub
	}
	public static Vector<Cotisation> getCotisation() throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM COTISATION";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			return DBToCotisation(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	
	public static Cotisation getCotisationByYear(int year) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM COTISATION WHERE YEAR =?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, year);
			return DBToCotisation(statement.executeQuery()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	
	public static void modify(Cotisation p) throws Exception {
		Connection con = UtilDB.getConnPostgre();
    	String req = "UPDATE COTISATION SET MONTANT = ?,"
    			+ " WHERE YEAR = ?";
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setDouble(1, p.getMontant());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			con.close();
		}
	}
	
	public static void insertCotisation(Cotisation p) throws Exception{
    	Connection con = UtilDB.getConnPostgre();
    	con.setAutoCommit(false);
    	String req = "INSERT INTO COTISATION (MONTANT,ANNEECOTISATION) "
    			+ "VALUES (?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setDouble(1, p.getMontant());
			statement.setInt(2, p.getAnneeCotisation());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Cotisation contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			con.close();
		}
	}
	
//	=================================================================================================================
	static Vector<Cotisation> DBToCotisation(ResultSet res)throws Exception{
		try{
			Vector<Cotisation> model = new Vector<Cotisation>();
			while(res.next()){
				model.add(Creation.creerCotisation(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}
}
