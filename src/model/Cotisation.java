package model;

import java.util.Vector;

import dao.MembreDAO;
import dao.PaiementCotisationDAO;
import utilitaire.StringUtil;

public class Cotisation {
	int id;
	int anneeCotisation;
	double montant;
	double montantPaye;
	int contribuable;
	Vector<PaiementCotisation> detailPaiement;
	
	public Cotisation() {}

	public Cotisation(int id, int anneeCotisation, double montant, double montantPaye, int contribuable) {
		this.setId(id);
		this.setAnneeCotisation(anneeCotisation);
		this.setMontant(montant);
		this.setMontantPaye(montantPaye);
		this.setContribuable(contribuable);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnneeCotisation() {
		return anneeCotisation;
	}

	public void setAnneeCotisation(int anneeCotisation) {
		this.anneeCotisation = anneeCotisation;
	}
	
	public double getMontant() {
		return montant;
	}
	public String getMontantString() {
		return StringUtil.moneyToString(getMontant());
	}
	
	public void setMontant(double montant) {
		this.montant = montant;
	}

	public double getMontantPaye() {
		return montantPaye;
	}
	public String getMontantPayeString() {
		return StringUtil.moneyToString(getMontantPaye());
	}

	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}

	public int getContribuable() {
		return contribuable;
	}

	public void setContribuable(int contribuable) {
		this.contribuable = contribuable;
	}

	public Vector<PaiementCotisation> getDetailPaiement() {
		return detailPaiement;
	}

	public void setDetailPaiement(Vector<PaiementCotisation> detailPaiement) {
		this.detailPaiement = detailPaiement;
	}
	public double getReste(Membre m) throws Exception{
		double sommeParMembre = PaiementCotisationDAO.getSommePaye(this.getAnneeCotisation(), m.getId());
		if(getMontant() - sommeParMembre < 0){
			return 0;
		}
		return getMontant() - sommeParMembre;
	}
	public String getResteString(Membre m) throws Exception{
		return StringUtil.moneyToString(getReste(m));
	}
}
