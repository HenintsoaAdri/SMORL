package traitement;


import dao.CotisationDAO;
import model.Cotisation;

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
