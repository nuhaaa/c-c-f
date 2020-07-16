<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="dao.entities.*"%>
<%@ page import="beans.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<div class="col-lg-10 col-lg-offset-1">
							<h3 class="page-header">Détail de traitement</h3>
							<%
								String idTrait = (String) session.getAttribute("idTrait");
								int id = Integer.parseInt(idTrait);
								TraitementDAO trait = new TraitementDAO();
								Traitement traitement = trait.trouverTumeurById(id);
								Date date = traitement.getDate();
								SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
								String dateFormatee = formatDateJour.format(date);
							
							%>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Detail de Traitement</h3>
								</div>
								<div class="panel-body">
									<table id="basic-datatables" class="table table-striped">
										<thead>
											<tr>
												<th>Date de traitement</th>
												<th>Indication</th>
												<th>Hopital</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><%=dateFormatee%></td>
												<td><%=traitement.getIndication()%></td>
												<td><%=traitement.getHopital()%></td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- panel-body -->
								<div class="panel-heading">
									<h3 class="panel-title">Detail de Chirurgie</h3>
								</div>
								<%
								Date date1 = traitement.getChirurgie().getDate();
								SimpleDateFormat formatDateJour1 = new SimpleDateFormat("dd/MM/yyyy");
								String dateFormatee1 = formatDateJour1.format(date);
								%>
								<div class="panel-body">
									<table id="basic-datatables" class="table table-striped">
										<thead>
											<tr>
												<th>Temps</th>
												<th>Date</th>
												<th>Service</th>
												<th>Type d'exerèse</th>
												<th>Elargie</th>
												<th>Geste</th>
												<th>Score</th>
												<th>Ref Ana-path</th>
												<th>Complication</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><%=traitement.getChirurgie().getTemps()%></td>
												<td><%=dateFormatee1%></td>
												<td><%=traitement.getChirurgie().getService()%></td>
												<td><%=traitement.getChirurgie().getType()%></td>
												<td><%=traitement.getChirurgie().getElargie().getElargie()%></td>
												<td><%=traitement.getChirurgie().getGeste()%></td>
												<td><%=traitement.getChirurgie().getScoring()%></td>
												<td><%=traitement.getChirurgie().getRefAnaPath()%></td>
												<td><%=traitement.getChirurgie().getComplication()%></td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- panel-body -->
								<div class="panel-heading">
									<h3 class="panel-title">Detail de Endoscopique</h3>
								</div>
								<div class="panel-body">
									<table id="basic-datatables" class="table table-striped">
										<thead>
											<tr>
												<th>Type d'intervention</th>
												<th>Ref Ana-path</th>
												<th>Complication</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:if test="${not empty traitement.getTraitEndo()}">
													<td><%=traitement.getTraitEndo().getIntervention()%></td>
													<td><%=traitement.getTraitEndo().getRefAnaPath()%></td>
													<td><%=traitement.getTraitEndo().getComplication()%></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- panel-body -->
								<div class="panel-heading">
									<h3 class="panel-title">Detail de Chimiotherapie</h3>
								</div>
								<div class="panel-body">

									<table id="basic-datatables" class="table table-striped">
										<thead>
											<tr>
												<th>Deroulement</th>
												<th>Date-Debut</th>
												<th>Date-Fin</th>

											</tr>
										</thead>
										<tbody>
											<tr>
												<c:if test="${not empty traitement.getChimiotherapie()}">
													<td><%=traitement.getChimiotherapie().getDeroulement()%></td>
													<td><%=traitement.getChimiotherapie().getDebut()%></td>
													<td><%=traitement.getChimiotherapie().getFin()%></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- panel-body -->
								<div class="panel-heading">
									<h3 class="panel-title">Detail de Radiothérapie</h3>
								</div>
								<div class="panel-body">
									<table id="basic-datatables" class="table table-striped">
										<thead>
											<tr>
												<th>Deroulement</th>
												<th>Date-Debut</th>
												<th>Date-Fin</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<c:if test="${not empty traitement.getRadiotherapie()}">
													<td><%=traitement.getRadiotherapie().getDeroulement()%></td>
													<td><%=traitement.getRadiotherapie().getDebut()%></td>
													<td><%=traitement.getRadiotherapie().getFin()%></td>
												</c:if>
											</tr>
										</tbody>
									</table>
								</div>

							</div>
						</div>
						<!-- panel -->
					</div>

				</div>
				<!-- End row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

		<%@include file="piedUt.jsp"%>
	</div>
	<!-- /#wrapper -->

	</div>
</body>


</html>