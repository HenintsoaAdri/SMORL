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
			if(request.getParameter("cotisation") == null) throw new Exception("Il vous faut choisir une cotisation"); 
			Cotisation c = TraitementCotisation.getCotisationByYear(request.getParameter("cotisation")); %>
	  <div class="row well">
	  	<div class="col-sm-12 form-horizontal">
	  	<h3>Cotisation annuelle</h3>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2">Ann&eacute;e :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(c.getAnneeCotisation()); %></p>
		    </div>
		    <label class="control-label col-sm-2">Montant &agrave; payer :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(c.getMontantString()); %></p>
		    </div>
		  </div>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2" for="email">Nombre de contribuable :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(c.getContribuable()); %> sur <% out.print(TraitementMembre.getListMembre().size()); %> </p>
		    </div>
		    <label class="control-label col-sm-2" for="email">Montant r&eacute;colt&eacute; :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(c.getMontantPayeString()); %></p>
		    </div>
		  </div>
	  	</div>
	  </div>
	  <div class="row well">
	  	<div class="col-sm-12">
	  	  <div class="row">
	  	  	<div class="col-sm-6">
	  	  		<h3>Montant pay&eacute; par membre</h3>
	  	  	</div>
	  	  </div>
	  	  <div class="row">
		  	  <table class="table table-striped table-hover col-sm-12">
			    <thead>
			      <tr>
			        <th>Membre</th>
			        <th>Montant pay&eacute;</th>
			        <th>Reste &agrave; payer</th>
			      </tr>
			    </thead>
			    <tbody>
			    <% for(PaiementCotisation p : c.getDetailPaiement()){%>
			      <tr>
			        <td><% out.print(p.getMembre()); %></td>
			        <td><% out.print(p.getMontantString()); %></td>
			        <td><% out.print(p.getResteString()); %></td>
			      </tr>
			    <% } %>
			    </tbody>
			  </table>
	  	  </div>
		</div>  
	  </div>
	  
	  <div class="row well">
	  	<div class="col-sm-12">
	  	  <div class="row">
	  	  	<div class="col-sm-6">
	  	  		<h3>Membre n'ayant pas encore pay&eacute;</h3>
	  	  	</div>
	  	  </div>
	  	  <div class="row">
		  	  <table class="table table-striped table-hover col-sm-12">
			    <thead>
			      <tr>
			        <th>Membre</th>
			      </tr>
			    </thead>
			    <tbody>
			    <% for(Membre mem : TraitementMembre.getListMembreNonContribuable()){%>
			      <tr>
			        <td><% out.print(mem); %></td>
			      </tr>
			    <% } %>
			    </tbody>
			  </table>
	  	  </div>
		</div>  
	  </div>
<% } catch(Exception e){ e.printStackTrace(); %>
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