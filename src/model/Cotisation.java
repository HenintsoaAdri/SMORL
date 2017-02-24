package model;

public class Cotisation {
	int id;
	int anneeCotisation;
	double montantObjectif;
	double montantPaye;
	int contribuable;

	public Cotisation() {}

	public Cotisation(int id, int anneeCotisation, double montantObjectif, double montantPaye, int contribuable) {
		this.setId(id);
		this.setAnneeCotisation(anneeCotisation);
		this.setMontantPaye(montantObjectif);
		this.setMontantPaye(montantPaye);
		this.setContribuable(contribuable);
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
	
	public double getMontantObjectif() {
		return montantObjectif;
	}

	public void setMontantObjectif(double montantObjectif) {
		this.montantObjectif = montantObjectif;
	}

	public double getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}

	public int getContribuable() {
		return contribuable;
	}

	public void setContribuable(int contribuable) {
		this.contribuable = contribuable;
	}
	
}
