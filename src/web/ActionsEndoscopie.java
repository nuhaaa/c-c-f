package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import dao.AspectMacroDAO;
import dao.CirconferenceDAO;
import dao.ComplicationDAO;
import dao.DossierDAO;
import dao.EndoAnormalDAO;
import dao.EndoscopieDAO;
import dao.ExamenPostOpDAO;
import dao.ExamenPreOpAnormalDAO;
import dao.ExamenPreOpDAO;
import dao.HopitalDAO;
import dao.IndividuDAO;
import dao.SiegeDAO;
import dao.TypeAndoscopieDAO;
import dao.TypeDAO;
import dao.entities.Anesthesie;
import dao.entities.AspectMacro;
import dao.entities.Circonference;
import dao.entities.Complication;
import dao.entities.DossierMedicale;
import dao.entities.EndoAnormal;
import dao.entities.Endoscopie;
import dao.entities.ExamenPostOp;
import dao.entities.ExamenPreOp;
import dao.entities.Hopital;
import dao.entities.MasseTumorale;
import dao.entities.Polypose;
import dao.entities.Siege;
import dao.entities.Stenose;
import dao.entities.SyndromesPostOp;
import dao.entities.TypeAndoscopie;

public class ActionsEndoscopie {
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private DossierDAO dosDAO;
	private HopitalDAO hopDAO;
	private TypeAndoscopieDAO typeDAO;
	private AspectMacroDAO aspectDAO;
	private CirconferenceDAO cirfDAO;
	private EndoscopieDAO endoDAO;
	private SiegeDAO siegeDAO;
	private ComplicationDAO compDAO ;
	private EndoAnormalDAO endoAnoDAO ;
	
	
	
	
	
	public ActionsEndoscopie(DossierDAO dosDAO, HopitalDAO hopDAO, TypeAndoscopieDAO typeDAO, AspectMacroDAO aspectDAO,
			CirconferenceDAO cirfDAO, EndoscopieDAO endoDAO, SiegeDAO siegeDAO, ComplicationDAO compDAO,
			EndoAnormalDAO endoAnoDAO) {
		super();
		this.dosDAO = dosDAO;
		this.hopDAO = hopDAO;
		this.typeDAO = typeDAO;
		this.aspectDAO = aspectDAO;
		this.cirfDAO = cirfDAO;
		this.endoDAO = endoDAO;
		this.siegeDAO = siegeDAO;
		this.compDAO = compDAO;
		this.endoAnoDAO = endoAnoDAO;
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

	public String ajoutEndoscopie(){
		
		List<MasseTumorale> masses = new ArrayList<>();
		List<Polypose> polyposes =new ArrayList<>();
		List<Complication> complications = new ArrayList<>();
		
		String idDossier = request.getParameter("dossier");
		int id_dossier =Integer.parseInt(idDossier);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		
		
		String idHopital= request.getParameter("hopital");
		
		int id_hopital =Integer.parseInt(idHopital);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
		
		String typeExamen= request.getParameter("typeExamen");
		int id_type =Integer.parseInt(typeExamen);
		TypeAndoscopie typeEndoscopie =typeDAO.trouverAspectMacroById(id_type);
		
		String date = request.getParameter("dateexamen");
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Date dateExamen = new Date();
		try {
			dateExamen = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String num= request.getParameter("numero");
		int numero =Integer.parseInt(num);
		
		String anest = request.getParameter("anesthesie");
		Anesthesie anestesie=null;
		if(anest.equalsIgnoreCase("locale")){
			anestesie= Anesthesie.LOCALE;
		} else if(anest.equalsIgnoreCase("sedation")){
			anestesie = Anesthesie.SEDATION;
		}else if(anest.equalsIgnoreCase("ANESTHESIE_GENERALE")){
			anestesie= Anesthesie.ANESTHESIE_GENERALE;
		}
	
		
			String idsiegeMasse= request.getParameter("siegeMasse");
			String idsiegePolype= request.getParameter("siegePolype");
			String[] tabComp = request.getParameterValues("complication");
		
			/*** file traitements ***/
			String save_dir = "CHU_Images\\endoscopie";
			String savePath = "";
			try {
				PrintWriter out = response.getWriter();
				savePath = "C:\\Users\\thinkpad\\git\\cancer-colorectal\\WebContent\\images" + File.separator + save_dir;
				File fileSaveDir = new File(savePath);

			} catch (IOException e) {
				e.printStackTrace();
			}
			Collection<Part> part;
			Vector<String> fileName= new Vector<>();
			try {
				part = request.getParts();
				fileName = extractFileName(part,savePath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Gson gson = new Gson();
			String files = gson.toJson(fileName);
			
			
		
		if(idsiegeMasse!=""){
			int idsiegeMas = Integer.parseInt(idsiegeMasse);
			Siege siegeMasse = siegeDAO.trouverSiegeById(idsiegeMas);
			String idaspectMasse= request.getParameter("aspectMasse");
			int idAspMasse = Integer.parseInt(idaspectMasse);
			
			AspectMacro aspectMasse =  aspectDAO.trouverAspectMacroById(idAspMasse);
			
			String idcirconferenceMasse= request.getParameter("circonferenceMasse");
			int idCirMasse = Integer.parseInt(idcirconferenceMasse);
			Circonference circonfMasse = cirfDAO.trouverAspectMacroById(idCirMasse);
			
			String tailMasse= request.getParameter("tailleMasse");
			Float tailleMasse = Float.parseFloat(tailMasse);
			
			String steno= request.getParameter("stenose");
			Stenose stenose = Stenose.valueOf(steno);
			
			String refAnaPathMasse= request.getParameter("refAnaPathMasse");
			MasseTumorale masse = new MasseTumorale(siegeMasse, tailleMasse, stenose, aspectMasse, circonfMasse,  refAnaPathMasse);
			
			masses.add(masse);
		}
		if(idsiegePolype!=""){
			int idsiegePol = Integer.parseInt(idsiegePolype);
			Siege siegePolype = siegeDAO.trouverSiegeById(idsiegePol);
			String nbre= request.getParameter("nbre");
			int nombre = Integer.parseInt(nbre);
			String nbre1= request.getParameter("nbre1");
			int nombre1 = Integer.parseInt(nbre1);
			
			String idaspectPolype= request.getParameter("aspectPolype");
			int idAspPolype = Integer.parseInt(idaspectPolype);
			AspectMacro aspectPolype = aspectDAO.trouverAspectMacroById(idAspPolype);
			
			String tailePolype= request.getParameter("taillePolype");
			Float taillePolype =Float.parseFloat(tailePolype);
			
			String couleur= request.getParameter("couleur");
			
			String refAnaPathPolype= request.getParameter("refAnaPathPolype");
			
			Polypose polypose = new Polypose(nombre, nombre1, taillePolype, couleur, null, aspectPolype, siegePolype, refAnaPathPolype);
			
			polyposes.add(polypose);
		}
		if(tabComp!=null){
			
			try { 
				for (int i = 0; i < tabComp.length; ++i){ 
				
				String complication = tabComp[i];
				int id_Complication = Integer.parseInt(complication);
				Complication comp =compDAO.trouverAspectMacroById(id_Complication);
				complications.add(comp);
				} 
			} catch (Exception e1) { 
			//e1.printStackTrace(); 
			}
		}
		if(idsiegeMasse==""&& idsiegePolype==""&&tabComp==null){
			Endoscopie endo = new Endoscopie(hopital, dossier, dateExamen, typeEndoscopie, numero, anestesie,files);
			endoDAO.ajouterEndoscopie(endo);
		}
		if(idsiegeMasse!="" ||idsiegePolype!=""||tabComp==null){
			EndoAnormal endoAnormal = new EndoAnormal(hopital, dossier, dateExamen, typeEndoscopie, numero, anestesie, masses, polyposes, complications,files);
			endoAnoDAO.ajouterEndoAnormal(endoAnormal);
		}
		
		return "/dossier.jsp";
	}
	public String suppEndoscopieTraitNormal(){
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		EndoscopieDAO examen =  new EndoscopieDAO();
		examen.supprimerEndoscopie(id);
		return "/suppEndoscopie.jsp";
	}
	public String suppEndoscopieTraitAnormal(){
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		EndoAnormalDAO examen =  new EndoAnormalDAO();
		examen.supprimerEndoAnormal(id);
		return "/suppEndoscopie.jsp";
	}
	
	public String modEndoscopieNormal(){
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		EndoscopieDAO examenDAO =  new EndoscopieDAO();
		Endoscopie examen = examenDAO.trouverEndoById(id);
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("Endoscopie", examen);
		return "/modEndoscopiePre.jsp";
	}
	
	public String suppEndoscopie(){
		String id_Patient = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPatient", id_Patient);
		return "/suppEndoscopie.jsp";
	}
	public String modEndoscopie(){
		String id_Patient = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPatient", id_Patient);
		return "/modEndoscopie.jsp";
	}
	public String modEndoscopieTrait(){
		
		String idExamen = request.getParameter("idExamen");
		int idAncienExamen= Integer.parseInt(idExamen);
		
		List<MasseTumorale> masses = new ArrayList<>();
		List<Polypose> polyposes = new ArrayList<>();
		List<Complication> complications = new ArrayList<>();
		
		String idDossier= request.getParameter("dossier");
		int id_dossier =Integer.parseInt(idDossier);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		
		
		String idHopital= request.getParameter("hopital");
		
		int id_hopital =Integer.parseInt(idHopital);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
		
		String typeExamen= request.getParameter("typeExamen");
		int id_type =Integer.parseInt(typeExamen);
		TypeAndoscopie typeEndoscopie =typeDAO.trouverAspectMacroById(id_type);
		
		String date = request.getParameter("dateexamen");
		
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Date dateExamen = new Date();
		try {
			dateExamen = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String num= request.getParameter("numero");
		int numero =Integer.parseInt(num);
		
		String anest= request.getParameter("anesthesie");
		
		Anesthesie anestesie=null;
		if(anest.equalsIgnoreCase("locale")){
			anestesie= Anesthesie.LOCALE;
		} else if(anest.equalsIgnoreCase("sedation")){
			anestesie = Anesthesie.SEDATION;
		}else if(anest.equalsIgnoreCase("ANESTHESIE_GENERALE")){
			anestesie= Anesthesie.ANESTHESIE_GENERALE;
		}
		
			String idsiegeMasse= request.getParameter("siegeMasse");
			String idsiegePolype= request.getParameter("siegePolype");
			String[] tabComp = request.getParameterValues("complication");
		
			
		
		if(idsiegeMasse!=""){
			int idsiegeMas = Integer.parseInt(idsiegeMasse);
			Siege siegeMasse = siegeDAO.trouverSiegeById(idsiegeMas);
			String idaspectMasse= request.getParameter("aspectMasse");
			int idAspMasse = Integer.parseInt(idaspectMasse);
			
			AspectMacro aspectMasse =  aspectDAO.trouverAspectMacroById(idAspMasse);
			
			String idcirconferenceMasse= request.getParameter("circonferenceMasse");
			int idCirMasse = Integer.parseInt(idcirconferenceMasse);
			Circonference circonfMasse = cirfDAO.trouverAspectMacroById(idCirMasse);
			
			String tailMasse= request.getParameter("tailleMasse");
			Float tailleMasse = Float.parseFloat(tailMasse);
			
			String steno= request.getParameter("stenose");
			Stenose stenose = Stenose.valueOf(steno);
			
			String refAnaPathMasse= request.getParameter("refAnaPathMasse");
			MasseTumorale masse = new MasseTumorale(siegeMasse, tailleMasse, stenose, aspectMasse, circonfMasse,  refAnaPathMasse);
			
			masses.add(masse);
		}
		if(idsiegePolype!=""){
			int idsiegePol = Integer.parseInt(idsiegePolype);
			Siege siegePolype = siegeDAO.trouverSiegeById(idsiegePol);
			String nbre= request.getParameter("nbre");
			int nombre = Integer.parseInt(nbre);
			String nbre1= request.getParameter("nbre1");
			int nombre1 = Integer.parseInt(nbre1);
			
			String idaspectPolype= request.getParameter("aspectPolype");
			int idAspPolype = Integer.parseInt(idaspectPolype);
			AspectMacro aspectPolype = aspectDAO.trouverAspectMacroById(idAspPolype);
			
			
			
			String tailePolype= request.getParameter("taillePolype");
			Float taillePolype =Float.parseFloat(tailePolype);
			
			String couleur= request.getParameter("couleur");
			
			String refAnaPathPolype= request.getParameter("refAnaPathPolype");
			
			Polypose polypose = new Polypose(nombre, nombre1, taillePolype, couleur, null, aspectPolype, siegePolype, refAnaPathPolype);
			
			polyposes.add(polypose);
		}
		if(tabComp!=null){
			
			try { 
				for (int i = 0; i < tabComp.length; ++i){ 
				
				String complication = tabComp[i];
				int id_Complication = Integer.parseInt(complication);
				Complication comp =compDAO.trouverAspectMacroById(id_Complication);
				complications.add(comp);
				} 
			} catch (Exception e1) { 
			//e1.printStackTrace(); 
			}
		}
		if(idsiegeMasse != "" || idsiegePolype != "" || tabComp != null){
			EndoAnormal endoAnormal = new EndoAnormal(hopital, dossier, dateExamen, typeEndoscopie, numero, anestesie, masses, polyposes, complications,null);
			endoAnoDAO.modifierEndoscopie(idAncienExamen, endoAnormal);
		}
		else {
			Endoscopie endo = new Endoscopie(hopital, dossier, dateExamen, typeEndoscopie, numero, anestesie,null);
			endoAnoDAO.modifierEndoscopie(idAncienExamen, endo);
		}
		
		return "/dossier.jsp";
	}
	
	private Vector<String> extractFileName(Collection<Part> parts,String savePath) {
		Vector<String> filenames = new Vector<>();
		for (Part part : parts) {
			String contentDiap = part.getHeader("content-disposition");
			String[] items = contentDiap.split(";");
			for (String item : items) {
				if (item.trim().startsWith("filename")) {
					String fileName=item.substring(item.indexOf("=") + 2, item.length() - 1);
					filenames.add(fileName);
					try {
						part.write(savePath + File.separator + fileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return filenames;
	}
}
