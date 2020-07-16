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
<title>Statuts</title>
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
                        <a href="espaceUtilisateur.jsp"><button type="button" class="btn btn-primary btn-custom waves-effect waves-light m-b-5" style="width:250px" >Gestion Des Familles</button></a>
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
                        <a href="statut.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:100px">Satut</button></a>
                        <a href="ExamenClinique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Examen Clinique</button></a>
                        <a href="Endoscopie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Endoscopie</button></a>
                        <a href="AnaPathologie.jsp" ><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:200px">Anatomie Pathologique</button></a>
                        <a href="Imagerie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Imagerie</button></a>
						<a href="Biologie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Biologie</button></a>
						<a href="Genetique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Génétique</button></a>
						<a href="Traitement.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:150px">Traitement</button></a>
                    </div>                       
                </div>
				<div class="col-md-8 col-md-offset-2">
							<form class="form-horizontal" role="form" action="ajoutStatut.chu" method="post">
                                <div class="panel panel-default">
									<div class="panel-heading"><h3 class="panel-title">Preciser le statut d'un individu</h3></div>
									<%
										String id_Dossier = (String)session.getAttribute("idDossier");
										int  idDossier = Integer.parseInt(id_Dossier);
										DossierDAO dosDAO = new DossierDAO();
										DossierMedicale dos = dosDAO.trouverDossierById(idDossier);
									
									%>
                                    <div class="form-group">
                                         <input type="hidden" class="form-control" name="patient" value="<%=dos.getPatient().getId()%>"  style="width:250px">
                                    </div>
									</br></br>
                                    <div class="panel-heading"><h3 class="panel-title">CCR</h3></div>
                                    <div class="panel-body">
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Type de Cancer:</label>
                                                <div class="col-sm-8">
                                                <%
                                                TypeStatutDAO staDAO = new TypeStatutDAO();
								            	List<TypeStatut> statuts = new ArrayList<>();
								            	statuts  = staDAO.listerType();
											            %>
											            
                                                    <select class="form-control" name="ccr" >
                                                    	<option value =""></option>
                                                         <% 
                                                         for(TypeStatut statut : statuts ){
				                                		%>	
				                                			    <option value ="<%=statut.getId() %>"><%=statut.getTypeCancer() %></option>
				                                		<% 
				                                			}
				                                		%> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
                                            <div class="form-group">
                                                <label for="dateccr" class="col-sm-3 control-label">Année:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="anneeccr" placeholder="aaaa" class="form-control" id="annee" style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label for="ageccr"class="col-sm-4 control-label" >Age:</label>
                                                <input type="text" class="form-control" name="ageccr" id="ageccr" placeholder="Age" style="width:250px">
                                            </div>
                                            
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Site:</label>
                                                
                                                <div class="col-sm-8">
                                                <%
                                                SiteDAO siteDAO = new SiteDAO();
								            	List<Site> sites = new ArrayList<>();
								            	sites  = siteDAO.listerSite();
											            %>
											            
                                                    <select class="form-control" name="siteccr" >
                                                         <% 
                                                         for(Site site : sites ){
				                                		%>	
				                                			    <option value ="<%=site.getId() %>"><%=site %></option>
				                                		<% 
				                                			}
				                                		%> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Type:</label>
                                                <div class="col-sm-8">
                                                
                                                <%
                                                TypeDAO typeDAO = new TypeDAO();
								            	List<Type> types = new ArrayList<>();
								            	types  = typeDAO.listerType();
											       %>
											            
                                                    <select class="form-control" name="typeccr" >
                                                         <% 
                                                         for(Type type : types ){
				                                		%>	
				                                			    <option value ="<%=type.getId() %>"><%=type %></option>
				                                		<% 
				                                			}
				                                		%> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">T:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="tccr">
                                                        <option><%=T.T0%></option>
                                                        <option><%=T.T1%></option> 
                                                        <option><%=T.T2%></option> 
                                                        <option><%=T.T3%></option> 
                                                        <option><%=T.Tis%></option> 
                                                        <option><%=T.Tx%></option> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">M:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="mccr">
                                                        <option><%=M.M0%></option>
                                                        <option><%=M.M1%></option>
                                                        <option><%=M.Mx%></option>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">N:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="nccr">
                                                        <option><%=N.N0%></option>
                                                        <option><%=N.N1%></option>
                                                        <option><%=N.N2%></option>
                                                        <option><%=N.N3%></option>
                                                        <option><%=N.NX%></option>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
											<label class="col-sm-4 control-label">Prise en charge:</label>
											 <%
											            	PriseEnChargeDAO priseDAO = new PriseEnChargeDAO();
											            	List<PriseEnCharge> prises = new ArrayList<>();
											            	prises  = priseDAO.listerPriseEnCharge();
											            
				                                			for(PriseEnCharge prise : prises ){
				                                			
				                               %>
											<div class="col-md-4">
												<div class="panel-body"> 
													<div class="checkbox">
														<input id="checkbox" type="checkbox" name="priseEnChargeccr" value ="<%=prise.getId() %>">
														<label for="checkbox" > <%=prise%> </label>
													</div>
												</div>
											</div>
											<% } %>
										</div>
											
                                    </div> <!-- panel-body -->
									</br></br>
									<div class="panel-heading"><h3 class="panel-title">CEC</h3></div>
                                    <div class="panel-body">
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Type de Cancer:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="cec">
                                                    <option value =""></option>
                                                         <% 
                                                         for(TypeStatut statut : statuts ){
				                                		%>	
				                                			    <option value ="<%=statut.getId() %>"><%=statut %></option>
				                                		<% 
				                                			}
				                                		%>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
                                            <div class="form-group">
                                                <label for="datecec" class="col-sm-3 control-label">Année:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="anneecec" placeholder="aaaa" class="form-control" id="annee" style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label for="agecec"class="col-sm-4 control-label" >Age:</label>
                                                <input type="text" class="form-control" name="agecec" id="ageccr" placeholder="Age" style="width:250px">
                                            </div>
                                            
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Site:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="sitecec">
                                                         <% 
                                                         for(Site site : sites ){
				                                		%>	
				                                			    <option value ="<%=site.getId() %>"><%=site %></option>
				                                		<% 
				                                			}
				                                		%> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Type:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="typecec">
                                                      <% 
                                                         for(Type type : types ){
				                                		%>	
				                                			    <option value ="<%=type.getId() %>"><%=type %></option>
				                                		<% 
				                                			}
				                                		%> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">T:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="tcec">
                                                         <option><%=T.T0%></option>
                                                        <option><%=T.T1%></option> 
                                                        <option><%=T.T2%></option> 
                                                        <option><%=T.T3%></option> 
                                                        <option><%=T.Tis%></option> 
                                                        <option><%=T.Tx%></option> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">M:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="mcec">
                                                        <option><%=M.M0%></option>
                                                        <option><%=M.M1%></option>
                                                        <option><%=M.Mx%></option> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">N:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="ncec">
                                                         <option><%=N.N0%></option>
                                                        <option><%=N.N1%></option>
                                                        <option><%=N.N2%></option>
                                                        <option><%=N.N3%></option>
                                                        <option><%=N.NX%></option> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
											<label class="col-sm-4 control-label">Prise en charge:</label>
											 <%
											            	prises  = priseDAO.listerPriseEnCharge();
											            
				                                			for(PriseEnCharge prise : prises ){
				                                			
				                               %>
											<div class="col-md-4">
												<div class="panel-body"> 
													<div class="checkbox">
														<input id="checkbox1" type="checkbox" name="priseEnChargecec" value="<%=prise.getId()%>">
														<label for="checkbox1" >
														<%=prise%>
														</label>
														</div>
															
												</div>
											</div>
											<% } %>
										</div>
                                    </div> <!-- panel-body -->
									</br></br>
									<div class="panel-heading"><h3 class="panel-title">P</h3></div>
                                    <div class="panel-body">
                                    <div class="panel-body">
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Type de Cancer:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="p">
                                                    <option value =""></option>
                                                         <% 
                                                         for(TypeStatut statut : statuts ){
				                                		%>	
				                                			    <option value ="<%=statut.getId() %>"><%=statut %></option>
				                                		<% 
				                                			}
				                                		%>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
                                            <div class="form-group">
                                                <label for="datep" class="col-sm-3 control-label">Année:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="anneep" placeholder="aaaa" class="form-control" id="annee" style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label for="agep"class="col-sm-4 control-label" >Age:</label>
                                                <input type="text" class="form-control" name="agep" id="ageccr" placeholder="Age" style="width:250px">
                                            </div>
                                            
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Forme:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="formep">
                                                         <%
											            	FormeDAO formeDAO = new FormeDAO();
											            	List<Forme> formes = new ArrayList<>();
											            	formes  = formeDAO.listerForme();
											            %>
											            <% 
				                                			for(Forme forme : formes ){
				                                				%>	
				                                			    <option value ="<%=forme.getId() %>"><%=forme %></option>
				                                			    <% 
				                                			}
				                                		%> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
											<label class="col-sm-4 control-label">Prise en charge:</label>
											 <%
											            
											            	prises  = priseDAO.listerPriseEnCharge();
											            
				                                			for(PriseEnCharge prise : prises ){
				                                			
				                               %>
											<div class="col-md-4">
												<div class="panel-body"> 
													<div class="checkbox">
														<input id="checkbox1" type="checkbox" name="priseEnChargep" value ="<%=prise.getId()%>">
														<label for="checkbox1" >
														<%=prise%>
														</label>
														</div>
															
												</div>
											</div>
											<% } %>
										</div>
                                    </div> <!-- panel-body -->
                                    </div>
									</br></br>
									<div class="panel-heading"><h3 class="panel-title">MEC</h3></div>
                                    <div class="panel-body">
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Type de Cancer:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="mec">
                                                    <option value =""></option>
                                                         <% 
                                                         for(TypeStatut statut : statuts ){
				                                		%>	
				                                			    <option value ="<%=statut.getId() %>"><%=statut %></option>
				                                		<% 
				                                			}
				                                		%>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
                                            <div class="form-group">
                                                <label for="datemec" class="col-sm-3 control-label">Année:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="anneemec" placeholder="aaaa" class="form-control" id="annee" style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label for="agemec"class="col-sm-4 control-label" >Age:</label>
                                                <input type="text" class="form-control" name="agemec" id="ageccr" placeholder="Age" style="width:250px">
                                            </div>
                                            
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Type:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="typemec">
                                                        <% 
                                                         for(Type type : types ){
				                                		%>	
				                                			    <option value ="<%=type.getId() %>"><%=type %></option>
				                                		<% 
				                                			}
				                                		%> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
											<label class="col-sm-4 control-label">Prise en charge:</label>
											 <%
											            	prises  = priseDAO.listerPriseEnCharge();
											            
				                                			for(PriseEnCharge prise : prises ){
				                                			
				                               %>
											<div class="col-md-4">
												<div class="panel-body"> 
													<div class="checkbox">
														<input id="checkbox1" type="checkbox" name="priseEnChargemec" value="<%=prise.getId()%>">
														<label for="checkbox1" >
														<%=prise%>
														</label>
														</div>
															
												</div>
											</div>
											<% } %>
										</div>
										</br></br>
                                        <div class="form-group m-b-0">
                                            <div class="col-sm-offset-3 col-sm-9">
                                                <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                            </div>
                                        </div>
                                    </div>
							</form>
									
                            </div> <!-- col -->
				
				
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