package dao;


import dao.entities.Administrateur;
import dao.entities.Infirmier;
import dao.entities.Medecin;
import dao.entities.Utilisateur;


public class Authentifieur {
	/* public static Utilisateur getUtilisateur(Class classe, String login , String pwd){
	        if(classe == Medecin.class){
	            MedecinDAO medDAO= new MedecinDAO();
	            return medDAO.anthentification(login, pwd);
	        }
	        else if(classe == Infirmier.class){
	            InfirmierDao infDAO = new InfirmierDao();
	            return infDAO.anthentification(login, pwd);
	        } else if(classe == Administrateur.class){
	            AdminDAO adminDAO = new AdminDAO();
	            return adminDAO.anthentification(login, pwd);
	        }
	        else    return null;
	    }*/
}
