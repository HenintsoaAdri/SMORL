package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.DetailCongres;
import model.PaiementCongres;

public class PaiementCongresDAO {

	public static double getSommePaye(int iddetailcongres,int idmembre) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT MONTANTPAYE FROM SOMMEPAYECONGRES WHERE IDMEMBRE=? AND IDDETAILCONGRES=?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, idmembre);
			statement.setInt(2, iddetailcongres);
			ResultSet res = statement.executeQuery();
			res.next();
			return res.getDouble("MONTANTPAYE");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}

	public static Vector<PaiementCongres> getPaiementMembreByDetailCongres(DetailCongres dc) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM SOMMEPAYECONGRES WHERE IDDETAILCONGRES = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, dc.getId());
			return DBToDetailCongres(statement.executeQuery(), dc);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}

	public static Vector<PaiementCongres> getPaiementCongresByIdMembre(DetailCongres c, int id) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM DETAILPAIEMENTCONGRES WHERE IDMEMBRE =? AND IDDETAILCONGRES = ? ORDER BY DATEPAIEMENT ASC";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, id);
			statement.setInt(2, c.getId());
			return DBToPaiementCongres(statement.executeQuery(),c);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	public static void insertPaiementCongres(PaiementCongres p) throws Exception{
    	Connection con = UtilDB.getConnPostgre();
    	con.setAutoCommit(false);
    	String req = "INSERT INTO PAIEMENTCONGRES (DATEPAIEMENT,MONTANT,IDDETAILCONGRES,IDMEMBRE) "
    			+ "VALUES (?,?,?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setDate(1, Date.valueOf(p.getDatePaiement()));
			statement.setDouble(2, p.getMontant());
			statement.setInt(3, p.getCongres().getId());
			statement.setInt(4, p.getMembre().getId());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Paiement contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			con.close();
		}
	}

//	=================================================================================================================
	static Vector<PaiementCongres> DBToPaiementCongres(ResultSet res)throws Exception{
		try{
			Vector<PaiementCongres> model = new Vector<PaiementCongres>();
			while(res.next()){
				model.add(Creation.creerPaiementCongres(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}
	static Vector<PaiementCongres> DBToPaiementCongres(ResultSet res, DetailCongres dc)throws Exception{
		try{
			Vector<PaiementCongres> model = new Vector<PaiementCongres>();
			while(res.next()){
				model.add(Creation.creerPaiementCongres(res,dc));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}
	static Vector<PaiementCongres> DBToDetailCongres(ResultSet res, DetailCongres dc)throws Exception{
		try{
			Vector<PaiementCongres> model = new Vector<PaiementCongres>();
			while(res.next()){
				model.add(Creation.creerDetailCongres(res, dc));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}
}
