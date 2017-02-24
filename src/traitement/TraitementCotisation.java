package traitement;

import java.time.LocalDate;
import java.util.Vector;

import dao.CotisationDAO;
import dao.MembreDAO;
import model.Cotisation;
import model.Membre;

public class TraitementCotisation {

	public static void insertionCotisation(String dateCotisation, String montant) throws Exception{
		Cotisation cotisation = new Cotisation(0, Integer.valueOf(dateCotisation), Double.valueOf(montant));
		CotisationDAO.insertCotisation(cotisation);
	}
	
	public static void modificationCotisation(String idCotisation, String dateCotisation, String montant) throws Exception{
		Cotisation cotisation = new Cotisation(Integer.valueOf(idCotisation), Integer.valueOf(dateCotisation), Double.valueOf(montant));
		CotisationDAO.modify(cotisation);
	}
	
	public static Cotisation getCotisationByYear(String annee)throws Exception{
		return CotisationDAO.getCotisationByYear(Integer.parseInt(annee));
	}
}
