package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.SyndromeFamille;

public class SyndromeFamilleDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;

    
    public SyndromeFamilleDAO() {

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
    public  boolean ajouterDiagnostic(SyndromeFamille syndrome){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(syndrome);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerDiagnostic(int diag_id){
        EntityManager em = this.newEntityManager();
        try {
        	SyndromeFamille aspectMacro = em.find(SyndromeFamille.class, diag_id);
            em.remove(aspectMacro);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierDiagnostic(int ancienDiag_id, SyndromeFamille newDiag){
        EntityManager em = this.newEntityManager();
        try {
        	SyndromeFamille ancienDiag = em.find(SyndromeFamille.class, ancienDiag_id);
        	em.detach(ancienDiag);
        	ancienDiag.setDiagnostic(newDiag.getDiagnostic());
        	em.merge(ancienDiag);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<SyndromeFamille> listerDiagnostic(){
        EntityManager em = this.newEntityManager();
        TypedQuery<SyndromeFamille> query = em.createQuery("SELECT a FROM SyndromeFamille a", SyndromeFamille.class);
        List<SyndromeFamille> diagnostics = query.getResultList();
        this.closeEntityManager(em);
        return diagnostics;
    }
    public SyndromeFamille trouverSyndParDiag(String diag){
    	SyndromeFamille syndrome = null;
	        try {
	            EntityManager em = this.newEntityManager();
	            TypedQuery<SyndromeFamille> query = em.createQuery("SELECT s FROM SyndromeFamille s WHERE s.diagnostic = :diag", SyndromeFamille.class);
	            query.setParameter("diag", diag);
	            syndrome = query.getSingleResult();
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur "+ e.getMessage());
	        }
	        return syndrome;
    }
    
    public SyndromeFamille trouverSyndById(int id){
    	SyndromeFamille syndrome = null;
	        try {
	            EntityManager em = this.newEntityManager();
	            syndrome = em.find(SyndromeFamille.class, id);
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur"+ e.getMessage());
	        }
	        return syndrome;
    }
}
