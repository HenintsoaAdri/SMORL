package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import utilitaire.StringUtil;

public class PaiementCotisation {
	int id;
	LocalDate datePaiement;
	double montant;	
	Membre membre;
	Cotisation cotisation;

	public PaiementCotisation() {}

	public PaiementCotisation(int id, LocalDate datePaiement, double montant) {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
	}

	public PaiementCotisation(int id, LocalDate datePaiement, double montant, Membre membre, Cotisation cotisation) {
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

	public LocalDate getDatePaiement() {
		return datePaiement;
	}
	public String getDatePaiementString() {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(getDatePaiement());
	}

	public void setDatePaiement(LocalDate datePaiement) {
		this.datePaiement = datePaiement;
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
