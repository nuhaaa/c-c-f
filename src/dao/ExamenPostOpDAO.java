package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.CategorieSynAnormal;
import dao.entities.DossierMedicale;
import dao.entities.ExamenClinique;
import dao.entities.ExamenPostOp;
import dao.entities.ExamenPreOp;
import dao.entities.ExamenPreOpAnormal;
import dao.entities.Hopital;
import dao.entities.Individu;
import dao.entities.SyndromesPostOp;
import dao.entities.ToucherRectal;
import dao.entities.TypeExamen;

public class ExamenPostOpDAO {
	
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;
	public ExamenPostOpDAO() {
		factory = Persistence.createEntityManagerFactory(PU_NAME);
	}
	private EntityManager newEntityManager(){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        return em;
	}
    
	private void closeEntityManager(EntityManager em){
        if(em != null){
            if(em.isOpen()){
                EntityTransaction trs = em.getTransaction();
                if(trs.isActive()){
                    trs.commit();
                }
                em.close();
            }
        }
	}
	public boolean ajouterExamenPostOp(ExamenPostOp examen){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = examen.getDossier();
            dossier.addExamenMedicale(examen);
//          
            em.persist(examen);
            this.closeEntityManager(em);
            System.out.println("enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
	}  
	 public boolean supprimerExamenPostOp(int examen_id){
	        EntityManager em = this.newEntityManager();
	        try {
	        	ExamenPostOp examen = em.find(ExamenPostOp.class, examen_id);
	        	DossierMedicale dossier = examen.getDossier();
	        	dossier.removeExamenMedicale(examen);
	        	examen.setSyndromes(null);
	        	examen.setTouchers(null);
	            em.remove(examen);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	  }
	 public boolean removeExamenPostOp(ExamenPostOp examen){
	        EntityManager em = this.newEntityManager();
	        try {
	            em.remove(examen);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	  }
	 public boolean modifierExamenPostOp(int ancienExamen_id, ExamenPostOp newExamen){
	        EntityManager em = this.newEntityManager();
	        try {
	        	ExamenPostOp ancienExamen = em.find(ExamenPostOp.class, ancienExamen_id);
	        	em.detach(ancienExamen);
	        	ancienExamen.setDateExamen(newExamen.getDateExamen());
	        	ancienExamen.setDelai(newExamen.getDelai());
	        	ancienExamen.setHopital(newExamen.getHopital());
	        	ancienExamen.setIMC(newExamen.getIMC());
	        	ancienExamen.setNbreSelles(newExamen.getNbreSelles());
	        	ancienExamen.setTaille(newExamen.getTaille());
	        	ancienExamen.setOMS(newExamen.getOMS());
	        	ancienExamen.setPoids(newExamen.getPoids());
	        	ancienExamen.setSyndromes(newExamen.getSyndromes());
	        	ancienExamen.setTouchers(newExamen.getTouchers());
	       
	        	em.merge(ancienExamen);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise Ã  jour : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	 public boolean modifierExamenClinique(int ancien_id, ExamenClinique newExamenClinique){
	    	EntityManager em = factory.createEntityManager();
	    	em.getTransaction().begin();
	        try {
	        	System.out.println("##oldID :: "+ancien_id);
	        	System.out.println("##newEnd :: "+newExamenClinique);
	        	ExamenClinique ancienExamenClinique = em.find(ExamenClinique.class, ancien_id);
	        	System.out.println("##oldEnd :: "+ancienExamenClinique);
	        	em.detach(ancienExamenClinique);
	        	
	        	ancienExamenClinique.setDateExamen(newExamenClinique.getDateExamen());
	        	ancienExamenClinique.setHopital(newExamenClinique.getHopital());
	        	ancienExamenClinique.setIMC(newExamenClinique.getIMC());
	        	ancienExamenClinique.setOMS(newExamenClinique.getOMS());
	        	ancienExamenClinique.setPoids(newExamenClinique.getPoids());
	        	ancienExamenClinique.setTaille(newExamenClinique.getTaille());
	        	ancienExamenClinique.setDossier(newExamenClinique.getDossier());
	        	if(newExamenClinique instanceof ExamenPreOp){
	        		if(!(ancienExamenClinique instanceof ExamenPreOp)){
	        			Hopital hopital = newExamenClinique.getHopital();
	                	DossierMedicale dossier = newExamenClinique.getDossier();
	                	Date date = newExamenClinique.getDateExamen();
	                	Float imc = newExamenClinique.getIMC();
	                	Float oms = newExamenClinique.getOMS();
	                	Float taille =newExamenClinique.getTaille();
	                	Float poids = newExamenClinique.getPoids();
	                	TypeExamen type  =((ExamenPreOp) newExamenClinique).getTypeExamen();
	                	em.detach(ancienExamenClinique);
//	                	em.remove(ancienExamenClinique);
	                	ancienExamenClinique = new ExamenPreOp(hopital, dossier, date, poids, oms, imc, taille, type);
	        		}
	        		((ExamenPreOp)ancienExamenClinique).setTypeExamen(((ExamenPreOp)newExamenClinique).getTypeExamen());
	        	}
		        	if(newExamenClinique instanceof ExamenPreOpAnormal){
		        		if(!(ancienExamenClinique instanceof ExamenPreOpAnormal)){
		        			Hopital hopital = newExamenClinique.getHopital();
		                	DossierMedicale dossier = newExamenClinique.getDossier();
		                	Date date = newExamenClinique.getDateExamen();
		                	Float imc = newExamenClinique.getIMC();
		                	Float oms = newExamenClinique.getOMS();
		                	Float taille =newExamenClinique.getTaille();
		                	Float poids = newExamenClinique.getPoids();
		                	TypeExamen type  =((ExamenPreOpAnormal) newExamenClinique).getTypeExamen();
		                	List<CategorieSynAnormal> categories=((ExamenPreOpAnormal) newExamenClinique).getCategories();
		                	em.detach(ancienExamenClinique);
//		        			em.remove(ancienExamenClinique);
		                	ancienExamenClinique = new ExamenPreOpAnormal(hopital, dossier, date, poids, oms, imc, taille, type, categories);
		        		}
		        		((ExamenPreOpAnormal)ancienExamenClinique).setCategories(((ExamenPreOpAnormal) newExamenClinique).getCategories());
		        
	        	}
		        	if(newExamenClinique instanceof ExamenPostOp){
		        		if(!(ancienExamenClinique instanceof ExamenPostOp)){
		        			Hopital hopital = newExamenClinique.getHopital();
		                	DossierMedicale dossier = newExamenClinique.getDossier();
		                	Date date = newExamenClinique.getDateExamen();
		                	Float imc = newExamenClinique.getIMC();
		                	Float oms = newExamenClinique.getOMS();
		                	Float taille =newExamenClinique.getTaille();
		                	Float poids = newExamenClinique.getPoids();
		                	int delai = ((ExamenPostOp) newExamenClinique).getDelai();
		                	int nbreSelles = ((ExamenPostOp) newExamenClinique).getNbreSelles();
		                	List<SyndromesPostOp> syndromes=((ExamenPostOp) newExamenClinique).getSyndromes();
		                	List<ToucherRectal> touchers =((ExamenPostOp) newExamenClinique).getTouchers();
		                	em.detach(ancienExamenClinique);
//		        			em.remove(ancienExamenClinique);
		                	ancienExamenClinique = new ExamenPostOp(hopital, dossier, date, poids, taille, oms, imc, delai, nbreSelles, syndromes, touchers);
		        		}
		        		((ExamenPostOp)ancienExamenClinique).setDelai(((ExamenPostOp) newExamenClinique).getDelai());
		        		((ExamenPostOp)ancienExamenClinique).setNbreSelles(((ExamenPostOp) newExamenClinique).getNbreSelles());
		        		((ExamenPostOp)ancienExamenClinique).setSyndromes(((ExamenPostOp) newExamenClinique).getSyndromes());
		        		((ExamenPostOp)ancienExamenClinique).setTouchers(((ExamenPostOp) newExamenClinique).getTouchers());
		        
	        	}
	        	em.merge(ancienExamenClinique);
	        	em.getTransaction().commit();
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	 public List<ExamenPostOp> listerExamenPostOpParPatient(Individu patient){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<ExamenPostOp> query = em.createQuery("SELECT b FROM ExamenPostOp b WHERE b.getDossier().getPatient = patient ", ExamenPostOp.class);
	        List<ExamenPostOp> examens = query.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public ExamenPostOp trouverExamenById(int id){
	        EntityManager em = this.newEntityManager();
	        ExamenPostOp examen = em.find(ExamenPostOp.class, id);
	        this.closeEntityManager(em);
	        return examen;
	    }
}
