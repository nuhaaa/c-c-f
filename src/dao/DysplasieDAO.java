package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Differentiation;
import dao.entities.Dysplasie;

public class DysplasieDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public DysplasieDAO() {
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
    public  boolean ajouterDysplasie(Dysplasie dysplasie){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(dysplasie);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerDysplasie(int dysp_id){
        EntityManager em = this.newEntityManager();
        try {
        	Dysplasie dysplasie = em.find(Dysplasie.class, dysp_id);
            em.remove(dysplasie);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierDysplasie(int ancienDysp_id, Dysplasie newDysplasie){
        EntityManager em = this.newEntityManager();
        try {
        	Dysplasie ancienDysp = em.find(Dysplasie.class, ancienDysp_id);
        	em.detach(ancienDysp);
        	ancienDysp.setDysplasie(newDysplasie.getDysplasie());
        	em.merge(ancienDysp);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Dysplasie> listerLesDysplasie(){
    	EntityManager em = this.newEntityManager();
        TypedQuery<Dysplasie> query = em.createQuery("SELECT a FROM Dysplasie a", Dysplasie.class);
        List<Dysplasie> dysplasies = query.getResultList();
        this.closeEntityManager(em);
        return dysplasies;
    }
    public Dysplasie trouverDifferentiationById(int id){
  		EntityManager em = this.newEntityManager();
  		Dysplasie categorie = em.find(Dysplasie.class, id);
          
          this.closeEntityManager(em);
          return categorie;
  		
  	}
}
