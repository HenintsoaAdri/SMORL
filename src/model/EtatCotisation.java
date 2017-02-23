package model;

public class EtatCotisation {
	int id;
	String libelle;
	double montant;
	double montantPaye;
	double resteAPayer;
	int annee;
	Membre membre; 

	public EtatCotisation() {
		// TODO Auto-generated constructor stub
	}

	public EtatCotisation(int id, String libelle, double montant, double montantPaye, int annee) {
		super();
		this.setId(id);
		this.setLibelle(libelle);
		this.setMontant(montantPaye);
		this.setMontantPaye(montantPaye);
		this.setAnnee(annee);
	}

	public EtatCotisation(int id, String libelle, double montant, double montantPaye, int annee,
			Membre membre) {
		this.setId(id);
		this.setLibelle(libelle);
		this.setMontant(montantPaye);
		this.setMontantPaye(montantPaye);
		this.setAnnee(annee);
		this.setMembre(membre);
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public double getMontantPaye() {
		return montantPaye;
	}

	public void setMontantPaye(double montantPaye) {
		this.montantPaye = montantPaye;
	}

	public double getResteAPayer() {
		return resteAPayer;
	}

	public void setResteAPayer(double resteAPayer) {
		this.resteAPayer = resteAPayer;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

}
