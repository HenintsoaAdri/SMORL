package model;

import java.sql.Date;

public class PaiementCotisation {
	int id;
	Date datePaiement;
	double montant;	
	Membre membre;
	Cotisation cotisation;

	public PaiementCotisation() {
		// TODO Auto-generated constructor stub
	}

	public PaiementCotisation(int id, Date datePaiement, double montant) {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
	}

	public PaiementCotisation(int id, Date datePaiement, double montant, Membre membre, Cotisation cotisation) {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
		this.setMembre(membre);
		this.setCotisation(cotisation);
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

	public Cotisation getCotisation() {
		return cotisation;
	}

	public void setCotisation(Cotisation cotisation) {
		this.cotisation = cotisation;
	}

}
