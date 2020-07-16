package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.MutaMMR;
import dao.entities.NiveauInvasion;

public class MutaMMRDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public MutaMMRDAO() {
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
    public  boolean ajouterMutaMMR(MutaMMR  mutammr){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(mutammr);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerMutaMMR(int mutammr_id){
        EntityManager em = this.newEntityManager();
        try {
        	MutaMMR mutammr = em.find(MutaMMR.class, mutammr_id);
            em.remove(mutammr);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierMutaMMR(int ancienMutaMMR_id, MutaMMR newMutaMMR){
        EntityManager em = this.newEntityManager();
        try {
        	MutaMMR ancienMutaMMR = em.find(MutaMMR.class, ancienMutaMMR_id);
        	em.detach(ancienMutaMMR);
        	ancienMutaMMR.setMMR(newMutaMMR.getMMR());
        	em.merge(ancienMutaMMR);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<MutaMMR> listerMutaMMR(){
        EntityManager em = this.newEntityManager();
        TypedQuery<MutaMMR> query = em.createQuery("SELECT a FROM MutaMMR a", MutaMMR.class);
        List<MutaMMR> mutammrs = query.getResultList();
        this.closeEntityManager(em);
        return mutammrs;
    }
    public MutaMMR trouverMutaMMRById(int id){
		EntityManager em = this.newEntityManager();
		MutaMMR categorie = em.find(MutaMMR.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
		
}
