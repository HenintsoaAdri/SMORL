package traitement;

import java.time.LocalDate;

import dao.CotisationDAO;
import dao.MembreDAO;
import dao.PaiementCotisationDAO;
import model.Cotisation;
import model.PaiementCotisation;

public class TraitementPaiementCotisation {

	public static void insertionPaiement(String datePaiement, String montant, String idMembre, String year) throws Exception{
		PaiementCotisation cotisation = new PaiementCotisation(0, LocalDate.parse(datePaiement), Double.valueOf(montant),
					MembreDAO.getMembreById(Integer.parseInt(idMembre)), CotisationDAO.getCotisationByYear(Integer.parseInt(year)));
		PaiementCotisationDAO.insertPaiementCotisation(cotisation);
	}

}
