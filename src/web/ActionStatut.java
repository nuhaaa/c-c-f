package web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FamilleDAO;
import dao.FormeDAO;
import dao.IndividuDAO;
import dao.PriseEnChargeDAO;
import dao.SiteDAO;
import dao.StatutDAO;
import dao.TypeDAO;
import dao.TypeStatutDAO;
import dao.entities.Forme;
import dao.entities.Individu;
import dao.entities.M;
import dao.entities.N;
import dao.entities.PriseEnCharge;
import dao.entities.RendezVous;
import dao.entities.Site;
import dao.entities.StatutCancereux;
import dao.entities.T;
import dao.entities.Type;
import dao.entities.TypeStatut;

public class ActionStatut {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private FamilleDAO familleDAO;
	private IndividuDAO indDAO;
	private StatutDAO statutDAO;
	private TypeDAO typeDAO;
	private SiteDAO siteDAO;
	private FormeDAO formeDAO;
	private PriseEnChargeDAO priseDAO;
	private TypeStatutDAO typeCancerDAO;

	public ActionStatut(FamilleDAO familleDAO, IndividuDAO indDAO, StatutDAO statutDAO, TypeDAO typeDAO,
			SiteDAO siteDAO, FormeDAO formeDAO, PriseEnChargeDAO priseDAO, TypeStatutDAO typeCancerDAO) {
		super();
		this.familleDAO = familleDAO;
		this.indDAO = indDAO;
		this.statutDAO = statutDAO;
		this.typeDAO = typeDAO;
		this.siteDAO = siteDAO;
		this.formeDAO = formeDAO;
		this.priseDAO = priseDAO;
		this.typeCancerDAO = typeCancerDAO;
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

	public String ajouter() {

		String pat = request.getParameter("patient");
		int id = Integer.parseInt(pat);
		Individu patient = indDAO.trouverIndById(id);

		String ccr = request.getParameter("ccr");
		System.out.println("ccr : "+ccr);

		String cec = request.getParameter("cec");

		String p = request.getParameter("p");

		String mec = request.getParameter("mec");

		if (ccr != "") {
			int ids = Integer.parseInt(ccr);
			TypeStatut cancerccr = typeCancerDAO.trouverSyndById(ids);

			String anneeccr = request.getParameter("anneeccr");
			String age = request.getParameter("ageccr");
			int ageccr = Integer.parseInt(age);
			String siteccr = request.getParameter("siteccr");
			int idsite = Integer.parseInt(siteccr);
			Site site = siteDAO.trouverSiteById(idsite);
			String typeccr = request.getParameter("typeccr");
			int idtype = Integer.parseInt(typeccr);
			Type type = typeDAO.trouverTypeById(idtype);
			String tccr = request.getParameter("tccr");
			T t = T.valueOf(tccr);
			String mccr = request.getParameter("mccr");
			M m = M.valueOf(mccr);
			String nccr = request.getParameter("nccr");
			N n = N.valueOf(nccr);
//			String priseEnChargeccr = request.getParameter("priseEnChargeccr");
			String[] res = request.getParameterValues("priseEnChargeccr");
			List<PriseEnCharge> prises = new ArrayList<>();
			try {
				for (int i = 0; i < res.length; ++i) {

					String priseEnChargeccr = res[i];
					int id_priseEnChargeccr = Integer.parseInt(priseEnChargeccr);
					PriseEnCharge prise = priseDAO.trouverPriseEnChargeVousById(id_priseEnChargeccr);
					prises.add(prise);
				}
			} catch (Exception e1) {
				// e1.printStackTrace();
			}

			statutDAO.ajouterStatutCCR(cancerccr, anneeccr, ageccr, type, site, t, m, n, prises, patient);
		}
		if (cec != "") {

			int idsc = Integer.parseInt(cec);
			TypeStatut cancercec = typeCancerDAO.trouverSyndById(idsc);
			String anneecec = request.getParameter("anneecec");
			String age = request.getParameter("agecec");
			int agecec = Integer.parseInt(age);
			String sitecec = request.getParameter("sitecec");
			int idsite = Integer.parseInt(sitecec);
			Site site = siteDAO.trouverSiteById(idsite);
			String typecec = request.getParameter("typecec");
			int idtype = Integer.parseInt(typecec);
			Type type = typeDAO.trouverTypeById(idtype);
			String tcec = request.getParameter("tcec");
			T t = T.valueOf(tcec);
			String mcec = request.getParameter("mcec");
			M m = M.valueOf(mcec);
			String ncec = request.getParameter("ncec");
			N n = N.valueOf(ncec);
			String[] res = request.getParameterValues("priseEnChargecec");
			List<PriseEnCharge> prises = new ArrayList<>();
			try {
				for (int i = 0; i < res.length; ++i) {

					String priseEnChargecec = res[i];
					int id_priseEnChargecec = Integer.parseInt(priseEnChargecec);
					PriseEnCharge prise = priseDAO.trouverPriseEnChargeVousById(id_priseEnChargecec);
					prises.add(prise);
				}
			} catch (Exception e1) {
				// e1.printStackTrace();
			}
			statutDAO.ajouterStatutCEC(cancercec, anneecec, agecec, type, site, t, m, n, prises, patient);
		}
		if (p != "") {

			int idp = Integer.parseInt(p);
//			System.out.println("id status :: "+idp);
			TypeStatut cancerp = typeCancerDAO.trouverSyndById(idp);
//			System.out.println("typesatus :: "+cancerp);
			String anneep = request.getParameter("anneep");
			String age = request.getParameter("agep");
			int agep = Integer.parseInt(age);
			String formep = request.getParameter("formep");
			int idforme = Integer.parseInt(formep);
			Forme forme = formeDAO.trouverFormeById(idforme);
//			String priseEnChargep = request.getParameter("priseEnChargep");
			String[] res = request.getParameterValues("priseEnChargep");
			List<PriseEnCharge> prises = new ArrayList<>();
			try {
				for (int i = 0; i < res.length; ++i) {

					String priseEnChargep = res[i];
					int id_priseEnChargep = Integer.valueOf(priseEnChargep);
					PriseEnCharge prise = priseDAO.trouverPriseEnChargeVousById(id_priseEnChargep);
					prises.add(prise);
				}
			} catch (Exception e1) {
				// e1.printStackTrace();
			}
			statutDAO.ajouterStatutP(cancerp, anneep, agep, forme, prises, patient);
		}

		if (mec != "") {
			int idm = Integer.parseInt(mec);
			TypeStatut cancermec = typeCancerDAO.trouverSyndById(idm);
			String anneemec = request.getParameter("anneemec");
			String age = request.getParameter("agemec");
			int agemec = Integer.parseInt(age);
			String typemec = request.getParameter("typemec");
			int idtype = Integer.parseInt(typemec);
			Type type = typeDAO.trouverTypeById(idtype);
//			String priseEnChargemec = request.getParameter("priseEnChargemec");
			String[] res = request.getParameterValues("priseEnChargemec");
			List<PriseEnCharge> prises = new ArrayList<>();
			try {
				for (int i = 0; i < res.length; ++i) {

					String priseEnChargemec = res[i];
					int id_priseEnChargemec = Integer.parseInt(priseEnChargemec);
					PriseEnCharge prise = priseDAO.trouverPriseEnChargeVousById(id_priseEnChargemec);
					prises.add(prise);
				}
			} catch (Exception e1) {
				// e1.printStackTrace();
			}
			statutDAO.ajouterStatutMEC(cancermec, anneemec, agemec, type, prises, patient);
		}

		return "/statut.jsp";
	}

	public String detailStatut() {
		String idStatut = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idStatut", idStatut);
		return "/detailStatut.jsp";
	}

	public String modStatut() {
		String idStatut = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("idStatut", idStatut);
		return "/modStatut.jsp";
	}

	public String modifierStatut() {
		String statut = request.getParameter("statut");
		int idStatut = Integer.parseInt(statut);
		//StatutCancereux st = statutDAO.trouverStautById(idStatut);
		String typeStatut = request.getParameter("typeStatut");
		int ids = Integer.parseInt(typeStatut);
		TypeStatut typeCancer = typeCancerDAO.trouverSyndById(ids);
		String annee = request.getParameter("annee");
		int age = Integer.parseInt(request.getParameter("age"));
		String siteccr = request.getParameter("site");
		int idsite = Integer.parseInt(siteccr);
		Site site = siteDAO.trouverSiteById(idsite);
		String type = request.getParameter("type");
		int idtype = Integer.parseInt(type);
		Type newtype = typeDAO.trouverTypeById(idtype);
		String tcec = request.getParameter("t");
		T t = T.valueOf(tcec);
		String mcec = request.getParameter("m");
		M m = M.valueOf(mcec);
		String ncec = request.getParameter("n");
		N n = N.valueOf(ncec);
		String[] res = request.getParameterValues("priseEnCharge");
		List<PriseEnCharge> prises = new ArrayList<>();
		try {
			for (int i = 0; i < res.length; ++i) {
				String priseEnChargep = res[i];
				int id_priseEnChargep = Integer.valueOf(priseEnChargep);
				PriseEnCharge prise = priseDAO.trouverPriseEnChargeVousById(id_priseEnChargep);
				prises.add(prise);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		StatutCancereux newStatut = new StatutCancereux(typeCancer, annee, age, newtype, site, t, m, n, prises);
		statutDAO.modifierStatut(idStatut, newStatut);
		return "/dossier.jsp";
	}
}
