package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Congres;
import model.Cotisation;
import model.DetailCongres;
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
				res.getDate("DATENAISSANCE").toLocalDate(),
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
				res.getInt("ANNEECOTISATION"), 
				res.getDouble("MONTANTOBJECTIF"),
				res.getDouble("MONTANTPAYE"),
				res.getInt("CONTRIBUABLE"));
		return model;
	}
	
	public static Congres creerCongres(ResultSet res) throws SQLException{
		Congres model = new Congres(res.getInt("IDCONGRES"), 
				res.getString("NOMCONGRES"));
		return model;
	}
	
	public static DetailCongres creerDetailCongres(ResultSet res) throws SQLException{
		DetailCongres model = new DetailCongres(res.getInt("IDDETAILCONGRES"), 
				res.getString("DESIGNATION"), 
				res.getDouble("MONTANT"));
		return model;
	}
	
	public static PaiementCongres creerPaiementCongres(ResultSet res) throws Exception{
		PaiementCongres model = new PaiementCongres(
				res.getInt("IDPAIEMENTCONGRES"), 
				res.getDate("DATEPAIEMENT").toLocalDate(), 
				res.getDouble("MONTANTPAYE"),
				creerMembre(res),
				creerCongres(res));
		return model;
	}
	
	public static PaiementCotisation creerDetailCotisation(ResultSet res, Cotisation c) throws Exception{
		PaiementCotisation model = new PaiementCotisation();
				model.setMontant(res.getDouble("MONTANTPAYE"));
				model.setMembre(creerMembre(res));
				model.setCotisation(c);
		return model;
	}
	
	public static PaiementCotisation creerPaiementCotisation(ResultSet res) throws Exception{
		return creerPaiementCotisation(res, creerCotisation(res));
	}
	
	public static PaiementCotisation creerPaiementCotisation(ResultSet res, Cotisation c) throws Exception{
		PaiementCotisation model = new PaiementCotisation(
				res.getInt("IDPAIEMENTCOTISATION"), 
				res.getDate("DATEPAIEMENT").toLocalDate(), 
				res.getDouble("MONTANTPAYE"),
				creerMembre(res),
				c);
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
