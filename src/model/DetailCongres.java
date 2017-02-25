package model;

import java.util.Vector;

import dao.PaiementCongresDAO;
import utilitaire.StringUtil;

public class DetailCongres {
	int id;
	String designation;
	double montant;
	double montantPaye;
	int contribuable;
	Vector<PaiementCongres> detailPaiement;
	Congres congres;

	public DetailCongres() {}

	public DetailCongres(int id, String designation, double montant) {
		super();
		this.setId(id);
		this.setDesignation(designation);
		this.setMontant(montant);
	}

	public DetailCongres(int id, String designation, double montant, double montantPaye, int contribuable) {
		super();
		this.setId(id);
		this.setDesignation(designation);
		this.setMontant(montant);
		this.setMontantPaye(montantPaye);
		this.setContribuable(contribuable);
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
	public String getMontantString() {
		return StringUtil.moneyToString(getMontant());
	}
	
	public void setMontant(double montantObjectif) {
		this.montant = montantObjectif;
	}

	public double getMontantPaye() {
		return montantPaye;
	}
	public String getMontantPayeString() {
		return StringUtil.moneyToString(getMontantPaye());
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

	public Congres getCongres() {
		return congres;
	}

	public void setCongres(Congres congres) {
		this.congres = congres;
	}
	
	public double getReste(Membre m) throws Exception{
		double sommeParMembre = PaiementCongresDAO.getSommePaye(this.getId(), m.getId());
		if(getMontant() - sommeParMembre < 0){
			return 0;
		}
		return getMontant() - sommeParMembre;
	}
	public String getResteString(Membre m) throws Exception{
		return StringUtil.moneyToString(getReste(m));
	}
	
}