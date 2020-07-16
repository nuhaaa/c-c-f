package web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BiologieDAO;
import dao.ComplicationDAO;
import dao.DossierDAO;
import dao.ElargissementDAO;
import dao.GesteDAO;
import dao.HopitalDAO;
import dao.RRscoringDAO;
import dao.TraitementDAO;
import dao.TypeExereseDAO;
import dao.TypeInterventionDAO;
import dao.UtilisateurDAO;
import dao.entities.Administrateur;
import dao.entities.Biologie;
import dao.entities.Chimiotherapie;
import dao.entities.Chirurgie;
import dao.entities.Complication;
import dao.entities.Deroulement;
import dao.entities.DossierMedicale;
import dao.entities.Elargissement;
import dao.entities.Geste;
import dao.entities.Hopital;
import dao.entities.Imagerie;
import dao.entities.Infirmier;
import dao.entities.Medecin;
import dao.entities.RRscoring;
import dao.entities.Radiotherapie;
import dao.entities.Traitement;
import dao.entities.TraitementEndoscopique;
import dao.entities.TypeExerese;
import dao.entities.TypeIntervention;
import dao.entities.Utilisateur;

public class ActionsUser {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private UtilisateurDAO userDAO;

	public ActionsUser(UtilisateurDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String authenticate() {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		Object[] user = (new UtilisateurDAO()).authentification(login, password);
		System.out.println("utilisateuuuur : " + user[2]);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("nom", user[0]);
			session.setAttribute("prenom", user[1]);
			if ("infirmier".equals(user[2])) {
				return "/espaceUtilisateur.jsp";
			}
			if ("medecin".equals(user[2])) {
				return "/espaceMedecin.jsp";
			}
			if ("admin".equals(user[2])) {
				return "/espaceAdmin.jsp";
			}
		} else {
			return "/index.jsp";
		}
		return null;
	}

	public String AjouterUtilisateur() {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String pwd = request.getParameter("password");
		String role = request.getParameter("role");
		Utilisateur newUser = null;
		if (role.equals("Medecin")) {
			// newUser = new Medecin(nom, prenom, email, login, pwd);
		} else if (role.equals("Infirmier")) {
			// newUser = new Infirmier(nom, prenom, email, login, pwd);
		} else if (role.equals("Utilisateur")) {
			newUser = new Utilisateur(nom, prenom, email, login, pwd);
		}
		userDAO.creerNouveauCompte(newUser);
		return "gestionUtilisateurs.jsp";
	}

	public String ModifierUtilisateur() {
		String id = request.getParameter("idUser");
		int idUser = Integer.parseInt(id);
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String pwd = request.getParameter("password");
		String role = request.getParameter("role");
		Utilisateur newUser = null;
		if (role.equals("Medecin")) {
			// newUser = new Medecin(nom, prenom, email, login, pwd);
		} else if (role.equals("Infirmier")) {
			// newUser = new Infirmier(nom, prenom, email, login, pwd);
		} else if (role.equals("Utilisateur")) {
			newUser = new Utilisateur(nom, prenom, email, login, pwd);
		}
		userDAO.mettreAjourProfile(idUser, newUser);
		return "gestionUtilisateurs.jsp";
	}

	public String SupprimerUtilisateur() {
		String idU = request.getParameter("id");
		int id = Integer.parseInt(idU);
		userDAO.supprimerUtilisateur(id);
		return "gestionUtilisateurs.jsp";
	}

	public String modUser() {
		String id = request.getParameter("id");
		int idUser = Integer.parseInt(id);
		Utilisateur user = userDAO.listerUserParId(idUser);
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("user", user);
		return "modUtilisateur.jsp";
	}

	public String ConsulterUtilisateur() {
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		Utilisateur user = userDAO.listerUserParId(id);
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("Utilisateur", user);
		return "detailUtilisateur.jsp";
	}
}
