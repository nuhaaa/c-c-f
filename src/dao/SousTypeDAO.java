package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Siege;
import dao.entities.SousType;

public class SousTypeDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public SousTypeDAO() {
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
    public  boolean ajouterSousType(SousType  soustype){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(soustype);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerSousType(int soustype_id){
        EntityManager em = this.newEntityManager();
        try {
        	SousType soustype = em.find(SousType.class, soustype_id);
            em.remove(soustype);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierSousType(int ancienSousType_id, SousType newSousType){
        EntityManager em = this.newEntityManager();
        try {
        	SousType ancienSousType = em.find(SousType.class, ancienSousType_id);
        	em.detach(ancienSousType);
        	ancienSousType.setSousType(newSousType.getSousType());
        	em.merge(ancienSousType);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<SousType> listerSousType(){
        EntityManager em = this.newEntityManager();
        TypedQuery<SousType> query = em.createQuery("SELECT a FROM SousType a", SousType.class);
        List<SousType> soustypes = query.getResultList();
        this.closeEntityManager(em);
        return soustypes;
    }
    public SousType trouverSousTypeById(int id){
    	SousType syndrome = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            syndrome = em.find(SousType.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return syndrome;
	    }	
}
