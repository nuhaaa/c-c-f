package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.NiveauInvasion;
import dao.entities.Tumeur;

public class NiveauInvasionDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public NiveauInvasionDAO() {
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
    public  boolean ajouterNiveauInvasion(NiveauInvasion  niveau){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(niveau);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerForme(int NiveauInvasion_id){
        EntityManager em = this.newEntityManager();
        try {
        	NiveauInvasion niveau = em.find(NiveauInvasion.class, NiveauInvasion_id);
            em.remove(niveau);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierForme(int ancienNiveau_id, NiveauInvasion newNiveau){
        EntityManager em = this.newEntityManager();
        try {
        	NiveauInvasion ancienNiveau = em.find(NiveauInvasion.class, ancienNiveau_id);
        	em.detach(ancienNiveau);
        	ancienNiveau.setNiveau(newNiveau.getNiveau());
        	em.merge(ancienNiveau);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<NiveauInvasion> listerForme(){
        EntityManager em = this.newEntityManager();
        TypedQuery<NiveauInvasion> query = em.createQuery("SELECT a FROM NiveauInvasion a", NiveauInvasion.class);
        List<NiveauInvasion> niveaux = query.getResultList();
        this.closeEntityManager(em);
        return niveaux;
    }
    public NiveauInvasion trouverNiveauInvasionById(int id){
		EntityManager em = this.newEntityManager();
		NiveauInvasion categorie = em.find(NiveauInvasion.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
		
}
