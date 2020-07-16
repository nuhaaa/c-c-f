<%@page import="com.google.gson.Gson"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Rapport Médical</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">




<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="css/plugins/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<!--     <link href="asset/css/style.css" type="text/css" rel="stylesheet"> -->
<style type="text/css">
@media print {
  #printPageButton {
    display: none;
  }
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">
	
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js"
	integrity="sha384-NaWTHo/8YCBYJ59830LTz/P4aQZK1sS0SneOgAvhsIl3zBu8r9RevNg5lHCHAuQ/"
	crossorigin="anonymous">
	
</script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
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
					<%
						String id_Dossier = (String) session.getAttribute("idDossier");
						int idDossier = Integer.parseInt(id_Dossier);
						DossierDAO dosDAO = new DossierDAO();
						DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
						int idPatient = dos.getPatient().getId();
					%>
					<!-- /.row -->


					<div id="content">
						</br> </br>
						<div class="row">
							<div class="col-md-8 col-md-offset-2">
								<h2 style="text-align: center;">
									Rapport médical de
									<%=dos.getPatient()%></h2>
							</div>
						</div>
						</br></br>
						<div class="row">
							<%-- <div class="col-md-3 col-md-offset-1">
								<img src="images/CHU_Images/<%=dos.getPatient().getImage()%>"
									width=200px; height=100px; />
							</div>
							<div class="col-md-6">
							<%=dos.getPatient()%>
							<%=dos.getPatient().getSexe()%>
							<%=dos.getPatient().getOccupation()%>
							<%=dos.getPatient().getOrigine()%>
							<%=dos.getPatient().getVille()%>
							<%=dos.getPatient().getCancers()%>
							</div> --%>
							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail de Patient</h3>
									</div>
									<%
									SimpleDateFormat formatDateNaiss = new SimpleDateFormat("dd/MM/yyyy"); 
									String dateFormat = formatDateNaiss.format(dos.getPatient().getDateNaissance());
									%>
									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Image</th>
													<th>Nom Complet</th>
													<th>Sexe</th>
													<th>Date de naissance</th>
													<th>Couverture Médical</th>
													<th>Téléphone</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><img src="images/CHU_Images/<%=dos.getPatient().getImage()%>"
									width=200px; height=100px; /></td>
													<td><%=dos.getPatient()%></td>
													<td><%=dos.getPatient().getSexe()%></td>
													<td><%=dateFormat%></td>
													<td><%=dos.getPatient().getCouvertMedicale()%></td>
													<td><%=dos.getPatient().getTel1()%></td>
												</tr>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
						</div>
						<br /> <br />
						<div class="row">
							<%
								StatutDAO statDAO = new StatutDAO();
								List<StatutCancereux> statuts = new ArrayList<>();
								statuts = statDAO.listerStatutParIndividu(idPatient);
								/* if (!statuts.isEmpty()) { */
							%>

							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail de Statuts</h3>
									</div>

									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Type Statut</th>
													<th>Année</th>
													<th>Age</th>
													<th>Site</th>
													<th>T</th>
													<th>M</th>
													<th>N</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (StatutCancereux statut : statuts) {
												%>
												<tr>
													<td><%=statut.getTypeStatut()%></td>
													<td><%=statut.getAnnee()%></td>
													<td><%=statut.getAge()%></td>
													<td><%=statut.getSite()%></td>
													<td><%=statut.getT()%></td>
													<td><%=statut.getM()%></td>
													<td><%=statut.getN()%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
							<%
								/* } */
							%>
						</div>
						<div class="row">
							<%
								List<ExamenPreOp> examensPre = new ArrayList<>();
								examensPre = dosDAO.listerExamenPreOpParPatient(idPatient);
								List<ExamenPostOp> examensPost = new ArrayList<>();
								examensPost = dosDAO.listerExamenPostOPParPatient(idPatient);
							%>

							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Examen Clinique Pré Opératoire</h3>
									</div>
									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<td>Hopital</td>
													<td>Date:</td>
													<td>Poids:</td>
													<td>Taille:</td>
													<td>OMS:</td>
													<td>IMC</td>
													<td>Type d'examen</td>
												</tr>
											</thead>
											<tbody>
												<%
													for (ExamenPreOp examen : examensPre) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(examen.getDateExamen());
												%>
												<tr>
													<td><%=examen.getHopital()%></td>
													<td><%=dateFormatee%></td>
													<td><%=examen.getPoids()%></td>
													<td><%=examen.getTaille()%></td>
													<td><%=examen.getOMS()%></td>
													<td><%=examen.getIMC()%></td>
													<td><%=examen.getTypeExamen()%></td>
												</tr>
												<%
													}
												%>
											</tbody>

										</table>
									</div>

								</div>
							</div>
							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Examen Clinique Post Operatoire</h3>
									</div>
									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<td>Hopital</td>
													<td>Date:</td>
													<td>Poids:</td>
													<td>Taille:</td>
													<td>OMS:</td>
													<td>IMC</td>
													<td>Delai</td>
													<td>Nombres de selles</td>
												</tr>
											</thead>
											<tbody>
												<%
													for (ExamenPostOp examen : examensPost) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(examen.getDateExamen());
												%>
												<tr>
													<td><%=examen.getHopital()%></td>
													<td><%=dateFormatee%></td>
													<td><%=examen.getPoids()%></td>
													<td><%=examen.getTaille()%></td>
													<td><%=examen.getOMS()%></td>
													<td><%=examen.getIMC()%></td>
													<td><%=examen.getDelai()%></td>
													<td><%=examen.getNbreSelles()%></td>
												</tr>
												<%
													}
												%>
											</tbody>

										</table>
									</div>

								</div>
							</div>
						</div>
						<div class="row">
							<%
								List<Endoscopie> examens = new ArrayList<>();
								examens = dosDAO.listerExamenEndoscopieParPatient(idPatient);
							%>

							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail d'Endoscopie</h3>
									</div>

									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Type</th>
													<th>Date</th>
													<th>Numero</th>
													<th>Anestesie</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (Endoscopie endo : examens) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(endo.getDateEndo());
												%>
												<tr>
													<td><%=endo.getTypeEndo()%></td>
													<td><%=dateFormatee%></td>
													<td><%=endo.getNumero()%></td>
													<td><%=endo.getAnestesie()%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
						</div>
						<div class="row">
							<%
								List<Tumeur> tumeurs = new ArrayList<>();
								tumeurs = dosDAO.listerTumeurParPatient(idPatient);
								List<Polype> polypes = new ArrayList<>();
								polypes = dosDAO.listerPolypeParPatient(idPatient);
							%>
							<%-- <c:if test="${tumeurs.size > 0}"> --%>
							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail de tumeur</h3>
									</div>
									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>

												</tr>
											</thead>
											<tbody>
												<%
													for (Tumeur tumeur : tumeurs) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(tumeur.getDatePrel());
												%>
												<tr>

												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail de polype</h3>
									</div>

									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Nombre</th>
													<th>Siege</th>
													<th>TupeHyst</th>
													<th>SousType</th>
													<th>Displasie</th>
													<th>Limite</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (Polype poly : polypes) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(poly.getDatePrel());
												%>
												<tr>
													<td><%=poly.getNombre()%></td>
													<td><%=poly.getSiege()%></td>
													<td><%=poly.getTupeHyst()%></td>
													<td><%=poly.getSousType()%></td>
													<td><%=poly.getDisplasie().getDysplasie()%></td>
													<td><%=poly.getLimite().getLimite()%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
						</div>
						<div class="row">
							<%
								List<Imagerie> radios = new ArrayList<>();
								radios = dosDAO.listerExamenImagParPatient(idPatient);
								List<Biologie> analyses = new ArrayList<>();
								analyses = dosDAO.listerBiologieParPatient(idPatient);
							%>
							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail d'Imagerie</h3>
									</div>

									<div class="panel-body">

										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Exame</th>
													<th>Hopital</th>
													<th>Date Radio</th>
													<th>image</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (Imagerie image : radios) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(image.getDateRadio());
												%>
												<tr>
													<td><%=image.getExamen()%></td>
													<td><%=image.getHopital()%></td>
													<td><%=dateFormatee%></td>
													<%
													Gson gson = new Gson();
													String[] images = gson.fromJson(image.getFile(), String[].class);
													for(String img:images){
													%>
													<td><img src="images/CHU_Images/imagerie/<%=img%>" width="200px;" height="200px"/></td>
												    <%
													 }
												    %>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail d'Examen Biologique</h3>
									</div>

									<div class="panel-body">

										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Analyse</th>
													<th>Valeur</th>
													<th>Hopital</th>
													<th>Date Analyse</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (Biologie bio : analyses) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(bio.getDataeAnalyse());
												%>
												<tr>
													<td><%=bio.getAnalyse()%></td>
													<td><%=bio.getValeur()%></td>
													<td><%=bio.getHopital()%></td>
													<td><%=dateFormatee%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
						</div>
						<div class="row">
							<%
								List<Genetique> genetiques = new ArrayList<>();
								genetiques = dosDAO.listerExamenGenetiqueParPatient(idPatient);
							%>

							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail d'Examen genetique</h3>
									</div>

									<div class="panel-body">

										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Hopital</th>
													<th>Numero Dossier Genetique</th>
													<th>Instabilite Macroscopique</th>
													<th>Mutation BRAF</th>
													<th>Mutation Kras</th>
													<th>Mutation APC</th>
													<th>Mutation MYH</th>
												</tr>
											</thead>
											<tbody>
												<%
													for (Genetique gen : genetiques) {
												%>
												<tr>
													<td><%=gen.getHopital()%></td>
													<td><%=gen.getNumeroDossierGenetique()%></td>
													<td><%=gen.getInstabiliteMacroscopique()%></td>
													<td><%=gen.getMutaBRAF()%></td>
													<td><%=gen.getMutaKras()%></td>
													<td><%=gen.getMutaAPC()%></td>
													<td><%=gen.getMutaMYH()%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
						</div>
						<div class="row">
							<%
								List<Traitement> traitements = new ArrayList<>();
								traitements = dosDAO.listerTraitementParPatient(idPatient);
							%>

							<div class="col-md-10 col-md-offset-1">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h3 class="panel-title">Detail de Traitement</h3>
									</div>
									<div class="panel-body">
										<table id="basic-datatables" class="table table-striped">
											<thead>
												<tr>
													<th>Indication</th>
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
												<%
													for (Traitement traitement : traitements) {
														SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
														String dateFormatee = formatDateJour.format(traitement.getDate());
												%>
												<tr>
													<td><%=traitement.getIndication()%></td>
													<td><%=traitement.getChirurgie().getTemps()%></td>
													<td><%=dateFormatee%></td>
													<td><%=traitement.getChirurgie().getService()%></td>
													<td><%=traitement.getChirurgie().getType()%></td>
													<td><%=traitement.getChirurgie().getElargie().getElargie()%></td>
													<td><%=traitement.getChirurgie().getGeste()%></td>
													<td><%=traitement.getChirurgie().getScoring()%></td>
													<td><%=traitement.getChirurgie().getRefAnaPath()%></td>
													<td><%=traitement.getChirurgie().getComplication()%></td>
												</tr>
												<%
													}
												%>
											</tbody>
										</table>
									</div>
									<!-- panel-body -->
								</div>
							</div>
						</div>

					</div>
					<div class="row">
						<div class="col-md-4 col-md-offset-7">
							<button type="button"
								class="btn btn-info btn-sm pull-right create_pdf"
								myprint="#content" id="printPageButton" onclick="window.print();">Télécharger</button>
						</div>
					</div>
				</div>

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

		<%@include file="piedUt.jsp"%>
	</div>
	<!-- /#wrapper -->

	</div>
	<!-- <script>
		function download() {
			const doc = new jsPDF('p', 'pt', 'a2');
			const specialElementHandlers = {
				'#editor' : function(element, renderer) {
					return true;
				}
			};
			doc.fromHTML($('#content').get(0), 90, 30, {
				//'width': 500, // max width of content on PDF
				'elementHandlers' : specialElementHandlers
			}, function(bla) {
				//doc.save('saveInCallback.pdf'); 
				window.open(doc.output('bloburl'));
			}, 0);
		}
	</script> -->

</body>

<!-- <body>

	
	<meta name="author" content="Sharma" />
	<div id="content">
		<table width="100%" border="0">
			<tr>
				<td colspan="2" bgcolor="#b5dcb3">
					<h1>This is khadija</h1>
				</td>
			</tr>
			<tr valign="top">
				<td bgcolor="#aaa" width="50"><b>Main Menu</b><br /> HTML<br />
					PHP<br /> PERL...</td>
				<td bgcolor="#eee" width="100" height="200">Technical and
					Managerial Tutorials</td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#b5dcb3">
					<center>Copyright © 2007 Tutorialspoint.com</center>
				</td>
			</tr>
		</table>

	</div>
	<div id="editor"></div>
	<button class="create_pdf" onclick="createPDFClick();"
		myprint="#content" id="btn">Generate PDF</button>
</body> -->
</html>

