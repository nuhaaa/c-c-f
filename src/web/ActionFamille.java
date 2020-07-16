package web;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DossierDAO;
import dao.FamilleDAO;
import dao.IndividuDAO;
import dao.SyndromeFamilleDAO;
import dao.entities.DossierMedicale;
import dao.entities.Famille;
import dao.entities.Individu;
import dao.entities.SyndromeFamille;


public class ActionFamille {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	 
	private FamilleDAO familleDAO;
	private IndividuDAO indDAO;
	private DossierDAO dosDAO;
	
	
	
	public ActionFamille(FamilleDAO familleDAO, IndividuDAO indDAO,DossierDAO dosDAO) {
		super();
		this.familleDAO = familleDAO;
		this.indDAO = indDAO;
		this.dosDAO = dosDAO;
	}

	public String listInd(){
		String idf = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idFamille", idf);
		return "/listeIndividu.jsp";
	}
	public String listIndDoss(){
		String idf = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idFamille", idf);
		return "/listeIndDos.jsp";
	}
	public String modFamille(){
		String idf = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idFamille", idf);
		return "/formFamille.jsp";
	}
	public String modFamilleTrait(){
		String idAncienFamille = request.getParameter("idAncienFamille");
		int idAncien = Integer.parseInt(idAncienFamille);
		String nomFamille = request.getParameter("nomFamille");
		String diagnostic = request.getParameter("diagnostic");
		System.out.println("diagnostic :"+diagnostic);
		int id = Integer.parseInt(diagnostic);
		
		SyndromeFamilleDAO syndDAO = new SyndromeFamilleDAO();
		
		SyndromeFamille syndrome = syndDAO.trouverSyndById(id);
		
		Famille famille = new Famille(nomFamille, syndrome);
		
		
		familleDAO.modifierFamille(idAncien, famille);
		return "/modFamille.jsp";
	}
	public String modifier(){
		String idD = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idDos", idD);
		return "/modDossier.jsp";
		
	}
	public String modifierTrait(){
		String iddos = request.getParameter("idDossier");
		int idDossierAncien = Integer.parseInt(iddos);
		String ind = request.getParameter("patient");
		int id = Integer.parseInt(ind);
		Individu patient = indDAO.trouverIndById(id);
		
		String date = request.getParameter("dateDossier");
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Date dateDossier = new Date();
		try {
			dateDossier = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DossierMedicale dossier = new DossierMedicale(dateDossier, patient);
		
		
		dosDAO.modifierDossier(idDossierAncien, dossier);
		
		return "/gestionDossier.jsp";
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	

}
