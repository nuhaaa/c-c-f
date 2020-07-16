package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Tumeur;
import dao.entities.TypeIntervention;

public class TypeInterventionDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public TypeInterventionDAO() {
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
    public  boolean ajouterTypeIntervention(TypeIntervention  type){
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
    public boolean supprimerTypeIntervention(int type_id){
        EntityManager em = this.newEntityManager();
        try {
        	TypeIntervention type = em.find(TypeIntervention.class, type_id);
            em.remove(type);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierTypeIntervention(int ancienType_id, TypeIntervention newType){
        EntityManager em = this.newEntityManager();
        try {
        	TypeIntervention ancienType = em.find(TypeIntervention.class, ancienType_id);
        	em.detach(ancienType);
        	ancienType.setIntervention(newType.getIntervention());
        	em.merge(ancienType);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<TypeIntervention> listerTypeIntervention(){
        EntityManager em = this.newEntityManager();
        TypedQuery<TypeIntervention> query = em.createQuery("SELECT a FROM TypeIntervention a", TypeIntervention.class);
        List<TypeIntervention> types = query.getResultList();
        this.closeEntityManager(em);
        return types;
    }
    public TypeIntervention trouverTypeInterventionById(int id){
		EntityManager em = this.newEntityManager();
		TypeIntervention categorie = em.find(TypeIntervention.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}	
	
}
