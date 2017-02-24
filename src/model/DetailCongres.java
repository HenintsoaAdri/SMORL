package model;

public class DetailCongres {
	int id;
	String designation;
	double montant;
	Congres congres;

	public DetailCongres() {
		// TODO Auto-generated constructor stub
	}

	public DetailCongres(int id, String designation, double montant) {
		super();
		this.setId(id);
		this.setDesignation(designation);
		this.setMontant(montant);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Congres getCongres() {
		return congres;
	}

	public void setCongres(Congres congres) {
		this.congres = congres;
	}

}
