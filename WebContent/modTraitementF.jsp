<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
<%@ page import="dao.entities.Deroulement.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/bootstrap-theme.css"/>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"/>
<script src="js/jquery.min.js">  </script>
<script src="js/bootstrap.js">  </script>
<title>Gestion des examens Cliniques</title>
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
						<a href="Biologie.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Biologie</button></a>
						<a href="Genetique.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:100px">Génétique</button></a>
						<a href="Traitement.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:150px">Traitement</button></a>
                    </div>                       
                </div>
				<div class="row">
                        <div class="col-sm-12">
                        	
                        	<div class="panel panel-default">
								<div class="panel-heading"><h3 class="panel-title">Traitement</h3></div>
								<form class="form-horizontal" role="form" action="modTraitementTrait.chu" method="post">
                                    <div class="row">
										<div class="col-md-7 col-lg-offset-3">
										<div class="panel-body">
											<%
											String id_trait = (String)session.getAttribute("idTrait");
											int  idTrait = Integer.parseInt(id_trait);
											TraitementDAO traitDAO = new TraitementDAO();
											Traitement examen = traitDAO.trouverTumeurById(idTrait);
											Date date = examen.getDate();
											SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
											SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
											String dateFormatee = formatDateJour.format(date);
											%>
		                                    <div class="form-group">
		                                         <input type="hidden" class="form-control" name="dossier" value="<%=examen.getDossier().getId() %>"  style="width:250px">
		                                    </div>
		                                    <div class="form-group">
		                                         <input type="hidden" class="form-control" name="idTrait" id="idTrait" value="<%=examen.getId()%>"  style="width:250px">
		                                    </div>
		                                    <div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Date de traitement :</label>
	                                                <input type="text" class="form-control" name="dateTrait" id="" value="<%=dateFormatee%>" style="width:250px">
	                                            </div>
		                                    <div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Indication</label>
	                                                <input type="text" class="form-control" name="indication" id="" value="<%=examen.getIndication()%>" style="width:250px"> 
	                                               
	                                            </div>
	                                            
	                                    	<div class="form-group">
													<label class="col-sm-2 form-control-label">Hopital:</label>
													<div class="col-sm-8">
													<%
											            	HopitalDAO hopDAO = new HopitalDAO();
											            	List<Hopital> hopitaux = new ArrayList<>();
											            	hopitaux=hopDAO.listerLesHopitaux();
											            	System.out.println("hopitaux :"+hopitaux);
											            %>
											            
														<select class="form-control" name="hopital" style="width:250px" required>
														<% 
				                                			for(Hopital hop : hopitaux ){
				                                		%>	
				                                			    <option value ="<%=hop.getId() %>"><%=hop.getHopital() %></option>
				                                		<% 
				                                			}
				                                		%>  
														</select>
													</div>
											</div>
												<div class="panel-heading"><h3 class="panel-title">Chirurgie</h3></div>
	                                    <div class="panel-body">
	                                    	
	                                    	<div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >en temps</label>
	                                                <input type="text" class="form-control" name="temps" id="" value="<%=examen.getChirurgie().getTemps() %>" style="width:250px"> 
	                                               
	                                            </div>
	                                    	<div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Date:</label>
	                                                <input type="text" class="form-control" name="date" id="" value="<%=dateFormatee %>" style="width:250px">
	                                            </div>
	                                       	<div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Service:</label>
	                                                <input type="text" class="form-control" name="service"  value="<%=examen.getChirurgie().getService() %>" style="width:250px">
	                                            </div>
	                                       
	                                       
												<div class="form-group">
	                                                <label class="col-sm-4 control-label">Type d'exerèse</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="typeExerese">
	                                                    <%
	                                                    TypeExereseDAO typeDAO = new TypeExereseDAO();
	                                                    List<TypeExerese> types = new ArrayList<>();
	                                                    types = typeDAO.listerTypeExerese();
	                                                    
	                                                    %>
	                                                        <%
	                                                        	for(TypeExerese type : types){
	                                                        %>
							                               <option value="<%=type.getId() %>"><%=type.getTypeExerese() %></option>
							                              <%
	                                                        	}
							                              %>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                            <div class="form-group">
	                                                <label class="col-sm-4 control-label">Elargie à:</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="elargissement">
	                                                    <%
	                                                    ElargissementDAO elargDAO = new ElargissementDAO();
	                                                    List<Elargissement> elargies = new ArrayList<>();
	                                                    elargies = elargDAO.listerLesElargissement();
	                                                    %>
	                                                        <%
	                                                        	for(Elargissement elarg : elargies){
	                                                        %>
							                               <option value="<%=elarg.getId() %>"><%=elarg.getElargie() %></option>
							                              <%
	                                                        	}
							                              %>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                           <div class="form-group">
	                                                <label class="col-sm-4 control-label">Gestes Complémentaires:</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="geste">
	                                                    <%
	                                                    GesteDAO gestDAO = new GesteDAO();
	                                                    List<Geste> gestes = new ArrayList<>();
	                                                    gestes = gestDAO.listerLesGeste();
	                                                    
	                                                    %>
	                                                        <%
	                                                        	for(Geste geste : gestes){
	                                                        %>
							                               <option value="<%=geste.getId() %>"><%=geste %></option>
							                              <%
	                                                        	}
							                              %>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                            <div class="form-group">
	                                                <label class="col-sm-4 control-label">RR scoring:</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="scoring">
	                                                    <%
	                                                    RRscoringDAO scoringDAO = new RRscoringDAO();
	                                                    List<RRscoring> scoring = new ArrayList<>();
	                                                    scoring = scoringDAO.listerRRscoring();
	                                                    %>
	                                                        <%
	                                                        	for(RRscoring scor : scoring){
	                                                        %>
							                               <option value="<%=scor.getId() %>"><%=scor.getScoring() %></option>
							                              <%
	                                                        	}
							                              %>
	                                                    </select>
	                                                </div>
	                                            </div>
												<div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Ref Ana-path:</label>
	                                                <input type="text" class="form-control" name="refChirg" value="<%=examen.getChirurgie().getRefAnaPath() %>"  style="width:250px">
	                                            </div>
	                                            <div class="form-group">
	                                                <label class="col-sm-4 control-label">Complication:</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="complicationChirurg">
	                                                    <%
	                                                    ComplicationDAO compDAO = new ComplicationDAO();
	                                                    List<Complication> complications = new ArrayList<>();
	                                                    complications = compDAO.listerLesComplication();
	                                                    %>
	                                                        <%
	                                                        	for(Complication comp : complications){
	                                                        %>
							                               <option value="<%=comp.getId() %>"><%=comp.getComplication() %></option>
							                             <%} %>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                        
	                                    </div>
										</br></br>
											<div class="panel-heading"><h3 class="panel-title">Traitement Endoscopique</h3></div>
	                                    <div class="panel-body">
	                                    	
												<div class="form-group">
	                                                <label class="col-sm-4 control-label">Type d'intervention</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="typeInterv">
	                                                    <%
	                                                    TypeInterventionDAO typeIntDAO = new TypeInterventionDAO();
	                                                    List<TypeIntervention> interventions = new ArrayList<>();
	                                                    interventions = typeIntDAO.listerTypeIntervention();
	                                                    %>
	                                                        <%
	                                                        	for(TypeIntervention interv : interventions){
	                                                        %>
							                               <option value="<%=interv.getId() %>"><%=interv.getIntervention() %></option>
							                             <%} %>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                            
	                                            
												<div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Ref Ana-path:</label>
	                                                <input type="text" class="form-control" name="refTrait"  placeholder="degre" value="<%=examen.getTraitEndo().getRefAnaPath() %>" style="width:250px">
	                                            </div>
	                                            <div class="form-group">
	                                                <label class="col-sm-4 control-label">Complication:</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="complicationTrait">
	                                                     <%
	                                                    complications = compDAO.listerLesComplication();
	                                                    System.out.println("complications 2 :"+complications);
	                                                    %>
	                                                        <%
	                                                        	for(Complication comp : complications){
	                                                        %>
							                               <option value="<%=comp.getId() %>"><%=comp.getComplication() %></option>
							                             <%} %>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                        
	                                    </div>
										</br></br>	
										<div class="panel-heading"><h3 class="panel-title">Chimiothérapie</h3></div>
	                                    <div class="panel-body">
											  <div class="form-group">
	                                                <label class="col-sm-4 control-label">Deroulement</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="deroulementChim">
							                               <option value=""><%=Deroulement.ADJUVANTE %></option>
							                               <option value=""><%=Deroulement.NEO_ADJUVANTE%></option>
							                               <option value=""><%=Deroulement.PALLIATIVE %></option>
							                              
	                                                    </select>
	                                                </div>
	                                            </div>
												<div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Date de début :</label>
	                                                <input type="text" class="form-control" name="dateDebutChim" id="" <c:if test="${not empty examen.getChimiotherapie()}"> value="<%=examen.getChimiotherapie().getDebut() %>" </c:if> style="width:250px">
	                                            </div>
	                                            <div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Date de fin:</label>
	                                                <input type="text" class="form-control" name="dateFinChim" id="" <c:if test="${not empty examen.getChimiotherapie()}"> value="<%=examen.getChimiotherapie().getFin() %>" </c:if> style="width:250px">
	                                            </div>
	                                            
	                                    </div>
										</br></br>
										<div class="panel-heading"><h3 class="panel-title">Radiothérapie</h3></div>
	                                    <div class="panel-body">
	                                    	<div class="form-group">
	                                                <label class="col-sm-4 control-label">Deroulement</label>
	                                                <div class="col-sm-8">
	                                                    <select class="form-control" name="deroulementRadio">
	                                                   
							                               <option value=""><%=Deroulement.ADJUVANTE %></option>
							                               <option value=""><%=Deroulement.NEO_ADJUVANTE%></option>
							                               <option value=""><%=Deroulement.PALLIATIVE %></option>
							                              
	                                                    </select>
	                                                </div>
	                                            </div>
												<div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Date de début :</label>
	                                               
	                                                <input type="text" class="form-control" name="dateDebutRad" id="" <c:if test="${not empty examen.getRadiotherapie()}"> value="<%=examen.getRadiotherapie().getDebut() %>" </c:if>  style="width:250px">
	                                            </div>
	                                            <div class="form-group">
	                                                <label for="" class="col-sm-4 control-label" >Date de fin:</label>
	                                                <input type="text" class="form-control" name="dateFinRad" id="" <c:if test="${not empty examen.getRadiotherapie()}"> value="<%=examen.getRadiotherapie().getFin() %>" </c:if> style="width:250px">
	                                            </div>
	                                    </div>
										</br></br>
														
										</div>
										</div>
									
									<div class="form-group m-b-0">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <button type="submit" class="btn btn-info waves-effect waves-light">Modifier</button>
                                        </div>
                                    </div>
									
											
									</div> 
							</form>
                        </div>
						
                </div>		
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