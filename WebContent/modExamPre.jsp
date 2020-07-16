<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>
<%@ page import="dao.entities.*"%>
<%@ page import="beans.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
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
						<div class="col-md-6 col-lg-offset-3">
							<h3 class="page-header">
								Modification d'un Examen Clinique <small></small>
							</h3>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Modifier Examen Pres Opération</h3>
								</div>
								<div class="panel-body">
									<form class="form-horizontal" role="form"
										action="modExamPre.chu" method="post">
										<div class="row">
											<div class="panel-body">
												<%
													String ide = request.getParameter("id");
													int id = Integer.parseInt(ide);
													ExamenPreOpDAO examenDAO = new ExamenPreOpDAO();
													ExamenPreOp examen = examenDAO.trouverExamenById(id);
													SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
													String dates = formatter.format(examen.getDateExamen());
													String id_Dossier = (String)session.getAttribute("idDossier");
													int  idDossier = Integer.parseInt(id_Dossier);
													DossierDAO dosDAO = new DossierDAO();
													DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
												%>
												<div class="form-group">
													<input type="hidden" class="form-control" name="dossier"
														value="<%=dos.getId()%>" style="width: 250px">
												</div>
												<div class="form-group">
													<input type="hidden" class="form-control" name="idExamen"
														value="<%=examen.getId()%>" style="width: 250px">
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">Hopital:</label>
													<div class="col-sm-8">
														<%
															HopitalDAO hopDAO = new HopitalDAO();
															List<Hopital> hopitaux = new ArrayList<>();
															hopitaux = hopDAO.listerLesHopitaux();
														%>

														<select class="form-control" name="hopital"
															style="width: 250px" required>
															<option value="<%=examen.getHopital().getId()%>"><%=examen.getHopital()%></option>
															<%
																for (Hopital hop : hopitaux) {
																	if (examen.getHopital().getId() != hop.getId()) {
															%>
															<option value="<%=hop.getId()%>"><%=hop%></option>
															<%
																}
																}
															%>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">Date
														d'examen:</label>
													<div class="col-sm-8">
														<input type="date" name="dateexamen" class="form-control"
															id="dateexamen" style="width: 250px" required
															value="<%=dates%>">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">poids</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="poids"
															id="poids" placeholder="Le poids" style="width: 250px"
															required value="<%=examen.getPoids()%>">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">Taille</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="taille"
															id="taille" placeholder="La taille" style="width: 250px"
															required value="<%=examen.getTaille()%>">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">OMS</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="oms"
															id="oms" placeholder="OMS" style="width: 250px" required
															value="<%=examen.getOMS()%>">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">IMC</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" name="imc"
															id="imc" placeholder="IMC" style="width: 250px" required
															value="<%=examen.getIMC()%>">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-4 control-label">Type Examen:</label>
													<div class="col-sm-8">
														<select class="form-control" name="typeExamen" required>
															<!--  <option><%=examen.getTypeExamen()%></option>-->
															<%
																if ((examen.getTypeExamen() == TypeExamen.INITIAL) || (examen.getTypeExamen() == TypeExamen.SUIVI)) {
															%>
															<option><%=TypeExamen.INITIAL%></option>
															<option><%=TypeExamen.SUIVI%></option>
															<%
																}
															%>
														</select>
													</div>
												</div>
											</div>
										</div>
										<button type="submit"
											class="btn btn-purple waves-effect waves-light">Modifier</button>
									</form>
								</div>
								<!-- panel-body -->

							</div>
							<!-- panel -->
						</div>


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
