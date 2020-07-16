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

<title></title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<style type="text/css">
.navbar-nav>li {
	float: none !important;
	display: flex;
	justify-content: center;
}
</style>
<!--  -->
<script src="assetss/js/scripts.js"></script>

</head>

<body>

	<div id="wrapper">
		<%--   <%@include file="enteteUt.jsp" %>
 --%>
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<a class="navbar-brand" href="">CRF-Data</a>
			</div>
			<%
				String nomUser = (String) session.getAttribute("nom");
				String prenomUser = (String) session.getAttribute("prenom");
			%>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> <%=prenomUser + "   " + nomUser%>
						<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> Profil</a></li>
						<li><a href="logout.chu"><i class="fa fa-fw fa-power-off"></i>
								Déconnexion</a></li>
					</ul></li>
			</ul>

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li class="nav-header">
						<div class="dropdown side-profile text-left">
							<span style="display: block;"> <img alt="image"
								class="img-circle" src="images\medecin.png" width="100px">
							</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear" style="display: block;"> <span
									class="block m-t-xs"> <strong class="font-bold"><%=prenomUser + "  " + nomUser%>
											<b class="caret"></b></strong>
								</span></span>
							</a>
						</div>
					</li>
					<li class="active"><a href="espaceUtilisateur.jsp"><i
							class="fa fa-fw fa-table"></i> Gestion des familles</a></li>
					<li ><a href="gestionDossier.jsp"><i
							class="fa fa-fw fa-table"></i> Gestion des dossiers</a></li>
					<li><a href="ajoutRendezVous.jsp"><i
							class="fa fa-fw fa-table"></i> Gestions des rendez-vous</a></li>
					<li><a href="charts.jsp"><i
							class="fa fa-fw fa-bar-chart-o"></i> Tableau de bord </a></li>
				</ul>
			</div>

		</nav>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h3 class="page-header" style="text-align: center">
							<img class="img" src="images\logo.png " width="" height=""
								alt="logo" /><small></small>
						</h3>
						<h3 class="page-header">
							Gestion des familles <small></small>
						</h3>
					</div>
					<div class="container-fluid"></div>
					<!-- /.row -->
					<div class="row">

						<div class="col-lg-12">
							<div class="panel-actions">
								<button type="button" class="btn btn-info btn-sm pull-right"
									data-toggle="modal" data-target="#myModal">Ajouter</button>
							</div>
							</br>
							</br>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Liste des familles</h3>
								</div>

								<div class="panel-body">
									<%
										FamilleDAO famDAO = new FamilleDAO();
										List<Famille> familles = new ArrayList<>();
										familles = famDAO.lister();
									%>
									<table id="basic-datatables" class="table table-striped">
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
												int i = 1;
												for (Famille famille : familles) {
											%>
											<tr>
												<td><%=i%></td>
												<td><%=famille.getNomFamille()%></td>
												<td><%=famille.getCasIndex()%></td>
												<td><%=famille.getDiagnostic()%></td>
												<td><a href="listInd.chu?id=<%=famille.getId()%>">
														<i class="fa fa-eye"> </i> Consulter
												</a> <a href="modFamille.chu?id=<%=famille.getId()%>"><i
														class="fa fa-pencil-square-o"></i> Modifier </a> <a
													href="arbre.jsp"><i class="fa fa-eye"></i> Arbre
														Genealogique</a></td>
											</tr>

											<%
												i++;
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

			</div>
			<!-- /#page-wrapper -->

		</div>
		<!-- /#wrapper -->

		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Ajout d'une famille</h4>
					</div>
					<div class="modal-body">
						<form role="form" action="ajoutFamille.chu" method="post">
							<div class="form-group">
								<label for="nomFamille">Nom de la famille</label> <input
									type="text" class="form-control" name="nomFamille"
									placeholder="Entrer le nom de la famille">
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
											for (SyndromeFamille diag : diagnostics) {
										%>

										<option value="<%=diag.getId()%>"><%=diag%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							</br>
							</br>
							<button type="submit"
								class="btn btn-purple waves-effect waves-light">Enregistrer</button>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>


		<!-- jQuery -->



		<!-- Bootstrap Core JavaScript -->

		<!-- Morris Charts JavaScript -->
		<script src="js/plugins/morris/raphael.min.js"></script>
		<script src="js/plugins/morris/morris.min.js"></script>
		<script src="js/plugins/morris/morris-data.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/jquery.js"></script>
		<script src="js/jquery.dataTables.min.js" type="text/javascript"></script>

		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

		<script type="text/javascript">
			$(document).ready(function() {
				$("#basic-datatables").dataTable();
			});
		</script>
</body>
<footer>
	<div class="row">
		<div
			class="col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xs-4 col-xs-offset-4"
			id="foot">
			<p>Copyright &copy; Registe@CRF</p>
		</div>
	</div>
</footer>
</html>
