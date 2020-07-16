package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Forme;
import dao.entities.Geste;

public class GesteDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public GesteDAO() {
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
	    public  boolean ajouterGeste(Geste  geste){
	        try {
	            EntityManager em = this.newEntityManager();
	            em.persist(geste);
	            this.closeEntityManager(em);
	            System.out.println("bien enregistré!");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur d'enregistrement!!!");
	            return false;
	        }
	    }
	    public boolean supprimerGeste(int geste_id){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Geste geste = em.find(Geste.class, geste_id);
	            em.remove(geste);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	    public boolean modifierGeste(int ancienGeste_id, Geste newGeste){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Geste ancienGeste = em.find(Geste.class, ancienGeste_id);
	        	em.detach(ancienGeste);
	        	ancienGeste.setGeste(newGeste.getGeste());
	        	em.merge(ancienGeste);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour  : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	    public List<Geste> listerLesGeste(){
	    	EntityManager em = this.newEntityManager();
	        TypedQuery<Geste> query = em.createQuery("SELECT a FROM Geste a", Geste.class);
	        List<Geste> gestes = query.getResultList();
	        this.closeEntityManager(em);
	        return gestes;
	    }
	    public Geste trouverGesteById(int id){
	    	Geste syndrome = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            syndrome = em.find(Geste.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return syndrome;
	    }
			
}
