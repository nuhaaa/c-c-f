package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.Siege;
import dao.entities.StatutCancereux;

public class SiegeDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public SiegeDAO() {
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
    public  boolean ajouterSiege(Siege  siege){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(siege);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerSiege(int siege_id){
        EntityManager em = this.newEntityManager();
        try {
        	Siege siege = em.find(Siege.class, siege_id);
            em.remove(siege);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierSiege(int ancienSiege_id, Siege newSiege){
        EntityManager em = this.newEntityManager();
        try {
        	Siege ancienSiege = em.find(Siege.class, ancienSiege_id);
        	em.detach(ancienSiege);
        	ancienSiege.setSiege(newSiege.getSiege());
        	em.merge(ancienSiege);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Siege> listerSiege(){
        EntityManager em = this.newEntityManager();
        TypedQuery<Siege> query = em.createQuery("SELECT a FROM Siege a", Siege.class);
        List<Siege> sieges = query.getResultList();
        this.closeEntityManager(em);
        return sieges;
    }
	
    public Siege trouverSiegeById(int id){
    	Siege syndrome = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            syndrome = em.find(Siege.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return syndrome;
	    }
}
