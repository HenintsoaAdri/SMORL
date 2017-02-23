package model;

import java.sql.Date;

public class Cotisation {
	int id;
	Date dateCotisation;
	double montant;

	public Cotisation() {
		// TODO Auto-generated constructor stub
	}

	public Cotisation(int id, Date dateCotisation, double montant) {
		super();
		this.setId(id);
		this.setDateCotisation(dateCotisation);
		this.setMontant(montant);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCotisation() {
		return dateCotisation;
	}

	public void setDateCotisation(Date dateCotisation) {
		this.dateCotisation = dateCotisation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
