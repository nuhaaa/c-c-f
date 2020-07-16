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
<title>Statuts</title>
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
                        <a href="espaceUtilisateur.jsp"><button type="button" class="btn btn-primary btn-custom waves-effect waves-light m-b-5" style="width:250px" >Gestion Des Familles</button></a>
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
                        <a href="gestionDossier.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:250px">Nouveau Dossier Médical</button></a>
                        <a href="examens.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:250px">Examens Medicales</button></a>
                        <a href="consultation.jsp"><button type="button" class="btn btn-warning btn-rounded waves-effect waves-light m-b-5" style="width:250px">Consultation</button></a>
						</br> </br>
						<button type="button" class="btn btn-purple btn-rounded waves-effect waves-light m-b-5" style="width:770px; height:150px">Dossier Médical</button>
                    </div>                       
                </div>
				<div class="col-md-8 col-md-offset-2">
					<div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Recherche par Famille</h3></div>
                                    <div class="panel-body ">
                                        <form class="form-horizontal" role="form" action="rechercheDossier.chu" method="post">
                                            <div class="form-group">
														<label class="col-sm-4 form-control-label">Famille</label> 
														<div class="col-sm-9">
                                                 			 <input type="text" name="famille" placeholder="alami" class="form-control" id="nomFamille" style="width:250px">
                                                		</div>
													</div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Rechercher</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
									<div class="panel-heading">
                                        <h3 class="panel-title">Liste des patients</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-8 col-sm-8 col-xs-8">
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>Prenom</th>
                                                            <th>Nom</th>
                                                            <th>Date de creation du dossier medical</th>
                                                        </tr>
                                                         
                                        		<%
                                        			BeanDossier beanDossier=(BeanDossier)session.getAttribute("beanDossier");
                                        			if(beanDossier!=null){
                                        				if(!beanDossier.getDossiers().isEmpty()){
                                        					for(DossierMedicale d : beanDossier.getDossiers()){
                                        						
                                        			
                                        		%>
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td><a href="examen.chu?id=<%=d.getId()%>"><%=d.getPatient().getPrenom() %></a></td>
                                                     	<td><a href="examen.chu?id=<%=d.getId()%>"><%=d.getPatient().getNom() %></a></td> 
                                                     	<td><a href="examen.chu?id=<%=d.getId()%>"><%=d.getDateCreation() %></a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		}
                                        				}
                                        			} %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
									
                                </div> <!-- panel -->				
               </div> <!-- col -->
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