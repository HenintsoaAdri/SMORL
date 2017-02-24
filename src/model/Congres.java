package model;

import java.util.Vector;

public class Congres {
	int id;
	String nom;
	Vector<DetailCongres> detailCongres;

	public Congres() {
		// TODO Auto-generated constructor stub
	}

	public Congres(int id, String nom) {
		super();
		this.setId(id);
		this.setNom(nom);
	}

	public Congres(int id, String nom, Vector<DetailCongres> detailCongres) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setDetailCongres(detailCongres);
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

	public Vector<DetailCongres> getDetailCongres() {
		return detailCongres;
	}

	public void setDetailCongres(Vector<DetailCongres> detailCongres) {
		this.detailCongres = detailCongres;
	}

}
