package model;

import java.sql.Date;

public class PaiementCongres {
	int id;
	Date datePaiement;
	double montant;
	Membre membre;
	Congres congres;
	
	public PaiementCongres() {}

	public PaiementCongres(int id, Date datePaiement, double montant) {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
	}
	
	public PaiementCongres(int id, Date datePaiement, double montant, Membre membre, Congres congres) {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
		this.setMembre(membre);
		this.setCongres(congres);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public Congres getCongres() {
		return congres;
	}

	public void setCongres(Congres congres) {
		this.congres = congres;
	}
}
