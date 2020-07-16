package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.SyndromeFamille;
import dao.entities.TypeStatut;

public class TypeStatutDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public TypeStatutDAO() {
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
    public  boolean ajouterTypeStatut(TypeStatut  type){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(type);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerTypeStatut(int type_id){
        EntityManager em = this.newEntityManager();
        try {
        	TypeStatut type = em.find(TypeStatut.class, type_id);
            em.remove(type);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierTypeStatut(int ancienType_id, TypeStatut newType){
        EntityManager em = this.newEntityManager();
        try {
        	TypeStatut ancienType = em.find(TypeStatut.class, ancienType_id);
        	em.detach(ancienType);
        	ancienType.setTypeCancer(newType.getTypeCancer());
        	em.merge(ancienType);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<TypeStatut> listerType(){
        EntityManager em = this.newEntityManager();
        TypedQuery<TypeStatut> query = em.createQuery("SELECT a FROM TypeStatut a", TypeStatut.class);
        List<TypeStatut> types = query.getResultList();
        this.closeEntityManager(em);
        return types;
    }
    
    public TypeStatut trouverSyndById(int id){
    	TypeStatut syndrome = null;
	        try {
	            EntityManager em = this.newEntityManager();
	            syndrome = em.find(TypeStatut.class, id);
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur"+ e.getMessage());
	        }
	        return syndrome;
    }
}
