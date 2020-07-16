<!DOCTYPE html>
<html lang="fr">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CRF-Data</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="assetss/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assetss/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="assetss/css/form-elements.css">
        <link rel="stylesheet" href="assetss/css/style.css">
		<link rel="stylesheet" href="back.css">
    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                	
                    <div class="row">
						<div class="col-sm-2">
							<img src="images/logooo.png" >
						</div>
                        <div class="col-sm-8 text">
                            <h1><strong>Cancers Colorectaux Familiaux</strong> </h1>
                            <div class="description">
                            	<p>
	                            	<strong>Registre hospitalier pour le suivi et le depistage des patients atteints d'un cancer colorectal familial.</strong> 

                            	</p>
                            </div>
                        </div>
						<div class="col-sm-2">
							<img src="images/logoo.png" >
						</div>
                    </div>
                    
                    <div class="row">
                        <div class="col-sm-5">
                        	
                        	<div class="form-box">
	                        	<div class="form-top">
	                        		<div class="form-top-left">
	                        			<h3>Se Connecter</h3>
	                            		<p>Entrer votre pseudo et mot de passe :</p>
	                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-lock"></i>
	                        		</div>
	                            </div>
	                            <div class="form-bottom">
				                    <form role="form" action="Connexion.chu" method="post" class="login-form">
				                    	<div class="form-group">
				                    		<label class="sr-only" for="form-username">Username</label>
				                        	<input type="text" name="login" placeholder="Pseudo..." class="form-username form-control" id="form-username">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-password">Password</label>
				                        	<input type="password" name="password" placeholder="Mot de passe..." class="form-password form-control" id="form-password">
				                        </div>
				                        <button type="submit" class="btn">Se connecter!</button>
				                    </form>
			                    </div>
		                    </div>
		                
		               
	                        
                        </div>
                        
                        <div class="col-sm-1 middle-border"></div>
                        <div class="col-sm-1"></div>
                        	
                        <div class="col-sm-5">
                        	
                        	<div class="form-box">
                        		<div class="form-top">
	                        		<div class="form-top-left">
	                        			<h3>S'inscrire</h3>
	                            		<p>Veuillez fournir vos informations:</p>
	                        		</div>
	                        		<div class="form-top-right">
	                        			<i class="fa fa-pencil"></i>
	                        		</div>
	                            </div>
	                            <div class="form-bottom">
				                    <form role="form"  action="Registre.chu" method="post" class="registration-form" >
				                    	<div class="form-group">
				                    		<label class="sr-only" for="form-first-name">First name</label>
				                        	<input type="text" name="nom" placeholder="Nom..." class="form-first-name form-control" id="form-first-name">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-last-name">Last name</label>
				                        	<input type="text" name="prenom" placeholder="Prenom..." class="form-last-name form-control" id="form-last-name">
				                        </div>
				                        <div class="form-group">
				                        	<label class="sr-only" for="form-email">Email</label>
				                        	<input type="email" name="email" placeholder="Email..." class="form-email form-control" id="form-email">
				                        </div>
										<div class="form-group">
				                        	<label class="sr-only" for="form-email">Password</label>
				                        	<input type="password" name="password" placeholder="Mot de passe..." class="form-email form-control" id="form-email">
				                        </div>
										<div class="form-group">
				                        	<label class="sr-only" for="form-email">Password</label>
				                        	<input type="password" name="confirm-password" placeholder="Confirmer mot de passe..." class="form-email form-control" id="form-email">
				                        </div>
										<div class="form-group">
				                        	<label class="sr-only" for="form-email">Date</label>
				                        	<input type="date" name="date" placeholder="jj/mm/aaaa" class="form-email form-control" id="form-email">
				                        </div>
				                        
				                        <button type="submit" class="btn">S'inscrire!</button>
				                    </form>
			                    </div>
                        	</div>
                        	
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>

        <!-- Footer -->
        <footer>
        	<div class="container">
        		<div class="row">
        			
        			<div class="col-sm-8 col-sm-offset-2">
        				<div class="footer-border"></div>
        				<p>Copyright <a href="" target="_blank"><strong>CRF-Data</strong></a> 
        					2016. <i class="fa fa-smile-o"></i></p>
        			</div>
        			
        		</div>
        	</div>
        </footer>

        <!-- Javascript -->
        <script src="assetss/js/jquery-1.11.1.min.js"></script>
        <script src="assetss/bootstrap/js/bootstrap.min.js"></script>
        <script src="assetss/js/jquery.backstretch.min.js"></script>
        <script src="assetss/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="assetss/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>