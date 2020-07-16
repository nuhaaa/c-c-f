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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="css/bootstrap.css"/>
		<link rel="stylesheet" href="css/bootstrap-theme.css"/>
		<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css"/>
		<script src="js/jquery.min.js">  </script>
		<script src="js/bootstrap.js">  </script>
		<title>Ajouter Un individu</title>
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
                <%
                        String id_Famille = (String)session.getAttribute("idFamille");
						int  idFamille = Integer.parseInt(id_Famille);
                        %>
                    <div class="panel-body">
                        <a href="espaceUtilisateur.jsp"><button type="button" class="btn btn-default btn-rounded waves-effect m-b-5" style="width:130px">Nouvelle Famille</button></a>
                        <div class="panel-body">
                        <a href="ajoutIndividu.jsp"><button type="button" class="btn btn-primary btn-rounded waves-effect waves-light m-b-5" style="width:250px">Nouvel individu</button></a>
                        <a href="suppIndividu.jsp"><button type="button" class="btn btn-info btn-rounded waves-effect waves-light m-b-5" style="width:250px">Supprimer Individu</button></a>
                        <a href="modIndividu.jsp" ><button type="button" class="btn btn-warning btn-rounded waves-effect waves-light m-b-5" style="width:250px">Modifier Individu</button></a>
                        
                    
						</br> </br>
						<button type="button" class="btn btn-purple btn-rounded waves-effect waves-light m-b-5" style="width:770px; height:150px">Bonjour</button>
                    </div>                       
                </div>
				
				
                        <div class="row">
                            
                            <div class="col-md-6">
                                <div class="panel panel-default">
									<form  role="form" action="ajoutIndividu.chu" method="post">
											<div class="panel-heading"><h3 class="panel-title">Famille</h3></div> 
											<div class="panel-body"> 
												
													<div class="form-group"> 
														<label class="col-sm-4 form-control-label">Famille</label>  
														<div class="col-sm-10"> 
														<% 
													          FamilleDAO famDAO = new FamilleDAO(); 
													          List<Famille> familles = new ArrayList<>(); 
													          familles = famDAO.lister(); 
													           %> 
																<select  class="form-control" name="famille" style="width:250px" required > -->
																
													            <%  
						                                			for(Famille famille : familles){ 
						                                		%> 
						                                			    <option value="<%=famille.getId()%>"><%=famille %></option> --%>
						                                		<%  
					                                			}
						                                		%> 
															</select> 
 														</div> 
													</div> 
 											</div> 
								
										<div class="panel-heading"><h3 class="panel-title">Nouvel Individu</h3></div>
										<div class="panel-body">
										
                                            <div class="form-group">
                                                <label for="prenom" class="form-control-label">Prénom</label>
                                                <input type="text" class="form-control" name ="prenom" id="prenom" placeholder="Entrer le prenom" style="width:250px" required >
                                            </div>
                                            
											</br>
											<div class="form-group">
                                                <label for="nom" class="form-control-label">Nom</label>
                                                <input type="text" class="form-control" name="nom" id="nom" placeholder="Entrer le nom" style="width:250px" required  >
                                            </div>
											</br>
											<div class="form-group">
												<label  for="dateNais">Date de Naissance</label>
												<input type="text" name="dateNais" placeholder="jj/mm/aaaa" class="form-control" id="dateNais" style="width:250px" required >
											</div>
											</br>
                                            <div class="form-group">
                                                <label class="form-control-label">Sexe</label></br>
                                                <div class="col-sm-10">
                                                    <select class="form-control" name="sexe" style="width:250px" required >
                                                        <option>Masculin</option>
                                                        <option>Féminin</option> 
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label for="ville" class="form-control-label">Ville</label>
                                                <input type="text" class="form-control" name="ville" id="ville" placeholder="La ville"  style="width:250px" required  >
                                            </div>
											</br>
											<div class="form-group">
                                                <label for="region" class="form-control-label">Region</label>
                                                <input type="text" class="form-control" name="region" id="region" placeholder="La region" style="width:250px" required >
                                            </div>
											</br>
											<div class="form-group">
                                                <label for="adresse" class="form-control-label">Adresse</label>
                                                <input type="text" class="form-control" name="adresse" id="adresse" placeholder="Adresse" style="width:250px"required >
                                            </div>
											</br>
											
											<div class="form-group">
                                                <label for="origine" class="form-control-label">Originaire de</label>
                                                <input type="text" class="form-control" name="origine" id="origine" placeholder="origine" style="width:250px" required >
                                            </div>
											</br>
											<div class="form-group">
                                                <label for="tel1" class="form-control-label">Numero de téléphone</label>
                                                <input type="text" class="form-control" name="tel1" id="tel1" placeholder="0500000000" style="width:250px" required >
                                            </div>
											</br>
											<div class="form-group">
                                                <label for="tel2" class="form-control-label">Deuxième numéro de téléphone</label>
                                                <input type="text" class="form-control" name="tel2" id="tel2" placeholder="0600000000" style="width:250px" required >
                                            </div>
											</br>
											<div class="form-group">
                                                <label class="form-control-label">Education</label>
                                                 </br> </br>
                                                <div class="col-sm-10">
                                                    <select class="form-control" name="education" style="width:250px"  required >
                                                        <option><%=Education.Aucune %></option>
                                                        <option><%=Education.Primaire %></option> 
                                                        <option><%=Education.Secondaire %></option>
                                                        <option><%=Education.Universitaire %></option>
                                                        <option><%=Education.Informelle %></option>
                                                        <option><%=Education.autre %></option>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="form-control-label">Niveau Socio-économique</label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" name="niveau" style="width:250px" required >
                                                        <option><%=NiveauSocial.BAS %></option>
                                                        <option><%=NiveauSocial.MOYEN %></option> 
														<option><%=NiveauSocial.HAUT %></option>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label class="control-label">Couverture Médicale</label>
                                                <div class="col-sm-10">
                                                    <select class="form-control" name="couverture" style="width:250px" required >
                                                        <option><%=CouvertureMedicale.Aucune %></option>
                                                        <option><%=CouvertureMedicale.Cnops %></option>
                                                        <option><%=CouvertureMedicale.Cnss %></option> 
                                                        <option><%=CouvertureMedicale.Privée %></option>
                                                        <option><%=CouvertureMedicale.Ramed %></option>
                                                        <option><%=CouvertureMedicale.Autre%></option>
                                                    </select>
                                                </div>
                                            </div>
											</br></br>
											<div class="form-group">
                                                <label for="occupation" class="form-control-label">Occupation</label>
                                                <input type="text" class="form-control" name="occupation" id="occupation" placeholder="Travail" style="width:250px" required  >
                                            </div>
											</br>
											
											<div class="form-group">
                                                <label for="residence" class="form-control-label">Résidence</label>
                                                </br> </br>
													<div class="checkbox checkbox-info checkbox-circle col-sm-10 col-sm-offeset-1">
														<input id="urbain" type="checkbox"  name="urbain" >
														<label for="urbain">
															Urbain
														</label>
													</div>
													<div class="checkbox checkbox-primary checkbox-circle col-sm-10 col-sm-offeset-1">
														<input id="rural" type="checkbox" name="rural">
														<label for="rural">
															Rural
														</label>
													</div>
                                            </div>
											
                                            </br></br>
                                            <button type="submit" class="btn btn-purple waves-effect waves-light">Enregistrer</button>
                                    
										</div><!-- panel-body -->
									</form>
                                </div> <!-- panel -->
                            </div> <!-- col-->
                            
                            <!-- Horizontal form -->
                            <div class="col-md-6">
                                <div class="panel panel-default">
                                
                                <div class="panel-heading"><h3 class="panel-title">Rechercher par Famille</h3></div>
											<div class="panel-body">
												 <form class="form-horizontal" role="form" action="RechercheParFamille.chu" method="post">
													<div class="form-group">
														<label class="col-sm-4 form-control-label">Famille</label>
														</br></br>
														<div class="col-sm-9">
                                                 			 <input type="text" name="famille" placeholder="alami" class="form-control" id="nomFamille" style="width:250px">
                                                		</div> 
														</br></br></br> 
													<div class="form-group m-b-0">
		                                                <div class="col-sm-offset-3 col-sm-9">
		                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Rechercher</button>
		                                                </div>
		                                            </div>
		                                           </form>
											</div>
                                </div>
                                			<%
                                			String nom_Famille = (String)session.getAttribute("nomFamille");
                                			IndividuDAO indDAO = new IndividuDAO();
                                			List<Individu> individus= new ArrayList<>();
                                			Integer  idFam =null;
                                			if(nom_Famille!=null){
//     										idFam = Integer.parseInt(id_Famille);
//     										int idFamille= idFam;
												if(nom_Famille!=""){
    										
    												individus = indDAO.listIndividuByNomFamille(nom_Famille);
												}
                                			}
    										
                                        			
                                        		%>
                                    
                                    
                                   		</br></br>
                                   		<div class="panel-heading"><h3 class="panel-title">Cas de deccès</h3></div>
											<div class="panel-body">
                                 		 <form class="form-horizontal" role="form" action="ajoutDecces.chu" method="post">
                                            
                                                <label for="date" class="col-sm-3 control-label">Date:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="dateDeces" placeholder="jj/mm/aaaa" class="form-control" id="dateDeces" style="width:250px">
                                                </div>
                                            	</br></br>
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Individu:</label>
                                                <div class="col-sm-8">
                                                
                                                    <select class="form-control" name="individu">
                                                    
                                                    
                                                    <% 
                                                    if(nom_Famille!=null){
                                        			if(!individus.isEmpty()){
                                                    for(Individu d : individus){
						                              %>
						                               <option value="<%=d.getId()%>"><%=d %></option>
						                              <% 
						                                }
                                        				}
                                                    }
						                              %>
                                                      
                                                    </select>
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Cause:</label>
                                                <div class="col-sm-8">
                                                <% 
                                                	MotifDeccesDAO motifDAO = new MotifDeccesDAO();
											            	List<MotifDecces> motifs = new ArrayList<>();
											            	motifs  = motifDAO.listerLesMotif();
											            %>
                                                    <select class="form-control" name="motif">
                                                        <%
                                                        	
				                                			for(MotifDecces motif : motifs ){
				                                				 %>	
				                                				<option value="<%=motif.getId() %>"><%=motif %>  </option>
				                                			<%
				                                			}
				                                		%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
									</form>
									</div>	
									</br></br>
									<div class="panel-heading"><h3 class="panel-title">Consanguinité</h3></div>
                                    <div class="panel-body">
                                        <form class="form-horizontal" role="form" action="ajoutConsang.chu" method="post">
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Individu:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="individu">
                                                        <% 
                                                        if(nom_Famille!=null){
                                                        if(!individus.isEmpty()){
                                                            for(Individu d1 : individus){
						                              %>
						                               <option value="<%=d1.getId()%>"><%=d1 %></option>
						                              <% 
						                                }
                                        				}
                                                        }
						                              %>
                                                    </select>
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label for="degre" class="col-sm-4 control-label" >Degré:</label>
                                                <input type="text" class="form-control" name="degre" id="degre" placeholder="degre" style="width:250px">
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
									</br></br>
									<div class="panel-heading"><h3 class="panel-title">Ajouter un couple</h3></div>
                                    <div class="panel-body">
                                        <form class="form-horizontal" role="form"action="ajoutCouple.chu" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Individu:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="ind1">
                                                       <% 
                                                       if(nom_Famille!=null){
                                                       if(!individus.isEmpty()){
                                                           for(Individu d2 : individus){
						                              %>
						                               <option value="<%=d2.getId()%>"><%=d2 %></option>
						                              <% 
						                                }
                                        				}
                                                       }
						                              %> 
                                                    </select>
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Individu:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="ind2">
                                                        <% 
                                                        if(nom_Famille!=null){
                                                        if(!individus.isEmpty()){
                                                            for(Individu d3 : individus){
						                              %>
						                               <option value="<%=d3.getId()%>"><%=d3 %></option>
						                              <% 
						                                }
                                        				}
                                                        }
						                              %>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
									</br></br>
									<div class="panel-heading"><h3 class="panel-title">Membre d'un couple</h3></div>
                                    <div class="panel-body">
                                        <form class="form-horizontal" role="form" action="ajoutMembre.chu" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Membre:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="menbre">
                                                        <%
                                                        if(nom_Famille!=null){
                                                        if(!individus.isEmpty()){
                                                            for(Individu d4 : individus){
						                              %>
						                               <option value="<%=d4.getId()%>"><%=d4 %></option>
						                              <% 
						                                }
                                        				}
                                                        }
						                              %>
                                                    </select>
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Couple:</label>
                                                <div class="col-sm-8">
                                                <%
											            	List<Couple> couples = new ArrayList<>();
                                                			if(nom_Famille!=null){
                                                				if(nom_Famille!=""){
											            			couples  = indDAO.listCoupleByNomFamille(nom_Famille);
                                                				}
                                                			}
// 															couples = indDAO.listerLesCouple();
											            %>
                                                    <select class="form-control" name="couple">
                                                        <%
											            	
                                                        if(nom_Famille!=null){
                                                            if(!couples.isEmpty()){
				                                			for(Couple couple : couples ){
				                                			%>
				                                				
				                                			  <option value ="<%=couple.getId()%>"><%=couple%> </option>
				                                			    <% }
                                                            }
                                                            }
				                                			
				                                			%>
				                                		
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
									</br></br>
									<div class="panel-heading"><h3 class="panel-title">Fils d'un couple</h3></div>
                                    <div class="panel-body">
                                        <form class="form-horizontal" role="form" action="ajoutFils.chu" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Fils:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="fils">
                                                       <%  
                                                       if(nom_Famille!=null){
                                                       if(!individus.isEmpty()){
                                                           for(Individu d5 : individus){
                                                        %>
						                               <option value="<%=d5.getId() %>"><%=d5 %></option>
						                             <% 
						                                }
                                        				}
                                                       }
                                                       
						                              %>
                                                    </select>
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Couple:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="couple">
                                                        <%
                                                        if(nom_Famille!=null){
                                                            if(!couples.isEmpty()){
											            
				                                			for(Couple couple : couples ){
				                                			%>
				                                				
				                                			  <option value =<%=couple.getId() %>> <%=couple %></option>
				                                			 <% 	
				                                			 }
                                                            }
				                                			
                                                        }
                                                        %> 
				                                		
				                                		 
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
									
                                </div> <!-- panel -->
                                <div class="panel panel-default">
                                    <div class="panel-heading"><h3 class="panel-title">Consentement  éclairé</h3></div>
                                    <div class="panel-body">
                                        <form class="form-horizontal" role="form" action="ajoutConsentement.chu" method="post">
											<div class="form-group">
                                                <label for="date" class="col-sm-3 control-label">Date:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="dateCons" placeholder="jj/mm/aaaa" class="form-control" id="dateCons" style="width:250px">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Individu:</label>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="individu">
                                                    <% 
                                                    if(nom_Famille!=null){
                                                    	
                                                    if(!individus.isEmpty()){
                                                        for(Individu d6 : individus){
						                              %>
						                               <option value="<%=d6.getId()%>"><%=d6 %></option>
						                              <% 
						                                }
                                        				}
                                                    }
						                              %>
                                        			
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-3 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div> <!-- panel-body -->
                            </div>
                            </div> <!-- col -->
							
				
            </div> 
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