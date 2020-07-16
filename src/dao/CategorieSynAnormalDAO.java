package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.CategorieSynAnormal;


public class CategorieSynAnormalDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public CategorieSynAnormalDAO() {
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
    public  boolean ajouterCategorie(CategorieSynAnormal catSynd){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(catSynd);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerCatSyndAnormal(int catSynd_id){
        EntityManager em = this.newEntityManager();
        try {
        	CategorieSynAnormal catSynd = em.find(CategorieSynAnormal.class, catSynd_id);
            em.remove(catSynd);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierCategorie(int ancienCatSynd_id, CategorieSynAnormal newCategorie){
        EntityManager em = this.newEntityManager();
        try {
        	CategorieSynAnormal ancienCatSynd = em.find(CategorieSynAnormal.class, ancienCatSynd_id);
        	em.detach(ancienCatSynd);
        	ancienCatSynd.setSyndrome(newCategorie.getSyndrome());
        	em.merge(ancienCatSynd);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<CategorieSynAnormal> listerCategorieSynAnormal(){
        EntityManager em = this.newEntityManager();
        TypedQuery<CategorieSynAnormal> query = em.createQuery("SELECT a FROM CategorieSynAnormal a", CategorieSynAnormal.class);
        List<CategorieSynAnormal> categories = query.getResultList();
        this.closeEntityManager(em);
        return categories;
    }
    
    public CategorieSynAnormal trouverCategorieSynAnormalById(int id){
		EntityManager em = this.newEntityManager();
		CategorieSynAnormal categorie = em.find(CategorieSynAnormal.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
