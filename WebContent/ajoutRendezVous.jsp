<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="dao.entities.*"%>
<%@ page import="beans.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
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
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<style type="text/css">
.navbar-nav>li {
	float: none !important;
	display: flex;
	justify-content: center;
}
</style>
</head>
<body>

	<div id="wrapper">
		<%-- <%@include file="enteteUt.jsp"%> --%>
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
				<li><a href="espaceUtilisateur.jsp"><i
						class="fa fa-fw fa-table"></i> Gestion des familles</a></li>
				<li><a href="gestionDossier.jsp"><i
						class="fa fa-fw fa-table"></i> Gestion des dossiers</a></li>
				<li class="active"><a href="ajoutRendezVous.jsp"><i
						class="fa fa-fw fa-table"></i> Gestions des rendez-vous</a></li>
				<li><a href="charts.jsp"><i class="fa fa-fw fa-bar-chart-o"></i>
						Tableau de bord </a></li>
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
					<!-- /.row -->
					<div class="row">

						<div class="col-lg-10 col-lg-offset-1">
							<div class="panel-actions">
								<button type="button" class="btn btn-info btn-sm pull-right"
									data-toggle="modal" data-target="#myModal">Ajouter</button>
							</div>
							</br> </br>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Liste des Rendez-vous</h3>
								</div>

								<div class="panel-body">
									<%
										RendezDAO rd = new RendezDAO();
										List<RendezVous> rdv = new ArrayList<>();
										rdv = rd.listerRendezVous();
									%>
									<table id="basic-datatables" class="table table-striped">
										<thead>
											<tr>
												<th>Patient</th>
												<th>Objet</th>
												<th>Date de Rendez-Vous</th>
												<th>Heure de Rendez-Vous</th>

											</tr>
										</thead>
										<tbody>
											<%
												for (RendezVous r : rdv) {
											%>
											<tr>
												<td><%=r.getPatient()%></td>
												<td><%=r.getObjet()%></td>
												<td><%=r.getDateRendezVous()%></td>
												<td><%=r.getHeureRendez()%></td>
												<td><a href="suppRend.chu?id=<%=r.getId()%>"
													onclick="return(confirm('Etes-vous sûr de vouloir supprimer ce Rendez Vous?'));">
														<i class="fa fa-times"> </i> Supprimer
												</a> <a href="modRendezVous.chu?id=<%=r.getId()%>"><i
														class="fa fa-pencil-square-o"></i> Modifier </a></td>
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
						<!-- End row -->

					</div>
					<!-- /.container-fluid -->

				</div>
				<!-- /#page-wrapper -->

				<%@include file="piedUt.jsp"%>
			</div>
			<!-- /#wrapper -->
			<!-- Modal -->
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Ajout d'un Rendez-Vous</h4>
						</div>
						<div class="modal-body">
							<form role="form" action="ajoutRendezVous.chu" method="post">
								<div class="form-group">
									<label for="nomFamille">Patient</label>
									<%
										IndividuDAO invidu = new IndividuDAO();
										List<Individu> individus = new ArrayList<>();
										individus = invidu.listerLesIndividu();
									%>
									<select class="form-control" name="patient">
										<%
											for (Individu indi : individus) {
										%>

										<option value="<%=indi.getId()%>"><%=indi%></option>
										<%
											}
										%>
									</select>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">Date Rendez-vous:</label>
									<input type="date" class="form-control" name="dateRDV">
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">Heure
										Rendez-vous:</label> <input type="time" class="form-control"
										name="hrRDV">
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">Objet</label>
									<textarea class="form-control" name="objRDV"></textarea>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">Note</label>
									<textarea class="form-control" name="ntRDV"></textarea>
								</div>
								</br>
								</br>
								<button type="submit"
									class="btn btn-purple waves-effect waves-light">Enregistrer</button>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>

				</div>
			</div>

		</div>
</body>


</html>

