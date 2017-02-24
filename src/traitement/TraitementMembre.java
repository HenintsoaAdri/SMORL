package traitement;

import java.util.Vector;

import dao.MembreDAO;
import model.Membre;

public class TraitementMembre {
	public static void insertionMembre(String nom, String prenom, String dateNaissance, String sexe, String email, String telephone, String adresse,
			String profession, String capacite) throws Exception{
		Membre m = new Membre(nom,prenom,dateNaissance,sexe,email,telephone,adresse,profession,capacite);
		MembreDAO.insertMembre(m);
	}
	public static Vector<Membre> getListMembre()throws Exception{
		return MembreDAO.getMembre();
	}
	public static Membre get(String id) throws Exception{
		return get(Integer.parseInt(id));
	}
	public static Membre get(int id) throws Exception {
		return MembreDAO.get(id);
	}
}
