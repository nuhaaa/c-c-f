package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.AspectMacro;
import dao.entities.Famille;
import dao.entities.SyndromeFamille;


public class FamilleDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	public FamilleDAO() {
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
	 public boolean ajouterFamille(Famille famille){
	        try {
	            EntityManager em = this.newEntityManager();
	            em.persist(famille);
	            this.closeEntityManager(em);
	            System.out.println("Famille bien enregistre au platfrome !");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur d'enregistrement d'une nouvelle famille !!!");
	            return false;
	        }
	    }
	 public boolean modifierFamille(int ancienFamille_id, Famille newFamille){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Famille ancienFamille = em.find(Famille.class, ancienFamille_id);
	        	em.detach(ancienFamille);
	        	ancienFamille.setNomFamille(newFamille.getNomFamille());
	        	ancienFamille.setDiagnostic(newFamille.getDiagnostic());
	        	em.merge(ancienFamille);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour: "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	 public List<Famille> lister(){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<Famille> query = em.createQuery("SELECT a FROM Famille a", Famille.class);
	        List<Famille> aspects = query.getResultList();
	        this.closeEntityManager(em);
	        return aspects;
	    }
	 
	 public Famille trouverFamilleParNom(String nom){
	    	Famille famille = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            TypedQuery<Famille> query = em.createQuery("SELECT s FROM Famille s WHERE s.nomFamille = :nom", Famille.class);
		            query.setParameter("nom", nom);
		            famille = query.getSingleResult();
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur authentification "+ e.getMessage());
		        }
		        return famille;
	    }
	 public Famille trouverFamilleById(int id){
	    	Famille famille = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            famille = em.find(Famille.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return famille;
	    }
	 
	 public List<Object[]> syndromeParFamille() {
		 List<Object[]> familles = null;
		 try {
	            EntityManager em = this.newEntityManager();
	            familles = em.createQuery( "SELECT count(c1), c2.diagnostic FROM Famille c1 JOIN c1.diagnostic c2 group by c2.diagnostic").getResultList();
	            //
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur authentification "+ e.getMessage());
	        }
		 return familles;
	 }
}
