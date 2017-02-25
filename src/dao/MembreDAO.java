package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.Date;

import model.Membre;

public class MembreDAO {
	
	public static Vector<Membre> getMembre() throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM MEMBRE";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			return DBToMembre(statement.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}
	
	public static Membre getMembreById(int id) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM MEMBRE WHERE IDMEMBRE = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		try {
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			res.next();
			return Creation.creerMembre(res);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			statement.close();
			conn.close();
		}
	}

	public static Membre get(int id) throws Exception {
		Connection conn = UtilDB.getConnPostgre();
		String query = "SELECT * FROM MEMBRE WHERE IDMEMBRE = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet res = null;
		try {
			statement.setInt(1, id);
			res = statement.executeQuery();
			if(res.next()) return Creation.creerMembre(res);
			throw new Exception("Ce membre est introuvable");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(res != null)res.close();
			statement.close();
			conn.close();
		}
	}

	public static void modify(Membre p) throws Exception {
		Connection con = UtilDB.getConnPostgre();
    	String req = "UPDATE MEMBRE SET NOM = ?,"
    			+ "PRENOM = ?,"
    			+ "DATENAISSANCE = ?,"
    			+ "SEXE = ?,"
    			+ "EMAIL = ?,"
    			+ "TELEPHONE = ?,"
    			+ "ADRESSE = ?,"
    			+ "PROFESSION = ?,"
    			+ "CAPACITE  = ?"
    			+ " WHERE IDMEMBRE = ?";
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, p.getNom());
			statement.setString(2, p.getPrenom());
			statement.setDate(3, Date.valueOf(p.getDateNaissance()));
			statement.setString(4, p.getSexe());
			statement.setString(5, p.getEmail());
			statement.setString(6, p.getTelephone());
			statement.setString(7, p.getAdresse());
			statement.setString(8, p.getProfession());
			statement.setString(9, p.getCapacite());
			statement.setInt(10, p.getId());
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

	public static void insertMembre(Membre p) throws Exception{
    	Connection con = UtilDB.getConnPostgre();
    	con.setAutoCommit(false);
    	String req = "INSERT INTO MEMBRE (NOM,PRENOM,DATENAISSANCE,SEXE,"
    			+ "EMAIL,TELEPHONE,ADRESSE,PROFESSION,CAPACITE) "
    			+ "VALUES (?,?,?,?,?,?,?,?,?)";
	
		PreparedStatement statement = con.prepareStatement(req);
		try{
			statement.setString(1, p.getNom());
			statement.setString(2, p.getPrenom());
			statement.setDate(3, Date.valueOf(p.getDateNaissance()));
			statement.setString(4, p.getSexe());
			statement.setString(5, p.getEmail());
			statement.setString(6, p.getTelephone());
			statement.setString(7, p.getAdresse());
			statement.setString(8, p.getProfession());
			statement.setString(9, p.getCapacite());
			statement.execute();
			con.commit();
		}
		catch(Exception e){
			con.rollback();
			e.printStackTrace();
			throw new Exception("Membre contenant des erreurs, cr\u00e9ation \u00e9chou\u00e9e.");
		}finally {
			con.close();
		}
	}
	
//	=================================================================================================================
	static Vector<Membre> DBToMembre(ResultSet res)throws Exception{
		try{
			Vector<Membre> model = new Vector<Membre>();
			while(res.next()){
				model.add(Creation.creerMembre(res));
			}
			return model;
		}catch(Exception e){
			throw e;
		}finally {
			res.close();
		}
	}
}
