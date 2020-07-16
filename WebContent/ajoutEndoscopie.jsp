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
<title>Gestion des examens Cliniques</title>
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
				</br> </br>
			</div>
			<div class="row">

				<div class="pull-left col-lg-1">

					<div class="panel-body">
						<a href="espaceUtilisateur.jsp"><button type="button"
								class="btn btn-primary btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Gestion Des Familles</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div>
						</br> </br> <a href="gestionDossier.jsp"><button type="button"
								class="btn btn-success btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Gestion Des Dossiers Medicales</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div>
						</br> </br> <a href="ajoutRendezVous.jsp"><button type="button"
								class="btn btn-info btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Gestion Des Rendez-Vous</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div>
						</br> </br> <a href="logout.chu"><button type="button"
								class="btn btn-purple btn-custom waves-effect waves-light m-b-5"
								style="width: 250px">Deconnexion</button></a>
					</div>

				</div>

				<div class="col-lg-9 col-lg-offset-2">
					<div class="panel-body">
						<a href="gestionDossier.jsp"><button type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 250px">Nouveau Dossier Médical</button></a> <a
							href="examens.jsp"><button type="button"
								class="btn btn-info btn-rounded waves-effect waves-light m-b-5"
								style="width: 250px">Examens Medicales</button></a> <a
							href="consultation.jsp"><button type="button"
								class="btn btn-warning btn-rounded waves-effect waves-light m-b-5"
								style="width: 250px">Consultation</button></a> </br> </br>
						<button type="button"
							class="btn btn-purple btn-rounded waves-effect waves-light m-b-5"
							style="width: 770px; height: 150px">Dossier Médical</button>
					</div>
				</div>
				<div class="col-lg-12 ">
					<div class="panel-body">
						<a href="statut.jsp"><button type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 100px">Satut</button></a> <a href="ExamenClinique.jsp"><button
								type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 150px">Examen Clinique</button></a> <a
							href="Endoscopie.jsp"><button type="button"
								class="btn btn-info btn-rounded waves-effect waves-light m-b-5"
								style="width: 150px">Endoscopie</button></a> <a
							href="AnaPathologie.jsp"><button type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 200px">Anatomie Pathologique</button></a> <a
							href="Imagerie.jsp"><button type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 100px">Imagerie</button></a> <a href="Biologie.jsp"><button
								type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 100px">Biologie</button></a> <a href="Genetique.jsp"><button
								type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 100px">Génétique</button></a> <a href="Traitement.jsp"><button
								type="button"
								class="btn btn-default btn-rounded waves-effect m-b-5"
								style="width: 150px">Traitement</button></a>
					</div>
				</div>
				<form class="form-horizontal" role="form"
					enctype='multipart/form-data' action="ajoutEndoscopie.chu"
					method="post">
					<div class="row">

						<div class="col-sm-8 col-sm-offset-2">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Cas d'une endoscopie normal</h3>
								</div>
								<div class="panel-body">
									<%
										String id_Dossier = (String) session.getAttribute("idDossier");
										int idDossier = Integer.parseInt(id_Dossier);
										DossierDAO dosDAO = new DossierDAO();
										DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
									%>
									<div class="form-group">
										<input type="hidden" class="form-control" name="dossier"
											value="<%=dos.getId()%>" style="width: 250px">
									</div>
									<div class="form-group">
										<label class="col-sm-2 form-control-label">Hopital:</label>
										<div class="col-sm-8">
											<%
												HopitalDAO hopDAO = new HopitalDAO();
												List<Hopital> hopitaux = new ArrayList<>();
												hopitaux = hopDAO.listerLesHopitaux();
											%>

											<select class="form-control" name="hopital"
												style="width: 250px" required>
												<%
													for (Hopital hop : hopitaux) {
												%>
												<option value="<%=hop.getId()%>"><%=hop%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 form-control-label">Medecin:</label>
										<div class="col-sm-8">
											<%
												MedecinDAO medDAO = new MedecinDAO();
												List<Object[]> medecins = medDAO.listerLesMedecin();
											%>
											<select class="form-control" name="medecin"
												style="width: 250px" required>
												<%
													for (Object[] med : medecins) {
												%>
												<option value="<%=med[0]%>"><%=med[1] + " " + med[2]%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-4 form-control-label">Type Examen
											endoscopique:</label>
										<div class="col-sm-8">
											<%
												TypeAndoscopieDAO typeDAO = new TypeAndoscopieDAO();
												List<TypeAndoscopie> types = new ArrayList<>();
												types = typeDAO.listerTypeAndoscopie();
											%>
											<select class="form-control" name="typeExamen"
												style="width: 250px">
												<%
													for (TypeAndoscopie type : types) {
												%>
												<option value="<%=type.getId()%>"><%=type%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="dateexamen" class="col-sm-4 form-control-label">Date
											d'examen:</label> <input type="text" name="dateexamen"
											placeholder="jj/mm/aaaa" class="form-control" id="dateexamen"
											style="width: 250px" required>

									</div>
									<div class="form-group">
										<label for="poids" class="col-sm-4 form-control-label">Numéro:</label>
										<input type="text" class="form-control" name="numero"
											id="numero" placeholder="" style="width: 250px" required>
									</div>
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Anesthesie</label>
										<div class="col-sm-8">
											<select class="form-control" name="anesthesie"
												style="width: 250px">

												<option value="locale"><%=Anesthesie.LOCALE%></option>
												<option value="sedation"><%=Anesthesie.SEDATION%></option>
												<option value="ANESTHESIE_GENERALE"><%=Anesthesie.ANESTHESIE_GENERALE%></option>

											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="poids" class="col-sm-4 form-control-label">Fichier</label>
										<div class="col-sm-4">
											<input type="file" id="files" name="file"
												class="form-control" multiple="multiple" required>
										</div>
									</div>
								</div>
								<!-- panel-body -->
								</br> </br>

							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 ">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Masse Tomorale</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Siege:</label>
										<%
											SiegeDAO siegeDAO = new SiegeDAO();
											List<Siege> sieges = new ArrayList<>();
											sieges = siegeDAO.listerSiege();
										%>
										<div class="col-sm-8">
											<select class="form-control" name="siegeMasse"
												style="width: 250px">
												<option></option>
												<%
													for (Siege siege : sieges) {
												%>
												<option value="<%=siege.getId()%>"><%=siege%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Aspect
											Macroscopique:</label>
										<%
											AspectMacroDAO aspectDAO = new AspectMacroDAO();
											List<AspectMacro> aspects = new ArrayList<>();
											aspects = aspectDAO.listerAspect();
										%>
										<div class="col-sm-8">
											<select class="form-control" name="aspectMasse"
												style="width: 250px">
												<%
													for (AspectMacro aspect : aspects) {
												%>
												<option value="<%=aspect.getId()%>"><%=aspect%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Circonference:</label>
										<%
											CirconferenceDAO cirDAO = new CirconferenceDAO();
											List<Circonference> circonferences = new ArrayList<>();
											circonferences = cirDAO.listerLesCirconference();
										%>
										<div class="col-sm-8">
											<select class="form-control" name="circonferenceMasse"
												style="width: 250px">
												<%
													for (Circonference cir : circonferences) {
												%>
												<option value="<%=cir.getId()%>"><%=cir%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="poids" class="col-sm-4 form-control-label">Taille
											:</label> <input type="text" class="form-control" name="tailleMasse"
											id="taille" placeholder="" style="width: 250px">
									</div>
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Sténose:</label>
										<div class="col-sm-8">
											<select class="form-control" name="stenose"
												style="width: 250px">
												<option><%=Stenose.FRANCHISSABLE%></option>
												<option><%=Stenose.NON_FRANCHISSABLE%></option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="poids" class="col-sm-4 form-control-label">Ref
											AnaPathologie:</label> <input type="text" class="form-control"
											name="refAnaPathMasse" id="refAnaPath" placeholder=""
											style="width: 250px">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 ">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Polype</h3>
								</div>
								<div class="panel-body">

									<div class="form-group">
										<label class="col-sm-4 form-control-label">Siege:</label>
										<div class="col-sm-8">
											<select class="form-control" name="siegePolype"
												style="width: 250px">
												<option></option>
												<%
													for (Siege siege : sieges) {
												%>
												<option value="<%=siege.getId()%>"><%=siege%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Nombre:</label>
										<div class="col-sm-8">
											<select class="form-control" name="nbre" style="width: 250px">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="nbr1" class="col-sm-4 form-control-label">Nombre
											> 1 cm:</label> <input type="text" class="form-control" name="nbre1"
											id="nbre1" placeholder="" style="width: 250px">
									</div>

									<div class="form-group">
										<label class="col-sm-4 form-control-label">Aspect
											Macroscopique:</label>
										<div class="col-sm-8">
											<select class="form-control" name="aspectPolype"
												style="width: 250px">
												<%
													for (AspectMacro aspect : aspects) {
												%>
												<option value="<%=aspect.getId()%>"><%=aspect%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Circonference:</label>
										<div class="col-sm-8">
											<select class="form-control" name="circonferencePolype"
												style="width: 250px">
												<%
													for (Circonference cir : circonferences) {
												%>
												<option value="<%=cir.getId()%>"><%=cir%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="poids" class="col-sm-4 form-control-label">Taille
											(grand diamètre):</label> <input type="text" class="form-control"
											name="taillePolype" id="taille" placeholder=""
											style="width: 250px">
									</div>
									<div class="form-group">
										<label class="col-sm-4 form-control-label">Couleur:</label>
										<div class="col-sm-8">
											<select class="form-control" name="couleur"
												style="width: 250px">
												<option>Bleu</option>
												<option>Rouge</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="poids" class="col-sm-4 form-control-label">Ref
											AnaPathologie:</label> <input type="text" class="form-control"
											name="refAnaPathPolype" id="refAnaPath" placeholder=""
											style="width: 250px">
									</div>
								</div>
							</div>
						</div>
						</br> </br>

						<div class="col-md-12 ">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Complications</h3>
								</div>
								<div class="panel-body">
									<div class="form-group">
										<label for="" class="col-sm-4 form-control-label">
											Complications: </label>
										<div
											class="checkbox checkbox-info checkbox-circle col-sm-10 col-sm-offset-4">
											<%
												ComplicationDAO compDAO = new ComplicationDAO();
												List<Complication> complications = new ArrayList<>();
												complications = compDAO.listerLesComplication();

												for (Complication comp : complications) {
											%>
											<input id="syndrome" type="checkbox" name="complication"
												value="<%=comp.getId()%>"> <label for="syndrome">
												<%=comp%>
											</label> </br> </br>
											<%
												}
											%>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
					<div class="form-group m-b-0">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit"
								class="btn btn-info waves-effect waves-light">Enregistrer</button>
						</div>
					</div>
				</form>



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