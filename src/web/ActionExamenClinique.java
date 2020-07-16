package web;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategorieSynAnormalDAO;
import dao.DossierDAO;
import dao.ExamenPostOpDAO;
import dao.ExamenPreOpAnormalDAO;
import dao.ExamenPreOpDAO;
import dao.HopitalDAO;
import dao.IndividuDAO;
import dao.SyndromesPostOpDAO;
import dao.ToucherRectalDAO;
import dao.entities.CategorieSynAnormal;
import dao.entities.DossierMedicale;
import dao.entities.ExamenPostOp;
import dao.entities.ExamenPreOp;
import dao.entities.ExamenPreOpAnormal;
import dao.entities.Hopital;
import dao.entities.SyndromesPostOp;
import dao.entities.ToucherRectal;
import dao.entities.TypeExamen;



public class ActionExamenClinique {
	
		private HttpServletRequest request;
		private HttpServletResponse response;
		private ExamenPostOpDAO examPostDAO;
		private ExamenPreOpDAO examPreDAO;
		private ExamenPreOpAnormalDAO examenAnoDAO;
		private IndividuDAO indDAO;
		private DossierDAO dosDAO;
		private HopitalDAO hopDAO;
		private CategorieSynAnormalDAO categDAO;
		private SyndromesPostOpDAO syndromesPostDAO;
		private ToucherRectalDAO toucherDAO;

		
		
		
		public ActionExamenClinique(ExamenPostOpDAO examPostDAO, ExamenPreOpDAO examPreDAO,
				ExamenPreOpAnormalDAO examenAnoDAO, IndividuDAO indDAO, DossierDAO dosDAO, HopitalDAO hopDAO,
				CategorieSynAnormalDAO categDAO, SyndromesPostOpDAO syndromesPostDAO, ToucherRectalDAO toucherDAO) {
			super();
			this.examPostDAO = examPostDAO;
			this.examPreDAO = examPreDAO;
			this.examenAnoDAO = examenAnoDAO;
			this.indDAO = indDAO;
			this.dosDAO = dosDAO;
			this.hopDAO = hopDAO;
			this.categDAO = categDAO;
			this.syndromesPostDAO = syndromesPostDAO;
			this.toucherDAO = toucherDAO;
		}

		public String ajouter(){
			
			String doss = request.getParameter("dossier");
			int id_dossier =Integer.parseInt(doss);
			DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
			String hop = request.getParameter("hopital");
			int id_hopital =Integer.parseInt(hop);
			Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
//			String medecin = request.getParameter("medecin");
			
			String date = request.getParameter("dateexamen");
			SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
			Date dateExamen = new Date();
			try {
				dateExamen = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pds = request.getParameter("poids");
			Float poids = Float.parseFloat(pds);
			String tail = request.getParameter("taille");
			Float taille = Float.parseFloat(tail);
			String imc = request.getParameter("imc");
			Float iMC = Float.parseFloat(imc);
			String oms = request.getParameter("oms");
			Float oMS = Float.parseFloat(oms);
			

			String type = request.getParameter("typeExamen");
			TypeExamen typeExamen = TypeExamen.valueOf(type);
			
			String del = request.getParameter("delai");
			
			
			String[] syndPreOp = request.getParameterValues("syndromePreOp");
			
			
			if(del.equalsIgnoreCase("") 
					&& syndPreOp==null){
				
				ExamenPreOp examenNormal = new ExamenPreOp(hopital, dossier, dateExamen, poids, taille, oMS, iMC, typeExamen);
				examPreDAO.ajouterExamenPreOp(examenNormal);
			}
			if(del!=""){
				int delai = Integer.parseInt(del);
				String nbreSel = request.getParameter("nbSelle");
				int nbreSelles = Integer.parseInt(nbreSel);
				String[] syndPostOp = request.getParameterValues("syndrome");
				List<SyndromesPostOp> syndromes = new ArrayList<>();
				try { 
					for (int i = 0; i < syndPostOp.length; ++i){ 
					
					String syndromesPostOp = syndPostOp[i];
					int id_SyndromesPostOp = Integer.parseInt(syndromesPostOp);
					SyndromesPostOp syndrome =syndromesPostDAO.trouverSyndromesPostOpById(id_SyndromesPostOp);
					syndromes.add(syndrome);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				String[] toucher = request.getParameterValues("toucher");
				List<ToucherRectal> touchers = new ArrayList<>();
				try { 
					for (int i = 0; i < toucher.length; ++i){ 
					
					String toucherRectal = toucher[i];
					int id_ToucherRectal = Integer.parseInt(toucherRectal);
					ToucherRectal toucherRect =toucherDAO.trouverToucherById(id_ToucherRectal);
					touchers.add(toucherRect);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				ExamenPostOp examenPostOp = new ExamenPostOp(hopital, dossier, dateExamen, poids, taille, oMS, iMC, delai, nbreSelles, syndromes, touchers);
				examPostDAO.ajouterExamenPostOp(examenPostOp);
			}
			if(syndPreOp!=null){
				List<CategorieSynAnormal> categories = new ArrayList<>();
				try { 
					for (int i = 0; i < syndPreOp.length; ++i){ 
					
					String categorieSynAnormal = syndPreOp[i];
					int id_CategorieSynAnormal = Integer.parseInt(categorieSynAnormal);
					CategorieSynAnormal categorie=categDAO.trouverCategorieSynAnormalById(id_CategorieSynAnormal);
					categories.add(categorie);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				ExamenPreOpAnormal examenAnormal = new ExamenPreOpAnormal(hopital, dossier, dateExamen, poids, taille, oMS, iMC, typeExamen, categories);
				examenAnoDAO.ajouterExamenPreOpAnormal(examenAnormal);
			}
			
			return "/ExamenClinique.jsp";
		}

		public String suppExamenCliniqueTraitNormal(){
			String ide = request.getParameter("id");
			int id = Integer.parseInt(ide);
			ExamenPreOpDAO examen =  new ExamenPreOpDAO();
			examen.supprimerExamenPreOp(id);
			return "/suppExamenClinique.jsp";
		}
		public String suppExamenCliniqueTraitAnormal(){
			String ide = request.getParameter("id");
			int id = Integer.parseInt(ide);
			ExamenPreOpAnormalDAO examen =  new ExamenPreOpAnormalDAO();
			examen.supprimerExamenPreOpAnormal(id);
			return "/suppExamenClinique.jsp";
		}
		public String suppExamenCliniqueTraitPost(){
			String ide = request.getParameter("id");
			int id = Integer.parseInt(ide);
			ExamenPostOpDAO examen =  new ExamenPostOpDAO();
			examen.supprimerExamenPostOp(id);
			return "/suppExamenClinique.jsp";
		}
		public String modExamenCliniqueNormal(){
			String ide = request.getParameter("id");
			int id = Integer.parseInt(ide);
			ExamenPreOpDAO examenDAO =  new ExamenPreOpDAO();
			ExamenPreOp examen = examenDAO.trouverExamenById(id);
			System.out.println("examen :"+examen);
			HttpSession sessionMod = request.getSession();
			sessionMod.setAttribute("ExamenPreOp", examen);
			return "/modExamenCliniquePre.jsp";
		}
		public String consExamenCliniqueNormal(){
			String ide = request.getParameter("id");
			int id = Integer.parseInt(ide);
			ExamenPreOpDAO examenDAO =  new ExamenPreOpDAO();
			ExamenPreOp examen = examenDAO.trouverExamenById(id);
			HttpSession sessionMod = request.getSession();
			sessionMod.setAttribute("ExamenPreOp", examen);
			return "/consExamenCliniquePre.jsp";
		}
		public String modExamenCliniquePost(){
			String ide = request.getParameter("id");
			int id = Integer.parseInt(ide);
			ExamenPostOpDAO examenDAO =  new ExamenPostOpDAO();
			ExamenPostOp examen = examenDAO.trouverExamenById(id);
			HttpSession sessionMod = request.getSession();
			sessionMod.setAttribute("ExamenPostOp", examen);
			return "/modExamenCliniquePost.jsp";
		}
		
		public String modPreOpe() {
			String idPreOP = request.getParameter("id");
			
			return "/modExamenPreOP.jsp";
		}
		public String consExamenCliniquePost(){
			String ide = request.getParameter("id");
			int id = Integer.parseInt(ide);
			ExamenPostOpDAO examenDAO =  new ExamenPostOpDAO();
			ExamenPostOp examen = examenDAO.trouverExamenById(id);
			HttpSession sessionMod = request.getSession();
			sessionMod.setAttribute("ExamenPostOp", examen);
			return "/consExamenCliniquePost.jsp";
		}
		public String suppExamenClinique(){
			String id_Patient = request.getParameter("id");
			HttpSession session = request.getSession();
			session.setAttribute("idPatient", id_Patient);
			return "/suppExamenClinique.jsp";
		}
		public String modExamenClinique(){
			String id_Patient = request.getParameter("id");
			HttpSession session = request.getSession();
			session.setAttribute("idPatient", id_Patient);
			return "/modExamenClinique.jsp";
		}
		public String modExamenCliniquePostTrait(){
			
			String idExamen = request.getParameter("idExamen");
			int idAncienExamen= Integer.parseInt(idExamen);
			
			String doss = request.getParameter("dossier");
			int id_dossier =Integer.parseInt(doss);
			DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
			String hop = request.getParameter("hopital");
			int id_hopital =Integer.parseInt(hop);
			Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
//			String medecin = request.getParameter("medecin");
			
			String date = request.getParameter("dateexamen");
			SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
			Date dateExamen = new Date();
			try {
				dateExamen = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pds = request.getParameter("poids");
			Float poids = Float.parseFloat(pds);
			String tail = request.getParameter("taille");
			Float taille = Float.parseFloat(tail);
			String imc = request.getParameter("imc");
			Float iMC = Float.parseFloat(imc);
			String oms = request.getParameter("oms");
			Float oMS = Float.parseFloat(oms);
			

			String type = request.getParameter("typeExamen");
			TypeExamen typeExamen = TypeExamen.valueOf(type);
			
			String del = request.getParameter("delai");
			
			
			String[] syndPreOp = request.getParameterValues("syndromePreOp");
			
			
			if(del.equalsIgnoreCase("") 
					&& syndPreOp==null){
				
				ExamenPreOp examenNormal = new ExamenPreOp(hopital, dossier, dateExamen, poids, taille, oMS, iMC, typeExamen);
//				examPreDAO.modifierExamenPreOp(idAncienExamen, examenNormal);
				examPostDAO.modifierExamenClinique(idAncienExamen, examenNormal);
			}
			if(del!=""){
				int delai = Integer.parseInt(del);
				String nbreSel = request.getParameter("nbSelle");
				int nbreSelles = Integer.parseInt(nbreSel);
				String[] syndPostOp = request.getParameterValues("syndrome");
				List<SyndromesPostOp> syndromes = new ArrayList<>();
				try { 
					for (int i = 0; i < syndPostOp.length; ++i){ 
					
					String syndromesPostOp = syndPostOp[i];
					int id_SyndromesPostOp = Integer.parseInt(syndromesPostOp);
					SyndromesPostOp syndrome =syndromesPostDAO.trouverSyndromesPostOpById(id_SyndromesPostOp);
					syndromes.add(syndrome);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				String[] toucher = request.getParameterValues("toucher");
				List<ToucherRectal> touchers = new ArrayList<>();
				try { 
					for (int i = 0; i < toucher.length; ++i){ 
					
					String toucherRectal = toucher[i];
					int id_ToucherRectal = Integer.parseInt(toucherRectal);
					ToucherRectal toucherRect =toucherDAO.trouverToucherById(id_ToucherRectal);
					touchers.add(toucherRect);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				ExamenPostOp examenPostOp = new ExamenPostOp(hopital, dossier, dateExamen, poids, taille, oMS, iMC, delai, nbreSelles, syndromes, touchers);
				examPostDAO.modifierExamenClinique(idAncienExamen, examenPostOp);
			}
			if(syndPreOp!=null){
				List<CategorieSynAnormal> categories = new ArrayList<>();
				try { 
					for (int i = 0; i < syndPreOp.length; ++i){ 
					
					String categorieSynAnormal = syndPreOp[i];
					int id_CategorieSynAnormal = Integer.parseInt(categorieSynAnormal);
					CategorieSynAnormal categorie=categDAO.trouverCategorieSynAnormalById(id_CategorieSynAnormal);
					categories.add(categorie);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				ExamenPreOpAnormal examenAnormal = new ExamenPreOpAnormal(hopital, dossier, dateExamen, poids, taille, oMS, iMC, typeExamen, categories);
//				examenAnoDAO.modifierExamenPreOpAnormal(idAncienExamen, examenAnormal);
				examPostDAO.modifierExamenClinique(idAncienExamen, examenAnormal);
			}
			
			
			return "/modExamenClinique.jsp";
		}
		public String modExamenCliniquePreTrait(){
			
			String idExamen = request.getParameter("idExamen");
			int idAncienExamen= Integer.parseInt(idExamen);
			
			String doss = request.getParameter("dossier");
			int id_dossier =Integer.parseInt(doss);
			DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
			String hop = request.getParameter("hopital");
			int id_hopital =Integer.parseInt(hop);
			Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
//			String medecin = request.getParameter("medecin");
			
			String date = request.getParameter("dateexamen");
			SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
			Date dateExamen = new Date();
			try {
				dateExamen = sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pds = request.getParameter("poids");
			Float poids = Float.parseFloat(pds);
			String tail = request.getParameter("taille");
			Float taille = Float.parseFloat(tail);
			String imc = request.getParameter("imc");
			Float iMC = Float.parseFloat(imc);
			String oms = request.getParameter("oms");
			Float oMS = Float.parseFloat(oms);
			

			String type = request.getParameter("typeExamen");
			TypeExamen typeExamen = TypeExamen.valueOf(type);
			
			String del = request.getParameter("delai");
			
			
			String[] syndPreOp = request.getParameterValues("syndromePreOp");
			
			
			if(del.equalsIgnoreCase("") 
					&& syndPreOp==null){
				
				ExamenPreOp examenNormal = new ExamenPreOp(hopital, dossier, dateExamen, poids, taille, oMS, iMC, typeExamen);
//				examenAnoDAO.modifierExamenPreOp(idAncienExamen, examenNormal);
				examPostDAO.modifierExamenClinique(idAncienExamen, examenNormal);
			}
			if(del!=""){
				int delai = Integer.parseInt(del);
				String nbreSel = request.getParameter("nbSelle");
				int nbreSelles = Integer.parseInt(nbreSel);
				String[] syndPostOp = request.getParameterValues("syndrome");
				List<SyndromesPostOp> syndromes = new ArrayList<>();
				try { 
					for (int i = 0; i < syndPostOp.length; ++i){ 
					
					String syndromesPostOp = syndPostOp[i];
					int id_SyndromesPostOp = Integer.parseInt(syndromesPostOp);
					SyndromesPostOp syndrome =syndromesPostDAO.trouverSyndromesPostOpById(id_SyndromesPostOp);
					syndromes.add(syndrome);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				String[] toucher = request.getParameterValues("toucher");
				List<ToucherRectal> touchers = new ArrayList<>();
				try { 
					for (int i = 0; i < toucher.length; ++i){ 
					
					String toucherRectal = toucher[i];
					int id_ToucherRectal = Integer.parseInt(toucherRectal);
					ToucherRectal toucherRect =toucherDAO.trouverToucherById(id_ToucherRectal);
					touchers.add(toucherRect);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				ExamenPostOp examenPostOp = new ExamenPostOp(hopital, dossier, dateExamen, poids, taille, oMS, iMC, delai, nbreSelles, syndromes, touchers);
//				examPostDAO.modifierExamenPostOp(idAncienExamen, examenPostOp);
				examPostDAO.modifierExamenClinique(idAncienExamen, examenPostOp);
			}
			if(syndPreOp!=null){
				List<CategorieSynAnormal> categories = new ArrayList<>();
				try { 
					for (int i = 0; i < syndPreOp.length; ++i){ 
					
					String categorieSynAnormal = syndPreOp[i];
					int id_CategorieSynAnormal = Integer.parseInt(categorieSynAnormal);
					CategorieSynAnormal categorie=categDAO.trouverCategorieSynAnormalById(id_CategorieSynAnormal);
					categories.add(categorie);
					} 
				} catch (Exception e1) { 
				//e1.printStackTrace(); 
				}
				ExamenPreOpAnormal examenAnormal = new ExamenPreOpAnormal(hopital, dossier, dateExamen, poids, taille, oMS, iMC, typeExamen, categories);
//				examenAnoDAO.modifierExamenPreOp(idAncienExamen, examenAnormal);
				examPostDAO.modifierExamenClinique(idAncienExamen, examenAnormal);
			}
			
			
			return "/modExamenClinique.jsp";
		}
		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}

		public void setResponse(HttpServletResponse response) {
			this.response = response;
		}
		
		public String modExamePre(){
			String idExamen = request.getParameter("idExamen");
			int idAncienExamen= Integer.parseInt(idExamen);
			String doss = request.getParameter("dossier");
			int id_dossier =Integer.parseInt(doss);
			DossierMedicale dossier = dosDAO.trouverDossierById(id_dossier);
			String hop = request.getParameter("hopital");
			int id_hopital =Integer.parseInt(hop);
			Hopital hopital = hopDAO.trouverHopitalById(id_hopital);
			String date = request.getParameter("dateexamen");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			Date journeyDate = null;
			try {
				journeyDate = new java.sql.Date(df.parse(date).getTime());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			String pds = request.getParameter("poids");
			Float poids = Float.parseFloat(pds);
			String tail = request.getParameter("taille");
			Float taille = Float.parseFloat(tail);
			String imc = request.getParameter("imc");
			Float iMC = Float.parseFloat(imc);
			String oms = request.getParameter("oms");
			Float oMS = Float.parseFloat(oms);
			

			String type = request.getParameter("typeExamen");
			TypeExamen typeExamen = TypeExamen.valueOf(type);
				
				ExamenPreOp examenNormal = new ExamenPreOp(hopital, dossier, journeyDate, poids, taille, oMS, iMC, typeExamen);
     			System.out.println("test "+idAncienExamen+ " " + id_dossier + " " +hop+ " " +pds+ " " +tail+ " " +imc+ " " + oms);
				examenAnoDAO.modifierExamenPreOp(idAncienExamen, examenNormal);
				//examPostDAO.modifierExamenClinique(idAncienExamen, examenNormal);
		
			return "/dossier.jsp";
		}
	

}
