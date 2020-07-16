<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/bootstrap-theme.css"/>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"/>
<script src="js/jquery.min.js">  </script>
<script src="js/bootstrap.js">  </script>
<title>Ajouter un Rendez-vous</title>
</head>
<body>
	<div class="container">
		<div    class="row" >
			<div  class="col-md-1 col-md-offset-1 col-lg-1 col-lg-offset-1 col-xs-1 col-xs-offset-1" id="logo">
          		<img  class="img"  src="images\logo.png " width="" height="" alt="logo"/>
          	</div>
     		<div class="col-lg-12 ">
				</br></br>
            </div>
			<div class="row">
				
                <div class="pull-left col-lg-1">
                                
                    <div class="panel-body">
                        <a href="espaceInfirmier.jsp"><button type="button" class="btn btn-primary btn-custom waves-effect waves-light m-b-5" style="width:250px" >Gestion Des Familles</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
                        <a href="gestionDossier.jsp"><button type="button" class="btn btn-success btn-custom waves-effect waves-light m-b-5" style="width:250px">Gestion Des Dossiers Medicales</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
                        <a href="ajoutRendezVous.jsp"><button type="button" class="btn btn-info btn-custom waves-effect waves-light m-b-5" style="width:250px" >Gestion Des Rendez-Vous</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
						<a><button type="button" class="btn btn-purple btn-custom waves-effect waves-light m-b-5" style="width:250px" >Deconnexion</button></a>
                    </div>
                                
                </div>
				
                <div class="col-lg-9 col-lg-offset-2">
                    <div class="panel-body">
                        <a href="formRendezVous.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:200px">Nouveau Rendez-Vous</button></a>
                        <a href="suppRendezVous.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:200px">Supprimer Rendez-vous</button></a>
                        <a href="modRendeVous.jsp"><button type="button" class="btn btn-warning btn-rounded waves-effect waves-light m-b-5" style="width:200px">Modifier Rendez-vous</button></a>
                        <a href="listeRendezVous.jsp"><button type="button" class="btn btn-primary btn-rounded waves-effect waves-light m-b-5" style="width:200px">Lister tous les rendez-vous</button></a>
						
                    </div>
                                
                </div>
				
				<div class="col-md-6 col-md-offset-2">
                                <div class="panel panel-default">
									<form  role="form" action="modifierRendezVous.chu" method="post">
										<div class="panel-heading"><h3 class="panel-title">Rendez-Vous</h3></div>
										<div class="panel-body">
										<%
										String id_Dossier = (String)session.getAttribute("Dossier");
										int  idDossier = Integer.parseInt(id_Dossier);
										DossierDAO dosDAO = new DossierDAO();
										DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
												
												RendezVous rendez = (RendezVous)session.getAttribute("RendezVous");
											
											%>
		                                    <div class="form-group">
		                                         <input type="hidden" class="form-control" name="dossier" value="<%=dos.getId()%>"  style="width:250px">
		                                    </div>
		                                    <div class="form-group">
		                                         <input type="hidden" class="form-control" name="idRendez" value="<%=rendez.getId()%>"  style="width:250px">
		                                    </div>
                                    <div class="form-group">
                                         <input type="hidden" class="form-control" name="patient" value="<%=dos.getPatient().getId()%>"  style="width:250px">
                                    </div>
										
											<div class="form-group">
                                                <label for="objet">Objet du rendez-vous</label>
                                                <input type="text" class="form-control" name="objet" id="objet" placeholder="<%=rendez.getObjet()%>" style="width:250px" required>
                                            </div>
											</br>
											<div class="form-group">
												<label  for="dateRendez">Date de rendez-vous</label>
												<input type="text" name="dateRendez" placeholder="<%=rendez.getDateRendezVous()%>" class="form-control" id="dateRendez" style="width:250px" required>
											</div>
											</br>
											<div class="form-group">
												<label  for="dateRendez">Heure de rendez-vous</label>
												<input type="time" name="heure"  class="form-control" id="heure" style="width:250px" placeholder="<%=rendez.getHeureRendez()%>" required>
											</div>
                                            </br></br>
											<div class="form-group">
                                                <label for="prenom">Notes</label>
                                                <textarea rows="10" cols="20" name="note"  placeholder="<%=rendez.getNote()%>"> </textarea>
                                            </div>
											</br>
                                            <button type="submit" class="btn btn-purple waves-effect waves-light">Modifier</button>
                                    
										</div><!-- panel-body -->
									</form>
                                </div> <!-- panel -->
                            </div> <!-- col-->
				
            </div> 
        </div>
    </div>	
</body>
<footer>
	<div class="row">
		<div class="col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xs-4 col-xs-offset-4" id="foot">
      		<p>Copyright &copy; Registe@CRF </p> 
      	</div>
	</div>        
</footer>
</html>