package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Congres;
import model.Cotisation;

public class CongresDAO {

	public CongresDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector<Congres> getCongresByID(int id) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM CONGRES WHERE IDCONGRES =?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, id);
			return DBToCongres(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	
	public static void modify(Congres p) throws Exception {
		Connection con = UtilDB.getConnPostgre();
    	String req = "UPDATE CONGRES SET NOM = ?,"
    			+ "SET LOGEMENT = ?,"
    			+ "SET FRAISTRANSPORT = ?,"
    			+ "SET RESTAURATION = ?,"
    			+ " WHERE IDCONGRES = ?";
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, p.getNom());
			statement.setDouble(1, p.getLogement());
			statement.setDouble(1, p.getFraisTransport());
			statement.setDouble(1, p.getRestauration());
			statement.setDouble(1, p.getId());
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
	
	public static void insertCongres(Congres p) throws Exception{
    	Connection con = UtilDB.getConnPostgre();
    	con.setAutoCommit(false);
    	String req = "INSERT INTO ONGRES (NOMCONGRES,LOGEMENT,FRAISTRANSPORT,RESTAURATION) "
    			+ "VALUES (?,?,?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, p.getNom());
			statement.setDouble(2, p.getLogement());
			statement.setDouble(3, p.getFraisTransport());
			statement.setDouble(4, p.getRestauration());
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
	static Vector<Congres> DBToCongres(ResultSet res)throws Exception{
		try{
			Vector<Congres> model = new Vector<Congres>();
			while(res.next()){
				model.add(Creation.creerCongres(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}

}
