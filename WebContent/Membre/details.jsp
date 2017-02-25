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
		    <label class="control-label col-sm-2">Date de naissance :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getDateNaissanceString()); %></p>
		    </div>
		    <label class="control-label col-sm-2">Sexe :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getSexeString()); %></p>
		    </div>
		  </div>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2">Email :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getEmail()); %></p>
		    </div>
		    <label class="control-label col-sm-2">T&eacute;l&eacute;phone :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getTelephone()); %></p>
		    </div>
		  </div>
	  	  <div class="form-group">
		    <label class="control-label col-sm-2">Profession :</label>
		    <div class="col-sm-4">
		      <p class="form-control-static"><% out.print(m.getProfession()); %></p>
		    </div>
		    <label class="control-label col-sm-2">Capacit&eacute; :</label>
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
	  	  		<button class="btn btn-primary" data-toggle="modal" data-target="#annuelle"><span class="glyphicon glyphicon-plus-sign"></span> Payer une cotisation</button>
	  	  	</div>
	  	  </div>
	  	  
			<%
			String datePaiement = LocalDate.now().toString();
			String anneeCotisation = Year.now().toString();
			String montant = "";
			try{
				if(request.getParameter("add")!=null){
					datePaiement = request.getParameter("datePaiement");
					anneeCotisation = request.getParameter("anneeCotisation");
					montant = request.getParameter("montant");
					TraitementCotisation.insertionPaiement(datePaiement, montant, m, anneeCotisation); %>
					
			  <div class="row">
				<div class="alert alert-success">
		  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				  <strong>Succ&egrave;s!</strong> Cotisation ins&eacute;r&eacute;.
				</div>
			  </div>
			<%	}
				else if(request.getParameter("deleteAnnuelle") != null) TraitementCotisation.deletePaiement(request.getParameter("deleteAnnuelle"));
			} catch(Exception e){ %>
			  <div class="row">
				<div class="alert alert-warning">
		  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				  <strong>Probl&egrave;me!</strong> <% out.print(e.getMessage()); %>.
				</div>
			  </div>
		 <% } %>
	  	  <% for(Cotisation c : TraitementCotisation.getListCotisation()){ %>
	  	  <hr>
	  	  <div class="row">
	  	  	  <div class="col-sm-12 form-horizontal">
		  	  	<div class="form-group">
				    <label class="control-label col-sm-2" for="email">Ann&eacute;e :</label>
				    <div class="col-sm-4">
				      <p class="form-control-static"><% out.print(c.getAnneeCotisation()); %></p>
				    </div>
				    <label class="control-label col-sm-2" for="email">Montant r&eacute;colt&eacute; :</label>
				    <div class="col-sm-4">
				      <p class="form-control-static"><% out.print(c.getMontantPayeString()); %></p>
				    </div>
				</div>
		  	  	<div class="form-group">
				    <label class="control-label col-sm-2" for="email">Montant pay&eacute; par le membre :</label>
				    <div class="col-sm-4">
				      <p class="form-control-static"><% out.print(TraitementMembre.getSommePayeCotisation(c, m)); %></p>
				    </div>
				    <label class="control-label col-sm-2" for="email">Reste &agrave; atteindre :</label>
				    <div class="col-sm-4">
				      <p class="form-control-static"><% out.print(c.getResteString()); %></p>
				    </div>
				</div>
			  </div>
		  	  <table class="table table-striped table-hover col-sm-12">
			    <thead>
			      <tr>
			        <th>Montant pay&eacute;</th>
			        <th>Date de paiement</th>
			        <th></th>
			      </tr>
			    </thead>
			    <tbody>
			    <% for(PaiementCotisation p : TraitementMembre.getDetailPaiementCotisation(c,m)){ %>
			    <form method="post">
			      <tr>
			        <td><% out.print(p.getMontantString()); %></td>
			        <td><% out.print(p.getDatePaiementString()); %></td>
			        <td><button class="btn btn-warning" type="submit" name="deleteAnnuelle" value="<% out.print(p.getId());%>"><span class="glyphicon glyphicon-trash"></span> Supprimer ce paiement</button></td>
			      </tr>
			    </form>
			    <% } %>
			    </tbody>
			  </table>
	  	  </div>
	  	  <% } %>
		</div>  
	  </div>
	  <div class="row well">
	  	<div class="col-s12">
	  	  	<h3>Cotisation de congr&eacute;s</h3>
	  	  <% for(Congres congres : TraitementCongres.getListCongres()){   
	  		
			String montantCongres = "";
			try{
				if(request.getParameter("add") == "annuelle"){
					datePaiement = request.getParameter("datePaiement");
					montantCongres = request.getParameter("montantCongres");
					String detailCongres = request.getParameter("detailCongres");
					TraitementCongres.insertionPaiement(datePaiement, montantCongres, m, detailCongres); %>
					
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
	  	  	<div class="col-sm-12">
		  	  	<div class="col-sm-6 text-right">
		  	  		<h4>Congr&egrave;s <% out.print(congres.getNom()); %></h4>
		  	  	</div>
		  	  	<div class="col-sm-6 text-right">
		  	  		<button class="btn btn-primary" data-toggle="modal" data-target="#congres<% out.print(congres.getId()); %>"><span class="glyphicon glyphicon-plus-sign"></span> Payer une cotisation</button>
		  	  	</div>
		  	   <%
		  	   for (DetailCongres dc : congres.getDetailCongres()){%>
		  	    <table class="table table-striped table-hover">
			   	  <thead>
			   	  	<tr>
			   	  	  <th>D&eacute;signation</th>
			      	  <th>Montant pay&eacute;</th>
			       	  <th>Date de paiement</th>
			      	  <th></th>
			        </tr>
			   	  </thead>
			      <tbody>
			    <% for(PaiementCongres p : TraitementMembre.getDetailPaiementCongres(dc,m)){ %>
			    <form method="post">
			      <tr>
			      	<td><% out.print(p.getCongres().getDesignation()); %></td>
			        <td><% out.print(p.getMontantString()); %></td>
			        <td><% out.print(p.getDatePaiementString()); %></td>
			        <td><button class="btn btn-warning" type="submit" name="deleteAnnuelle" value="<% out.print(p.getId());%>"><span class="glyphicon glyphicon-trash"></span> Supprimer ce paiement</button></td>
			      </tr>
			    </form>
			    <% } %>
			      <tr colspan="4">
			      	<td>Reste &agrave; payer : <% out.print(dc.getResteString()); %></td>
			      </tr>
			      </tbody>
			    </table>
			    <% } %>
		  	 </div>
			  
<div id="congres<% out.print(congres.getId()); %>" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Payer une cotisation de congr&egrave;s</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="detailcongres">Cotisation :</label>
		    <div class="col-sm-9">
			  <select class="form-control" name="detailcongres">
			  	<% for(DetailCongres dc : congres.getDetailCongres()){ %>
			    <option value="<% out.print(dc.getId()); %>"><% out.print(dc.getDesignation()); %></option>
			    <% } %>
			  </select>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="montantCongres">Montant :</label>
		    <div class="col-sm-9"> 
		      <input type="number" class="form-control" name="montantCongres" min="1">
		    </div>
		  </div>
		  <div class="form-group"> 
		    <div class="col-sm-offset-3 col-sm-9">
		      <button type="submit" class="btn btn-success" name="add" value="congres1">Ajouter</button>
		    </div>
		  </div>
		</form>
      </div>
    </div>
  </div>
</div>
	  	  </div>
	  	 <% } %>
		</div>  
	  </div>
<div id="annuelle" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Payer une cotisation annuelle</h4>
      </div>
      <div class="modal-body">
      	<form class="form-horizontal" method="post">
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="datePaiement">Date de paiement :</label>
		    <div class="col-sm-9">
		      <input type="date" class="form-control" name="datePaiement" value="<% out.print(datePaiement); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="anneeCotisation">Ann&eacute;e :</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" name="anneeCotisation" value="<% out.print(anneeCotisation); %>">
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
		      <button type="submit" class="btn btn-success" name="add" value="annuelle">Ajouter</button>
		    </div>
		  </div>
		</form>
      </div>
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