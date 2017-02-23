package dao;

import java.sql.*;
import java.util.Vector;

import model.EtatCotisation;

public class EtatCotisationDAO {

	public EtatCotisationDAO() {
		// TODO Auto-generated constructor stub
	}
	
    public void viderEtatDePaie(Employe emp, int mois, int annee) throws Exception{
    	Connection con = UtilDB.getConnOracle();
    	String sql = "DELETE FROM etatdepaie WHERE idemploye=? and mois=? and annee=?";
    	
		PreparedStatement statement = con.prepareStatement(sql);
		try{
			statement.setInt(1, emp.getIdEmploye());
			statement.setInt(2, mois);
			statement.setInt(3, annee);
    		statement.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw e;
		}finally {
			statement.close();
			con.close();
		}
	}
	
    public void insertEtatDePaie(EtatDePaie etat) throws Exception{
    	Connection con = UtilDB.getConnOracle();
    	String req = "INSERT INTO etatdepaie (IDEMPLOYE,MOIS,ANNEE,GAIN,RETENU,ETAT,LIBELLE) VALUES (?,?,?,?,?,?,?)";
    	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setInt(1, etat.getEmploye().getIdEmploye());
			statement.setInt(2, etat.getMois());
			statement.setInt(3, etat.getAnnee());
			statement.setDouble(4, etat.getGain());
			statement.setDouble(5, etat.getRetenu());
			statement.setInt(6, etat.getEtat());
			statement.setString(7, etat.getLibelle());
			statement.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw e;
		}finally {
			statement.close();
			con.close();
		}
	}
    
    public Vector<EtatCotisation> getEtatDePaie(EtatCotisation etat) throws Exception{
    	Connection con = UtilDB.getConnOracle();
    	con.setAutoCommit(false);
        String req = "select * from ETATCOTISATION where IDMEMBRE = ? AND ANNEECOTISATION = ?";
        Vector<EtatCotisation> ltetatdepaie = new Vector<EtatCotisation>();
		PreparedStatement statement = con.prepareStatement(req);
		try{
			ResultSet res = statement.executeQuery();
			statement.setInt(1, etat.getEmploye().getIdEmploye());
			statement.setInt(2, etat.getMois());
			statement.setInt(3, etat.getAnnee());
			while(res.next()){
				ltetatdepaie.add(Creation.creerEtatCotisation(res));
				
				ltetatdepaie.add(etatpaie);
			}
			con.commit();
			return ltetatdepaie;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			throw e;
		}finally {
			statement.close();
			con.close();
		}
	}

}
