package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.ToucherRectal;

public class ToucherRectalDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public ToucherRectalDAO() {
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
    public  boolean ajouterToucherRectal(ToucherRectal  toucher){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(toucher);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerToucherRectal(int toucher_id){
        EntityManager em = this.newEntityManager();
        try {
        	ToucherRectal toucher = em.find(ToucherRectal.class, toucher_id);
            em.remove(toucher);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierToucherRectal(int ancienToucher_id, ToucherRectal newToucher){
        EntityManager em = this.newEntityManager();
        try {
        	ToucherRectal ancienToucher = em.find(ToucherRectal.class, ancienToucher_id);
        	em.detach(ancienToucher);
        	ancienToucher.setToucher(newToucher.getToucher());
        	em.merge(ancienToucher);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<ToucherRectal> listerToucher(){
        EntityManager em = this.newEntityManager();
        TypedQuery<ToucherRectal> query = em.createQuery("SELECT a FROM ToucherRectal a", ToucherRectal.class);
        List<ToucherRectal> touchers = query.getResultList();
        this.closeEntityManager(em);
        return touchers;
    }
		
    public ToucherRectal trouverToucherById(int id){
		EntityManager em = this.newEntityManager();
		ToucherRectal toucher = em.find(ToucherRectal.class, id);
        
        this.closeEntityManager(em);
        return toucher;
		
	}
}
