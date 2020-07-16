package web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ComplicationDAO;
import dao.DossierDAO;
import dao.ElargissementDAO;
import dao.GesteDAO;
import dao.HopitalDAO;
import dao.RRscoringDAO;
import dao.TraitementDAO;
import dao.TypeExereseDAO;
import dao.TypeInterventionDAO;
import dao.entities.Chimiotherapie;
import dao.entities.Chirurgie;
import dao.entities.Complication;
import dao.entities.Deroulement;
import dao.entities.DossierMedicale;
import dao.entities.Elargissement;
import dao.entities.Geste;
import dao.entities.Hopital;
import dao.entities.Imagerie;
import dao.entities.RRscoring;
import dao.entities.Radiotherapie;
import dao.entities.Traitement;
import dao.entities.TraitementEndoscopique;
import dao.entities.TypeExerese;
import dao.entities.TypeIntervention;

public class ActionsTraitement {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private TypeExereseDAO typeExDAO;
	private ElargissementDAO elargDAO;
	private GesteDAO gestDAO;
	private RRscoringDAO scoringDAO;
	private ComplicationDAO compDAO;
	private TypeInterventionDAO typInterDAO;
	private DossierDAO dosDAO;
	private HopitalDAO hopDAO;
	private TraitementDAO traitDAO;

	public ActionsTraitement() {
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

	public ActionsTraitement(TypeExereseDAO typeExDAO, ElargissementDAO elargDAO, GesteDAO gestDAO,
			RRscoringDAO scoringDAO, ComplicationDAO compDAO, TypeInterventionDAO typInterDAO, DossierDAO dosDAO,
			HopitalDAO hopDAO, TraitementDAO traitDAO) {
		super();
		this.typeExDAO = typeExDAO;
		this.elargDAO = elargDAO;
		this.gestDAO = gestDAO;
		this.scoringDAO = scoringDAO;
		this.compDAO = compDAO;
		this.typInterDAO = typInterDAO;
		this.dosDAO = dosDAO;
		this.hopDAO = hopDAO;
		this.traitDAO = traitDAO;
	}

	public String ajoutTraitement() {
		String doss = request.getParameter("dossier");
		String indicat = request.getParameter("indication");
		String hop = request.getParameter("hopital");

		String dateT = request.getParameter("dateTrait");

		String temp = request.getParameter("temps");
		Chirurgie chig = null;
		if (temp != "") {
			int temps = Integer.parseInt(temp);
			String date = request.getParameter("date");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateChu = new Date();
			try {
				dateChu = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String service = request.getParameter("service");
			String typeExeres = request.getParameter("typeExerese");
			int id_typeExerese = Integer.parseInt(typeExeres);
			TypeExerese typeExerese = typeExDAO.trouverTypeExereseById(id_typeExerese);
			String elargisement = request.getParameter("elargissement");
			int id_elarg = Integer.parseInt(elargisement);
			Elargissement elargissement = elargDAO.trouverElargissementById(id_elarg);
			String gest = request.getParameter("geste");
			int id_geste = Integer.parseInt(gest);
			Geste geste = gestDAO.trouverGesteById(id_geste);

			String scorg = request.getParameter("scoring");
			int id_Scoring = Integer.parseInt(scorg);
			RRscoring scoring = scoringDAO.trouverTumeurById(id_Scoring);

			String refChirg = request.getParameter("refChirg");
			String complicationChirurg = request.getParameter("complicationChirurg");
			Complication complication = null;
			if (complicationChirurg != "") {
				int id_comp = Integer.parseInt(complicationChirurg);
				complication = compDAO.trouverAspectMacroById(id_comp);
			}
			chig = new Chirurgie(refChirg, dateChu, service, temps, typeExerese, elargissement, geste, scoring,
					complication);
		}
		TraitementEndoscopique traitEndo = null;
		String typeInterv = request.getParameter("typeInterv");
		if (typeInterv != "") {
			int id_typeIntervention = Integer.parseInt(typeInterv);
			TypeIntervention intervention = typInterDAO.trouverTypeInterventionById(id_typeIntervention);
			String refTrait = request.getParameter("refTrait");
			String complicationTrait = request.getParameter("complicationTrait");
			Complication complication = null;
			if (complicationTrait != "") {
				int id_comp = Integer.parseInt(complicationTrait);
				complication = compDAO.trouverAspectMacroById(id_comp);
			}
			traitEndo = new TraitementEndoscopique(refTrait, intervention, complication);
		}
		Chimiotherapie chimio = null;
		String deroulementChim = request.getParameter("deroulementChim");
		if (deroulementChim != "") {
			Deroulement deroulement = Deroulement.valueOf(deroulementChim);
			String dateDChim = request.getParameter("dateDebutChim");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDebutChim = new Date();
			try {
				dateDebutChim = sdf.parse(dateDChim);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String dateFChim = request.getParameter("dateFinChim");

			Date dateFinChim = new Date();
			try {
				dateFinChim = sdf.parse(dateFChim);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chimio = new Chimiotherapie(deroulement, dateDebutChim, dateFinChim);
		}
		Radiotherapie radio = null;
		String deroulementRadio = request.getParameter("deroulementRadio");
		if (deroulementRadio != "") {
			Deroulement deroulement = Deroulement.valueOf(deroulementRadio);
			String dateDRad = request.getParameter("dateDebutRad");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDebutRad = new Date();
			try {
				dateDebutRad = sdf.parse(dateDRad);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String dateFRad = request.getParameter("dateFinRad");
			Date dateFinRad = new Date();
			try {
				dateFinRad = sdf.parse(dateFRad);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			radio = new Radiotherapie(deroulement, dateDebutRad, dateFinRad);
		}

		int indication = Integer.parseInt(indicat);
		int id_dossier = Integer.parseInt(doss);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		int id_hopital = Integer.parseInt(hop);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTrait = new Date();
		try {
			dateTrait = sdf.parse(dateT);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Traitement trait = new Traitement(dateTrait, indication, chig, traitEndo, chimio, radio, hopital, dossier);
		traitDAO.addTraitement(trait);

		return "/ajoutTraitement.jsp";
	}

	public String suppTraitement() {
		String id_Patient = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPatient", id_Patient);
		return "/suppTraitement.jsp";
	}

	public String modTraitement() {
		String id_Patient = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idPatient", id_Patient);
		return "/modTraitement.jsp";
	}

	public String suppTraitementTrait() {
		String ide = request.getParameter("id");
		int id = Integer.parseInt(ide);
		traitDAO.supprimerTraitement(id);
		return "/suppTraitement.jsp";
	}

	public String modTrait() {
		String ide = request.getParameter("id");
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("idTrait", ide);
		return "/modTraitementF.jsp";
	}

	public String modTraitementTrait() {
		String idTrait = request.getParameter("idTrait");
		int idAncienTrait = Integer.parseInt(idTrait);

		String doss = request.getParameter("dossier");
		String indicat = request.getParameter("indication");
		String hop = request.getParameter("hopital");

		String dateT = request.getParameter("dateTrait");

		String temp = request.getParameter("temps");
		Chirurgie chig = null;
		if (temp != "") {
			int temps = Integer.parseInt(temp);
			String date = request.getParameter("date");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateChu = new Date();
			try {
				dateChu = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String service = request.getParameter("service");
			String typeExeres = request.getParameter("typeExerese");
			int id_typeExerese = Integer.parseInt(typeExeres);
			TypeExerese typeExerese = typeExDAO.trouverTypeExereseById(id_typeExerese);
			String elargisement = request.getParameter("elargissement");
			int id_elarg = Integer.parseInt(elargisement);
			Elargissement elargissement = elargDAO.trouverElargissementById(id_elarg);
			String gest = request.getParameter("geste");
			int id_geste = Integer.parseInt(gest);
			Geste geste = gestDAO.trouverGesteById(id_geste);

			String scorg = request.getParameter("scoring");
			int id_Scoring = Integer.parseInt(scorg);
			RRscoring scoring = scoringDAO.trouverTumeurById(id_Scoring);

			String refChirg = request.getParameter("refChirg");
			String complicationChirurg = request.getParameter("complicationChirurg");
			Complication complication = null;
			if (complicationChirurg != "") {
				int id_comp = Integer.parseInt(complicationChirurg);
				complication = compDAO.trouverAspectMacroById(id_comp);
			}
			chig = new Chirurgie(refChirg, dateChu, service, temps, typeExerese, elargissement, geste, scoring,
					complication);
		}
		TraitementEndoscopique traitEndo = null;
		String typeInterv = request.getParameter("typeInterv");
		if (typeInterv != "") {
			int id_typeIntervention = Integer.parseInt(typeInterv);
			TypeIntervention intervention = typInterDAO.trouverTypeInterventionById(id_typeIntervention);
			String refTrait = request.getParameter("refTrait");
			String complicationTrait = request.getParameter("complicationTrait");
			Complication complication = null;
			if (complicationTrait != "") {
				int id_comp = Integer.parseInt(complicationTrait);
				complication = compDAO.trouverAspectMacroById(id_comp);
			}
			traitEndo = new TraitementEndoscopique(refTrait, intervention, complication);
		}
		Chimiotherapie chimio = null;
		String deroulementChim = request.getParameter("deroulementChim");
		if (deroulementChim != "") {
			Deroulement deroulement = Deroulement.valueOf(deroulementChim);
			String dateDChim = request.getParameter("dateDebutChim");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDebutChim = new Date();
			try {
				dateDebutChim = sdf.parse(dateDChim);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String dateFChim = request.getParameter("dateFinChim");

			Date dateFinChim = new Date();
			try {
				dateFinChim = sdf.parse(dateFChim);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chimio = new Chimiotherapie(deroulement, dateDebutChim, dateFinChim);
		}
		Radiotherapie radio = null;
		String deroulementRadio = request.getParameter("deroulementRadio");
		if (deroulementRadio != "") {
			Deroulement deroulement = Deroulement.valueOf(deroulementRadio);
			String dateDRad = request.getParameter("dateDebutRad");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateDebutRad = new Date();
			try {
				dateDebutRad = sdf.parse(dateDRad);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String dateFRad = request.getParameter("dateFinRad");
			Date dateFinRad = new Date();
			try {
				dateFinRad = sdf.parse(dateFRad);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			radio = new Radiotherapie(deroulement, dateDebutRad, dateFinRad);
		}

		int indication = Integer.parseInt(indicat);
		int id_dossier = Integer.parseInt(doss);
		DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
		int id_hopital = Integer.parseInt(hop);
		Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateTrait = new Date();
		try {
			dateTrait = sdf.parse(dateT);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Traitement trait = new Traitement(dateTrait, indication, chig, traitEndo, chimio, radio, hopital, dossier);
		traitDAO.modifierAnalyse(idAncienTrait, trait);
		return "/dossier.jsp";
	}

	public String consulTraitement() {
		String id_Trait = request.getParameter("id");
		HttpSession sessionMod = request.getSession();
		sessionMod.setAttribute("idTrait", id_Trait);
		return "/detailTraitement.jsp";
	}
}
