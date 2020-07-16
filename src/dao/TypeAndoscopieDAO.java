package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.AspectMacro;
import dao.entities.TypeAndoscopie;

public class TypeAndoscopieDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public TypeAndoscopieDAO() {
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
    public  boolean ajouterTypeAndoscopie(TypeAndoscopie  type){
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
    public boolean supprimerTypeAndoscopie(int type_id){
        EntityManager em = this.newEntityManager();
        try {
        	TypeAndoscopie type = em.find(TypeAndoscopie.class, type_id);
            em.remove(type);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierTypeAndoscopie(int ancienType_id, TypeAndoscopie newType){
        EntityManager em = this.newEntityManager();
        try {
        	TypeAndoscopie ancienType = em.find(TypeAndoscopie.class, ancienType_id);
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
    public List<TypeAndoscopie> listerTypeAndoscopie(){
        EntityManager em = this.newEntityManager();
        TypedQuery<TypeAndoscopie> query = em.createQuery("SELECT a FROM TypeAndoscopie a", TypeAndoscopie.class);
        List<TypeAndoscopie> types = query.getResultList();
        this.closeEntityManager(em);
        return types;
    }
    public TypeAndoscopie trouverAspectMacroById(int id){
		EntityManager em = this.newEntityManager();
		TypeAndoscopie categorie = em.find(TypeAndoscopie.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
