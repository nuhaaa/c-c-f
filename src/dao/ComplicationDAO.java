package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Circonference;
import dao.entities.Complication;


public class ComplicationDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public ComplicationDAO() {
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
    public  boolean ajouterComplication(Complication compl){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(compl);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerComplication(int compl_id){
        EntityManager em = this.newEntityManager();
        try {
        	Complication compl = em.find(Complication.class, compl_id);
            em.remove(compl);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierComplication(int compl_id, Complication newCompl){
        EntityManager em = this.newEntityManager();
        try {
        	Complication ancienCompl = em.find(Complication.class, compl_id);
        	em.detach(ancienCompl);
        	ancienCompl.setComplcation(newCompl.getComplication());
        	em.merge(ancienCompl);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Complication> listerLesComplication(){
    	EntityManager em = this.newEntityManager();
        TypedQuery<Complication> query = em.createQuery("SELECT a FROM Complication a", Complication.class);
        List<Complication> complications = query.getResultList();
        this.closeEntityManager(em);
        return complications;
    }
    
    public Complication trouverAspectMacroById(int id){
  		EntityManager em = this.newEntityManager();
  		Complication categorie = em.find(Complication.class, id);
          
          this.closeEntityManager(em);
          return categorie;
  		
  	}
}
