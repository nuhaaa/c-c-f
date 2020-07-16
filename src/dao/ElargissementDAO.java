package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Elargissement ;
import dao.entities.Tumeur;

public class ElargissementDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public ElargissementDAO() {
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
    public  boolean ajouterElargissement (Elargissement  aspectMacro){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(aspectMacro);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerElargissement (int dysp_id){
        EntityManager em = this.newEntityManager();
        try {
        	Elargissement  dysplasie = em.find(Elargissement .class, dysp_id);
            em.remove(dysplasie);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierElargissement (int ancienDysp_id, Elargissement  newElargissement){
        EntityManager em = this.newEntityManager();
        try {
        	Elargissement  ancienDysp = em.find(Elargissement .class, ancienDysp_id);
        	em.detach(ancienDysp);
        	ancienDysp.setElargie(newElargissement.getElargie());
        	em.merge(ancienDysp);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Elargissement> listerLesElargissement(){
    	 EntityManager em = this.newEntityManager();
         TypedQuery<Elargissement> query = em.createQuery("SELECT a FROM Elargissement a", Elargissement.class);
         List<Elargissement> elargies = query.getResultList();
         this.closeEntityManager(em);
         return elargies;
    }
    public Elargissement trouverElargissementById(int id){
		EntityManager em = this.newEntityManager();
		Elargissement categorie = em.find(Elargissement.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
