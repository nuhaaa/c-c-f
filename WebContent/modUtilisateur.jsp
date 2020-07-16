<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="dao.entities.*"%>
<%@ page import="beans.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
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
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">


</head>

<body>

	<div id="wrapper">
		<%@include file="enteteUt.jsp"%>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<h3 class="page-header" style="text-align: center">
						<img class="img" src="images\logo.png " width="" height=""
							alt="logo" /><small></small>
					</h3>

					<!-- /.row -->
					<div class="row">
						<div class="col-md-6 col-lg-offset-3">
							<h3 class="page-header">
								Modification d'un Utilisateur <small></small>
							</h3>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Modifier un Utilisateur</h3>
								</div>
								<div class="panel-body">
									<form role="form" action="modUtilisateur.chu" method="post">
										<%
											Utilisateur user = (Utilisateur)session.getAttribute("user");
										
										%>
										
										<div class="form-group">

											<input type="hidden" class="form-control" name="idUser"
												value="<%=user.getId()%>" placeholder="">
										</div>
										
										<div class="form-group">
										<label class="col-sm-4 control-label">Nom</label> <input
											type="text" class="form-control" name="nom"
											value="<%=user.getNom() %>" required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Prénom</label> <input
											type="text" class="form-control" name="prenom" 
											value="<%=user.getPrenom()%>"required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Email</label> <input
											type="email" class="form-control" name="email" 
											value="<%=user.getEmail() %>" required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Login</label> <input
											type="text" class="form-control" name="login" 
											value="<%=user.getLogin() %>" required>
									</div>
									<div class="form-group">
										<label class="col-sm-12 control-label">Role</label> 
										<select  class="form-control" name="role" style="width:250px" required>
											<option>Medecin</option>
											<option>Infirmier</option>
											<option>Utilisateur</option>
										</select>
									</div>
										</br>
										<button type="submit"
											class="btn btn-purple waves-effect waves-light">Modifier</button>
									</form>
								</div>
								<!-- panel-body -->

							</div>
							<!-- panel -->
						</div>


					</div>
					<!-- End row -->

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->
	</div>
</body>

<%@include file="piedUt.jsp"%>
</html>
