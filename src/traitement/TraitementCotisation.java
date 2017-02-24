package traitement;


import java.time.LocalDate;
import java.util.Vector;

import dao.CotisationDAO;
import dao.MembreDAO;
import dao.PaiementCotisationDAO;
import model.Cotisation;
import model.PaiementCotisation;

public class TraitementCotisation {

	public static void insertionCotisation(String anneeCotisation, String montant) throws Exception{
		Cotisation cotisation = new Cotisation(0, Integer.valueOf(anneeCotisation), Double.valueOf(montant),0,0);
		CotisationDAO.insertCotisation(cotisation);
	}
	
	public static void modificationCotisation(String idCotisation, String dateCotisation, String montant) throws Exception{
		Cotisation cotisation = new Cotisation(Integer.valueOf(idCotisation), Integer.valueOf(dateCotisation), Double.valueOf(montant),0,0);
		CotisationDAO.modify(cotisation);
	}
	
	public static Vector<Cotisation> getListCotisation()throws Exception{
		return CotisationDAO.getCotisation();
	}
	public static Cotisation getCotisationByYear(String annee)throws Exception{
		return CotisationDAO.getCotisationByYear(Integer.parseInt(annee));
	}

	public static void insertionPaiement(String datePaiement, String montant, String idMembre, String year) throws Exception{
		PaiementCotisation cotisation = new PaiementCotisation(0, LocalDate.parse(datePaiement), Double.valueOf(montant),
					MembreDAO.getMembreById(Integer.parseInt(idMembre)), CotisationDAO.getCotisationByYear(Integer.parseInt(year)));
		PaiementCotisationDAO.insertPaiementCotisation(cotisation);
	}
}
