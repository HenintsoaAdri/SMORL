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
			if(request.getParameter("congres") == null) throw new Exception("Il vous faut choisir un Congr&egraves"); 
			Congres c = TraitementCongres.getCongresById(request.getParameter("congres")); %>
	  <h2>Congr&egrave;s <% out.print(c.getNom()); %>, <% out.print(c.getDateString()); %></h2>
	  <% for(DetailCongres dc : c.getDetailCongres()){ %>
	  <div class="row well">
	  	<div class="col-sm-12 form-horizontal">
	  	<h3><% out.print(dc.getDesignation()); %></h3>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2">Montant objectif :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(dc.getMontantObjectifString()); %></p>
		    </div>
		    <label class="control-label col-sm-2" for="email">Reste &agrave; atteindre :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(dc.getResteString()); %></p>
		    </div>
		  </div>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2" for="email">Nombre de contribuable :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(dc.getContribuable()); %></p>
		    </div>
		    <label class="control-label col-sm-2" for="email">Montant r&eacute;colt&eacute; :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(dc.getMontantPayeString()); %></p>
		    </div>
		  </div>
	  	</div>
	  	<div class="col-sm-12">
	  	  <div class="row">
	  	  	<div class="col-sm-6">
	  	<hr>
	  	  		<h4>Montant pay&eacute; par membre</h4>
	  	  	</div>
	  	  </div>
	  	  <div class="row">
		  	  <table class="table table-striped table-hover col-sm-12">
			    <thead>
			      <tr>
			        <th>Membre</th>
			        <th>Montant pay&eacute;</th>
			      </tr>
			    </thead>
			    <tbody>
			    <% for(PaiementCongres p : TraitementCongres.getPaiementDetailCongre(dc)){%>
			      <tr>
			        <td><% out.print(p.getMembre()); %></td>
			        <td><% out.print(p.getMontantString()); %></td>
			      </tr>
			    <% } %>
			    </tbody>
			  </table>
	  	  </div>
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
	</div>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>