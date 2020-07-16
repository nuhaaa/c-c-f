package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.SousType;
import dao.entities.Stroma;

public class StromaDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public StromaDAO() {
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
    public  boolean ajouterStroma(Stroma  stroma){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(stroma);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerStroma(int stroma_id){
        EntityManager em = this.newEntityManager();
        try {
        	Stroma stroma = em.find(Stroma.class, stroma_id);
            em.remove(stroma);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierStroma(int ancienStroma_id, Stroma newStroma){
        EntityManager em = this.newEntityManager();
        try {
        	Stroma ancienStroma = em.find(Stroma.class, ancienStroma_id);
        	em.detach(ancienStroma);
        	ancienStroma.setStroma(newStroma.getStroma());
        	em.merge(ancienStroma);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Stroma> listerStroma(){
        EntityManager em = this.newEntityManager();
        TypedQuery<Stroma> query = em.createQuery("SELECT a FROM Stroma a", Stroma.class);
        List<Stroma> stromas = query.getResultList();
        this.closeEntityManager(em);
        return stromas;
    }
    
    public Stroma trouverStromaById(int id){
    	Stroma syndrome = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            syndrome = em.find(Stroma.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return syndrome;
	    }	
}
