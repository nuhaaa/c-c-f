<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="dao.*" %>
<%@ page import="dao.entities.*" %>
<%@ page import="beans.*" %>
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
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


</head>

<body>

    <div id="wrapper">
	<%@include file="enteteUt.jsp" %>

        <div id="page-wrapper">

            <div class="container-fluid">
				<%
			            	Individu ind = new Individu();
			            	ind =(Individu)session.getAttribute("Individu");
			            	int  idFamille = ind.getFamille().getId();
			    %>
                <!-- Page Heading -->
                <div class="row">
                <h3 class="page-header" style="text-align:center">
                            <img class="img"  src="images\logo.png " width="" height="" alt="logo"/><small></small>
                   </h3>
                  <h3 class="page-header">
                            Completer les informations sur <%=ind.toString() %> <small></small>
                   </h3>
                <!-- /.row -->
				
				<div class="row">
				
					<div class="col-md-6 ">
						<div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">Déclarer un cas de deccès</h3></div>
								<div class="panel-body">
									<form class="form-horizontal" role="form" action="ajoutDecces.chu" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Défunt:</label>
                                                <div >
                                                    <input type="text" name="" value="<%=ind.toString() %>" class="form-control"  style="width:250px">
                                                    <input type="hidden" name="individu" value="<%=ind.getId() %>" class="form-control"  style="width:250px">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="date" class="col-sm-2 control-label">Date:</label>
                                                <div >
                                                  <input type="text" name="dateDeces" placeholder="jj/mm/aaaa" class="form-control" id="dateDeces" style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label class="col-sm-2 control-label">Cause:</label>
                                                <div class="col-sm-6">
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
                                                <div class="col-sm-offset-6 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
									</form>
								</div>
							<div class="panel-heading"><h3 class="panel-title">Déclarer une consanguinité</h3></div>
								<div class="panel-body">
									<form class="form-horizontal" role="form" action="ajoutConsang.chu" method="post">
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Membre concerné:</label>
                                                <div >
                                                    <input type="text" name="" value="<%=ind.toString() %>" class="form-control"  style="width:250px">
                                                    <input type="hidden" name="individu" value="<%=ind.getId() %>" class="form-control"  style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label for="degre" class="col-sm-4 control-label" >Degré:</label>
                                                <input type="text" class="form-control" name="degre" id="degre" placeholder="degre" style="width:250px">
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-6 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
								</div>
							<div class="panel-heading"><h3 class="panel-title">Enregistrer consentement signé</h3></div>
								<div class="panel-body">
									 <form class="form-horizontal" role="form" action="ajoutConsentement.chu" method="post">
											
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">Individu:</label>
                                                <div class="col-sm-8">
                                                	<input type="text" name="" value="<%=ind.toString() %>" class="form-control"  style="width:250px">
                                                	<input type="hidden" name="individu" value="<%=ind.getId() %>" class="form-control"  style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label for="date" class="col-sm-3 control-label">Date:</label>
                                                <div class="col-sm-9">
                                                  <input type="text" name="dateCons" placeholder="jj/mm/aaaa" class="form-control" id="dateCons" style="width:250px">
                                                </div>
                                            </div>
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-6 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
								</div>
						</div>
                    </div>
					<div class="col-md-6 ">
						<div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title">Ajouter un couple</h3></div>
								<div class="panel-body">
									<form class="form-horizontal" role="form"action="ajoutCouple.chu" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Femme:</label>
                                                <%
                                                List<Individu> individus= new ArrayList<>();
                                                IndividuDAO indDAO = new IndividuDAO();
                                                String nom_Famille= ind.getFamille().getNomFamille();
                                                individus = indDAO.listIndividuByNomFamille(nom_Famille);
                                                %>
                                                <div class="col-sm-8">
                                                    <select class="form-control" name="ind1">
                                                    <option>Inconue</option>
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
                                                <label class="col-sm-4 control-label">Mari:</label>
                                                 <div class="col-sm-8">
                                                    <select class="form-control" name="ind2">
                                                    <option>Inconu</option>
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
                                            <div class="form-group m-b-0">
                                                <div class="col-sm-offset-6 col-sm-9">
                                                  <button type="submit" class="btn btn-info waves-effect waves-light">Enregistrer</button>
                                                </div>
                                            </div>
                                        </form>
								</div>
							<div class="panel-heading"><h3 class="panel-title">Completer un couple</h3></div>
								<div class="panel-body">
									 <form class="form-horizontal" role="form" action="ajoutMembre.chu" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Conjoint:</label>
                                                <div class="col-sm-8">
                                                	<input type="text" name="" value="<%=ind.toString() %>" class="form-control"  style="width:250px">
                                                	<input type="hidden" name="menbre" value="<%=ind.getId() %>" class="form-control"  style="width:250px">
                                                </div>
                                            </div>
											<div class="form-group">
                                                <label class="col-sm-4 control-label">Couple:</label>
                                                <div class="col-sm-8">
                                                <%      	
                                                List<Couple> couples = new ArrayList<>();
											    couples  = indDAO.listCoupleByNomFamille(nom_Famille);			
											    %>
                                                <select class="form-control" name="couple">
                                                    
                                                <% 	
                                                   if(nom_Famille!=null){
                                                      if(!couples.isEmpty()){
				                                		for(Couple couple : couples ){	
				                                %>
				                                <option value ="<%=couple.getId()%>"><%=couple%> </option>
				                               <%    }
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
							<div class="panel-heading"><h3 class="panel-title">Déclarer un fils d'un couple</h3></div>
								<div class="panel-body">
									<form class="form-horizontal" role="form" action="ajoutFils.chu" method="post">
                                            <div class="form-group">
                                                <label class="col-sm-4 control-label">Fils:</label>
                                                <div class="col-sm-8">
                                                	<input type="text" name="" value="<%=ind.toString() %>" class="form-control"  style="width:250px">
                                                	<input type="hidden" name="fils" value="<%=ind.getId() %>" class="form-control"  style="width:250px">
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
						</div>
                    </div>					
				</div>
				
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    </div>
    </body>
    
	<%@include file="piedUt.jsp" %>
</html>
    