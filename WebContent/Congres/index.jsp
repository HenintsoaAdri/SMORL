<%@ page import="traitement.*,model.*,java.time.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<title>SM ORL - Cotisation par Congr&egrave;</title>
</head>
<body>
	<div class="container">
	<hr>
	<%
try{
	String nomCongres = "";
	String dateCongres = YearMonth.now().atEndOfMonth().toString();
	String[] detail = {"","","","","","",""};
	String[] montant = {"","","","","","",""};
	try{
		if(request.getParameter("add")!=null){
			nomCongres = request.getParameter("nomCongres");
			dateCongres = request.getParameter("dateCongres");
			detail = request.getParameterValues("detail");
			montant = request.getParameterValues("montant");
			
			TraitementCongres.insertionCongres(nomCongres, dateCongres, TraitementCongres.createDetailCongres(detail,montant)); %>
			
	  <div class="row">
		<div class="alert alert-success">
  		  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		  <strong>Succ&egrave;s!</strong> Congr&egrave; ins&eacute;r&eacute;.
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
	  <hr>
	  <div class="row">
	  	<div class="col-sm-12">
	  	<h2>Cotisations par Congr&egrave;s</h2>
	  	<button class="btn btn-success" data-toggle="modal" data-target="#nouveau"><span class="glyphicon glyphicon-plus-sign"></span> Ajouter un nouveau congr&egrave;s</button>
		  <div class="row">
			<table class="table table-hover table-striped col-sm-12">
			 <thead>
			   <tr>
			     <th>Nom du Congr&egrave;s</th>
			     <th>Date</th>
			     <th></th>
			   </tr>
			 </thead>
			 <tbody>
			 <% for(Congres c : TraitementCongres.getListCongres()){ %>
			   <tr>
			     <td><% out.print(c.getNom()); %></td>
			     <td><% out.print(c.getDateString()); %></td>
			     <td><a class="btn btn-danger" href="details.jsp?congres=<% out.print(c.getId()); %>">Voir <span class="glyphicon glyphicon-chevron-right"></span></a></td>
			   </tr>
			 <% } %>
			 </tbody>
			</table>
		  </div>
		</div>
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
		    <label class="control-label col-sm-3" for="nomCongres">Nom du Congr&egrave;s :</label>
		    <div class="col-sm-9">
		      <input type="text" class="form-control" name="nomCongres" value="<% out.print(nomCongres); %>">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="dateCongres">Date du Congr&egrave;s :</label>
		    <div class="col-sm-9"> 
		      <input type="date" class="form-control" name="dateCongres" value="<% out.print(dateCongres); %>">
		    </div>
		  </div>
		  <% for(int i=0 ; i<detail.length ; i++){ %>
		  <div class="form-group">
		    <label class="control-label col-sm-3" for="detail">D&eacute;signation :</label>
		    <div class="col-sm-3"> 
		      <input type="text" class="form-control" name="detail" value="<% out.print(detail[i]); %>">
		    </div>
		    <label class="control-label col-sm-3" for="montant">Montant :</label>
		    <div class="col-sm-3"> 
		      <input type="number" class="form-control" name="montant" min="0" value="<% out.print(montant[i]); %>">
		    </div>
		  </div>
		  <% } %>
		  <div class="form-group"> 
		    <div class="col-sm-offset-3 col-sm-9">
		      <button type="submit" class="btn btn-success" name="add">Ajouter</button>
		    </div>
		  </div>
		</form>
      </div>
    </div>

  </div>
<%	} catch(Exception e){ %>
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