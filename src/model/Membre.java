package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

import utilitaire.StringUtil;

public class Membre {
	int id;
	String nom;
	String prenom;
	LocalDate dateNaissance;
	String sexe;
	String email;
	String telephone;
	String adresse;
	String profession;
	String capacite;

	public Membre() {}

	public Membre(int id, String nom, String prenom, Date dateNaissance, String sexe, String email, String telephone, String adresse,
			String profession, String capacite) throws Exception {
		super();
		this.setId(id);
		this.setNom(prenom);
		this.setPrenom(prenom);
		this.setDateNaissance(dateNaissance);
		this.setSexe(sexe);
		this.setEmail(email);
		this.setTelephone(telephone);
		this.setAdresse(adresse);
		this.setProfession(profession);
		this.setCapacite(capacite);
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

	public void setNom(String nom) throws Exception{
		if(!StringUtil.fullLetter(nom)) throw new Exception("Nom contient des caractères spéciaux");
		else if(nom.isEmpty()) throw new Exception("Veuillez insérer un nom");
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) throws Exception {
		if(!StringUtil.fullLetter(prenom)) throw new Exception("Prénom contient des caractères spéciaux");
		this.prenom = prenom;
	}
	public String getFullName() {
		return getNom()+" "+getPrenom();
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public String getDateNaissanceString(){
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(getDateNaissance());
	}
	public String getFullDateNaissanceString(){
		String ne = "N&eacute;";
		if(isFemme()) ne+="e";
		return ne+" le "+getDateNaissanceString();
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		setDateNaissance(dateNaissance.toLocalDate());
	}
	public void setDateNaissance(String dateNaissance) throws Exception{
		try{
			setDateNaissance(LocalDate.parse(dateNaissance));
		} catch (DateTimeParseException e) {
			try{
				DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive().appendPattern("d MMMM uuuu").toFormatter(Locale.FRANCE);
				setDateNaissance(LocalDate.parse(dateNaissance, format));
			}catch(Exception e1){
				e1.printStackTrace();
				throw new Exception("Format de date non supporté, essayez dans le format AAAA-MM-JJ");				
			}
		}
	}
	public String getSexe() {
		return sexe;
	}
	public String getSexeString(){
		switch (getSexe()) {
		case "M":
			return "Homme";
		default:
			return "Femme";
		}
	}

	public void setSexe(String sexe) {
		if(sexe.startsWith("F")||sexe.startsWith("f")) {
			this.sexe = "F";
		}else{
			this.sexe = "M";
		}
	}
	public boolean isFemme(){
		return getSexe().startsWith("F");
	}
	public boolean isHomme(){
		return getSexe().startsWith("M");
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		if(!StringUtil.isEmail(email))throw new Exception("Adresse email invalide");
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) throws Exception {
		if(!StringUtil.isTelephone(telephone))throw new Exception("Numero de telephone invalide");
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCapacite() {
		return capacite;
	}

	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}

}
