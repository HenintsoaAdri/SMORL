package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import utilitaire.StringUtil;

public class PaiementCongres {
	int id;
	LocalDate datePaiement;
	double montant;
	Membre membre;
	DetailCongres congres;
	
	public PaiementCongres() {}

	public PaiementCongres(int id, LocalDate datePaiement, double montant) throws Exception {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setMontant(montant);
	}
	
	public PaiementCongres(int id, LocalDate datePaiement, double montant, Membre membre, DetailCongres congres, boolean paye) throws Exception {
		this.setId(id);
		this.setDatePaiement(datePaiement);
		this.setCongres(congres);
		this.setMembre(membre);
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

	public DetailCongres getCongres() {
		return congres;
	}

	public void setCongres(DetailCongres congres) {
		this.congres = congres;
	}
	
	public double getReste() throws Exception{
		return getCongres().getReste(getMembre());
	}
	public String getResteString() throws Exception{
		return getCongres().getResteString(getMembre());
	}
}