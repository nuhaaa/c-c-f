package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.ExamenImagerie;

public class ExamenImagerieDAO {

	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public ExamenImagerieDAO() {
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
    public  boolean ajouterExamenImagerie(ExamenImagerie aspectMacro){
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
    public boolean supprimerExamenImagerie(int aspect_id){
        EntityManager em = this.newEntityManager();
        try {
        	ExamenImagerie aspectMacro = em.find(ExamenImagerie.class, aspect_id);
            em.remove(aspectMacro);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierAspect(int ExamenImagerie_id, ExamenImagerie newAspectMacro){
        EntityManager em = this.newEntityManager();
        try {
        	ExamenImagerie ancienAspectMacro = em.find(ExamenImagerie.class, ExamenImagerie_id);
        	em.detach(ancienAspectMacro);
        	ancienAspectMacro.setExamen(newAspectMacro.getExamen());
        	em.merge(ancienAspectMacro);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<ExamenImagerie> listerExamenImagerie(){
        EntityManager em = this.newEntityManager();
        TypedQuery<ExamenImagerie> query = em.createQuery("SELECT a FROM ExamenImagerie a", ExamenImagerie.class);
        List<ExamenImagerie> aspects = query.getResultList();
        this.closeEntityManager(em);
        return aspects;
    }
    
    public ExamenImagerie trouverAspectMacroById(int id){
		EntityManager em = this.newEntityManager();
		ExamenImagerie categorie = em.find(ExamenImagerie.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
