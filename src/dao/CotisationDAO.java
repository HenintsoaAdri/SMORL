package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.Cotisation;

public class CotisationDAO {

	public static Vector<Cotisation> getCotisation() throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM LISTCOTISATION";
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
		String query = "SELECT * FROM LISTCOTISATION WHERE ANNEECOTISATION = ?";
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
    	String req = "UPDATE COTISATION SET MONTANTOBJECTIF = ?,"
    			+ " WHERE IDCOTISATION = ?";
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setDouble(1, p.getMontantObjectif());
			statement.setInt(1, p.getId());
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
    	String req = "INSERT INTO COTISATION (MONTANTOBJECTIF,ANNEECOTISATION) "
    			+ "VALUES (?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setDouble(1, p.getMontantObjectif());
			statement.setInt(2, p.getAnneeCotisation());
			statement.execute();
			con.commit();
		}
		catch(SQLException sqle){
			if(sqle.getSQLState().compareTo("23505") == 0){
				try{
					req = "UPDATE COTISATION SET MONTANTOBJECTIF = ? WHERE ANNEECOTISATION = ?";
					statement = con.prepareStatement(req);
					statement.setDouble(1, p.getMontantObjectif());
					statement.setInt(2, p.getAnneeCotisation());
					statement.execute();
					con.commit();
				}catch(Exception e){
					con.rollback();
					e.printStackTrace();
					throw e;
				}
			}else throw sqle;
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
