package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanDossier;
import dao.DossierDAO;
import dao.EndoscopieDAO;
import dao.IndividuDAO;
import dao.entities.Endoscopie;
import dao.entities.Individu;


public class ActionExamenMedical {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DossierDAO dosDAO;
	private EndoscopieDAO endoDAO ;
	
	public ActionExamenMedical(DossierDAO dosDAO, EndoscopieDAO endoDAO) {
		super();
		this.dosDAO = dosDAO;
		this.endoDAO = endoDAO;
	}
	public String TrouverIdDossierSession(){
		String idDossier = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idDossier", idDossier);
		return "/dossier.jsp";
	}
	public String rechercheDossier(){
		String nom = request.getParameter("famille");
		BeanDossier bean =  new BeanDossier();
//		bean.setId(id_Famille);
		bean.setDossiers(dosDAO.listeDossierByNonFamille(nom));
		HttpSession session =  request.getSession();
		session.setAttribute("beanDossier", bean);
		return "/examens.jsp";
	}
	public String dossier(){
		String idInd = request.getParameter("id");
//		int id = Integer.parseInt(idInd);
//		IndividuDAO indDAO = new  IndividuDAO();
//		Individu ind = indDAO.trouverIndById(id);
		HttpSession session = request.getSession();
		session.setAttribute("Individu", idInd);
		return "/ajoutDossier.jsp";
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
	
	public String examEndoDetail() {
		String idEndo = request.getParameter("id");
		int id = Integer.parseInt(idEndo);
		Endoscopie endo = endoDAO.trouverEndoById(id);
		HttpSession session = request.getSession();
		session.setAttribute("endo", endo);
		return "/endoDetail.jsp";	
	}
	public String examPolyDetail() {
		String idPoly = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPoly", idPoly);
		return "/polyDetail.jsp";	
	}
	public String rapportMedical() {
		String idDossier = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idDossier", idDossier);
		return "/rapportMedical.jsp";	
	}
}
