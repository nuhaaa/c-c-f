package web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BiologieDAO;
import dao.DossierDAO;
import dao.HopitalDAO;
import dao.entities.Biologie;
import dao.entities.DossierMedicale;
import dao.entities.ExamenImagerie;
import dao.entities.Hopital;
import dao.entities.Imagerie;

public class ActionsBiologie {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DossierDAO dosDAO;
	private HopitalDAO hopDAO;
	private BiologieDAO bioDAO;
	public ActionsBiologie() {
		super();
		// TODO Auto-generated constructor stub
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
	
	public ActionsBiologie(DossierDAO dosDAO, HopitalDAO hopDAO, BiologieDAO bioDAO) {
		super();
		this.dosDAO = dosDAO;
		this.hopDAO = hopDAO;
		this.bioDAO = bioDAO;
	}
	public String ajoutBiologie(){
		String idDossier= request.getParameter("dossier");
		int id_dossier =Integer.parseInt(idDossier);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		String idHopital= request.getParameter("hopital");
		int id_hopital =Integer.parseInt(idHopital);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
//		String medecin= request.getParameter("medecin");
		String date= request.getParameter("dateBiologie");
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Date dateBiologie = new Date();
		try {
			dateBiologie = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String analyse= request.getParameter("analyse");
		
		String val= request.getParameter("valeur");
		Float valeur = Float.parseFloat(val);
		
		Biologie bio= new Biologie(hopital, dossier, analyse, valeur, dateBiologie);
		bioDAO.ajouterAnalyse(bio);
		return "/dossier.jsp";
	}
	public String suppBiologie(){
		String id_Patient = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPatient", id_Patient);
		return"/suppBiologie.jsp";
	}
	public String modBiologie(){
		String id_Patient = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPatient", id_Patient);
		return "/modBiologie.jsp";
	}
	public String suppBiologieTrait(){
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		bioDAO.supprimerAnalyse(id);
		return "/suppBiologie.jsp";
	}
	public String modBiol(){
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		Biologie biologie = bioDAO.trouverBiologieById(id);
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("Biologie", biologie);
		return "/modBiologieF.jsp";
	}
	public String modBiologieTrait(){
		
		String idBiologie = request.getParameter("idBiologie");
		int idAncienBiologie= Integer.parseInt(idBiologie);
		
		String idDossier= request.getParameter("dossier");
		int id_dossier =Integer.parseInt(idDossier);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		String idHopital= request.getParameter("hopital");
		int id_hopital =Integer.parseInt(idHopital);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
//		String medecin= request.getParameter("medecin");
		String date= request.getParameter("dateBiologie");
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Date dateBiologie = new Date();
		try {
			dateBiologie = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String analyse= request.getParameter("analyse");
		
		String val= request.getParameter("valeur");
		Float valeur = Float.parseFloat(val);
		
		Biologie bio= new Biologie(hopital, dossier, analyse, valeur, dateBiologie);
		bioDAO.modifierAnalyse(idAncienBiologie, bio);
		return "/dossier.jsp";
	}
	public String consultBiologie() {
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		Biologie biologie = bioDAO.trouverBiologieById(id);
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("Biologie", biologie);
		return "detailBiologie.jsp";
	}
}
