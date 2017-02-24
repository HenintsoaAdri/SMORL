package model;

public class Congres {
	int id;
	String nom;
	double logement;
	double fraisTransport;
	double restauration;

	public Congres() {}

	public Congres(int id, String nom, double logement, double fraisTransport, double restauration) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setLogement(logement);
		this.setFraisTransport(fraisTransport);
		this.setRestauration(restauration);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getLogement() {
		return logement;
	}

	public void setLogement(double logement) {
		this.logement = logement;
	}

	public double getFraisTransport() {
		return fraisTransport;
	}

	public void setFraisTransport(double fraisTransport) {
		this.fraisTransport = fraisTransport;
	}

	public double getRestauration() {
		return restauration;
	}

	public void setRestauration(double restauration) {
		this.restauration = restauration;
	}

}
