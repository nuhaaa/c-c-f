package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import dao.entities.Consanguin;

public class ConsanguinDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;
	public ConsanguinDAO() {
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
	public boolean enregistrerConsanguinité(Consanguin consanguin){
		 try {
	            EntityManager em = this.newEntityManager();
	            em.persist(consanguin);
	            this.closeEntityManager(em);
	            System.out.println(" bien enregistre  !");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur d'enregistrement!!!");
	            return false;
	        }
	}
	 public boolean supprimerConsanguin(int consanguin_id){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Consanguin consanguin = em.find(Consanguin.class, consanguin_id);
	            em.remove(consanguin);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
}
