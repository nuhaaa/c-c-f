package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.TypeIntervention;
import dao.entities.TypePrelevement;

public class TypePrelevementDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public TypePrelevementDAO() {
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
    public  boolean ajouterTypePrelevement(TypePrelevement  type){
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
    public boolean supprimerTypePrelevement(int type_id){
        EntityManager em = this.newEntityManager();
        try {
        	TypePrelevement type = em.find(TypePrelevement.class, type_id);
            em.remove(type);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierTypePrelevement(int ancienType_id, TypePrelevement newType){
        EntityManager em = this.newEntityManager();
        try {
        	TypePrelevement ancienType = em.find(TypePrelevement.class, ancienType_id);
        	em.detach(ancienType);
        	ancienType.setTypePrevement(newType.getTypePrevement());
        	em.merge(ancienType);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<TypePrelevement> listerTypePrelevement(){
        EntityManager em = this.newEntityManager();
        TypedQuery<TypePrelevement> query = em.createQuery("SELECT a FROM TypePrelevement a", TypePrelevement.class);
        List<TypePrelevement> types = query.getResultList();
        this.closeEntityManager(em);
        return types;
    }
    public TypePrelevement trouverTypePrelevementById(int id){
		EntityManager em = this.newEntityManager();
		TypePrelevement categorie = em.find(TypePrelevement.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
	
}
