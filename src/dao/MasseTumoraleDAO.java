package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.MasseTumorale;

public class MasseTumoraleDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public MasseTumoraleDAO() {
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
	public  boolean ajouterMasseTumorale(MasseTumorale  masse){
	        try {
	            EntityManager em = this.newEntityManager();
	            
	            em.persist(masse);
	            this.closeEntityManager(em);
	            System.out.println("bien enregistré!");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur d'enregistrement!!!");
	            return false;
	        }
	 }
	public  boolean supprimerMasseTumorale(MasseTumorale  masse){
        try {
            EntityManager em = this.newEntityManager();
            em.remove(masse);
            this.closeEntityManager(em);
            System.out.println("bien supprimer!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de suppression!!!");
            return false;
        }
	}
	public boolean modifierMasseTumorale(int ancienMasse_id, MasseTumorale newMasse){
        EntityManager em = this.newEntityManager();
        try {
        	MasseTumorale ancienMasse = em.find(MasseTumorale.class, ancienMasse_id);
        	em.detach(ancienMasse);
        	ancienMasse.setAspect(newMasse.getAspect());
        	ancienMasse.setCirconf(newMasse.getCirconf());
        	ancienMasse.setSiege(newMasse.getSiege());
        	ancienMasse.setStenose(newMasse.getStenose());
        	ancienMasse.setTaille(newMasse.getTaille());
        	ancienMasse.setRefAnaPath(newMasse.getRefAnaPath());
        	ancienMasse.setType(newMasse.getType());
        	
        	em.merge(ancienMasse);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
	}
	 public List<MasseTumorale> listerLesMasseTumorale(){
	    	EntityManager em = this.newEntityManager();
	        TypedQuery<MasseTumorale> query = em.createQuery("SELECT a FROM MasseTumorale a", MasseTumorale.class);
	        List<MasseTumorale> masses = query.getResultList();
	        this.closeEntityManager(em);
	        return masses;
	    }
}
