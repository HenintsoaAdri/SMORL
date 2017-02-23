package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Congres;
import model.Cotisation;
import model.Membre;
import model.PaiementCongres;
import model.PaiementCotisation;
import model.EtatCotisation;

public class Creation {
	
	public static Membre creerMembre(ResultSet res) throws Exception{
		Membre model = new Membre(
				res.getInt("IDMEMBRE"), 
				res.getString("NOM"), 
				res.getString("PRENOM"), 
				res.getDate("DATENAISSANCE"),
				res.getString("SEXE"),
				res.getString("EMAIL"),
				res.getString("TELEPHONE"), 
				res.getString("ADRESSE"), 
				res.getString("PROFESSION"), 
				res.getString("CAPACITE"));
		return model;
	}
	
	public static Cotisation creerCotisation(ResultSet res) throws SQLException{
		Cotisation model = new Cotisation(
				res.getInt("IDCOTISATION"), 
				res.getDate("DATE"), 
				res.getDouble("MONTANT"));
		return model;
	}
	
	public static Congres creerCongres(ResultSet res) throws SQLException{
		Congres model = new Congres(res.getInt("ID"), 
				res.getString("NOMCONGRES"), 
				res.getDouble("LOGEMENT"), 
				res.getDouble("FRAISTRANSPORT"), 
				res.getDouble("RESTAURATION"));
		return model;
	}
	
	public static PaiementCongres creerPaiementCongres(ResultSet res) throws Exception{
		PaiementCongres model = new PaiementCongres(
				res.getInt("IDPAIEMENTCONGRES"), 
				res.getDate("DATEPAIEMENT"), 
				res.getDouble("MONTANT"),
				creerMembre(res),
				creerCongres(res));
		return model;
	}
	
	public static PaiementCotisation creerPaiementCotisation(ResultSet res) throws Exception{
		PaiementCotisation model = new PaiementCotisation(
				res.getInt("IDPAIEMENTCOTISATION"), 
				res.getDate("DATEPAIEMENT"), 
				res.getDouble("MONTANT"),
				creerMembre(res),
				creerCotisation(res));
		return model;
	}
	
	public static EtatCotisation creerEtatCotisation(ResultSet res) throws Exception{
		EtatCotisation model = new EtatCotisation(
				res.getInt("IDETATCOTISATION"), 
				res.getString("LIBELLE"), 
				res.getDouble("MONTANT"), 
				res.getDouble("MONTANTPAYE"),
				res.getInt("ANNEECOTISATION"),
				creerMembre(res));
		return model;
	}

}
