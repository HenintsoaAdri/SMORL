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

	public PaiementCotisation(int id, LocalDate datePaiement, double montant) throws Exception {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
	}

	public PaiementCotisation(int id, LocalDate datePaiement, double montant, Membre membre, Cotisation cotisation, boolean paye) throws Exception {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMembre(membre);
		this.setCotisation(cotisation);
		this.setMontant(montant);
		if(paye) this.setMontantPaye(montant);
		else this.setMontant(montant);
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

	public void setMontant(double montant) throws Exception {
		if(montant <= 0) throw new Exception("Montant invalide");
		else if(getReste()< montant) throw new Exception("Montant sup\u00e9rieur au reste a payer");
		this.montant = montant;
	}
	public void setMontantPaye(double montant) throws Exception {
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

	public double getReste() throws Exception{
		return getCotisation().getReste(getMembre());
	}
	public String getResteString() throws Exception{
		return getCotisation().getResteString(getMembre());
	}
}