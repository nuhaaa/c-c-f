package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.AspectMacro;
import dao.entities.CategorieSynAnormal;





public class AspectMacroDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;

    
    public AspectMacroDAO() {

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
    public  boolean ajouterAspect(AspectMacro aspectMacro){
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
    public boolean supprimerAspect(int aspect_id){
        EntityManager em = this.newEntityManager();
        try {
        	AspectMacro aspectMacro = em.find(AspectMacro.class, aspect_id);
            em.remove(aspectMacro);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierAspect(int ancienAspect_id, AspectMacro newAspectMacro){
        EntityManager em = this.newEntityManager();
        try {
        	AspectMacro ancienAspectMacro = em.find(AspectMacro.class, ancienAspect_id);
        	em.detach(ancienAspectMacro);
        	ancienAspectMacro.setAspect(newAspectMacro.getAspect());
        	em.merge(ancienAspectMacro);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<AspectMacro> listerAspect(){
        EntityManager em = this.newEntityManager();
        TypedQuery<AspectMacro> query = em.createQuery("SELECT a FROM AspectMacro a", AspectMacro.class);
        List<AspectMacro> aspects = query.getResultList();
        this.closeEntityManager(em);
        return aspects;
    }
    
    public AspectMacro trouverAspectMacroById(int id){
		EntityManager em = this.newEntityManager();
		AspectMacro categorie = em.find(AspectMacro.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
