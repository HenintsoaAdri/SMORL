package model;

import java.time.LocalDate;

public class Cotisation {
	int id;
	LocalDate dateCotisation;
	int anneeCotisation;
	double montant;

	public Cotisation() {}

	public Cotisation(int id, int anneeCotisation, double montant) {
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
