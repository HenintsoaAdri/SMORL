<%@ page import="traitement.*,model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<title>SMORL - Membres</title>
</head>
<body>
	<div class="container">
	<hr>
	<%
	String nom = "";
	String prenom = "";
	String dateNaissance = "";
	String sexe = "";
	String email = "";
	String telephone = "";
	String adresse = "";
	String profession = "";
	String capacite = "";
	try{
		if(request.getParameter("add")!=null){
			nom = request.getParameter("nom");
			prenom = request.getParameter("prenom");
			dateNaissance = request.getParameter("dateNaissance");
			sexe = request.getParameter("sexe");
			email = request.getParameter("email");
			telephone = request.getParameter("telephone");
			adresse = request.getParameter("adresse");
			profession = request.getParameter("profession");
			capacite = request.getParameter("capacite");
			TraitementMembre.insertionMembre(nom, prenom, dateNaissance, sexe, email, telephone, adresse, profession, capacite); %>
			
	  <div class="row">
		<div class="alert alert-success">
  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		  <strong>Succ&egrave;s!</strong> Membre ins&eacute;r&eacute;.
		</div>
	  </div>
	<%	}
	} catch(Exception e){ %>
	  <div class="row">
		<div class="alert alert-warning">
  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		  <strong>Probl&egrave;me!</strong> <% out.print(e.getMessage()); %>.
		</div>
	  </div>
 <% } %>
	  <div class="row">
		<a href="../"  class="btn btn-default col-sm-2 col-sm-offset-5"><span class="glyphicon glyphicon-home"></span></a>
	  </div>
	  <div class="row">
	  	<h2>Liste des membres</h2>
	  	<button class="btn btn-success" data-toggle="modal" data-target="#nouveau"><span class="glyphicon glyphicon-plus-sign"></span> Ajouter un nouveau membre</button>
		<table class="table table-hover table-striped">
		 <thead>
		   <tr>
		     <th>Nom</th>
		     <th>Pr&eacute;nom</th>
		     <th>Date de naissance</th>
		     <th>Sexe</th>
		     <th>Contact(s)</th>
		     <th>Informations</th>
		     <th></th>
		   </tr>
		 </thead>
		 <tbody>
		 <% for(Membre m : TraitementMembre.getListMembre()){ %>
		   <tr>
		     <td><% out.print(m.getNom()); %></td>
		     <td><% out.print(m.getPrenom()); %></td>
		     <td><% out.print(m.getDateNaissanceString()); %></td>
		     <td><% out.print(m.getSexeString()); %></td>
		     <td><% out.print(m.getContact()); %></td>
		     <td><% out.print(m.getInformation()); %></td>
		     <td><a class="btn btn-danger" href="details.jsp?membre=<% out.print(m.getId()); %>">Voir <span class="glyphicon glyphicon-chevron-right"></span></a></td>
		   </tr>
		 <% } %>
		 </tbody>
		</table>
	  </div>
	</div>
<div id="nouveau" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Ajouter un nouveau membre</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="nom">Nom :</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" name="nom" value="<% out.print(nom); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="prenom">Pr&eacute;nom :</label>
		    <div class="col-sm-9"> 
		      <input type="text" class="form-control" name="prenom" value="<% out.print(prenom); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="dateNaissance">Date de naissance:</label>
		    <div class="col-sm-9"> 
		      <input type="date" class="form-control" name="dateNaissance" placeholder="AAAA-MM-JJ" value="<% out.print(dateNaissance); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="dateNaissance">Sexe :</label>
		    <div class="col-sm-9">
			    <label class="radio-inline">
			      <input type="radio" name="sexe" value="Homme" <% if(sexe == "Homme")out.print("checked"); %>>Homme
			    </label>
			    <label class="radio-inline">
			      <input type="radio" name="sexe" value="Femme" <% if(sexe == "Femme")out.print("checked"); %>>Femme
			    </label>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="telephone">T&eacute;l&eacute;phone :</label>
		    <div class="col-sm-9"> 
		      <input type="tel" class="form-control" name="telephone" value="<% out.print(telephone); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="email">Email :</label>
		    <div class="col-sm-9"> 
		      <input type="email" class="form-control" name="email" value="<% out.print(email); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="adresse">Adresse :</label>
		    <div class="col-sm-9"> 
		      <input type="text" class="form-control" name="adresse" value="<% out.print(adresse); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="profession">Profession :</label>
		    <div class="col-sm-9"> 
		      <input type="text" class="form-control" name="profession" value="<% out.print(profession); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="capacite">Capacit&eacute; :</label>
		    <div class="col-sm-9"> 
		      <input type="text" class="form-control" name="capacite" value="<% out.print(capacite); %>">
		    </div>
		  </div>
		  <div class="form-group"> 
		    <div class="col-sm-offset-3 col-sm-9">
		      <button type="submit" class="btn btn-success" name="add">Ajouter</button>
		    </div>
		  </div>
		</form>
      </div>
    </div>

  </div>
</div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>