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

<title>Dossiers médicaux</title>

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
		<%-- <%@include file="enteteUt.jsp" %> --%>
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
					<li><a href="espaceUtilisateur.jsp"><i
							class="fa fa-fw fa-table"></i> Gestion des familles</a></li>
					<li class="active"><a href="gestionDossier.jsp"><i
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
					<h3 class="page-header" style="text-align: center">
						<img class="img" src="images\logo.png " width="" height=""
							alt="logo" /><small></small>
					</h3>
					<h3 class="page-header">
						Gestion des dossiers médicaux <small></small>
					</h3>
					<!-- /.row -->
					<div class="row">
						<div class="col-md-10 col-lg-offset-1">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Liste des familles</h3>
								</div>
								<%
									FamilleDAO famDAO = new FamilleDAO();
									List<Famille> familles = new ArrayList<>();
									familles = famDAO.lister();
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
												int i = 1;
												for (Famille famille : familles) {
											%>
											<tr>
												<td><%=i%></td>
												<td><%=famille.getNomFamille()%></td>
												<td><%=famille.getCasIndex()%></td>
												<td><%=famille.getDiagnostic()%></td>
												<td><a href="listIndDoss.chu?id=<%=famille.getId()%>">
														<i class="fa fa-eye"> </i> Consulter
												</a></td>
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
	</div>


</body>

<%@include file="piedUt.jsp"%>
</html>