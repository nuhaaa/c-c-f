package web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DossierDAO;
import dao.FamilleDAO;
import dao.IndividuDAO;
import dao.MotifDeccesDAO;
import dao.SyndromeFamilleDAO;
import dao.entities.Consanguin;
import dao.entities.Couple;
import dao.entities.CouvertureMedicale;
import dao.entities.Decces;
import dao.entities.DossierMedicale;
import dao.entities.Education;
import dao.entities.Famille;
import dao.entities.Individu;
import dao.entities.MotifDecces;
import dao.entities.NiveauSocial;
import dao.entities.SyndromeFamille;


public class ActionsIndividu {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private FamilleDAO familleDAO;
	private IndividuDAO indDAO;
	private MotifDeccesDAO motifDAO;
	private DossierDAO dosDAO;

	public ActionsIndividu(FamilleDAO familleDAO, IndividuDAO indDAO, MotifDeccesDAO motifDAO, DossierDAO dosDAO) {
		super();
		this.familleDAO = familleDAO;
		this.indDAO = indDAO;
		this.motifDAO = motifDAO;
		this.dosDAO = dosDAO;
	}

	public String ajouter() {
		String fam = request.getParameter("famille");
		int id = Integer.parseInt(fam);

		Famille famille = familleDAO.trouverFamilleById(id);

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String date = request.getParameter("dateNais");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNais = new Date();
		try {
			dateNais = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sexe = request.getParameter("sexe");
		String ville = request.getParameter("ville");
		String region = request.getParameter("region");
		String adresse = request.getParameter("adresse");
		String origine = request.getParameter("origine");
		/*** image traitements ***/
		String save_dir = "CHU_Images";
		String savePath = "";
		try {
			PrintWriter out = response.getWriter();
			savePath = "C:\\Users\\thinkpad\\git\\cancer-colorectal\\WebContent\\images" + File.separator + save_dir;
			File fileSaveDir = new File(savePath);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Part part;
		String fileName = "";
		try {
			part = request.getPart("file");
			fileName = extractFileName(part);
			if("".equals(fileName)) {
				fileName=request.getParameter("file");
			}
			part.write(savePath + File.separator + fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String image = savePath + File.separator + fileName;
		String image = fileName;
		String t1 = request.getParameter("tel1");
		String t2 = request.getParameter("tel2");

		int tel1 = Integer.parseInt(t1);
		int tel2 = Integer.parseInt(t2);

		String educ = request.getParameter("education");
		Education education = Education.valueOf(educ);

		String niveau = request.getParameter("niveau");

		NiveauSocial niveauSocial = NiveauSocial.valueOf(niveau);

		String couverture = request.getParameter("couverture");

		CouvertureMedicale couvertMedicale = CouvertureMedicale.valueOf(couverture);

		String occupation = request.getParameter("occupation");
//		String urb = request.getParameter("urbain");
		String rur = request.getParameter("rural");
		boolean urbain = false;
		if (rur == " ") {
			urbain = true;
		} else {
			urbain = false;
		}

		Individu individu = new Individu(prenom, nom, dateNais, sexe, urbain, ville, region, adresse, origine, image,
				tel1, tel2, education, occupation, niveauSocial, couvertMedicale, famille);

		indDAO.enregistrementIndividu(individu);
		return "/listeIndividu.jsp";
	}

	public String modIndividuTrait() {
		String idInd = request.getParameter("idIndividu");
		int idIndividu = Integer.parseInt(idInd);
		String fam = request.getParameter("famille");
		int id = Integer.parseInt(fam);

		Famille famille = familleDAO.trouverFamilleById(id);

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String date = request.getParameter("dateNais");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateNais = new Date();
		try {
			dateNais = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String sexe = request.getParameter("sexe");
		String ville = request.getParameter("ville");
		String region = request.getParameter("region");
		String adresse = request.getParameter("adresse");
		String origine = request.getParameter("origine");
		String image = request.getParameter("image");
		String t1 = request.getParameter("tel1");
		String t2 = request.getParameter("tel2");

		int tel1 = Integer.parseInt(t1);
		int tel2 = Integer.parseInt(t2);

		String educ = request.getParameter("education");
		Education education = Education.valueOf(educ);

		String niveau = request.getParameter("niveau");

		NiveauSocial niveauSocial = NiveauSocial.valueOf(niveau);

		String couverture = request.getParameter("couverture");

		CouvertureMedicale couvertMedicale = CouvertureMedicale.valueOf(couverture);

		String occupation = request.getParameter("occupation");
//		String urb = request.getParameter("urbain");
		String rur = request.getParameter("rural");
		boolean urbain = false;
		if (rur == " ") {
			urbain = true;
		} else {
			urbain = false;
		}

		Individu individu = new Individu(prenom, nom, dateNais, sexe, urbain, ville, region, adresse, origine, image,
				tel1, tel2, education, occupation, niveauSocial, couvertMedicale, famille);

		return "/modIndividu.jsp";
	}

	public String ajoutFamille() {
		String nomFamille = request.getParameter("nomFamille");
		String diagnostic = request.getParameter("diagnostic");
		int id = Integer.parseInt(diagnostic);

		SyndromeFamilleDAO syndDAO = new SyndromeFamilleDAO();

		SyndromeFamille syndrome = syndDAO.trouverSyndById(id);

		Famille famille = new Famille(nomFamille, syndrome);

		familleDAO.ajouterFamille(famille);

		return "/espaceUtilisateur.jsp";
	}

	public String RechercheParFamille() {
		String nom = request.getParameter("famille");
//		
		HttpSession session = request.getSession();
		session.setAttribute("nomFamille", nom);
		return "/completIndividu.jsp";
	}

	public String RechercheParFamilleSupp() {
		String nom = request.getParameter("famille");
//		
		HttpSession session = request.getSession();
		session.setAttribute("nomFamille", nom);
		return "/suppIndividu.jsp";
	}

	public String RechercheParFamilleMod() {
		String nom = request.getParameter("famille");
//		
		HttpSession session = request.getSession();
		session.setAttribute("nomFamille", nom);
		return "/modIndividu.jsp";
	}

	public String ajoutDecces() {
		String ind = request.getParameter("individu");

		int id = Integer.parseInt(ind);

		Individu individu = indDAO.trouverIndById(id);

		String date = request.getParameter("dateDeces");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateDeces = new Date();
		try {
			dateDeces = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String cause = request.getParameter("motif");
		int idmotif = Integer.parseInt(cause);
		MotifDecces motif = null;

		motif = motifDAO.trouverMotifById(idmotif);

		Decces decces = new Decces(individu, motif, dateDeces);
		indDAO.ajoutDecces(decces);

		return "/ajoutIndividu.jsp";
	}

	public String ajoutConsang() {
		String ind = request.getParameter("individu");
		int id = Integer.parseInt(ind);

		Individu individu = indDAO.trouverIndById(id);

//		Individu casIndex=indDAO.trouverCasIndexParIndividu(id);
		Individu casIndex = individu.getFamille().getCasIndex();

		String degre = request.getParameter("degre");

		Consanguin consang = new Consanguin(casIndex, individu, degre);
		indDAO.ajoutConsanguin(consang);
		return "/completIndividu.jsp";
	}

	public String ajoutCouple() {

		String mer = request.getParameter("ind1");
		int id1 = Integer.parseInt(mer);

		Individu mere = indDAO.trouverIndById(id1);

		String per = request.getParameter("ind2");
		int id2 = Integer.parseInt(per);
		Individu pere = indDAO.trouverIndById(id2);

		Couple conjoint = new Couple(mere, pere);
		indDAO.ajoutCouple(conjoint);
		return "/completIndividu.jsp";

	}

	public String ajoutMembre() {

		String memb = request.getParameter("menbre");
		int id = Integer.parseInt(memb);
		Individu membre = indDAO.trouverIndById(id);

		String coupl = request.getParameter("couple");
		int id1 = Integer.parseInt(coupl);
//		int bg = coupl.lastIndexOf(".");
//		String nomC = coupl.substring(bg+1, coupl.length());
		Couple couple = indDAO.trouverCoupleById(id1);

		indDAO.ajoutMembreCouple(membre, couple);
		return "/completIndividu.jsp";
	}

	public String ajoutFils() {
		String fls = request.getParameter("fils");
		int id = Integer.parseInt(fls);
		Individu fils = indDAO.trouverIndById(id);

		String coupl = request.getParameter("couple");
		int id2 = Integer.parseInt(coupl);

		Couple couple = indDAO.trouverCoupleById(id2);

		indDAO.ajoutFilsCouple(fils, couple);
		return "/completIndividu.jsp";
	}

	public String ajoutConsentement() {

		String ind = request.getParameter("individu");
		int id = Integer.parseInt(ind);
		Individu individu = indDAO.trouverIndById(id);

		String date = request.getParameter("dateCons");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateCons = new Date();
		try {
			dateCons = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		indDAO.ajoutConsentement(individu, dateCons);
		return "/completIndividu.jsp";
	}

	public String suppInd() {
		String idy = request.getParameter("id");
		int id = Integer.parseInt(idy);
		indDAO.supprimerIndividu(id);

		return "/suppIndividu.jsp";
	}

	public String compInd() {
		String idy = request.getParameter("id");
		int id = Integer.parseInt(idy);
		Individu ind = indDAO.trouverIndById(id);
		HttpSession session = request.getSession();
		session.setAttribute("Individu", ind);
		return "/completIndividu.jsp";
	}

	public String modIndividu() {
		String idy = request.getParameter("id");
		int id = Integer.parseInt(idy);
		Individu ind = indDAO.trouverIndById(id);
		HttpSession session = request.getSession();
		session.setAttribute("Individu", ind);
		return "/modIndividuTrait.jsp";
	}

	public String ajoutDossier() {

		String ind = request.getParameter("patient");
		int id = Integer.parseInt(ind);
//		Individu patient = indDAO.trouverIndById(id);

		String date = request.getParameter("dateDossier");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateDossier = new Date();
		try {
			dateDossier = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		DossierMedicale dossier = new DossierMedicale(dateDossier, patient);

		dosDAO.creerDossier(dateDossier, id);

		return "/gestionDossier.jsp";
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	private String extractFileName(Part part) {
		String contentDiap = part.getHeader("content-disposition");
		String[] items = contentDiap.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				return item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return "";
	}
}
