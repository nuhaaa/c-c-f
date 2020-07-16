<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="arbre.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="assetss/css/common/jquery-ui.min.css" rel="stylesheet">
<link href="assetss/css/common/samples.css" rel="stylesheet">
<script type="text/javascript" src="assetss/js/common/jquery.min.js"></script>
<script type="text/javascript" src="assetss/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="assetss/js/common/samples.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/sb-admin.css" rel="stylesheet">
<style type="text/css">
.navbar-nav>li {
	float: none !important;
	display: flex;
	justify-content: center;
}
@media print {
  #printPageButton {
    display: none;
  }
}
</style>
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
</head>

<body>
<body>
	<div id="wrapper">
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
		%> <!-- Top Menu Items -->
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
						</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
							class="clear" style="display: block;"> <span
								class="block m-t-xs"> <strong class="font-bold"><%=prenomUser + "  " + nomUser%>
										<b class="caret"></b></strong>
							</span></span>
						</a>
					</div>
				</li>
				<li class="active"><a href="espaceUtilisateur.jsp"><i
						class="fa fa-fw fa-table"></i> Gestion des familles</a></li>
				<li><a href="gestionDossier.jsp"><i
						class="fa fa-fw fa-table"></i> Gestion des dossiers</a></li>
				<li><a href="ajoutRendezVous.jsp"><i
						class="fa fa-fw fa-table"></i> Gestions des rendez-vous</a></li>
				<li><a href="charts.jsp"> <i class="fas fa-chart-bar"></i>
						Tableau de bord
				</a></li>
			</ul>
		</div>

		</nav>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="row"></div>
					<div class="col-lg-12">
						<h3 class="page-header" style="text-align: center">
							<img class="img" src="images\logo.png " width="" height=""
								alt="logo" /><small></small>
						</h3>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div id="content" style="top: 60px; bottom: 24px;">
								<!-- The Diagram component is bound to the canvas element below -->
								<div class="tree">
									<canvas id="diagram" width="100%" height="100%"> </canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4 col-md-offset-7">
							<button type="button"
								class="btn btn-info btn-sm pull-right create_pdf" id="printPageButton"
								onclick="window.print()">Télécharger</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="assetss/js/common/MindFusion.Common.js"
		type="text/javascript"></script>
	<script src="assetss/js/common/MindFusion.Diagramming.js"
		type="text/javascript"></script>
	<script src="assetss/js/common/OrgChartEditor.js"
		type="text/javascript"></script>
</body>
</html>