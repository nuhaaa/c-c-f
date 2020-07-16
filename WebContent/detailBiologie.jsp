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
						<div class="col-lg-6 col-lg-offset-3">
							<h3 class="page-header">Détail des Biologies</h3>
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Detail de Biologie</h3>
								</div>

								<div class="panel-body">
									<%
										Biologie examen = (Biologie) session.getAttribute("Biologie");
										Date date = examen.getDataeAnalyse();
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
										String dateFormatee = formatDateJour.format(date);
									%>
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
											<tr>
												<td><%=examen.getAnalyse()%></td>
												<td><%=examen.getValeur()%></td>
												<td><%=examen.getHopital()%></td>
												<td><%=dateFormatee%></td>
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