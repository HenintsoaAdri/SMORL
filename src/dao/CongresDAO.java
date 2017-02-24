package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Congres;
import model.Cotisation;
import model.DetailCongres;

public class CongresDAO {

	public CongresDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector<Congres> getCongres() throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM CONGRES WHERE IDCONGRES =?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			return DBToCongres(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	
	public static Congres getCongresByID(int id) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM CONGRESDETAILVIEW WHERE IDCONGRES =?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, id);
			return DBToCongresDetail(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	
	public static Congres getCongresByNom(String nom) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM CONGRESDETAILVIEW WHERE NOMCONGRES =?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setString(1, nom);
			return DBToCongresDetail(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	
	public static void modifyCongres(Congres p) throws Exception {
		Connection con = UtilDB.getConnPostgre();
    	String req = "UPDATE CONGRES SET NOM = ?,"
    			+ " WHERE IDCONGRES = ?";
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, p.getNom());
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
	
	public static void modifyDetailCongres(DetailCongres d) throws Exception {
		Connection con = UtilDB.getConnPostgre();
    	String req = "UPDATE DETAILCONGRES SET DESIGNATION = ?, "
    			+ "SET MONTANT = ?"
    			+ " WHERE IDCONGRES = ? AND IDDETAILCONGRES = ?";
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, d.getDesignation());
			statement.setDouble(2, d.getMontant());
			statement.setInt(2, d.getCongres().getId());
			statement.setInt(3, d.getId());
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
    	String req = "INSERT INTO CONGRES (NOMCONGRES) "
    			+ "VALUES (?)";
    	String req2 = "INSERT INTO DETAILCONGRES (IDCONGRES,DESIGNATION,MONTANT) "
    			+ "VALUES (currval('congres_idcongres_seq'),?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, p.getNom());
			statement.execute();
			
			for(DetailCongres det : p.getDetailCongres()){
				PreparedStatement statement2 = con.prepareStatement(req2);
				
				statement2.setString(1, det.getDesignation());
				statement2.setDouble(2, det.getMontant());
				statement2.execute();
			}
			
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Congres contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
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
	
	static Congres DBToCongresDetail(ResultSet res)throws Exception{
		try{
			Congres model = new Congres();
			Vector<DetailCongres> ltdetailcongres = new Vector<DetailCongres>();
			if(res.next()){
				model = Creation.creerCongres(res);
				DetailCongres detail = Creation.creerDetailCongres(res);
				ltdetailcongres.add(detail);
				detail.setCongres(model);
			}
			while(res.next()){
				DetailCongres detail = Creation.creerDetailCongres(res);
				ltdetailcongres.add(detail);
				detail.setCongres(model);
			}
			model.setDetailCongres(ltdetailcongres);
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}

}
