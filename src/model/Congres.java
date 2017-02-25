package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Vector;

public class Congres {
	int id;
	String nom;
	LocalDate date;
	Vector<DetailCongres> detailCongres;

	public Congres() {}

	public Congres(int id, String nom, LocalDate date) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setDate(date);
	}

	public Congres(int id, String nom, LocalDate date, Vector<DetailCongres> detailCongres) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setDate(date);
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
	
	public LocalDate getDate() {
		return date;
	}
	public String getDateString() {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(getDate());
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Vector<DetailCongres> getDetailCongres() {
		return detailCongres;
	}

	public void setDetailCongres(Vector<DetailCongres> detailCongres) {
		this.detailCongres = detailCongres;
	}

}