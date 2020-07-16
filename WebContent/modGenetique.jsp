<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/bootstrap-theme.css"/>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"/>
<script src="js/jquery.min.js">  </script>
<script src="js/bootstrap.js">  </script>
<title>Gestion des examens </title>
</head>
<body>
	<div class="container">
		<div    class="row" >
			<div  class="col-md-1 col-md-offset-1 col-lg-1 col-lg-offset-1 col-xs-1 col-xs-offset-1" id="logo">
          		<img  class="img"  src="images\logo.png " width="" height="" alt="logo"/>
          	</div>
     		<div class="col-lg-12 ">
				</br></br>
            </div>
			<div class="row">
				
                <div class="pull-left col-lg-1">
                                
                    <div class="panel-body">
                        <a href="espaceInfirmier.jsp"><button type="button" class="btn btn-primary btn-custom waves-effect waves-light m-b-5" style="width:250px" >Gestion Des Familles</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
                        <a href="gestionDossier.jsp"><button type="button" class="btn btn-success btn-custom waves-effect waves-light m-b-5" style="width:250px">Gestion Des Dossiers Medicales</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
                        <a href="ajoutRendezVous.jsp"><button type="button" class="btn btn-info btn-custom waves-effect waves-light m-b-5" style="width:250px" >Gestion Des Rendez-Vous</button></a>
						<div class="col-sm-2 col-xs-2 col-md-2 col-lg-2"></div></br></br>
						<a><button type="button" class="btn btn-purple btn-custom waves-effect waves-light m-b-5" style="width:250px" >Deconnexion</button></a>
                    </div>
                                
                </div>
				
                <div class="col-lg-9 col-lg-offset-2">
                    <div class="panel-body">
                        <a href="gestionDossier.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:250px">Nouveau Dossier Médical</button></a>
                        <a href="examens.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:250px">Examens Medicales</button></a>
                        <a href="consultation.jsp"><button type="button" class="btn btn-warning btn-rounded waves-effect waves-light m-b-5" style="width:250px">Consultation</button></a>
						</br> </br>
						<button type="button" class="btn btn-purple btn-rounded waves-effect waves-light m-b-5" style="width:770px; height:150px">Dossier Médical</button>
                    </div>                       
                </div>
				<div class="col-lg-12 ">
                   <div class="panel-body">
                        <a href="statut.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Satut</button></a>
                        <a href="ExamenClinique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Examen Clinique</button></a>
                        <a href="Endoscopie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Endoscopie</button></a>
                        <a href="AnaPathologie.jsp" ><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:200px">Anatomie Pathologique</button></a>
                        <a href="Imagerie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Imagerie</button></a>
						<a href="Biologie.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:100px">Biologie</button></a>
						<a href="Genetique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Génétique</button></a>
						<a href="Traitement.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Traitement</button></a>
                    </div>                      
                </div>
				<div class="row">
					<div class="col-sm-12">

						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Radiologie</h3>
							</div>
							<form class="form-horizontal" role="form"
								action="modGenetique.chu" method="post">
								<div class="row">
									<div class="col-md-7 col-lg-offset-3">
										<div class="panel-body">
											<%
											    Genetique genetique = (Genetique)session.getAttribute("Genetique");
											%>
											<div class="form-group">
												<input type="hidden" class="form-control" name="dossier"
													value="<%=genetique.getDossier().getId()%>" style="width: 250px">
											</div>
											<div class="form-group">
												<input type="hidden" class="form-control" name="idGenetique"
													value="<%=genetique.getId()%>" style="width: 250px">
											</div>

											<div class="form-group">
												<label for="hopital" class="col-sm-4 form-control-label">Hopital:</label>
												<div class="col-sm-8">
													<%
														HopitalDAO hopDAO = new HopitalDAO();
														List<Hopital> hopitaux = new ArrayList<>();
														hopitaux = hopDAO.listerLesHopitaux();
													%>

													<select class="form-control" name="hopital" id="hoptal"
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
												<label for="numero" class="col-sm-4 form-control-label">Numero
													de dossier genetique:</label> <input type="text" name="numero"
													class="form-control" id="numero" value="<%=genetique.getNumeroDossierGenetique() %>" style="width: 250px"
													required>

											</div>
											<div class="form-group">
												<label for="instabiliteMacroscopique"
													class="col-sm-4 form-control-label">Instabilité des
													microsatellites:</label>
												<div class="col-sm-8">
													<select class="form-control" id="instabiliteMacroscopique"
														name="instabiliteMacroscopique" style="width: 250px">

														<option value=""><%=InstMacro.MSI%></option>
														<option value=""><%=InstMacro.MSS%></option>

													</select>
												</div>
											</div>

											<div class="form-group">
												<label for="mutaBRAF" class="col-sm-4 form-control-label">Mutation
													VC00E du gène BRAF:</label>
												<div class="col-sm-8">
													<select class="form-control" id="mutaBRAF" name="mutaBRAF"
														style="width: 250px">

														<option value="">OUI</option>
														<option value="">NON</option>

													</select>
												</div>

											</div>
											<div class="form-group">
												<label for="mutaKras" class="col-sm-4 form-control-label">Mutation
													du gène K-ras:</label>
												<div class="col-sm-8">
													<select class="form-control" id="mutaKras" name="mutaKras"
														style="width: 250px">

														<option value=""><%=Resultat.OUI%></option>
														<option value=""><%=Resultat.NON%></option>
														<option value=""><%=Resultat.VSI%></option>

													</select>
												</div>
											</div>

											<div class="form-group">
												<label for="mutaAPC" class="col-sm-4 form-control-label">Mutation
													du gène APC:</label>
												<div class="col-sm-8">
													<select class="form-control" id="mutaAPC" name="mutaAPC"
														style="width: 250px">

														<option value="">OUI</option>
														<option value="">NON</option>

													</select>
												</div>
											</div>

											<div class="form-group">
												<label for="mutaMYH" class="col-sm-4 form-control-label">Mutation
													du gène MYH:</label>
												<div class="col-sm-8">
													<select class="form-control" id="mutaMYH" name="mutaMYH" style="width: 250px">

														<option value="">OUI</option>
														<option value="">NON</option>

													</select>
												</div>
											</div>


											<div class="form-group">
												<label for="mutaMMR"
													class="col-sm-4 col-sm-offset-0 form-control-label">Mutation du gène MMR: </label>
												<div class=" col-sm-8">
													<select class="form-control" id="mutaMMR" name="mutaMMR"
														style="width: 250px">

														<option value=""><%=Resultat.OUI%></option>
														<option value=""><%=Resultat.NON%></option>
														<option value=""><%=Resultat.VSI%></option>

													</select>
												</div>
											</div>


											<div class="form-group">
												<label for="autre" class="col-sm-4 form-control-label">autre</label>
												<input type="text" class="form-control" name="autre"
													id="autre" value="<%=genetique.getAutre() %>" style="width: 250px" required>
											</div>

										</div>
									</div>

									<div class="form-group m-b-0">
										<div class="col-sm-offset-3 col-sm-9">
											<button type="submit"
												class="btn btn-info waves-effect waves-light">Modifier</button>
										</div>
									</div>


								</div>
							</form>
						</div>

					</div>
				</div>
				<!-- col -->
				
				
        </div>
    </div>	
</body>
<footer>
		<div class="row">
			<div class="col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 col-xs-4 col-xs-offset-4" id="foot">
      			<p>Copyright &copy; Registe@CRF </p> 
      		</div>
		</div>        
</footer>
</html>