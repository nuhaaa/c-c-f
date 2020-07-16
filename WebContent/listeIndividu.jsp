<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>Espace Utilisateur</title>

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

    <div id="wrapper">
	<%@include file="enteteUt.jsp" %>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                  <h3 class="page-header" style="text-align:center">
                            <img class="img"  src="images\logo.png " width="" height="" alt="logo"/><small></small>
                   </h3>
                <!-- /.row -->
				<div class="row">
					<%
                        String id_Famille = (String)session.getAttribute("idFamille");
						int  idFamille = Integer.parseInt(id_Famille);
						FamilleDAO famDAO = new FamilleDAO();
						Famille famille= famDAO.trouverFamilleById(idFamille);
						IndividuDAO indDAO = new IndividuDAO();
						List<Individu> individus = indDAO.listIndividuByFamille(idFamille);
                        %>
                	<div class="col-md-10 col-lg-offset-1">
                        <h3 class="page-header">
                            Consultation<small></small>
                        </h3>
                        <div class="panel-actions">
                        		<button onclick="location.href='ajoutIndividu.jsp?id=<%=idFamille  %>'" type="button" class="btn btn-info btn-sm pull-right">Ajouter</button>
                    	</div></br></br>
                        <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">Liste des individus de la famille  <%=famille.getNomFamille() %></h3></div>
                        
                        <div class="panel-body">
                            	<table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>Nom</th>
                                                            <th>Prenom</th>
                                                            <th>Date de Naissance</th>
                                                            <th></th>
                                                            
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
															<td><a href="suppInd.chu?id=<%=ind.getId()%>" onclick="return(confirm('Etes-vous sûr de vouloir supprimer cet individu?'));"> <i class="fa fa-times" > </i> Supprimer </a> <a href="modIndividu.chu?id=<%=ind.getId()%>" ><i class="fa fa-pencil-square-o"></i> Modifier </a>  <a href="compInd.chu?id=<%=ind.getId()%>"><i class="fa fa-pencil-square-o"></i> Completer info</a></td>
                                                        </tr>
														
													<% 
													i++;
                                        			}
												
													%>
                                                    </tbody>
                                        </table>
                        </div><!-- panel-body -->
						
                    </div> <!-- panel -->
                    </div>
               

				</div> <!-- End row -->
				
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
    
	