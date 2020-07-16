package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.SyndromesPostOp;

public class SyndromesPostOpDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public SyndromesPostOpDAO() {
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
    public  boolean ajouterSyndromesPostOp(SyndromesPostOp  syndrome){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(syndrome);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerSyndromesPostOp(int syndrome_id){
        EntityManager em = this.newEntityManager();
        try {
        	SyndromesPostOp syndrome = em.find(SyndromesPostOp.class, syndrome_id);
            em.remove(syndrome);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierSyndromesPostOp(int ancienSyndrome_id, SyndromesPostOp newSyndrome){
        EntityManager em = this.newEntityManager();
        try {
        	SyndromesPostOp ancienSyndrome = em.find(SyndromesPostOp.class, ancienSyndrome_id);
        	em.detach(ancienSyndrome);
        	ancienSyndrome.setSyndrome(newSyndrome.getSyndrome());
        	em.merge(ancienSyndrome);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<SyndromesPostOp> listerSyndromesPostOp(){
        EntityManager em = this.newEntityManager();
        TypedQuery<SyndromesPostOp> query = em.createQuery("SELECT a FROM SyndromesPostOp a", SyndromesPostOp.class);
        List<SyndromesPostOp> syndromes = query.getResultList();
        this.closeEntityManager(em);
        return syndromes;
    }
	
    public SyndromesPostOp trouverSyndromesPostOpById(int id){
		EntityManager em = this.newEntityManager();
		SyndromesPostOp synd = em.find(SyndromesPostOp.class, id);
        
        this.closeEntityManager(em);
        return synd;
		
	}
}
