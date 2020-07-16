package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Anesthesie;
import dao.entities.CategorieSynAnormal;
import dao.entities.Complication;
import dao.entities.DossierMedicale;
import dao.entities.EndoAnormal;
import dao.entities.ExamenPreOp;
import dao.entities.ExamenPreOp;
import dao.entities.ExamenPreOpAnormal;
import dao.entities.Hopital;
import dao.entities.Individu;
import dao.entities.MasseTumorale;
import dao.entities.Polypose;
import dao.entities.TypeAndoscopie;
import dao.entities.TypeExamen;

public class ExamenPreOpAnormalDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public ExamenPreOpAnormalDAO() {
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
    public  boolean ajouterExamenPreOpAnormal(ExamenPreOpAnormal  examenAnormal){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = examenAnormal.getDossier();
            dossier.addExamenMedicale(examenAnormal);
//            List<CategorieSynAnormal> categories = examenAnormal.getCategories();
//            for(CategorieSynAnormal cat: categories){
//            	examenAnormal.addCategorie(cat);
//            }
            em.persist(examenAnormal);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("examen clinique non enregister!!!");
            return false;
        }
    }
    public boolean supprimerExamenPreOpAnormal(int examenAnormal_id){
        EntityManager em = this.newEntityManager();
        try {
        	ExamenPreOpAnormal examenAnormal = em.find(ExamenPreOpAnormal.class, examenAnormal_id);
        	DossierMedicale dossier = examenAnormal.getDossier();
        	dossier.removeExamenMedicale(examenAnormal);
        	examenAnormal.setCategories(null);
            em.remove(examenAnormal);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean removeExamenPreOpAnormal(ExamenPreOpAnormal  examenAnormal){
        EntityManager em = this.newEntityManager();
        try {
            em.remove(examenAnormal);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierExamenPreOpAnormal(int ancienExamenAnormal_id, ExamenPreOpAnormal newExamenAnormal){
        EntityManager em = this.newEntityManager();
        try {
        	ExamenPreOpAnormal ancienEndoAnormal = em.find(ExamenPreOpAnormal.class, ancienExamenAnormal_id);
        	em.detach(ancienEndoAnormal);
        	
        	ancienEndoAnormal.setCategories(newExamenAnormal.getCategories());
        	ancienEndoAnormal.setDateExamen(newExamenAnormal.getDateExamen());
        	ancienEndoAnormal.setHopital(newExamenAnormal.getHopital());
        	ancienEndoAnormal.setIMC(newExamenAnormal.getIMC());
        	ancienEndoAnormal.setOMS(newExamenAnormal.getOMS());
        	ancienEndoAnormal.setPoids(newExamenAnormal.getPoids());
        	ancienEndoAnormal.setTypeExamen(newExamenAnormal.getTypeExamen());
        	ancienEndoAnormal.setTaille(newExamenAnormal.getTaille());
        	
        	em.merge(ancienEndoAnormal);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    
    public boolean modifierExamenPreOp(int ancien_id, ExamenPreOp newExamenPreOp){
    	EntityManager em = factory.createEntityManager();
    	em.getTransaction().begin();
        try {
        	System.out.println("##oldID :: "+ancien_id);
        	System.out.println("##newEnd :: "+newExamenPreOp);
        	ExamenPreOp ancienExamenPreOp = em.find(ExamenPreOp.class, ancien_id);
        	System.out.println("##oldEnd :: "+ancienExamenPreOp);
        	em.detach(ancienExamenPreOp);
        	
        	ancienExamenPreOp.setDateExamen(newExamenPreOp.getDateExamen());
        	ancienExamenPreOp.setHopital(newExamenPreOp.getHopital());
        	ancienExamenPreOp.setIMC(newExamenPreOp.getIMC());
        	ancienExamenPreOp.setOMS(newExamenPreOp.getOMS());
        	ancienExamenPreOp.setPoids(newExamenPreOp.getPoids());
        	ancienExamenPreOp.setTaille(newExamenPreOp.getTaille());
        	ancienExamenPreOp.setTypeExamen(newExamenPreOp.getTypeExamen());
        	ancienExamenPreOp.setDossier(newExamenPreOp.getDossier());
        	
        	
        	if(newExamenPreOp instanceof ExamenPreOpAnormal){
        		if(!(ancienExamenPreOp instanceof ExamenPreOpAnormal)){
        			Hopital hopital = newExamenPreOp.getHopital();
                	DossierMedicale dossier = newExamenPreOp.getDossier();
                	Date date = newExamenPreOp.getDateExamen();
                	Float imc = newExamenPreOp.getIMC();
                	Float oms = newExamenPreOp.getOMS();
                	Float taille =newExamenPreOp.getTaille();
                	Float poids = newExamenPreOp.getPoids();
                	TypeExamen type  =newExamenPreOp.getTypeExamen();
                	List<CategorieSynAnormal> categories=((ExamenPreOpAnormal) newExamenPreOp).getCategories();
        			em.remove(ancienExamenPreOp);
        			ancienExamenPreOp = new ExamenPreOpAnormal(hopital, dossier, date, poids, oms, imc, taille, type, categories);
        		}
        		((ExamenPreOpAnormal)ancienExamenPreOp).setCategories(((ExamenPreOpAnormal)newExamenPreOp).getCategories());
        
        	}
        	em.merge(ancienExamenPreOp);
        	em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise � jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    

    public List<ExamenPreOpAnormal> listerAndoscopieAnormalParPatient(Individu patient){
        EntityManager em = this.newEntityManager();
        TypedQuery<ExamenPreOpAnormal> query = em.createQuery("SELECT b FROM ExamenPreOpAnormal b WHERE b.getDossier().getPatient = patient ", ExamenPreOpAnormal.class);
        List<ExamenPreOpAnormal> examenAnormals = query.getResultList();
        this.closeEntityManager(em);
        return examenAnormals;
    }
    public ExamenPreOpAnormal trouverExamenById(int id){
        EntityManager em = this.newEntityManager();
        ExamenPreOpAnormal examen = em.find(ExamenPreOpAnormal.class, id);
        this.closeEntityManager(em);
        return examen;
    }
}
