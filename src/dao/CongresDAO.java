package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Congres;
import model.DetailCongres;

public class CongresDAO {
	
	public static Vector<Congres> getCongres() throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM CONGRES";
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
	public static void getDetailCongres(Congres c) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM LISTDETAILCONGRES WHERE IDCONGRES = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, c.getId());
			DBToDetailCongres(statement.executeQuery(),c);
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
			return DBToUniqueCongres(statement.executeQuery());
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
		String query = "SELECT * FROM CONGRES WHERE NOMCONGRES =?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setString(1, nom);
			return DBToUniqueCongres(statement.executeQuery());
			
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
			statement.setDouble(2, d.getMontantObjectif());
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
    	String req = "INSERT INTO CONGRES (NOMCONGRES, DATECONGRES) "
    			+ "VALUES (?,?)";
    	String req2 = "INSERT INTO DETAILCONGRES (IDCONGRES,DESIGNATION,MONTANT) "
    			+ "VALUES (currval('congres_idcongres_seq'),?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, p.getNom());
			statement.setDate(2, Date.valueOf(p.getDate()));
			statement.execute();
			
			for(DetailCongres det : p.getDetailCongres()){
				PreparedStatement statement2 = con.prepareStatement(req2);
				
				statement2.setString(1, det.getDesignation());
				statement2.setDouble(2, det.getMontantObjectif());
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
				Congres c = Creation.creerCongres(res);
				getDetailCongres(c);
				model.add(c);
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}
	static Congres DBToUniqueCongres(ResultSet res)throws Exception{
		try{
			if(res.next()){
				Congres c = Creation.creerCongres(res);
				getDetailCongres(c);
				return c;
			}
			throw new Exception("Nous ne retrouvons pas ce congr&egrave;s");
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
				ltdetailcongres.add(Creation.creerDetailCongres(res, model));
			}
			while(res.next()){
				ltdetailcongres.add(Creation.creerDetailCongres(res, model));
			}
			model.setDetailCongres(ltdetailcongres);
			return model;
		}catch(Exception e){
			throw e;
		}
	}
	static void DBToDetailCongres(ResultSet res, Congres model) throws Exception {
		try{
			Vector<DetailCongres> ltdetailcongres = new Vector<DetailCongres>();
			while(res.next()){
				ltdetailcongres.add(Creation.creerDetailCongres(res, model));
			}
			model.setDetailCongres(ltdetailcongres);
		}catch(Exception e){
			throw e;
		}
	}

}
