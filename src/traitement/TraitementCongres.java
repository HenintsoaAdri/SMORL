package traitement;

import java.util.Vector;

import dao.CongresDAO;
import dao.CotisationDAO;
import model.Congres;
import model.Cotisation;
import model.DetailCongres;

public class TraitementCongres {

	public static void insertionCongres(Congres congres) throws Exception{
		CongresDAO.insertCongres(congres);
	}
	
	public static void modificationCongres(String idCongres, String nom) throws Exception{
		Congres congres = new Congres(Integer.valueOf(idCongres), nom);
		CongresDAO.modifyCongres(congres);
	}
	
	public static void modificationDetailCongres(String idDetailCongres, String nom, String montant) throws Exception{
		DetailCongres detailCongres = new DetailCongres(Integer.valueOf(idDetailCongres), nom, Double.valueOf(montant));
		CongresDAO.modifyDetailCongres(detailCongres);
	}
	
	public static Congres getCongresById(String idCongres)throws Exception{
		return CongresDAO.getCongresByID(Integer.parseInt(idCongres));
	}
	
	public static Congres getCongresByNom(String nom)throws Exception{
		return CongresDAO.getCongresByNom(nom);
	}
}
