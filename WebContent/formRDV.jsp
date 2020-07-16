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
								Modification d'un Rendez-Vous <small></small>
							</h3>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Modifier un Rendez Vous</h3>
								</div>
								<div class="panel-body">
									<form role="form" action="modifierRendezVous.chu" method="post">
										<%
											String id_rdv = (String) session.getAttribute("idRDV");
											int idRDV = Integer.parseInt(id_rdv);
											RendezDAO rdvDAO = new RendezDAO();
											RendezVous rdv = rdvDAO.trouverRendezVousById(idRDV); 
											SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
											String dates=formatter.format(rdv.getDateRendezVous());
										%>
										
										<div class="form-group">

											<input type="hidden" class="form-control" name="idRendez"
												value='<%=rdv.getId()%>' placeholder="">
										</div>
										<div class="form-group">
											<label for="nomFamille">Patient</label>
											<%
												IndividuDAO invidu = new IndividuDAO();
												List<Individu> individus = new ArrayList<>();
												individus = invidu.listerLesIndividu();
											%>
											<select class="form-control" name="patient">
											<option value="<%=rdv.getPatient().getId()%>"><%=rdv.getPatient() %></option>
												<%
													for (Individu indi : individus) {
														if(rdv.getPatient().getId()!= indi.getId()){
												%>

												<option value="<%=indi.getId()%>"><%=indi%></option>
												<%
													}
													}
												%>
											</select>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label">Date
												Rendez-vous:</label> <input type="date" class="form-control"
												name="dateRDV" value='<%=dates%>' />
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label">Heure
												Rendez-vous:</label> <input type="time" class="form-control"
												name="hrRDV" value='<%=rdv.getHeureRendez()%>' />
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label"> Objet</label>
											<textarea class="form-control" name="objRDV"><%=rdv.getObjet()%></textarea>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label"> Note</label>
											<textarea class="form-control" name="ntRDV"><%=rdv.getNote()%></textarea>
										</div>
										</br>
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
