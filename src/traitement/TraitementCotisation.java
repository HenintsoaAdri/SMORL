package traitement;


import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import dao.CotisationDAO;
import dao.PaiementCotisationDAO;
import model.Cotisation;
import model.Membre;
import model.PaiementCotisation;

public class TraitementCotisation {

	public static void insertionCotisation(String anneeCotisation, String montant) throws Exception{
		try{
			Year.parse(anneeCotisation);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Ann&eacute;e invalide");
		}
		Cotisation cotisation = new Cotisation(0, Integer.valueOf(anneeCotisation), Double.valueOf(montant),0,0);
		CotisationDAO.insertCotisation(cotisation);
	}
	
	public static void modificationCotisation(String idCotisation, String anneeCotisation, String montant) throws Exception{
		Cotisation cotisation = new Cotisation(Integer.valueOf(idCotisation), Integer.valueOf(anneeCotisation), Double.valueOf(montant),0,0);
		CotisationDAO.modify(cotisation);
	}
	
	public static Vector<Cotisation> getListCotisation()throws Exception{
		return CotisationDAO.getCotisation();
	}
	public static Cotisation getCotisationByYear(String annee)throws Exception{
		return CotisationDAO.getCotisationByYear(Integer.parseInt(annee));
	}

	public static void insertionPaiement(String datePaiement, String montant, Membre membre, String year) throws Exception{
		try{
			PaiementCotisation cotisation = new PaiementCotisation(0, LocalDate.parse(datePaiement), Double.valueOf(montant),
					membre, CotisationDAO.getCotisationByYear(Integer.parseInt(year)));
			PaiementCotisationDAO.insertPaiementCotisation(cotisation);
		}catch (DateTimeParseException e){
			throw new Exception("Date invalide");
		}
	}

	public static void deletePaiement(String idPaiement) throws Exception{
		try{
			PaiementCotisationDAO.deletePaiementCotisation(Integer.parseInt(idPaiement));	
		} catch(Exception e){
			throw new Exception("Paiement invalide");
		}
	}
}
