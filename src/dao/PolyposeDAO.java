package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Endoscopie;
import dao.entities.Polypose;

public class PolyposeDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public PolyposeDAO() {
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
	public  boolean ajouterPolypose(Polypose  poly){
	        try {
	            EntityManager em = this.newEntityManager();
	            em.persist(poly);
	            this.closeEntityManager(em);
	            System.out.println("bien enregistré!");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur d'enregistrement!!!");
	            return false;
	        }
	 }
	public  boolean supprimerMasseTumorale(Polypose  poly){
        try {
            EntityManager em = this.newEntityManager();
            em.remove(poly);
            this.closeEntityManager(em);
            System.out.println("bien supprimer!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de suppression!!!");
            return false;
        }
	}
	public boolean modifierMasseTumorale(int ancienPolypose_id, Polypose newPolypose){
        EntityManager em = this.newEntityManager();
        try {
        	Polypose ancienPolypose = em.find(Polypose.class, ancienPolypose_id);
        	em.detach(ancienPolypose);
        	ancienPolypose.setAspect(newPolypose.getAspect());
        	ancienPolypose.setCouleur(newPolypose.getCouleur());
        	ancienPolypose.setNombre(newPolypose.getNombre());
        	ancienPolypose.setNombre1(newPolypose.getNombre1());
        	ancienPolypose.setRefAnaPath(newPolypose.getRefAnaPath());
        	ancienPolypose.setSiege(newPolypose.getSiege());
        	ancienPolypose.setTaille(newPolypose.getTaille());
        	ancienPolypose.setType(newPolypose.getType());
        	
        	em.merge(ancienPolypose);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
	}
	 public List<Polypose> listerLesPolypose(){
	    	EntityManager em = this.newEntityManager();
	        TypedQuery<Polypose> query = em.createQuery("SELECT a FROM Polypose a", Polypose.class);
	        List<Polypose> polyposes = query.getResultList();
	        this.closeEntityManager(em);
	        return polyposes;
	 }
	 
	 public Polypose listerPolyposeById(int id){
	    	EntityManager em = this.newEntityManager();
	        Polypose polypose = em.find(Polypose.class, id);
	        this.closeEntityManager(em);
	        return polypose;
	 }
}
