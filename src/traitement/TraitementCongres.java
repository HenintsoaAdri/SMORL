package traitement;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import dao.CongresDAO;
import dao.PaiementCongresDAO;
import dao.PaiementCotisationDAO;
import model.*;

public class TraitementCongres {

	public static void insertionCongres(String nomCongres, String dateCongre, Vector<DetailCongres> detail) throws Exception{
		try{
			Congres c = new Congres(0, nomCongres, LocalDate.parse(dateCongre), detail);
			CongresDAO.insertCongres(c);
		}catch (DateTimeParseException e){
			throw new Exception("Date invalide");
		}
	}
	public static Vector<DetailCongres> createDetailCongres(String[] detail, String[] montant) throws Exception{
		Vector<DetailCongres> vect = new Vector<DetailCongres>();
		for(int i = 0; i<detail.length; i++){
			if(!detail[i].isEmpty()&&!montant[i].isEmpty())
			vect.addElement(new DetailCongres(0, detail[i], Double.parseDouble(montant[i])));
		}
		if(vect.isEmpty())throw new Exception("Veuillez rajouter au moins un (1) d\u00e9tail a votre congres");
		return vect;
	}
	
	public static void modificationCongres(String idCongres, String nom, String dateCongre) throws Exception{
		try{
			Congres congres = new Congres(Integer.valueOf(idCongres), nom, LocalDate.parse(dateCongre));
			CongresDAO.modifyCongres(congres);
		}catch (DateTimeParseException e){
			throw new Exception("Date invalide");
		}
	}
	
	public static void modificationDetailCongres(String idDetailCongres, String nom, String montant) throws Exception{
		DetailCongres detailCongres = new DetailCongres(Integer.parseInt(idDetailCongres), nom, Double.parseDouble(montant));
		CongresDAO.modifyDetailCongres(detailCongres);
	}

	public static void insertionPaiement(String datePaiement, String montant, Membre membre, String idDetailCongres) throws Exception{
		try{
			PaiementCongres paiement = new PaiementCongres(0, LocalDate.parse(datePaiement), Double.valueOf(montant),
					membre, new DetailCongres(Integer.parseInt(idDetailCongres),"",0));
			PaiementCongresDAO.insertPaiementCongres(paiement);
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
	
	public static Vector<Congres> getListCongres()throws Exception{
		return CongresDAO.getCongres();
	}
	
	public static Congres getCongresById(String idCongres)throws Exception{
		return CongresDAO.getCongresByID(Integer.parseInt(idCongres));
	}
	
	public static Congres getCongresByNom(String nom)throws Exception{
		return CongresDAO.getCongresByNom(nom);
	}
	public static Vector<PaiementCongres> getPaiementDetailCongre(DetailCongres dc) throws Exception{
		return PaiementCongresDAO.getPaiementMembreByDetailCongres(dc);
	}
}
