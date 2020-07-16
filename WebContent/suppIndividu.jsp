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
<title>Supprimer un indivivu</title>
</head>
<body>
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
                        <button type="button" class="btn btn-purple btn-rounded waves-effect waves-light m-b-5" style="width:770px">Gestion d'individu</button>
						</br> </br>
						<button type="button" class="btn btn-purple btn-rounded waves-effect waves-light m-b-5" style="width:770px; height:150px"></button>
                    </div>                       
                </div>
            </div>
			<div class="row">
   		
                                        		<%
                        String id_Famille = (String)session.getAttribute("idFamille");
						int  idFamille = Integer.parseInt(id_Famille);
						IndividuDAO indDAO = new IndividuDAO();
						List<Individu> individus = indDAO.listIndividuByFamille(idFamille);
                        %>
				<div class="col-md-10 col-md-offset-1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Liste des individus existantes</h3>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <table class="table table-striped">
                                                    <thead>
                                                         <tr>
                                                            <th></th>
                                                            <th>Nom</th>
                                                            <th>Prenom</th>
                                                            <th>Date de Naissance</th>
                                                            
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <%
												int i=1;
												
												for(Individu ind : individus){	
													Date date = ind.getDateNaissance();
													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
													String dateFormatee = formatDateJour.format(date); 
				                               %>
												
                                                        <tr>
                                                            <td><%=i %></td>
                                                            <td><%=ind.getNom()%></td>
                                                            <td><%=ind.getPrenom()%></td> 
                                                            <td><%=dateFormatee%></td>
															<td><a href="suppInd.chu?id=<%=ind.getId()%>"><button type="button" class="btn btn-info btn-custom waves-effect waves-light m-b-5" style="width:250px" onclick="return(confirm('Etes-vous sûr de vouloir supprimer cet individu?'));" >Supprimer</button></a></td>
                                                        </tr>

													<% 
													i++;
														}
                                        			
													%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
				</div>
				<div class="col-lg-12 col-lg-offset-0">
                    <div class="panel-body">
                        <a href="ajoutIndividu.jsp?id=<%=idFamille  %>"><button type="button" class="btn btn-purple btn-custom waves-effect waves-light m-b-5" style="width:200px">Nouvel individu</button></a>
                        <a href="completIndividu.jsp"><button type="button" class="btn btn-purple btn-custom waves-effect waves-light m-b-5" style="width:200px">Completer info individu</button></a>
                        <a href="suppIndividu.jsp?id=<%=idFamille  %>"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:200px">Supprimer Individu</button></a>
                        <a href="modIndividu.jsp?id=<%=idFamille  %>" ><button type="button" class="btn btn-purple btn-custom waves-effect waves-light m-b-5" style="width:200px">Modifier Individu</button></a>
                    </div>                       
                </div>
			</div>
        </div>
    </div>	
</body>
</html>