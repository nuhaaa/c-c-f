package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.TypedQuery;

import dao.entities.AspectMacro;
import dao.entities.Circonference;


public class CirconferenceDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public CirconferenceDAO() {
	
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
    public  boolean ajouterCirconference(Circonference aspectMacro){
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
    public boolean supprimerCirconference(int cirf_id){
        EntityManager em = this.newEntityManager();
        try {
        	Circonference cirf = em.find(Circonference.class, cirf_id);
            em.remove(cirf);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierCircconference(int ancienCircf_id, Circonference newCirconf){
        EntityManager em = this.newEntityManager();
        try {
        	Circonference ancienCirconf = em.find(Circonference.class, ancienCircf_id);
        	em.detach(ancienCirconf);
        	ancienCirconf.setCirconference(newCirconf.getCirconference());
        	em.merge(ancienCirconf);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Circonference> listerLesCirconference(){
    	EntityManager em = this.newEntityManager();
        TypedQuery<Circonference> query = em.createQuery("SELECT a FROM Circonference a", Circonference.class);
        List<Circonference> circonferences = query.getResultList();
        this.closeEntityManager(em);
        return circonferences;
    }
    public Circonference trouverAspectMacroById(int id){
  		EntityManager em = this.newEntityManager();
  		Circonference categorie = em.find(Circonference.class, id);
          
          this.closeEntityManager(em);
          return categorie;
  		
  	}
    
}
