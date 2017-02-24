package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Membre;
import model.PaiementCongres;
import model.PaiementCotisation;

public class PaiementCotisationDAO {

	public PaiementCotisationDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static double getSommePaye(int annee,int idmembre) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT MONTANTPAYE FROM SOMMEPAYECOTISATION WHERE IDMEMBRE=? AND ANNEECOTISATION=?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, idmembre);
			statement.setInt(2, annee);
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

	public static void insertPaiementCotisation(PaiementCotisation p) throws Exception{
    	Connection con = UtilDB.getConnPostgre();
    	con.setAutoCommit(false);
    	String req = "INSERT INTO PAIEMENTCOTISATION (DATEPAIEMENT,MONTANT) "
    			+ "VALUES (?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setDate(1, p.getDatePaiement());
			statement.setDouble(2, p.getMontant());
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
	static Vector<PaiementCotisation> DBToPaiementCotisation(ResultSet res)throws Exception{
		try{
			Vector<PaiementCotisation> model = new Vector<PaiementCotisation>();
			while(res.next()){
				model.add(Creation.creerPaiementCotisation(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}
}
