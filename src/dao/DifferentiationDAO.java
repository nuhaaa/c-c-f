package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Differentiation;
import dao.entities.Tumeur;

public class DifferentiationDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public DifferentiationDAO() {
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
    public  boolean ajouterDetait(Differentiation diff){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(diff);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerComplication(int diff_id){
        EntityManager em = this.newEntityManager();
        try {
        	Differentiation diff = em.find(Differentiation.class, diff_id);
            em.remove(diff);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierComplication(int diff_id, Differentiation newDetail){
        EntityManager em = this.newEntityManager();
        try {
        	Differentiation ancienDiff = em.find(Differentiation.class, diff_id);
        	em.detach(ancienDiff);
        	ancienDiff.setDiff(newDetail.getDiff());
        	em.merge(ancienDiff);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Differentiation> listerLesDifferenciation(){
    	EntityManager em = this.newEntityManager();
        TypedQuery<Differentiation> query = em.createQuery("SELECT a FROM Differentiation a", Differentiation.class);
        List<Differentiation> diff = query.getResultList();
        this.closeEntityManager(em);
        return diff;
    }
    public Differentiation trouverDifferentiationById(int id){
		EntityManager em = this.newEntityManager();
		Differentiation categorie = em.find(Differentiation.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
