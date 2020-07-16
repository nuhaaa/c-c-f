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
    
    


</head>
<body>

    <div id="wrapper">
	<%@include file="enteteUt.jsp" %>

        <div id="page-wrapper">

            <div class="container-fluid">
				<%
				String id_Dossier = (String)session.getAttribute("idDossier");
				int  idDossier = Integer.parseInt(id_Dossier);
				DossierDAO dosDAO = new DossierDAO();
				DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
				int idPatient = dos.getPatient().getId();				
				%>
                <!-- Page Heading -->
                <div class="row">
                  <h3 class="page-header" style="text-align:center">
                            <img class="img"  src="images\logo.png " width="" height="" alt="logo"/><small></small>
                   </h3>
                   <h3 class="page-header" >
                            Consultation du dossier de <%=dos.getPatient() %> <small></small>
                   </h3>
                <!-- /.row -->
				<div class="row">

	<div class="col-lg-12 ">
                    <div class="panel-body">
                        <a href="statut.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:100px">Satut</button></a>
                        <a href="ExamenClinique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Examen Clinique</button></a>
                        <a href="Endoscopie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Endoscopie</button></a>
                        <a href="AnaPathologie.jsp" ><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:200px">Anatomie Pathologique</button></a>
                        <a href="Imagerie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Imagerie</button></a>
						<a href="Biologie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Biologie</button></a>
						<a href="Genetique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Génétique</button></a>
						<a href="Traitement.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Traitement</button></a>
                    </div>                       
                </div>
                
				<div class="col-md-5 col-md-offset-4">
								<div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutStatut.jsp'" >Ajouter</button>
                    			</div></br></br>
                                <div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Statuts  de  <%=dos.getPatient() %></h3></div>
										 <div >
										 	<%StatutDAO statDAO = new StatutDAO();
											List<StatutCancereux> statuts = new ArrayList<>();
											statuts = statDAO.listerStatutParIndividu(idPatient);
											%>
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                       
                                        		
                                                    </thead>
                                                     <tbody> 
                                                     <% for(StatutCancereux s :statuts){ %>
                                                    <tr>
                                                    	<td><%=s.getTypeStatut() %></td>
                                                     	<td><a href="detailStatut.chu?id=<%=s.getId()%>" ><i class="fa fa-eye" ></i>Détail</a>  <a  href="modStatut.chu?id=<%=s.getId()%>" ><i class="fa fa-pencil-square-o"></i> Modifier </a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                    <div class="panel-body">
                                    </div>
                                </div>	
                            </div> <!-- col -->
				
	</div> <!-- End row -->
				
            </div>
            <!-- /.container-fluid -->
			
        </div>
        <!-- /#page-wrapper -->
        
	<%@include file="piedUt.jsp" %>
    </div>
    <!-- /#wrapper -->
    
    </div>
    </body>
    
	
</html>