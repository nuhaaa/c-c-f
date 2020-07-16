package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Dysplasie;
import dao.entities.LimiteResectEndo;



public class LimiteResectEndoDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public LimiteResectEndoDAO() {
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
    public  boolean ajouterLimite(LimiteResectEndo  limite){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(limite);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerForme(int limite_id){
        EntityManager em = this.newEntityManager();
        try {
        	LimiteResectEndo limite = em.find(LimiteResectEndo.class, limite_id);
            em.remove(limite);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierForme(int ancienLimite_id, LimiteResectEndo newLimite){
        EntityManager em = this.newEntityManager();
        try {
        	LimiteResectEndo ancienLimite = em.find(LimiteResectEndo.class, ancienLimite_id);
        	em.detach(ancienLimite);
        	ancienLimite.setLimite(newLimite.getLimite());
        	em.merge(ancienLimite);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour  : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<LimiteResectEndo> listerLesLimite(){
    	EntityManager em = this.newEntityManager();
        TypedQuery<LimiteResectEndo> query = em.createQuery("SELECT a FROM LimiteResectEndo a", LimiteResectEndo.class);
        List<LimiteResectEndo> limites = query.getResultList();
        this.closeEntityManager(em);
        return limites;
    }
    public LimiteResectEndo trouverDifferentiationById(int id){
  		EntityManager em = this.newEntityManager();
  		LimiteResectEndo categorie = em.find(LimiteResectEndo.class, id);
          
          this.closeEntityManager(em);
          return categorie;
  		
  	}
    
}
