<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="dao.entities.*"%>
<%@ page import="beans.*"%>
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
		<%@include file="enteteAdmin.jsp"%>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<h3 class="page-header" style="text-align: center">
						<img class="img" src="images\logo.png " width="" height=""
							alt="logo" /><small></small>
					</h3>
					<h3 class="page-header">
						Gestion des Utilisateurs<small></small>
					</h3>
					<!-- /.row -->
					<div class="row">
						<div class="col-md-10 col-lg-offset-1">
							<div class="panel-actions">
								<button type="button" class="btn btn-info btn-sm pull-right"
									data-toggle="modal" data-target="#myModal">Ajouter</button>
							</div>

						</div>
						<div class="col-md-10 col-lg-offset-1">

							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Liste des utilisateurs</h3>
								</div>
								<%
									UtilisateurDAO userDAO = new UtilisateurDAO();
									List<Utilisateur> users = new ArrayList<>();
									users = userDAO.listerUsers();
								%>
								<div class="panel-body">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>Nom</th>
												<th>Prenom</th>
											</tr>
										</thead>
										<tbody>
											<%
												int i = 1;
												for (Utilisateur user : users) {
											%>
											<tr>
												<td><%=user.getNom()%></td>
												<td><%=user.getPrenom()%></td>
												<td><a
													href="detailUtilisateur.chu?id=<%=user.getId()%>"><i
														class="fa fa-eye"></i>Détail</a> <a
													href="modUser.chu?id=<%=user.getId()%>"><i
														class="fa fa-pencil-square-o"></i> Modifier </a> <a
													href="suppUtilisateur.chu?id=<%=user.getId()%>"
													onclick="return(confirm('Etes-vous sûr de vouloir supprimer cet Utilisateur?'));"><i
														class="fa fa-times"></i> Supprimer </a></td>
												</td>
											</tr>

											<%
												}
											%>
										</tbody>
									</table>
								</div>
								<!-- panel-body -->

							</div>
							<!-- panel -->
						</div>
						<!-- col-->

					</div>
					<!-- End row -->
				</div>
				<!-- /.container-fluid -->
				<!-- Modal -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Ajouter un Utilisateur</h4>
							</div>
							<div class="modal-body">
								<form role="form" action="ajoutUtilisateur.chu" method="post">
									<div class="form-group">
										<label class="col-sm-4 control-label">Nom</label> <input
											type="text" class="form-control" name="nom" required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Prénom</label> <input
											type="text" class="form-control" name="prenom" required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Email</label> <input
											type="email" class="form-control" name="email" required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Login</label> <input
											type="text" class="form-control" name="login" required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label">Mot de passe</label> <input
											type="password" class="form-control" name="password" required>
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
									</br>
									<button type="submit"
										class="btn btn-purple waves-effect waves-light">Enregistrer</button>
								</form>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->
	</div>
	
	
	
	<script src="assetss/js/scripts.js"></script>
</body>

<%@include file="piedUt.jsp"%>
</html>