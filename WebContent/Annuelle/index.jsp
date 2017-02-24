<%@ page import="traitement.*,model.*,java.time.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<title>SM ORL - Cotisation Annuelle</title>
</head>
<body>
	<div class="container">
	<hr>
	<%
	String anneeCotisation = Year.now().toString();
	String montant = "";
	try{
		if(request.getParameter("add")!=null){
			anneeCotisation = request.getParameter("anneeCotisation");
			montant = request.getParameter("montant");
			TraitementCotisation.insertionCotisation(anneeCotisation, montant); %>
			
	  <div class="row">
		<div class="alert alert-success">
  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		  <strong>Succ&egrave;s!</strong> Cotisation ins&eacute;r&eacute;.
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
	  	<h2>Cotisations annuelles</h2>
	  	<button class="btn btn-success" data-toggle="modal" data-target="#nouveau"><span class="glyphicon glyphicon-plus-sign"></span> Ajouter une nouvelle cotisation</button>
		<table class="table table-hover table-striped">
		 <thead>
		   <tr>
		     <th>Ann&eacute;e</th>
		     <th>Montant objectif</th>
		     <th>Montant r&eacute;colt&eacute;</th>
		     <th>Nombre de contribuable</th>
		     <th></th>
		   </tr>
		 </thead>
		 <tbody>
		 <% for(Cotisation c : TraitementCotisation.getListCotisation()){ %>
		   <tr>
		     <td><% out.print(c.getAnneeCotisation()); %></td>
		     <td><% out.print(c.getMontantObjectifString()); %></td>
		     <td><% out.print(c.getMontantPayeString()); %></td>
		     <td><% out.print(c.getContribuable()); %></td>
		     <td><a class="btn btn-danger" href="details.jsp?cotisation=<% out.print(c.getAnneeCotisation()); %>">Voir <span class="glyphicon glyphicon-chevron-right"></span></a></td>
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
        <h4 class="modal-title">Ajouter une nouvelle cotisation</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="anneeCotisation">Ann&eacute;e :</label>
		    <div class="col-sm-9">
		      <input type="numeric" class="form-control" name="anneeCotisation" value="<% out.print(anneeCotisation); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="montant">Montant Objectif :</label>
		    <div class="col-sm-9"> 
		      <input type="text" class="form-control" name="montant" value="<% out.print(montant); %>">
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