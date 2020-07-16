package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BeanDossier;
import beans.BeanExamenClinique;
import beans.BeanIndividu;
import dao.AspectMacroDAO;
import dao.BiologieDAO;
import dao.CategorieSynAnormalDAO;
import dao.CirconferenceDAO;
import dao.ComplicationDAO;
import dao.DifferentiationDAO;
import dao.DossierDAO;
import dao.DysplasieDAO;
import dao.ElargissementDAO;
import dao.EndoAnormalDAO;
import dao.EndoscopieDAO;
import dao.ExamenImagerieDAO;
import dao.ExamenPostOpDAO;
import dao.ExamenPreOpAnormalDAO;
import dao.ExamenPreOpDAO;
import dao.FamilleDAO;
import dao.FormeDAO;
import dao.GenetiqueDAO;
import dao.GesteDAO;
import dao.HopitalDAO;
import dao.ImagerieDAO;
import dao.IndividuDAO;
import dao.LimiteResectEndoDAO;
import dao.MotifDeccesDAO;
import dao.NiveauInvasionDAO;
import dao.PolypeDAO;
import dao.PriseEnChargeDAO;
import dao.RRscoringDAO;
import dao.RendezDAO;
import dao.SiegeDAO;
import dao.SiteDAO;
import dao.SousTypeDAO;
import dao.StatutDAO;
import dao.StromaDAO;
import dao.SyndromeFamilleDAO;
import dao.SyndromesPostOpDAO;
import dao.ToucherRectalDAO;
import dao.TraitementDAO;
import dao.TumeurDAO;
import dao.TypeAndoscopieDAO;
import dao.TypeDAO;
import dao.TypeExereseDAO;
import dao.TypeHystologiqueDAO;
import dao.TypeInterventionDAO;
import dao.TypePrelevementDAO;
import dao.TypeStatutDAO;
import dao.UtilisateurDAO;
import dao.entities.Administrateur;
import dao.entities.CategorieSynAnormal;
import dao.entities.Consanguin;
import dao.entities.Couple;
import dao.entities.CouvertureMedicale;
import dao.entities.Decces;
import dao.entities.DossierMedicale;
import dao.entities.Education;
import dao.entities.ExamenClinique;
import dao.entities.ExamenPostOp;
import dao.entities.ExamenPreOp;
import dao.entities.ExamenPreOpAnormal;
import dao.entities.Famille;
import dao.entities.Forme;
import dao.entities.Hopital;
import dao.entities.Individu;
import dao.entities.Infirmier;
import dao.entities.M;
import dao.entities.Medecin;
import dao.entities.MotifDecces;
import dao.entities.N;
import dao.entities.NiveauSocial;
import dao.entities.PriseEnCharge;
import dao.entities.RendezVous;
import dao.entities.Site;
import dao.entities.StatutCancereux;
import dao.entities.SyndromeFamille;
import dao.entities.SyndromesPostOp;
import dao.entities.T;
import dao.entities.ToucherRectal;
import dao.entities.Type;
import dao.entities.TypeExamen;
import dao.entities.TypeStatut;
import dao.entities.Utilisateur;

//@WebServlet({ "/Controller", "/Connexion" })
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
maxFileSize = 1024*1024*10,
maxRequestSize = 1024*1024*50)
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IndividuDAO indDAO;
	private FamilleDAO familleDAO;
	private MotifDeccesDAO motifDAO;
	private RendezDAO rendDAO;
	private DossierDAO dosDAO;
	private TypeDAO typeDAO;
	private StatutDAO statutDAO;
	private SiteDAO siteDAO;
	private FormeDAO formeDAO;
	private PriseEnChargeDAO priseDAO;
	private TypeStatutDAO typeCancerDAO;
	private Couple couple;
	private CategorieSynAnormalDAO categDAO;
	private SyndromesPostOpDAO syndromesPostDAO;
	private ToucherRectalDAO toucherDAO;
	private HopitalDAO hopDAO;
	private ExamenPostOpDAO examPostDAO;
	private ExamenPreOpDAO examPreDAO;
	private ExamenPreOpAnormalDAO examenAnoDAO;
	private ActionsIndividu actionIndividu;
	private ActionStatut actionStatut;
	private ActionExamenClinique examenClinique;
	private ActionExamenMedical examenMedical;
	private ActionsRendezVous rendezVous;
	private AspectMacroDAO aspectDAO;
	private CirconferenceDAO cirfDAO;
	private EndoscopieDAO endoDAO;
	private SiegeDAO siegeDAO;
	private ComplicationDAO compDAO;
	private EndoAnormalDAO endoAnoDAO;
	private ActionsEndoscopie endoscopie;
	private TypeAndoscopieDAO typeAndoDAO;
	private TypePrelevementDAO typePrelDAO;
	private TypeHystologiqueDAO typeHystoDAO;
	private DifferentiationDAO diffDAO;
	private StromaDAO stromaDAO;
	private NiveauInvasionDAO niveauDAO;
	private TumeurDAO tumeurDAO;
	private PolypeDAO polypeDAO;
	private SousTypeDAO sousTypeDAO;
	private DysplasieDAO dyspDAO;
	private LimiteResectEndoDAO limiteEndoDAO;
	private ActionsAnapath anatomie;
	private BiologieDAO bioDAO;
	private ActionsBiologie biologie;
	private ExamenImagerieDAO examImagDAO;
	private ImagerieDAO imagDAO;
	private ActionsImagerie imagerie;
	private TypeExereseDAO typeExDAO;
	private ElargissementDAO elargDAO;
	private GesteDAO gestDAO;
	private RRscoringDAO scoringDAO;
	private TypeInterventionDAO typInterDAO;
	private TraitementDAO traitDAO;
	private ActionsTraitement traitement;
	private ActionFamille actionFamille;
	private ActionGenetique genetique;
	private GenetiqueDAO genDAO;
	private UtilisateurDAO userDAO;
	private ActionsUser actionUser;
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		indDAO = new IndividuDAO();
		familleDAO = new FamilleDAO();
		motifDAO = new MotifDeccesDAO();
		rendDAO = new RendezDAO();
		dosDAO = new DossierDAO();
		typeDAO = new TypeDAO();
		statutDAO = new StatutDAO();
		siteDAO = new SiteDAO();
		formeDAO = new FormeDAO();
		priseDAO = new PriseEnChargeDAO();
		typeCancerDAO = new TypeStatutDAO();
		couple = new Couple();
		categDAO = new CategorieSynAnormalDAO();
		syndromesPostDAO = new SyndromesPostOpDAO();
		toucherDAO = new ToucherRectalDAO();
		hopDAO = new HopitalDAO();
		couple = new Couple();
		examPostDAO = new ExamenPostOpDAO();
		examPreDAO = new ExamenPreOpDAO();
		examenAnoDAO = new ExamenPreOpAnormalDAO();
		aspectDAO = new AspectMacroDAO();
		cirfDAO = new CirconferenceDAO();
		endoDAO = new EndoscopieDAO();
		siegeDAO = new SiegeDAO();
		compDAO = new ComplicationDAO();
		endoAnoDAO = new EndoAnormalDAO();
		typeAndoDAO = new TypeAndoscopieDAO();
		typePrelDAO = new TypePrelevementDAO();
		typeHystoDAO = new TypeHystologiqueDAO();
		diffDAO = new DifferentiationDAO();
		stromaDAO = new StromaDAO();
		niveauDAO = new NiveauInvasionDAO();
		tumeurDAO = new TumeurDAO();
		polypeDAO = new PolypeDAO();
		sousTypeDAO = new SousTypeDAO();
		dyspDAO = new DysplasieDAO();
		limiteEndoDAO = new LimiteResectEndoDAO();
		bioDAO = new BiologieDAO();
		examImagDAO = new ExamenImagerieDAO();
		imagDAO = new ImagerieDAO();
		typeExDAO = new TypeExereseDAO();
		elargDAO = new ElargissementDAO();
		gestDAO = new GesteDAO();
		scoringDAO = new RRscoringDAO();
		typInterDAO = new TypeInterventionDAO();
		traitDAO = new TraitementDAO();
		genDAO = new GenetiqueDAO();
		userDAO = new UtilisateurDAO();
		// les actions
		actionIndividu = new ActionsIndividu(familleDAO, indDAO, motifDAO, dosDAO);
		actionStatut = new ActionStatut(familleDAO, indDAO, statutDAO, typeDAO, siteDAO, formeDAO, priseDAO,
				typeCancerDAO);
		examenClinique = new ActionExamenClinique(examPostDAO, examPreDAO, examenAnoDAO, indDAO, dosDAO, hopDAO,
				categDAO, syndromesPostDAO, toucherDAO);
		examenMedical = new ActionExamenMedical(dosDAO,endoDAO);
		rendezVous = new ActionsRendezVous(indDAO, rendDAO, dosDAO);
		endoscopie = new ActionsEndoscopie(dosDAO, hopDAO, typeAndoDAO, aspectDAO, cirfDAO, endoDAO, siegeDAO, compDAO,
				endoAnoDAO);
		biologie = new ActionsBiologie(dosDAO, hopDAO, bioDAO);
		anatomie = new ActionsAnapath(dosDAO, hopDAO, typePrelDAO, siegeDAO, typeHystoDAO, diffDAO, stromaDAO,
				niveauDAO, tumeurDAO, polypeDAO, sousTypeDAO, dyspDAO, limiteEndoDAO);
		imagerie = new ActionsImagerie(dosDAO, hopDAO, examImagDAO, imagDAO);
		traitement = new ActionsTraitement(typeExDAO, elargDAO, gestDAO, scoringDAO, compDAO, typInterDAO, dosDAO,
				hopDAO, traitDAO);
		actionFamille = new ActionFamille(familleDAO, indDAO, dosDAO);
		genetique = new ActionGenetique(dosDAO, hopDAO, genDAO);
		actionUser = new ActionsUser(userDAO);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getRequestURI();
		System.out.println("action uri : " + action);
		int begin = action.lastIndexOf("/");
		int end = action.lastIndexOf(".chu");
		String actionKey = action.substring(begin + 1, end);
		String vue = "/index.jsp";

		actionIndividu.setRequest(request);
		actionIndividu.setResponse(response);

		actionStatut.setRequest(request);
		actionStatut.setResponse(response);

		examenClinique.setRequest(request);
		examenClinique.setResponse(response);

		examenMedical.setRequest(request);
		examenMedical.setResponse(response);

		rendezVous.setRequest(request);
		rendezVous.setResponse(response);

		anatomie.setRequest(request);
		anatomie.setResponse(response);

		endoscopie.setRequest(request);
		endoscopie.setResponse(response);

		biologie.setRequest(request);
		biologie.setResponse(response);

		imagerie.setRequest(request);
		imagerie.setResponse(response);
		
		genetique.setRequest(request);
		genetique.setResponse(response);
		
		traitement.setRequest(request);
		traitement.setResponse(response);

		actionFamille.setRequest(request);
		actionFamille.setResponse(response);
		
        actionUser.setRequest(request);
		actionUser.setResponse(response);
		if (actionKey.equalsIgnoreCase("Connexion")) {
			vue = actionUser.authenticate();
		}
		if (actionKey.equalsIgnoreCase("Registre")) {
//			String nom = request.getParameter("nom");
//			String prenom = request.getParameter("prenom");
//			String email = request.getParameter("email");
//			String login = request.getParameter("email");
//			String pwd = request.getParameter("password");
			//Utilisateur newUser = new Infirmier(nom, prenom, email, login, pwd);
			//System.out.println(newUser);
//			if ((new UtilisateurDAO()).creerNouveauCompte(newUser)) {
//				vue = "/espaceUtilisateur.jsp";
//			} else {
//				vue = "/index.jsp";
//			}

		} else if (actionKey.equalsIgnoreCase("logout")) {
			
			   HttpSession session = request.getSession(false); if(session != null)
			   session.invalidate();
			   request.getRequestDispatcher("/index.jsp");
			
		} else if (actionKey.equalsIgnoreCase("ajoutFamille")) {
			vue = actionIndividu.ajoutFamille();
		} else if (actionKey.equalsIgnoreCase("ajoutIndividu")) {

			vue = actionIndividu.ajouter();
		} else if (actionKey.equalsIgnoreCase("RechercheParFamille")) {
			vue = actionIndividu.RechercheParFamille();
		} else if (actionKey.equalsIgnoreCase("RechercheParFamilleSupp")) {
			vue = actionIndividu.RechercheParFamilleSupp();
		} else if (actionKey.equalsIgnoreCase("RechercheParFamilleMod")) {
			vue = actionIndividu.RechercheParFamilleMod();
		}

		else if (actionKey.equalsIgnoreCase("recherchePatientRendez")) {
			vue = rendezVous.recherchePatientRendez();
		} else if (actionKey.equalsIgnoreCase("recherchePatientRendezMod")) {
			vue = rendezVous.recherchePatientRendezMod();
		} else if (actionKey.equalsIgnoreCase("rechercheRendezVous")) {
			vue = rendezVous.rechercheRendezVous();
		} else if (actionKey.equalsIgnoreCase("rechercheRendezVousMod")) {
			vue = rendezVous.rechercheRendezVousMod();
		} else if (actionKey.equalsIgnoreCase("ajoutDecces")) {

			vue = actionIndividu.ajoutDecces();

		} else if (actionKey.equalsIgnoreCase("ajoutConsang")) {

			vue = actionIndividu.ajoutConsang();

		} else if (actionKey.equalsIgnoreCase("ajoutCouple")) {

			vue = actionIndividu.ajoutCouple();

		} else if (actionKey.equalsIgnoreCase("ajoutMembre")) {

			vue = actionIndividu.ajoutMembre();
		} else if (actionKey.equalsIgnoreCase("ajoutFils")) {
			vue = actionIndividu.ajoutFils();

		} else if (actionKey.equalsIgnoreCase("ajoutConsentement")) {

			vue = actionIndividu.ajoutConsentement();
		} else if (actionKey.equalsIgnoreCase("ajoutRendezVous")) {
			vue = rendezVous.ajoutRendezVous();

		} else if (actionKey.equalsIgnoreCase("ajoutDossier")) {

			vue = actionIndividu.ajoutDossier();
		} else if (actionKey.equalsIgnoreCase("ajoutStatut")) {

			vue = actionStatut.ajouter();
		} else if (actionKey.equalsIgnoreCase("suppInd")) {
			vue = actionIndividu.suppInd();
		} else if (actionKey.equalsIgnoreCase("modIndividu")) {
			vue = actionIndividu.modIndividu();
		} else if (actionKey.equalsIgnoreCase("suppRend")) {

			vue = rendezVous.suppRend();
		} else if (actionKey.equalsIgnoreCase("ajoutExamenClinique")) {
			vue = examenClinique.ajouter();
		} else if (actionKey.equalsIgnoreCase("rechercheDossier")) {

			vue = examenMedical.rechercheDossier();

		} else if (actionKey.equalsIgnoreCase("examen")) {

			vue = examenMedical.TrouverIdDossierSession();
		} else if (actionKey.equalsIgnoreCase("suppExamenClinique")) {

			vue = examenClinique.suppExamenClinique();
		}

		else if (actionKey.equalsIgnoreCase("suppExamenCliniqueTraitNormal")) {
			vue = examenClinique.suppExamenCliniqueTraitNormal();
		} else if (actionKey.equalsIgnoreCase("suppExamenCliniqueTraitAnormal")) {
			vue = examenClinique.suppExamenCliniqueTraitAnormal();
		} else if (actionKey.equalsIgnoreCase("suppExamenCliniqueTraitPost")) {
			vue = examenClinique.suppExamenCliniqueTraitPost();
		} else if (actionKey.equalsIgnoreCase("modExamenClinique")) {
			vue = examenClinique.modExamenClinique();
		} else if (actionKey.equalsIgnoreCase("modExamenCliniqueNormal")) {
			vue = examenClinique.modExamenCliniqueNormal();
		} else if (actionKey.equalsIgnoreCase("modExamenCliniquePost")) {
			vue = examenClinique.modExamenCliniquePost();
		}

		else if (actionKey.equalsIgnoreCase("modExamenCliniquePreTrait")) {
			vue = examenClinique.modExamenCliniquePreTrait();
		} else if (actionKey.equalsIgnoreCase("modExamenCliniquePostTrait")) {
			vue = examenClinique.modExamenCliniquePostTrait();
		} else if (actionKey.equalsIgnoreCase("ajoutEndoscopie")) {
			vue = endoscopie.ajoutEndoscopie();
		} else if (actionKey.equalsIgnoreCase("suppEndoscopie")) {
			vue = endoscopie.suppEndoscopie();
		} else if (actionKey.equalsIgnoreCase("suppEndoscopieTraitNormal")) {
			vue = endoscopie.suppEndoscopieTraitNormal();
		} else if (actionKey.equalsIgnoreCase("modEndoscopie")) {
			vue = endoscopie.modEndoscopie();
		} else if (actionKey.equalsIgnoreCase("suppEndoscopieTraitAnormal")) {
			vue = endoscopie.suppEndoscopieTraitAnormal();
		} else if (actionKey.equalsIgnoreCase("modEndoscopieTrait")) {
			vue = endoscopie.modEndoscopieTrait();
		} else if (actionKey.equalsIgnoreCase("modEndoscopieNormal")) {
			vue = endoscopie.modEndoscopieNormal();
		} else if (actionKey.equalsIgnoreCase("ajoutAnapath")) {
			vue = anatomie.ajoutAnapath();
		} else if (actionKey.equalsIgnoreCase("suppAnatPath")) {
			vue = anatomie.suppAnatPath();
		} else if (actionKey.equalsIgnoreCase("modAnatPath")) {
			vue = anatomie.modAnatPath();
		} else if (actionKey.equalsIgnoreCase("suppTumeurTrait")) {
			vue = anatomie.suppTumeurTrait();
		} else if (actionKey.equalsIgnoreCase("suppPolypeTrait")) {
			vue = anatomie.suppPolypeTrait();
		} else if (actionKey.equalsIgnoreCase("modTumeur")) {
			vue = anatomie.modTumeur();
		} else if (actionKey.equalsIgnoreCase("modPolype")) {
			vue = anatomie.modPolype();
		} else if (actionKey.equalsIgnoreCase("modTumeurTrait")) {
			vue = anatomie.modTumeurTrait();
		} else if (actionKey.equalsIgnoreCase("modPolypeTrait")) {
			vue = anatomie.modPolypeTrait();
		} else if (actionKey.equalsIgnoreCase("ajoutBiologie")) {
			vue = biologie.ajoutBiologie();
		} else if (actionKey.equalsIgnoreCase("suppBiologie")) {
			vue = biologie.suppBiologie();
		} else if (actionKey.equalsIgnoreCase("modBiologie")) {
			vue = biologie.modBiologie();
		} else if (actionKey.equalsIgnoreCase("suppBiologieTrait")) {
			vue = biologie.suppBiologieTrait();
		} else if (actionKey.equalsIgnoreCase("modBiol")) {
			vue = biologie.modBiol();
		} else if (actionKey.equalsIgnoreCase("modBiologieTrait")) {
			vue = biologie.modBiologieTrait();
		} else if (actionKey.equalsIgnoreCase("ajoutImagerie")) {
			vue = imagerie.ajoutImagerie();
		} else if (actionKey.equalsIgnoreCase("suppImagerie")) {
			vue = imagerie.suppImagerie();
		} else if (actionKey.equalsIgnoreCase("modImagerie")) {
			vue = imagerie.modImagerie();
		} else if (actionKey.equalsIgnoreCase("suppImagerieTrait")) {
			vue = imagerie.suppImagerieTrait();
		} else if (actionKey.equalsIgnoreCase("modImag")) {
			vue = imagerie.modImag();
		} else if (actionKey.equalsIgnoreCase("modImagerieTrait")) {
			vue = imagerie.modImagerieTrait();
		} else if (actionKey.equalsIgnoreCase("ajoutTraitement")) {
			vue = traitement.ajoutTraitement();
		} else if (actionKey.equalsIgnoreCase("suppTraitement")) {
			vue = traitement.suppTraitement();
		} else if (actionKey.equalsIgnoreCase("modTraitement")) {
			vue = traitement.modTraitement();
		} else if (actionKey.equalsIgnoreCase("suppTraitementTrait")) {
			vue = traitement.suppTraitementTrait();
		} else if (actionKey.equalsIgnoreCase("modTrait")) {
			vue = traitement.modTrait();
		} else if (actionKey.equalsIgnoreCase("modTraitementTrait")) {
			vue = traitement.modTraitementTrait();
		} else if (actionKey.equalsIgnoreCase("rendezVous")) {
			vue = rendezVous.rendezVous();
		} else if (actionKey.equalsIgnoreCase("rechercheDossierRendez")) {
			vue = rendezVous.rechercheDossierRendez();
		} else if (actionKey.equalsIgnoreCase("modRendezTrait")) {
			vue = rendezVous.modRendezTrait();
		} else if (actionKey.equalsIgnoreCase("modifierRendezVous")) {
			vue = rendezVous.modifierRendezVous();
		} else if (actionKey.equalsIgnoreCase("listInd")) {
			vue = actionFamille.listInd();
		} else if (actionKey.equalsIgnoreCase("listIndDoss")) {
			vue = actionFamille.listIndDoss();
		} else if (actionKey.equalsIgnoreCase("dossier")) {
			vue = examenMedical.dossier();
		} else if (actionKey.equalsIgnoreCase("modFamille")) {
			vue = actionFamille.modFamille();
		} else if (actionKey.equalsIgnoreCase("modFamilleTrait")) {
			vue = actionFamille.modFamilleTrait();
		} else if (actionKey.equalsIgnoreCase("modifier")) {
			vue = actionFamille.modifier();
		} else if (actionKey.equalsIgnoreCase("modifierTrait")) {
			vue = actionFamille.modifierTrait();
		} else if (actionKey.equalsIgnoreCase("consExamenCliniqueNormal")) {
			vue = examenClinique.consExamenCliniqueNormal();
		} else if (actionKey.equalsIgnoreCase("consExamenCliniquePost")) {
			vue = examenClinique.consExamenCliniquePost();
		} else if (actionKey.equalsIgnoreCase("modIndividuTrait")) {
			vue = actionIndividu.modIndividuTrait();
		} else if (actionKey.equalsIgnoreCase("compInd")) {
			vue = actionIndividu.compInd();
		} else if (actionKey.equalsIgnoreCase("modRendezVous")) {
			vue = rendezVous.modRendezVous();
		} else if (actionKey.equalsIgnoreCase("detailStatut")) {
			vue = actionStatut.detailStatut();
		} else if (actionKey.equalsIgnoreCase("modStatut")) { // pour l'affichage du page jsp
			vue = actionStatut.modStatut();
		} else if (actionKey.equalsIgnoreCase("consEndoscopieNormal")) {
			vue = examenMedical.examEndoDetail();
		} else if (actionKey.equalsIgnoreCase("consPolype")) {
			vue = examenMedical.examPolyDetail();
		} else if (actionKey.equalsIgnoreCase("modifierStatut")) {
			vue = actionStatut.modifierStatut();
		} else if (actionKey.equalsIgnoreCase("modExamPre")) {
			vue = examenClinique.modExamePre();
		} else if (actionKey.equalsIgnoreCase("consTraitement")) {
			vue = traitement.consulTraitement();
		}
		
		else if (actionKey.equalsIgnoreCase("consImagerie")) {
			vue = imagerie.getImagerie();
		}else if (actionKey.equalsIgnoreCase("consBiologie")) {
			vue = biologie.consultBiologie();
		}else if (actionKey.equalsIgnoreCase("ajoutGenetique")) {
			vue = genetique.ajoutGenetique();
		}
		else if (actionKey.equalsIgnoreCase("modGen")) {
			vue = genetique.modGen();
		}else if (actionKey.equalsIgnoreCase("consGenetique")) {
			vue = genetique.consultGenetique();
		}else if (actionKey.equalsIgnoreCase("modGenetique")) {
			vue = genetique.modGenetiqueTrait();
		}else if (actionKey.equalsIgnoreCase("detailUtilisateur")) {
			vue = actionUser.ConsulterUtilisateur();
		}else if (actionKey.equalsIgnoreCase("suppUtilisateur")) {
			vue = actionUser.SupprimerUtilisateur();
		}else if (actionKey.equalsIgnoreCase("modUser")) {
			vue = actionUser.modUser();
		}else if (actionKey.equalsIgnoreCase("modUtilisateur")) {
			vue = actionUser.ModifierUtilisateur();
		}else if (actionKey.equalsIgnoreCase("ajoutUtilisateur")) {
			vue = actionUser.AjouterUtilisateur();
		}else if (actionKey.equalsIgnoreCase("rapportMedical")) {
			vue = examenMedical.rapportMedical();
		}
		
		
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher(vue);
		dispatcher.forward(request, response);
//        this.getServletContext().getRequestDispatcher(vue).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
