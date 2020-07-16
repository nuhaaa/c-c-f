package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Siege;
import dao.entities.Type;

public class TypeDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public TypeDAO() {
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
    public  boolean ajouterType(Type  type){
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
    public boolean supprimerType(int type_id){
        EntityManager em = this.newEntityManager();
        try {
        	Type type = em.find(Type.class, type_id);
            em.remove(type);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierType(int ancienType_id, Type newType){
        EntityManager em = this.newEntityManager();
        try {
        	Type ancienType = em.find(Type.class, ancienType_id);
        	em.detach(ancienType);
        	ancienType.setType(newType.getType());
        	em.merge(ancienType);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Type> listerType(){
        EntityManager em = this.newEntityManager();
        TypedQuery<Type> query = em.createQuery("SELECT a FROM Type a", Type.class);
        List<Type> types = query.getResultList();
        this.closeEntityManager(em);
        return types;
    }
    public Type trouverTypeById(int id){
    	Type syndrome = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            syndrome = em.find(Type.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return syndrome;
	    }	
	
}
