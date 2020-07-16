<%@page import="dao.entities.Medecin"%>
<%@page import="dao.MedecinDAO"%>
<%@page import="dao.IndividuDAO"%>
<%@page import="dao.entities.Decces"%>
<%@page import="dao.DeccesDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Vector"%>
<%@page import="dao.entities.Famille"%>
<%@page import="java.util.List"%>
<%@page import="dao.FamilleDAO"%>
<%@page import="org.eclipse.persistence.jpa.jpql.parser.Join"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="css/sb-admin.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="css/plugins/morris.css" rel="stylesheet">
<link href="css/chart.css" rel="stylesheet" type="text/css"> 
<style type="text/css">
.navbar-nav>li {
	float: none !important;
	display: flex;
	justify-content: center;
}

</style>
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link href="font-awesome/css/all.css" rel="stylesheet"
	type="text/css">
<script src="font-awesome/js/all.js"></script>
<script src="assetss/js/Chart.min.js"></script>
<!-- <script type="text/javascript" src="https://cdn.zingchart.com/zingchart.min.js"></script>  -->
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->

			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<a class="navbar-brand" href="">CRF-Data</a>
			</div>
			<%
				String nomUser = (String) session.getAttribute("nom");
				String prenomUser = (String) session.getAttribute("prenom");
			%>
			<!-- Top Menu Items -->
				<ul class="nav navbar-right top-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><i class="fa fa-user"></i> <%=prenomUser + "   " + nomUser%>
						<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="fa fa-fw fa-user"></i> Profil</a></li>
						<li><a href="logout.chu"><i class="fa fa-fw fa-power-off"></i>
								Déconnexion</a></li>
					</ul></li>
			</ul>

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li class="nav-header">
						<div class="dropdown side-profile text-left">
							<span style="display: block;"> <img alt="image"
								class="img-circle" src="images\medecin.png" width="100px">
							</span> <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<span class="clear" style="display: block;"> <span
									class="block m-t-xs"> <strong class="font-bold"><%=prenomUser + "  " + nomUser%>
											<b class="caret"></b></strong>
								</span></span>
							</a>
						</div>
					</li>
					<li><a href="espaceUtilisateur.jsp"><i
							class="fa fa-fw fa-table"></i> Gestion des familles</a></li>
					<li><a href="gestionDossier.jsp"><i
							class="fa fa-fw fa-table"></i> Gestion des dossiers</a></li>
					<li><a href="ajoutRendezVous.jsp"><i
							class="fa fa-fw fa-table"></i> Gestions des rendez-vous</a></li>
					<li class="active"><a href="charts.jsp">
					<i class="fas fa-chart-bar"></i> Tableau de bord </a></li>
				</ul>
			</div>

		</nav>
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row" >

				   <div class="col-lg-12">
						<h3 class="page-header" style="text-align: center">
							<img class="img" src="images\logo.png " width="" height=""
								alt="logo" /><small></small>
						</h3>
					</div>
					<div class="row">
						<%
							DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
							Date dateobj = new Date();
							DeccesDAO decceDAO = new DeccesDAO();
							int nbDecce = decceDAO.nombredecces();
						%>
						<div class="col-md-5 col-md-offset-1">
							<div class="card m-b-30 shadow-sm">
								<div class="card-body pb-2">
									<div class="row">
										<div class="col-md-5">
											<span class="action-icon badge badge-danger-inverse mr-0">
												<i class="fas fa-skull"></i>
											</span>
										</div>
										<div class="col-md-7 text-right">
											<h5 class="card-title font-14 text-muted">Décès</h5>
											<h4 class="mb-0 font-30"><%=nbDecce%></h4>
										</div>
									</div>
								</div>
								<div class="card-footer">
									<div class="row align-items-center">
										<div class="col-md-12 text-center">
											<span class="font-13">Dernière mise à jour : <%=df.format(dateobj)%></span>
										</div>

									</div>
								</div>
							</div>
						</div>

						<div class="col-md-5">
							<div class="card m-b-30 shadow-sm">
								<div class="card-body pb-2">
									<div class="row">
										<div class="col-md-5">
											<span class="action-icon badge badge-success-inverse mr-0">
											<i class="fas fa-clipboard-check"></i></span>
										</div>
										<div class="col-md-7 text-right">
											<h5 class="card-title font-14 text-muted">Guéris</h5>
											<h4 class="mb-0 font-30">14</h4>
										</div>
									</div>
								</div>
								<div class="card-footer">
									<div class="row align-items-center">
										<div class="col-md-12 text-center">
											<span class="font-13">Dernière mise à jour : <%=df.format(dateobj)%></span>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-5 col-md-offset-1">
							<%
								FamilleDAO famille = new FamilleDAO();
								List<Object[]> familles = famille.syndromeParFamille();
								Vector<Object> data = new Vector<>();
								Vector<String> labels = new Vector<>();
								for (Object[] obj : familles) {
									data.add(obj[0]);
									labels.add((String) obj[1]);
								}
							%>
							<!--<canvas id="syndromes" width="400" height="400"></canvas>-->
							<div class="card m-b-30 shadow-sm">
								<div class="card-header">
									<h5 class="card-title">Syndrome par familles</h5>
								</div>
								<div class="card-body p-0 p-lg-2">
									<canvas id="syndromes"
										style="display: block; width: 524px; height: 349px;"
										width="524" height="349" class="chartjs-render-monitor"></canvas>
								</div>
							</div>
						</div>
						<div class="col-md-5">
						  <%
						    IndividuDAO indivduDAO = new IndividuDAO();
						    List<Object[]> individus = indivduDAO.individuParTypeCancer();
						    Vector<Object> data1 = new Vector<>();
							Vector<Object> labels1 = new Vector<>();
							for (Object[] obj : individus) {
								data1.add(obj[0]);
								labels1.add(obj[1]);
							}
						  %> 
							<div class="card m-b-30 shadow-sm">
								<div class="card-header">
									<h5 class="card-title">Type de cancer par individu</h5>
								</div>
								<div class="card-body p-0 p-lg-2">
									<canvas id="test1"
										style="display: block; width: 524px; height: 349px;"
										width="524" height="349" class="chartjs-render-monitor"></canvas>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						
						<div class="col-md-5 col-md-offset-1">
							<%
								DeccesDAO decce = new DeccesDAO();
								List<Object[]> decces = decce.deccesParMotif();
								Vector<Object> data2 = new Vector<>();
								Vector<String> labels2 = new Vector<>();
								for (Object[] obj : decces) {
									data2.add(obj[0]);
									labels2.add((String) obj[1]);
								}
							%>
							<div class="card m-b-30 shadow-sm">
								<div class="card-header">
									<h5 class="card-title">Motifs de décés</h5>
								</div>
								<div class="card-body p-0 p-lg-2">
									<canvas id="test3"
										style="display: block; width: 524px; height: 349px;"
										width="524" height="349" class="chartjs-render-monitor"></canvas>
								</div>
							</div>
						</div>
						<!-- <div class="col-md-5 ">
							<div class="card m-b-30 shadow-sm">
								<div class="card-header">
									<h5 class="card-title">Évolution du nombre de cas par jour</h5>
								</div>
								<div class="card-body p-0 p-lg-2">
									<canvas id="test2"
										style="display: block; width: 524px; height: 349px;"
										width="524" height="349" class="chartjs-render-monitor"></canvas>
								</div>
							</div>
						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var ctx = document.getElementById('syndromes').getContext('2d');
		var labelsArray=[];
	    var labels = "<%=labels%>";
		var newLabels = labels.split(",");
		for (var i = 0; i < newLabels.length; i++) {
			labelsArray.push(newLabels[i].replace("[", "").replace("]", ""));
		}
		var myChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : labelsArray,
				datasets : [ {
					label : 'Syndromes',
					data :<%out.print(data);%>,
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				}
			}
		});
	</script>
	<script>
		var ctx = document.getElementById('test1').getContext('2d');
		var labelsArray=[];
	    var labels = "<%=labels1%>";
		var newLabels = labels.split(",");
		for (var i = 0; i < newLabels.length; i++) {
			labelsArray.push(newLabels[i].replace("[", "").replace("]", ""));
		}
		var myChart = new Chart(ctx, {
			type : 'bar',
			data : {
				labels :labelsArray,
				datasets : [ {
					label : 'Type cancer',
					data : <%=data1%>,
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				} ]
			},
			options : {
				scales : {
					yAxes : [ {
						ticks : {
							beginAtZero : true
						}
					} ]
				}
			}
		});
	</script>
	<!-- <script>
		var ctx = document.getElementById('test2').getContext('2d');
		var myChart = new Chart(ctx, {
			type : 'radar',
			data : {
				labels : [ 'lynch', 'Peutz-jeghers',
						'Polypose adénomateuse familiale', 'Polypose juvénile',
						'Polypose liée au MYH' ],
				datasets : [ {
					label : 'Syndromes',
					data : [ 4, 3, 3, 2, 1 ],
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				} ]
			}
		});
	</script> -->
	<script>
		var ctx = document.getElementById('test3').getContext('2d');
		var labelsArray=[];
	    var labels = "<%=labels2%>";
		var newLabels = labels.split(",");
		for (var i = 0; i < newLabels.length; i++) {
			labelsArray.push(newLabels[i].replace("[", "").replace("]", ""));
		}
		var myChart = new Chart(ctx, {
			type : 'polarArea',
			data : {
				labels : labelsArray,
				datasets : [ {
					label : 'Motifs de déces',
					data :<%=data2%>,
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)',
							'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)',
							'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255, 99, 132, 1)',
							'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
							'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
							'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				} ]
			}
		});
	</script>


</body>
</html>