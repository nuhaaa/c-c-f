package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dao.entities.Decces;
import dao.entities.DossierMedicale;

public class DeccesDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;

    
    public DeccesDAO() {

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
   
    public List<Decces> listerLesDecces(){
        EntityManager em = this.newEntityManager();
        TypedQuery<Decces> query = em.createQuery("SELECT a FROM Decces a", Decces.class);
        List<Decces> aspects = query.getResultList();
        this.closeEntityManager(em);
        return aspects;
    }
    public boolean listerLesDecces(int id_patient){
	 	
    	List<Decces>  decces= listerLesDecces();
        for(int i=0;i<decces.size();i++){
        	
        	Decces d=decces.get(i);
        	if (d.getIndividu().getId()==id_patient)  return true;
        }
        
        return false;
    }
    
    public List<Object[]> deccesParMotif() {
		 List<Object[]> decces = null;
		 try {
	            EntityManager em = this.newEntityManager();
	            decces = em.createQuery( "SELECT count(d), m.motif FROM Decces d JOIN d.motif m group by m.motif").getResultList();
	            //
	            this.closeEntityManager(em);
	        } catch (Exception e) {
	            System.out.println("Erreur authentification "+ e.getMessage());
	        }
		 return decces;
	 }
   
   public int nombredecces() {
   	int decces = 0;
		 try {
	            EntityManager em = this.newEntityManager();
	            Query query =em.createQuery( "SELECT count(d) as nombreDecces FROM Decces d");
	            decces = Integer.parseInt(query.getSingleResult().toString());
	            this.closeEntityManager(em);
	        } catch (Exception e) {
	            System.out.println("Erreur authentification "+ e.getMessage());
	        }
		 return decces;
	 }
}
