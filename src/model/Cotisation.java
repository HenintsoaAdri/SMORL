package model;

import java.util.Vector;

import utilitaire.StringUtil;

public class Cotisation {
	int id;
	int anneeCotisation;
	double montantObjectif;
	double montantPaye;
	int contribuable;
	Vector<PaiementCotisation> detailPaiement;
	public Cotisation() {}

	public Cotisation(int id, int anneeCotisation, double montantObjectif, double montantPaye, int contribuable) {
		this.setId(id);
		this.setAnneeCotisation(anneeCotisation);
		this.setMontantObjectif(montantObjectif);
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
	
	public double getMontantObjectif() {
		return montantObjectif;
	}
	public String getMontantObjectifString() {
		return StringUtil.moneyToString(getMontantObjectif());
	}
	
	public void setMontantObjectif(double montantObjectif) {
		this.montantObjectif = montantObjectif;
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
	public double getReste(){
		return getMontantObjectif() - getMontantPaye();
	}
	public String getResteString(){
		return StringUtil.moneyToString(getReste());
	}
}
