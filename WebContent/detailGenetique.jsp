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
							<h3 class="page-header">Détail des Genetiques</h3>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Detail de Genetique</h3>
								</div>

								<div class="panel-body">
									<%
										Genetique gen = (Genetique)session.getAttribute("Genetique");
										System.out.println("genetique "+gen);
									%>
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
											<tr>
												<td><%=gen.getHopital() %></td>
												<td><%=gen.getNumeroDossierGenetique() %></td>
												<td><%=gen.getInstabiliteMacroscopique() %></td>
												<td><%=gen.getMutaBRAF() %></td>
												<td><%=gen.getMutaKras() %></td>
												<td><%=gen.getMutaAPC() %></td>
												<td><%=gen.getMutaMYH() %></td>
											</tr>
										</tbody>
									</table>
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

			<%@include file="piedUt.jsp"%>
		</div>
		<!-- /#wrapper -->

	</div>
</body>


</html>