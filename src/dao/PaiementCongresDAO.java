package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import model.Cotisation;
import model.PaiementCongres;

public class PaiementCongresDAO {

	public PaiementCongresDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static void insertPaiementCongres(PaiementCongres p) throws Exception{
    	Connection con = UtilDB.getConnPostgre();
    	con.setAutoCommit(false);
    	String req = "INSERT INTO PAIEMENTCONGRES (DATEPAIEMENT,MONTANT) "
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
}
