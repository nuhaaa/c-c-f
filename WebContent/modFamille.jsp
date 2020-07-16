<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css/bootstrap.css"/>
		<link rel="stylesheet" href="css/bootstrap-theme.css"/>
		<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"/>
		<script src="js/jquery.min.js">  </script>
		<script src="js/bootstrap.js">  </script>
		<title>Espace Utilisateur</title>
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
                        <a href="espaceUtilisateur.jsp"><button type="button" class="btn btn-info btn-custom waves-effect waves-light m-b-5" style="width:250px" >Gestion Des Familles</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
                        <a href="gestionDossier.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:250px">Gestion Des Dossiers Medicales</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
                        <a href="ajoutRendezVous.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:250px" >Gestion Des Rendez-Vous</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
						<a><button type="button" class="btn btn-purple btn-custom waves-effect waves-light m-b-5" style="width:250px" >Deconnexion</button></a>
                    </div>
                                
                </div>
				
                <div class="col-lg-9 col-lg-offset-2">
                    <div class="panel-body">
						<button type="button" class="btn btn-purple btn-rounded waves-effect waves-light m-b-5" style="width:770px; height:200px">Bonjour</button>
                    </div>                       
                </div>
            </div>
			<div class="row">
                <div class="col-md-10 col-lg-offset-1">
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">Liste des familles</h3></div>
                        <%
                        	FamilleDAO famDAO = new FamilleDAO();
                        	List<Famille> familles = new ArrayList<>();
                        	familles= famDAO.lister();
                        %>
                        <div class="panel-body">
                            	<table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>Nom de Famille</th>
                                                            <th>Cas Index</th>
                                                            <th>Diagnostic</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
												<%
												int i=1;
												for(Famille famille :familles){		
				                               %>
                                                        <tr>
                                                            <td><%=i %></td>
                                                            <td><%=famille.getNomFamille() %></td>
                                                            <td><%=famille.getCasIndex()%></td> 
                                                            <td><%=famille.getDiagnostic()%></td>
															<td><a href="modFamille.chu?id=<%=famille.getId()%>">Modifier</a></td>
                                                        </tr>
														
													<% 
													i++;
                                        			}
												
													%>
                                                    </tbody>
                                        </table>
                        </div><!-- panel-body -->
						
                    </div> <!-- panel -->
                </div> <!-- col-->
               
				 <div class="col-lg-12 col-lg-offset-0">
                    <div class="panel-body">
                        <a href="ajoutFamille.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:250px">Nouvelle Famille</button></a>
                        <a href="#"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:250px">Supprimer Famille</button></a>
                        <a href="modFamille.jsp" ><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:250px">Modifier Famille</button></a>
                        <a href="arbre.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:250px">Arbre Genealogique</button></a>
                    </div>                       
                </div>

            </div> <!-- End row -->
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