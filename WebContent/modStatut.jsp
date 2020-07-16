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
							<%
								String idStatut = (String) session.getAttribute("idStatut");
								int id = Integer.parseInt(idStatut);
								StatutDAO type = new StatutDAO();
								StatutCancereux statut = type.trouverStautById(id);
							%>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Modifier le Statut
										<%=statut.getTypeStatut()%></h3>
								</div>
								<div class="panel-body">
									<form role="form" action="modifierStatut.chu" method="post">
										<div class="form-group">
											<input type="hidden" class="form-control" name="statut"
												value="<%=statut.getId()%>" style="width: 250px">
										</div>
										<div class="panel-body">
											<div class="form-group">
												<label class="col-sm-4 control-label">Type de Cancer</label>
												<div class="col-sm-8">
													<%
														TypeStatutDAO staDAO = new TypeStatutDAO();
														List<TypeStatut> statuts = new ArrayList<>();
														statuts = staDAO.listerType();
													%>

													<select class="form-control" name="typeStatut">
														<option value="<%=statut.getTypeStatut().getId()%>"><%=statut.getTypeStatut()%></option>
														<%
															for (TypeStatut st : statuts) {
																if (st.getId() != statut.getTypeStatut().getId()) {
														%>
														<option value="<%=st.getId()%>"><%=st.getTypeCancer()%></option>
														<%
															}
															}
														%>
													</select>
												</div>
											</div>
											</br> </br>
											<div class="form-group">
												<label class="col-sm-4 control-label">Année</label>
												<div class="col-sm-8">
													<input type="text" name="annee" placeholder="aaaa"
														class="form-control" id="annee" style="width: 250px"
														value="<%=statut.getAnnee()%>">
												</div>
											</div>
											</br> </br>
											<div class="form-group">
												<label class="col-sm-4 control-label">Age</label>
												<div class="col-sm-8">
													<input type="text" class="form-control" name="age" id="age"
														placeholder="age" style="width: 250px"
														value="<%=statut.getAge()%>">
												</div>
												</br> </br>
												<div class="form-group">
													<label class="col-sm-4 control-label">Site</label>

													<div class="col-sm-8">
														<%
															SiteDAO siteDAO = new SiteDAO();
															List<Site> sites = new ArrayList<>();
															sites = siteDAO.listerSite();
														%>

														<select class="form-control" name="site">
															<option value="<%=statut.getSite().getId()%>"><%=statut.getSite()%></option>
															<%
																for (Site site : sites) {
																	if (statut.getSite().getId() != site.getId()) {
															%>
															<option value="<%=site.getId()%>"><%=site%></option>
															<%
																}
																}
															%>
														</select>
													</div>
												</div>
												</br> </br>
												<div class="form-group">
													<label class="col-sm-4 control-label">Type:</label>
													<div class="col-sm-8">

														<%
															TypeDAO typeDAO = new TypeDAO();
															List<Type> types = new ArrayList<>();
															types = typeDAO.listerType();
														%>

														<select class="form-control" name="type">
															<option value="<%=statut.getType().getId()%>"><%=statut.getType().getType()%></option>
															<%
																for (Type t : types) {
																	if(statut.getType().getId()!=t.getId()){
															%>
															<option value="<%=t.getId()%>"><%=t.getType()%></option>
															<%
																}
																}
															%>
														</select>
													</div>
												</div>
												</br> </br>
												<div class="form-group">
													<label class="col-sm-4 control-label">T:</label>
													<div class="col-sm-8">
														<select class="form-control" name="t">
															<option><%=statut.getT()%></option>
															<option><%=T.T0%></option>
															<option><%=T.T1%></option>
															<option><%=T.T2%></option>
															<option><%=T.T3%></option>
															<option><%=T.Tis%></option>
															<option><%=T.Tx%></option>
														</select>
													</div>
												</div>
												</br> </br>
												<div class="form-group">
													<label class="col-sm-4 control-label">M:</label>
													<div class="col-sm-8">
														<select class="form-control" name="m">
															<option><%=statut.getM()%></option>
															<option><%=M.M0%></option>
															<option><%=M.M1%></option>
															<option><%=M.Mx%></option>
														</select>
													</div>
												</div>
												</br> </br>
												<div class="form-group">
													<label class="col-sm-4 control-label">N:</label>
													<div class="col-sm-8">
														<select class="form-control" name="n">
															<option><%=statut.getN()%></option>
															<option><%=N.N0%></option>
															<option><%=N.N1%></option>
															<option><%=N.N2%></option>
															<option><%=N.N3%></option>
															<option><%=N.NX%></option>
														</select>
													</div>
												</div>
												</br> </br>
												<div class="form-group">
													<label class="col-sm-4 control-label">Prise en
														charge:</label> </br> </br>
													<%
														PriseEnChargeDAO priseDAO = new PriseEnChargeDAO();
														List<PriseEnCharge> prises = new ArrayList<>();
														prises = priseDAO.listerPriseEnCharge();

														for (PriseEnCharge prise : prises) {
													%>
													<div class="col-md-6">
														<div class="panel-body">
															<div class="checkbox">
																<input id="checkbox" type="checkbox"
																	name="priseEnCharge" value="<%=prise.getId()%>">
																<label for="checkbox"> <%=prise%>
																</label>
															</div>
														</div>
													</div>
													<%
														}
													%>
												</div>

											</div>
											<!-- panel-body -->
											<button type="submit"
												class="btn btn-purple waves-effect waves-light">Modifier</button>
											</br> </br>
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
