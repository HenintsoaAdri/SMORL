package model;

import java.sql.Date;
import java.time.LocalDate;

public class Cotisation {
	int id;
	LocalDate dateCotisation;
	double montant;

	public Cotisation() {}

	public Cotisation(int id, Date dateCotisation, double montant) {
		this.setId(id);
		this.setDateCotisation(dateCotisation);
		this.setMontant(montant);
	}

	public Cotisation(int id, LocalDate dateCotisation, double montant) {
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

	public LocalDate getDateCotisation() {
		return dateCotisation;
	}

	public void setDateCotisation(LocalDate dateCotisation) {
		this.dateCotisation = dateCotisation;
	}

	public void setDateCotisation(Date dateCotisation) {
		setDateCotisation(dateCotisation.toLocalDate());
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
