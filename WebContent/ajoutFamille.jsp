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
                  <h3 class="page-header">
                            <small></small>
                   </h3>
                <!-- /.row -->
				<div class="row">
                	<div class="col-md-6 col-lg-offset-3">
                        <h3 class="page-header">
                             Ajouter une famille<small></small>
                        </h3>
                     
                    <div class="panel panel-default">
                        <div class="panel-heading"><h3 class="panel-title">Ajout d'une famille</h3></div>
                        <div class="panel-body">
                            <form role="form" action="ajoutFamille.chu" method="post">
                                <div class="form-group">
                                    <label for="nomFamille">Nom de la famille</label>
                                    <input type="text" class="form-control" name="nomFamille" placeholder="Entrer le nom de la famille">
                                </div>
                                <div class="form-group">
                                	<label class="col-sm-4 control-label">Dignostic:</label>
                                    <div class="col-sm-8">
                                    <%
							            	SyndromeFamilleDAO diagDAO = new SyndromeFamilleDAO();
							            	List<SyndromeFamille> diagnostics = new ArrayList<>();
							            	diagnostics = diagDAO.listerDiagnostic();
							            %>
                                    	<select class="form-control" name="diagnostic">
                                    	 <% 
                                			for(SyndromeFamille diag : diagnostics){
                                			%>
                                				
                                			    <option value="<%=diag.getId() %>" ><%=diag %></option>
                                			 <% 
                                			}
                                		%>
                                		</select>
                                   </div>
                               </div>
                               </br></br>
                                <button type="submit" class="btn btn-purple waves-effect waves-light">Enregistrer</button>
                            </form>
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
