package model;

import java.sql.Date;

public class Cotisation {
	int id;
	int anneeCotisation;
	double montant;

	public Cotisation() {
		// TODO Auto-generated constructor stub
	}

	public Cotisation(int id, int anneeCotisation, double montant) {
		super();
		this.setId(id);
		this.setAnneeCotisation(anneeCotisation);
		this.setMontant(montant);
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

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
