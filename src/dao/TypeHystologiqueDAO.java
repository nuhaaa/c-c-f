package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.TypeHystologique;
import dao.entities.TypePrelevement;

public class TypeHystologiqueDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public TypeHystologiqueDAO() {
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
    public  boolean ajouterTypeHystologique(TypeHystologique  type){
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
    public boolean supprimerTypeHystologique(int type_id){
        EntityManager em = this.newEntityManager();
        try {
        	TypeHystologique type = em.find(TypeHystologique.class, type_id);
            em.remove(type);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierTypeHystologique(int ancienType_id, TypeHystologique newType){
        EntityManager em = this.newEntityManager();
        try {
        	TypeHystologique ancienType = em.find(TypeHystologique.class, ancienType_id);
        	em.detach(ancienType);
        	ancienType.setTypeHysto(newType.getTypeHysto());
        	em.merge(ancienType);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<TypeHystologique> listerTypeHystologique(){
        EntityManager em = this.newEntityManager();
        TypedQuery<TypeHystologique> query = em.createQuery("SELECT a FROM TypeHystologique a", TypeHystologique.class);
        List<TypeHystologique> types = query.getResultList();
        this.closeEntityManager(em);
        return types;
    }
    public TypeHystologique trouverTypeHystologiqueById(int id){
  		EntityManager em = this.newEntityManager();
  		TypeHystologique categorie = em.find(TypeHystologique.class, id);
          
          this.closeEntityManager(em);
          return categorie;
  		
  	}
}
