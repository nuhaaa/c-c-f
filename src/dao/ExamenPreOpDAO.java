package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.DossierMedicale;
import dao.entities.ExamenPreOp;
import dao.entities.Individu;

public class ExamenPreOpDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	public ExamenPreOpDAO() {
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
	public boolean ajouterExamenPreOp(ExamenPreOp examen){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = examen.getDossier();
            dossier.addExamenMedicale(examen);
            em.persist(examen);
            this.closeEntityManager(em);
            System.out.println(" enregistre examen");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement !!!");
            return false;
        }
	}
	 public boolean supprimerExamenPreOp(int examen_id){
	        EntityManager em = this.newEntityManager();
	        try {
	        	ExamenPreOp examen = em.find(ExamenPreOp.class, examen_id);
	        	DossierMedicale dossier = examen.getDossier();
	        	dossier.removeExamenMedicale(examen);
	            em.remove(examen);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	  }
	 public boolean modifierExamenPreOp(int ancienExamen_id, ExamenPreOp newExamen){
	        EntityManager em = this.newEntityManager();
	        try {
	        	ExamenPreOp ancienExamen = em.find(ExamenPreOp.class, ancienExamen_id);
	        	em.detach(ancienExamen);
	        	ancienExamen.setDateExamen(newExamen.getDateExamen());
	        	ancienExamen.setHopital(newExamen.getHopital());
	        	ancienExamen.setIMC(newExamen.getIMC());
	        	ancienExamen.setOMS(newExamen.getOMS());
	        	ancienExamen.setPoids(newExamen.getPoids());
	        	ancienExamen.setTaille(newExamen.getTaille());
	        	ancienExamen.setTypeExamen(newExamen.getTypeExamen());
	        	
	        	em.merge(ancienExamen);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	// lister
	 
	    public List<ExamenPreOp> listerParPatient(Individu patient){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<ExamenPreOp> query = em.createQuery("SELECT b FROM ExamenPreOp b WHERE b.getDossier().getPatient = patient ", ExamenPreOp.class);
	        List<ExamenPreOp> examens = query.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	    public ExamenPreOp trouverExamenById(int id){
	        EntityManager em = this.newEntityManager();
	        ExamenPreOp examen = em.find(ExamenPreOp.class, id);
	        this.closeEntityManager(em);
	        return examen;
	    }
}
