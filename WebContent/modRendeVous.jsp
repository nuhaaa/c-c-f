<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="dao.entities.*"%>
<%@ page import="beans.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap-theme.css" />
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css" />
<script src="js/jquery.min.js">
	
</script>
<script src="js/bootstrap.js">
	
</script>
<title>Modifier un Rendez vous</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div
				class="col-md-1 col-md-offset-1 col-lg-1 col-lg-offset-1 col-xs-1 col-xs-offset-1"
				id="logo">
				<img class="img" src="images\logo.png " width="" height=""
					alt="logo" />
			</div>
			<div class="col-lg-12 ">
				</br>
				</br>
			</div>
			<div class="row">

				<div class="pull-left col-lg-1">

					<div class="panel-body">
						<a href="espaceInfirmier.jsp"><button type="button"
								class="btn btn-primary btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Gestion Des Familles</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div>
						</br>
						</br> <a href="gestionDossier.jsp"><button type="button"
								class="btn btn-success btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Gestion Des Dossiers Medicales</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div>
						</br>
						</br> <a href="ajoutRendezVous.jsp"><button type="button"
								class="btn btn-info btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Gestion Des Rendez-Vous</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div>
						</br>
						</br> <a><button type="button"
								class="btn btn-purple btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Deconnexion</button></a>
					</div>

				</div>

				<div class="col-lg-9 col-lg-offset-2">
					<div class="panel-body">
						<a href="ajoutRendezVous.jsp"><button type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 200px">Nouveau Rendez-Vous</button></a> <a
							href="suppRendezVous.jsp"><button type="button"
								class="btn btn-info btn-rounded waves-effect waves-light m-b-5"
								style="width: 200px">Supprimer Rendez-vous</button></a> <a
							href="modRendeVous.jsp"><button type="button"
								class="btn btn-warning btn-rounded waves-effect waves-light m-b-5"
								style="width: 200px">Modifier Rendez-vous</button></a> <a
							href="listeRendezVous.jsp"><button type="button"
								class="btn btn-primary btn-rounded waves-effect waves-light m-b-5"
								style="width: 200px">Lister tous les rendez-vous</button></a>
						<button type="button"
							class="btn btn-purple btn-rounded waves-effect waves-light m-b-5"
							style="width: 810px; height: 164px">Rendez-vous</button>
					</div>

				</div>
				<div class="row">

					<div class="col-md-9  col-md-offset-2">
						<%
							String id_Dossier = (String) session.getAttribute("Dossier");
							int idDossier = Integer.parseInt(id_Dossier);
							DossierDAO dosDAO = new DossierDAO();
							DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
							RendezDAO rendDAO = new RendezDAO();
							List<RendezVous> lesRendez = new ArrayList<>();
							lesRendez = rendDAO.listerRendezVousParPatient(dos.getPatient().getId());
						%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									Liste des rendez vous de
									<%=dos.getPatient().getPrenom()%>
									<%=dos.getPatient().getNom()%></h3>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12 col-sm-12 col-xs-12">

										<table class="table table-striped">
											<thead>
												<tr>
													<th>#</th>
													<th>Patient</th>
													<th>Objet</th>
													<th>Date</th>
													<th>Heure</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<%
													if (!lesRendez.isEmpty()) {

														for (RendezVous rend : lesRendez) {
												%>
												<tr>
													<td><%=rend.getId()%></td>
													<td><%=rend.getPatient()%></td>
													<td><%=rend.getObjet()%></td>
													<td><%=rend.getDateRendezVous()%></td>
													<td><%=rend.getHeureRendez()%></td>
													<td><a href="modRendezTrait.chu?id=<%=rend.getId()%>">Modifier</a></td>
												</tr>
												<%
													}
													}
												%>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>



				</div>
			</div>
		</div>
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