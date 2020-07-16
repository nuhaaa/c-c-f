package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.MotifDecces;

public class MotifDeccesDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public MotifDeccesDAO() {
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
    public  boolean ajouterMotifDecces(MotifDecces  motif){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(motif);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerMotif(int motif_id){
        EntityManager em = this.newEntityManager();
        try {
        	MotifDecces motif = em.find(MotifDecces.class, motif_id);
            em.remove(motif);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierMotif(int ancienMotif_id, MotifDecces newMotif){
        EntityManager em = this.newEntityManager();
        try {
        	MotifDecces ancienMotif = em.find(MotifDecces.class, ancienMotif_id);
        	em.detach(ancienMotif);
        	ancienMotif.setMotif(newMotif.getMotif());
        	em.merge(ancienMotif);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour  : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<MotifDecces> listerLesMotif(){
    	EntityManager em = this.newEntityManager();
        TypedQuery<MotifDecces> query = em.createQuery("SELECT a FROM MotifDecces a", MotifDecces.class);
        List<MotifDecces> motifs = query.getResultList();
        this.closeEntityManager(em);
        return motifs;
    }
    public MotifDecces trouverMotifParMotif(String cause){
    	MotifDecces motif = null;
	        try {
	            EntityManager em = this.newEntityManager();
	            TypedQuery<MotifDecces> query = em.createQuery("SELECT s FROM MotifDecces s WHERE s.motif = :motif", MotifDecces.class);
	            query.setParameter("motif", cause);
	            motif = query.getSingleResult();
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur authentification "+ e.getMessage());
	        }
	        return motif;
	    }	
    public MotifDecces trouverMotifById(int id){
    	MotifDecces syndrome = null;
	        try {
	            EntityManager em = this.newEntityManager();
	            syndrome = em.find(MotifDecces.class, id);
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur"+ e.getMessage());
	        }
	        return syndrome;
    }
}
