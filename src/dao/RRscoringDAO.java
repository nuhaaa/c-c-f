package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import dao.entities.RRscoring;
import dao.entities.Traitement;

public class RRscoringDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public RRscoringDAO() {
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
    public  boolean ajouterRRscoring(RRscoring  scoring){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(scoring);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerRRscoring(int scoring_id){
        EntityManager em = this.newEntityManager();
        try {
        	RRscoring scoring = em.find(RRscoring.class, scoring_id);
            em.remove(scoring);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierForme(int ancienRRscoring_id, RRscoring newRRscoring){
        EntityManager em = this.newEntityManager();
        try {
        	RRscoring ancienRRscoring = em.find(RRscoring.class, ancienRRscoring_id);
        	em.detach(ancienRRscoring);
        	ancienRRscoring.setScoring(newRRscoring.getScoring());
        	em.merge(ancienRRscoring);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<RRscoring> listerRRscoring(){
        EntityManager em = this.newEntityManager();
        TypedQuery<RRscoring> query = em.createQuery("SELECT a FROM RRscoring a", RRscoring.class);
        List<RRscoring> scorings = query.getResultList();
        this.closeEntityManager(em);
        return scorings;
    }
    public RRscoring trouverTumeurById(int id){
		EntityManager em = this.newEntityManager();
		RRscoring categorie = em.find(RRscoring.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
