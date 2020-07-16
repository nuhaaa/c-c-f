<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html>
<html lang="fr">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<title>Ajouter Un individu</title>
	

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	
	


    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


</head>

<body>
	 <%
       String id_Famille = (String)session.getAttribute("idFamille");
	   int  idFamille = Integer.parseInt(id_Famille);
	   FamilleDAO famDAO = new FamilleDAO();
	   Famille famille= famDAO.trouverFamilleById(idFamille);
      %>
    <div id="wrapper">
	<%@include file="enteteUt.jsp" %>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                	<h3 class="page-header" style="text-align:center">
                            <img class="img"  src="images\logo.png " width="" height="" alt="logo"/><small></small>
                   </h3>
                  <h3 class="page-header">
                      Ajouter un individu dans la famille  <%=famille.getNomFamille() %><small></small>
                   </h3>
                <!-- /.row -->
				<div class="container">
						<form class="form-horizontal" role="form"  action="ajoutIndividu.chu" method="post" enctype='multipart/form-data'>
						<div class="col-md-11  ">
						<div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">Nouvel individu</h3></div>
							<div class="panel-body">
							
						    <div class="col-md-6 ">
                            
							 
							  	<div class="form-group">
		                                         <input type="hidden" class="form-control" name="famille" value="<%=idFamille%>"  style="width:250px">
		                        </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="prenom">Prénom:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name ="prenom" id="prenom" placeholder="Entrer le prenom" style="width:250px" required >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="nom">Nom:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name="nom" id="nom" placeholder="Entrer le nom" style="width:250px" required  >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Naissance:</label>
							      <div class="col-sm-10">
							        <input type="text" name="dateNais" placeholder="jj/mm/aaaa" class="form-control" id="dateNais" style="width:250px" required >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Sexe:</label>
							      <div class="col-sm-10">
                                                    <select class="form-control" name="sexe" style="width:250px" required >
                                                        <option>Masculin</option>
                                                        <option>Féminin</option> 
                                                    </select>
                                  </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Image:</label>
							      <div class="col-sm-10">
							        <input type="file" class="form-control" name="file" id="file"  style="width:250px" required >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Origine:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name="origine" id="origine" placeholder="origine" style="width:250px" required >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Ville:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name="ville" id="ville" placeholder="La ville"  style="width:250px" required  >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Adresse:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name="adresse" id="adresse" placeholder="Adresse" style="width:250px"required >
							      </div>
							    </div>
								<div class="form-group">
							      <label class="control-label col-sm-2" for="">Region:</label>
							      <div class="col-sm-10">
							         <input type="text" class="form-control" name="region" id="region" placeholder="La region" style="width:250px" required >
							      </div>
							    </div>
						</div>
						 <div class="col-md-6 col-md-offset-0">
                            
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Fixe:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name="tel1" id="tel1" placeholder="0500000000" style="width:250px" required >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Mobile:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name="tel2" id="tel2" placeholder="0600000000" style="width:250px" required >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Edu:</label>
							      <div class="col-sm-10">
                                                    <select class="form-control" name="education" style="width:250px"  required >
                                                        <option><%=Education.Aucune %></option>
                                                        <option><%=Education.Primaire %></option> 
                                                        <option><%=Education.Secondaire %></option>
                                                        <option><%=Education.Universitaire %></option>
                                                        <option><%=Education.Informelle %></option>
                                                        <option><%=Education.autre %></option>
                                                    </select>
                                                </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Niveau:</label>
							      <div class="col-sm-10">
                                                    <select class="form-control" name="niveau" style="width:250px" required >
                                                        <option><%=NiveauSocial.BAS %></option>
                                                        <option><%=NiveauSocial.MOYEN %></option> 
														<option><%=NiveauSocial.HAUT %></option>
                                                    </select>
                                                </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Couv:</label>
							      <div class="col-sm-10">
                                                    <select class="form-control" name="couverture" style="width:250px" required >
                                                        <option><%=CouvertureMedicale.Aucune %></option>
                                                        <option><%=CouvertureMedicale.Cnops %></option>
                                                        <option><%=CouvertureMedicale.Cnss %></option> 
                                                        <option><%=CouvertureMedicale.Privée %></option>
                                                        <option><%=CouvertureMedicale.Ramed %></option>
                                                        <option><%=CouvertureMedicale.Autre%></option>
                                                    </select>
                                                </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Travail:</label>
							      <div class="col-sm-10">
							        <input type="text" class="form-control" name="occupation" id="occupation" placeholder="Travail" style="width:250px" required  >
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="">Réside:</label>
							      <div class="col-sm-10">
							        <div class="checkbox checkbox-info checkbox-circle col-sm-10 col-sm-offeset-1">
														<input id="urbain" type="checkbox"  name="urbain" >
														<label for="urbain">
															Urbain
														</label>
													</div>
													<div class="checkbox checkbox-primary checkbox-circle col-sm-10 col-sm-offeset-1">
														<input id="rural" type="checkbox" name="rural">
														<label for="rural">
															Rural
														</label>
													</div>
							      </div>
							    </div>
							    <div class="form-group">
							      <div class="col-sm-offset-2 col-sm-10">
							        <button type="submit" class="btn btn-default">Enregistrer</button>
							      </div>
							    </div>
							  
						</div>
						</div>
						
						</div>
						</div>
						</form>
						</div>
				
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    </div>
    </body>
    
	<%@include file="piedUt.jsp" %>
</html>
