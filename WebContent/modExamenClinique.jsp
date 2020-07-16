<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/bootstrap-theme.css"/>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"/>
<script src="js/jquery.min.js">  </script>
<script src="js/bootstrap.js">  </script>
<title>Gestion des examens Cliniques</title>
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
				<div class="col-lg-12 ">
                    <div class="panel-body">
                        <a href="statut.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Satut</button></a>
                        <a href="ExamenClinique.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:150px">Examen Clinique</button></a>
                        <a href="Endoscopie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Endoscopie</button></a>
                        <a href="AnaPathologie.jsp" ><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:200px">Anatomie Pathologique</button></a>
                        <a href="Imagerie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Imagerie</button></a>
						<a href="Biologie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Biologie</button></a>
						<a href="Genetique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Génétique</button></a>
						<a href="Traitement.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Traitement</button></a>
                    </div>                       
                </div>
				<div class="row">
                        <div class="col-sm-8 col-sm-offset-2">
                        	<%
										String id_Patient = (String)session.getAttribute("idPatient");
										int  idPatient = Integer.parseInt(id_Patient);
										IndividuDAO indDAO = new IndividuDAO();
										Individu patient = indDAO.trouverIndById(idPatient);
										DossierDAO dosDAO = new DossierDAO();
										List<ExamenPostOp> examensPost = new ArrayList<>();
										 examensPost = dosDAO.listerExamenPostOPParPatient(idPatient);
										 List<ExamenPreOp> examensPre = new ArrayList<>();
										 examensPre=dosDAO.listerExamenPreOpParPatient(idPatient);
										 List<ExamenPreOpAnormal> examensAnormal = new ArrayList<>();
										 examensAnormal =dosDAO.listerExamenPreOpAnormalParPatient(idPatient);
									%>
                        	<div class="panel panel-default">
								<div class="panel-heading"><h3 class="panel-title">Examen Clinique Pré Opératoire de <%=patient.getPrenom()%> <%=patient.getNom() %></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(ExamenPreOp e: examensPre){
                                                         		Date date = e.getDateExamen();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Examen clinique</td>
                                                     	<td>du <%=dateFormatee %></td> 
                                                     	<td><a href="modExamenCliniqueNormal.chu?id=<%=e.getId()%>" onclick=""><button type="button" class="btn btn-info btn-custom waves-effect waves-light m-b-5" style="width:250px"  >Modifier</button></a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                        	
                                    <div class="panel-heading"><h3 class="panel-title">Examen Clinique Post Operatoire de <%=patient.getPrenom()%> <%=patient.getNom() %></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(ExamenPostOp e: examensPost){
                                                         		Date date = e.getDateExamen();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Examen clinique</td>
                                                     	<td>du <%=dateFormatee%></td> 
                                                     	<td><a href="modExamenCliniquePost.chu?id=<%=e.getId()%>" onclick=""><button type="button" class="btn btn-info btn-custom waves-effect waves-light m-b-5" style="width:250px"  >Modifier</button></a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                        	</div>
						
                </div>		
               </div> <!-- col -->
				
				
				
        </div>
    </div>
    <script >  </script>	
</body>
<footer>
		<div class="row">
			<div class="col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xs-4 col-xs-offset-4" id="foot">
      			<p>Copyright &copy; Registe@CRF </p> 
      		</div>
		</div>        
</footer>
</html>