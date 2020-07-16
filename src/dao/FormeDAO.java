package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import dao.entities.Forme;
import dao.entities.SyndromeFamille;

public class FormeDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public FormeDAO() {
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
    public  boolean ajouterForme(Forme  forme){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(forme);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerForme(int forme_id){
        EntityManager em = this.newEntityManager();
        try {
        	Forme forme = em.find(Forme.class, forme_id);
            em.remove(forme);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierForme(int ancienForme_id, Forme newForme){
        EntityManager em = this.newEntityManager();
        try {
        	Forme ancienForme = em.find(Forme.class, ancienForme_id);
        	em.detach(ancienForme);
        	ancienForme.setForme(newForme.getForme());
        	em.merge(ancienForme);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Forme> listerForme(){
        EntityManager em = this.newEntityManager();
        TypedQuery<Forme> query = em.createQuery("SELECT a FROM Forme a", Forme.class);
        List<Forme> formes = query.getResultList();
        this.closeEntityManager(em);
        return formes;
    }
    public Forme trouverFormeById(int id){
    	Forme syndrome = null;
	        try {
	            EntityManager em = this.newEntityManager();
	            syndrome = em.find(Forme.class, id);
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur"+ e.getMessage());
	        }
	        return syndrome;
    }
	
}
