package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import dao.entities.PriseEnCharge;
import dao.entities.RendezVous;

public class PriseEnChargeDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public PriseEnChargeDAO() {
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
    public  boolean ajouterPriseEnCharge(PriseEnCharge  charge){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(charge);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerPriseEnCharge(int charge_id){
        EntityManager em = this.newEntityManager();
        try {
        	PriseEnCharge charge = em.find(PriseEnCharge.class, charge_id);
            em.remove(charge);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierPriseEnCharge(int ancienCharge_id, PriseEnCharge newCharge){
        EntityManager em = this.newEntityManager();
        try {
        	PriseEnCharge ancienCharge = em.find(PriseEnCharge.class, ancienCharge_id);
        	em.detach(ancienCharge);
        	ancienCharge.setPriseEnCharge(newCharge.getPriseEnCharge());
        	em.merge(ancienCharge);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<PriseEnCharge> listerPriseEnCharge(){
        EntityManager em = this.newEntityManager();
        TypedQuery<PriseEnCharge> query = em.createQuery("SELECT a FROM PriseEnCharge a", PriseEnCharge.class);
        List<PriseEnCharge> charges = query.getResultList();
        this.closeEntityManager(em);
        return charges;
    }
    public PriseEnCharge trouverPriseEnChargeVousById(int id){
    	PriseEnCharge syndrome = null;
			        try {
			            EntityManager em = this.newEntityManager();
			            syndrome = em.find(PriseEnCharge.class, id);
			            this.closeEntityManager(em);
			        } catch (NoResultException e) {
			            System.out.println("Erreur"+ e.getMessage());
			        }
			        return syndrome;
		    }		
	
}
