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
                   <h3 class="page-header" >
                            Modifacation du dossier <small></small>
                   </h3>
                <!-- /.row -->
				<div class="row">
				<div class="col-md-8 col-md-offset-2">
											<% 
                                            	String id_Dossier = (String)session.getAttribute("idDos");
												int  idDossier = Integer.parseInt(id_Dossier);
												DossierDAO dosDAO = new DossierDAO();
                                            	DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
                                            	
                                            	Date date = dos.getDateCreation();
												SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
												SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
												String dateFormatee = formatDateJour.format(date); 
											%>
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">Modification du dossier de <%=dos.getPatient() %></h3></div>
                        <div class="panel-body">
                             <form class="form-horizontal" role="form" action="modifierTrait.chu" method="post">
                             				
											<div class="form-group">
                                                <label for="date" class="col-sm-3 control-label">Date de creation:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="dateDossier" placeholder="<%=dateFormatee %>" class="form-control" id="dateCreat" style="width:250px">
                                                </div>
                                            </div>
                                            	
		                                    <div class="form-group">
		                                         <input type="hidden" class="form-control" name="patient" value="<%=dos.getPatient().getId()%>"  style="width:250px">
		                                    </div>
		                                    <div class="form-group">
		                                         <input type="hidden" class="form-control" name="idDossier" value="<%=dos.getId()%>"  style="width:250px">
		                                    </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Modifier</button>
                                                </div>
                                            </div>
                                        </form>
                        </div><!-- panel-body -->
						
                    </div> <!-- panel -->
                </div>
				
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