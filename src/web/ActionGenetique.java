package web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BiologieDAO;
import dao.DossierDAO;
import dao.GenetiqueDAO;
import dao.HopitalDAO;
import dao.entities.Biologie;
import dao.entities.Deroulement;
import dao.entities.DossierMedicale;
import dao.entities.ExamenImagerie;
import dao.entities.Genetique;
import dao.entities.Hopital;
import dao.entities.Imagerie;
import dao.entities.InstMacro;
import dao.entities.Resultat;

public class ActionGenetique {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DossierDAO dosDAO;
	private HopitalDAO hopDAO;
	private GenetiqueDAO genDAO;

	public ActionGenetique() {
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

	public ActionGenetique(DossierDAO dosDAO, HopitalDAO hopDAO, GenetiqueDAO genDAO) {
		super();
		this.dosDAO = dosDAO;
		this.hopDAO = hopDAO;
		this.genDAO = genDAO;
	}

	public String ajoutGenetique(){
		String idDossier= request.getParameter("dossier");
		System.out.println("idDossier "+idDossier);
		int id_dossier =Integer.parseInt(idDossier);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		String idHopital= request.getParameter("hopital");
		int id_hopital =Integer.parseInt(idHopital);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
		String numero= request.getParameter("numero");
		int num =Integer.parseInt(numero);
        String instab= request.getParameter("instabiliteMacroscopique");
        InstMacro instabilite=null;
        if(instab!="") {
        	 instabilite = InstMacro.valueOf(instab);
        }
		
        String mutaBRAF= request.getParameter("mutaBRAF");
        boolean mutationBraf = Boolean.parseBoolean(mutaBRAF);
        String mutaKras= request.getParameter("mutaKras");
        Resultat mutationKras=null;
        if(mutaKras!="") {
        	 mutationKras = Resultat.valueOf(mutaKras);
        }
       
        String mutaAPC= request.getParameter("mutaAPC");
        boolean mutationAPC =  Boolean.parseBoolean(mutaAPC);
        String mutaMYH= request.getParameter("mutaMYH");
        boolean mutationMYH =  Boolean.parseBoolean(mutaMYH);
        String autre= request.getParameter("autre");
        System.out.println(dossier+" "+hopital+" "+numero+" "+mutationBraf+" "+mutaKras+" "
    		   +mutationAPC+" "+ mutationMYH+" "+autre  );
		Genetique gen= new Genetique(hopital, dossier,num, instabilite,mutationBraf,mutationKras,
		mutationAPC,mutationMYH,autre);
		genDAO.ajouterGenetique(gen);
		return "dossier.jsp";
	}

	public String suppGenetique() {
		String id_Patient = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPatient", id_Patient);
		return "/suppBiologie.jsp";
	}



	public String suppGenetiqueTrait() {
		/*String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		bioDAO.supprimerAnalyse(id);*/
		return "/suppBiologie.jsp";
	}

	public String modGen() {
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		Genetique genetique = genDAO.trouverGenetiqueById(id);
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("Genetique", genetique);
		return "/modGenetique.jsp";
	}

	public String modGenetiqueTrait() {

		String idGenetique = request.getParameter("idGenetique");
		int idAncienGenetique = Integer.parseInt(idGenetique);

		String idDossier= request.getParameter("dossier");
		System.out.println("idDossier "+idDossier);
		int id_dossier =Integer.parseInt(idDossier);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		String idHopital= request.getParameter("hopital");
		int id_hopital =Integer.parseInt(idHopital);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
		String numero= request.getParameter("numero");
		int num =Integer.parseInt(numero);
        String instab= request.getParameter("instabiliteMacroscopique");
        InstMacro instabilite=null;
        if(instab!="") {
        	 instabilite = InstMacro.valueOf(instab);
        }
		
        String mutaBRAF= request.getParameter("mutaBRAF");
        boolean mutationBraf = Boolean.parseBoolean(mutaBRAF);
        String mutaKras= request.getParameter("mutaKras");
        Resultat mutationKras=null;
        if(mutaKras!="") {
        	 mutationKras = Resultat.valueOf(mutaKras);
        }
       
        String mutaAPC= request.getParameter("mutaAPC");
        boolean mutationAPC =  Boolean.parseBoolean(mutaAPC);
        String mutaMYH= request.getParameter("mutaMYH");
        boolean mutationMYH =  Boolean.parseBoolean(mutaMYH);
        String autre= request.getParameter("autre");
        System.out.println(dossier+" "+hopital+" "+numero+" "+mutationBraf+" "+mutaKras+" "
    		   +mutationAPC+" "+ mutationMYH+" "+autre  );
		Genetique gen= new Genetique(hopital, dossier,num, instabilite,mutationBraf,mutationKras,
		mutationAPC,mutationMYH,autre);
		genDAO.modifierAnalyse(idAncienGenetique, gen);
		return "/dossier.jsp";
	}

	public String consultGenetique() {
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		Genetique biologie = genDAO.trouverGenetiqueById(id);
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("Genetique", biologie);
		return "detailGenetique.jsp";
	}
}
