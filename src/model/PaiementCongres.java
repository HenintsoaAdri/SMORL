package model;

import java.time.LocalDate;

public class PaiementCongres {
	int id;
	LocalDate datePaiement;
	double montant;
	Membre membre;
	Congres congres;
	
	public PaiementCongres() {}

	public PaiementCongres(int id, LocalDate datePaiement, double montant) {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
	}
	
	public PaiementCongres(int id, LocalDate datePaiement, double montant, Membre membre, Congres congres) {
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

	public LocalDate getDatePaiement() {
		return datePaiement;
	}

	public void setDatePaiement(LocalDate datePaiement) {
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
