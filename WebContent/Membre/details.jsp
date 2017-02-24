<%@ page import="traitement.*, model.*, java.time.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<title>SMORL</title>
</head>
<body>
	<div class="container">
	<hr>
	  <div class="row">
		<a href="../"  class="btn btn-default col-sm-2 col-sm-offset-5"><span class="glyphicon glyphicon-home"></span></a>
	  </div>
	  <hr>
<%  	try{
			if(request.getParameter("membre") == null) throw new Exception("Il vous faut choisir un membre"); 
			Membre m = TraitementMembre.get(request.getParameter("membre")); %>
	  <div class="row well">
	  	<div class="col-sm-12 form-horizontal">
	  	<h3>Membre</h3>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2" for="email">Nom :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getNom()); %></p>
		    </div>
		    <label class="control-label col-sm-2" for="email">Pr&eacute;nom :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getPrenom()); %></p>
		    </div>
		  </div>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2" for="email">Date de naissance :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getDateNaissanceString()); %></p>
		    </div>
		    <label class="control-label col-sm-2" for="email">Sexe :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getSexeString()); %></p>
		    </div>
		  </div>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2" for="email">Email :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getEmail()); %></p>
		    </div>
		    <label class="control-label col-sm-2" for="email">T&eacute;l&eacute;phone :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getTelephone()); %></p>
		    </div>
		  </div>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2" for="email">Profession :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getProfession()); %></p>
		    </div>
		    <label class="control-label col-sm-2" for="email">Capacit&eacute; :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getCapacite()); %></p>
		    </div>
		  </div>
	  	</div>
	  </div>
	  <div class="row well">
	  	<div class="col-sm-12">
	  	  <div class="row">
	  	  	<div class="col-sm-6">
	  	  		<h3>Cotisation annuelle</h3>
	  	  	</div>
	  	  	<div class="col-sm-6 text-right">
	  	  		<button class="btn btn-danger" data-toggle="modal" data-target="#annuelle"><span class="glyphicon glyphicon-plus-sign"></span> Ajouter une cotisation</button>
	  	  	</div>
	  	  </div>
	  	  <table class="table table-striped table-hover">
		    <thead>
		      <tr>
		        <th>Ann&eacute;e</th>
		        <th>Montant pay&eacute;</th>
		        <th>Date de paiement</th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>John</td>
		        <td>Doe</td>
		        <td>john@example.com</td>
		      </tr>
		    </tbody>
		  </table>
		</div>  
	  </div>
	  <div class="row well">
	  	<div class="col-s12">
	  	  	<h3>Cotisation de congr&eacute;s</h3>
	  	  <div class="row">
	  	  	<div class="col-sm-6 text-right">
	  	  		<h4>Congr&eacute de Mahajanga 2012</h4>
	  	  	</div>
	  	  	<div class="col-sm-6 text-right">
	  	  		<button class="btn btn-danger" data-toggle="modal" data-target="#congres1"><span class="glyphicon glyphicon-plus-sign"></span> Ajouter une cotisation</button>
	  	  	</div>
		  	  <table class="table table-striped table-hover">
			    <thead>
			      <tr>
			        <th>D&eacute;signation</th>
			        <th>Montant pay&eacute;</th>
			        <th>Date de paiement</th>
			      </tr>
			    </thead>
			    <tbody>
			      <tr>
			        <td>John</td>
			        <td>Doe</td>
			        <td>john@example.com</td>
			      </tr>
			    </tbody>
			  </table>
			  
<div id="congres1" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Ajouter une cotisation de congré</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="annee">Ann&eacute;e :</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" name="annee" value="<% out.print(Year.now()); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="montant">Montant :</label>
		    <div class="col-sm-9"> 
		      <input type="number" class="form-control" name="montant" min="0">
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
	  	  </div>
		</div>  
	  </div>
<div id="annuelle" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Ajouter une cotisation annuelle</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="annee">Ann&eacute;e :</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" name="annee" value="<% out.print(Year.now()); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="montant">Montant :</label>
		    <div class="col-sm-9"> 
		      <input type="number" class="form-control" name="montant" min="0">
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
<% } catch(Exception e){ %>
	  <div class="row">
		<div class="alert alert-warning">
  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		  <strong>Probl&egrave;me!</strong> <% out.print(e.getMessage()); %>.
		</div>
	  </div>
<% } %>
	</div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>