<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
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
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
<!--     <link href="asset/css/style.css" type="text/css" rel="stylesheet"> -->

</head>
<body>

    <div id="wrapper">
	<%@include file="enteteUt.jsp" %>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                  <h3 class="page-header" style="text-align:center">
                            <img class="img"  src="images\logo.png " width="" height="" alt="logo"/><small></small>
                   </h3>
              <%
				String id_Dossier = (String)session.getAttribute("idDossier");
				int  idDossier = Integer.parseInt(id_Dossier);
				DossierDAO dosDAO = new DossierDAO();
				DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
				int idPatient = dos.getPatient().getId();
			 %>
                <!-- /.row -->
				
				 
			<div class="container">
			  <h2>Dossier médical de <%=dos.getPatient() %></h2>
			  <ul class="nav nav-tabs">
			    <li class="active"><a data-toggle="tab" href="#home">Statut</a></li>
			    <li><a data-toggle="tab" href="#menu1">Examen Clinique</a></li>
			    <li><a data-toggle="tab" href="#menu2">Endoscopie</a></li>
			    <li><a data-toggle="tab" href="#menu3">Anatomie Pathologique</a></li>
			    <li><a data-toggle="tab" href="#menu4">Imagerie</a></li>
			    <li><a data-toggle="tab" href="#menu5">Biologie</a></li>
			    <li><a data-toggle="tab" href="#menu6">Genetique</a></li>
			    <li><a data-toggle="tab" href="#menu7">Traitement</a></li>
			  </ul>
			
			  <div class="tab-content">
			    <div id="home" class="tab-pane fade in active">
			      <h3></h3>
			      <div class="col-md-6 col-md-offset-3">
			      <div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutStatut.jsp?id=<%=dos.getPatient().getId()%>'" >Ajouter</button>
                    			</div></br></br>
                                <div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Statuts  de  <%=dos.getPatient() %></h3></div>
										 <div >
										 	<%StatutDAO statDAO = new StatutDAO();
											List<StatutCancereux> statuts = new ArrayList<>();
											statuts = statDAO.listerStatutParIndividu(idPatient);
											%>
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                       
                                        		
                                                    </thead>
                                                     <tbody> 
                                                     <% for(StatutCancereux s :statuts){ %>
                                                    <tr>
                                                    	<td><%=s.getTypeStatut() %></td>
                                                     	<td><a href="detailStatut.chu?id=<%=s.getId()%>" ><i class="fa fa-eye" ></i>Détail</a>  <a  href="modStatut.chu?id=<%=s.getId()%>" ><i class="fa fa-pencil-square-o"></i> Modifier </a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                    <div class="panel-body">
                                    </div>
                                </div>
                              </div>
			    </div>
			    <div id="menu1" class="tab-pane fade">
			      <h3>Examens Cliniques</h3>
			       <div class="col-sm-10 col-sm-offset-1">
                        	<%
										
										IndividuDAO indDAO = new IndividuDAO();
										Individu patient = indDAO.trouverIndById(idPatient);
										List<ExamenPostOp> examensPost = new ArrayList<>();
										examensPost = dosDAO.listerExamenPostOPParPatient(idPatient);
										 List<ExamenPreOp> examensPre = new ArrayList<>();
										 examensPre=dosDAO.listerExamenPreOpParPatient(idPatient);
										 List<ExamenPreOpAnormal> examensAnormal = new ArrayList<>();
										 examensAnormal =dosDAO.listerExamenPreOpAnormalParPatient(idPatient);
									%>
							<div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutExamenClinique.jsp?id=<%=dos.getId()%>'" >Ajouter</button> 
                    			</div></br></br>
                        	<div class="panel panel-default">
								<div class="panel-heading"><h3 class="panel-title">Examen Clinique Pré Opératoire de <%=patient.getPrenom()%> <%=patient.getNom() %></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(ExamenPreOp e: examensPre){
                                                         		Date date = e.getDateExamen();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Examen clinique</td>
                                                     	<td>du <%=dateFormatee%></td> 
                                                     	<td><a href="consExamenCliniqueNormal.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Détail</a>  <a  href="modExamPre.jsp?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i> Modifier </a></td>
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                        	
                                    <div class="panel-heading"><h3 class="panel-title">Examen Clinique Post Operatoire de <%=patient.getPrenom()%> <%=patient.getNom() %></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(ExamenPostOp e: examensPost){
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(e.getDateExamen());	
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Examen clinique</td>
                                                     	<td>du <%=dateFormatee%></td> 
                                                     	<td><a href="consExamenCliniquePost.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Détail</a>  <a  href="modExamenCliniquePost.chu?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i> Modifier </a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                        	</div>
						
                </div>
			    </div>
			    <div id="menu2" class="tab-pane fade">
			      <h3>Endoscopie</h3>
			      <div class="col-md-10 col-md-offset-1">
		
                                <div class="col-sm-10 col-sm-offset-1">
                        	<%
										 List<Endoscopie> examens = new ArrayList<>();
										 examens=dosDAO.listerExamenEndoscopieParPatient(idPatient);
										 
									%>
							<div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutEndoscopie.jsp?id=<%=dos.getId()%>'" >Ajouter</button>
                    			</div></br></br>
                        	<div class="panel panel-default">
								<div class="panel-heading"><h3 class="panel-title">Examens Endoscopique de <%=patient.getPrenom()%> <%=patient.getNom() %></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(Endoscopie e: examens){
                                                         		Date date = e.getDateEndo();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);	
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Examen endoscopique</td>
                                                     	<td>du <%=dateFormatee%></td> 
                                                     	<td><a href="consEndoscopieNormal.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Détail</a><a href="modEndoscopieNormal.chu?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i>Modifier</a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                        	
                        	</div>
						
                </div>		
               </div> <!-- col -->
			    </div>
			    <div id="menu3" class="tab-pane fade">
			      <h3>Menu 3</h3>
			       <div class="col-sm-10 col-sm-offset-1">
                        			<%
										
										List<Tumeur> tumeurs = new ArrayList<>();
										tumeurs = dosDAO.listerTumeurParPatient(idPatient);
										List<Polype> polypes = new ArrayList<>();
										polypes=dosDAO.listerPolypeParPatient(idPatient); 
									%>
							 <div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutAnaPathologie.jsp?id=<%=dos.getId()%>'" >Ajouter</button>
                    			</div></br></br>
                        	<div class="panel panel-default">
								<div class="panel-heading"><h3 class="panel-title">Les tumeurs du patient <%=patient.getPrenom()%> <%=patient.getNom() %></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(Tumeur e: tumeurs){
                                                         		Date date = e.getDatePrel();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Tumeur </td>
                                                     	<td>du <%=dateFormatee %></td> 
                                                     	<td><a href="consTumeur.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Detail</a> <a href="modTumeur.chu?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i>Modifier</a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                        	
                                    <div class="panel-heading"><h3 class="panel-title">Les polypes du patient <%=patient.getPrenom()%> <%=patient.getNom() %></h3></div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(Polype e: polypes){
                                                         		Date date = e.getDatePrel();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Polype</td>
                                                     	<td>du <%=dateFormatee%></td> 
                                                     	<td><a href="consPolype.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Détail</a> <a href="modPolype.chu?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i>Modifier</a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                        	</div>
						
                </div>
			    </div>
			    <div id="menu4" class="tab-pane fade">
			      <h3>Imagerie</h3>
			     <div class="col-md-8 col-md-offset-2">
				
									<%
										List<Imagerie> radios = new ArrayList<>();
										radios = dosDAO.listerExamenImagParPatient(idPatient);
									%>
								<div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutImagerie.jsp?id=<%=dos.getId()%>'" >Ajouter</button>
                    			</div></br></br>
                                <div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Imageries de <%=dos.getPatient() %></h3></div>
                                    <div class="panel-body">
                                    	<div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(Imagerie e: radios){
                                                         		Date date = e.getDateRadio();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Imagerie </td>
                                                     	<td>du <%=dateFormatee %></td> 
                                                     	<td><a href="consImagerie.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Detail</a> <a href="modImag.chu?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i>Modifier</a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    </div>
			    </div>
			    <div id="menu5" class="tab-pane fade">
			      <h3>Biologie</h3>
			      <div class="col-md-8 col-md-offset-2">
									
									<%
										List<Biologie> analyses = new ArrayList<>();
										analyses = dosDAO.listerBiologieParPatient(idPatient);
									%>
								<div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutBiologie.jsp?id=<%=dos.getId()%>'" >Ajouter</button>
                    			</div></br></br>
                                <div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Examens Biologiques de <%=dos.getPatient() %></h3></div>
									
                                    <div class="panel-body">
                                    	<div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(Biologie e: analyses){
                                                         		Date date = e.getDataeAnalyse();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Analyse médical </td>
                                                     	<td>du <%=dateFormatee %></td> 
                                                     	<td><a href="consBiologie.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Detail</a> <a href="modBiol.chu?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i>Modifier</a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
              </div> <!-- col -->
			    </div>
			    <div id="menu6" class="tab-pane fade">
			      <h3>Génetique</h3>
			      <div class="col-md-8 col-md-offset-2">
				
									<%
										List<Genetique> genetiques = new ArrayList<>();
										genetiques = dosDAO.listerExamenGenetiqueParPatient(idPatient);
									%>
								<div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutGenetique.jsp?id=<%=dos.getId() %>'" >Ajouter</button>
                    			</div></br></br>
                                <div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Examens genetiques de <%=dos.getPatient() %></h3></div>
									
                                    <div class="panel-body">
                                    	<div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(Genetique e: genetiques){
                                                         		Number numero = e.getNumeroDossierGenetique();
            												
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Examen génetique  </td>
                                                     	<td> numéro <%=numero %></td> 
                                                     	<td><a href="consGenetique.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Detail</a> <a href="modGen.chu?id=<%=e.getId()%>" ><i class="fa fa-pencil-square-o"></i>Modifier</a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
               </div> <!-- col -->
			    </div>
			    <div id="menu7" class="tab-pane fade">
			      <h3>Traitement</h3>
			      <div class="col-md-8 col-md-offset-2">
								
									<%
										 List<Traitement> traitements = new ArrayList<>();
										 traitements=dosDAO.listerTraitementParPatient(idPatient);
										 
									%>
								<div class="panel-actions">
                        			<button type="button" class="btn btn-info btn-sm pull-right" onclick="location.href='ajoutTraitement.jsp?id=<%=dos.getId()%>'" >Ajouter</button>
                    			</div></br></br>
                                <div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Les traitements de <%=dos.getPatient() %></h3></div>
									
                                    <div class="panel-body">
                                    	<div class="row">
                                            <div >
                                            <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th></th>
                                                            <th></th>
                                                        </tr>
                                                         
                                                         <%
                                                         	for(Traitement e: traitements){
                                                         		Date date = e.getDate();
            													SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
            													SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy"); 
            													String dateFormatee = formatDateJour.format(date);	
                                                         %>
                                        		
                                                    </thead>
                                                     <tbody> 
                                                    <tr>
                                                    	<td>Traitement </td>
                                                     	<td>du <%=dateFormatee%></td> 
                                                     	<td><a href="consTraitement.chu?id=<%=e.getId()%>" ><i class="fa fa-eye" ></i>Détail</a>  <a href="modTrait.chu?id=<%=e.getId()%>"><i class="fa fa-pencil-square-o"></i>Modifier</a></td> 
                                                        
                                                        </tr>             
                                                     </tbody>
                                                     <%		
                                        				}
                                        			 %> 
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                          </div> <!-- col -->
			    </div>
			    </div>
			  </div>
			</div>
				
            </div>
            <!-- /.container-fluid -->
			
        </div>
        <!-- /#page-wrapper -->
        
	<%@include file="piedUt.jsp" %>
    </div>
    <!-- /#wrapper -->
    
    </div>
    </body>
</html>
    
	