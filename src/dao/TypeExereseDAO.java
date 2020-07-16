package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.TypeExerese;
import dao.entities.TypeHystologique;

public class TypeExereseDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public TypeExereseDAO() {
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
    public  boolean ajouterTypeExerese(TypeExerese  type){
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
    public boolean supprimerTypeExerese(int type_id){
        EntityManager em = this.newEntityManager();
        try {
        	TypeExerese type = em.find(TypeExerese.class, type_id);
            em.remove(type);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierTypeExerese(int ancienType_id, TypeExerese newType){
        EntityManager em = this.newEntityManager();
        try {
        	TypeExerese ancienType = em.find(TypeExerese.class, ancienType_id);
        	em.detach(ancienType);
        	ancienType.setTypeExerese(newType.getTypeExerese());
        	em.merge(ancienType);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<TypeExerese> listerTypeExerese(){
        EntityManager em = this.newEntityManager();
        TypedQuery<TypeExerese> query = em.createQuery("SELECT a FROM TypeExerese a", TypeExerese.class);
        List<TypeExerese> types = query.getResultList();
        this.closeEntityManager(em);
        return types;
    }
    public TypeExerese trouverTypeExereseById(int id){
  		EntityManager em = this.newEntityManager();
  		TypeExerese categorie = em.find(TypeExerese.class, id);
          
          this.closeEntityManager(em);
          return categorie;
  		
  	}	
	
}
