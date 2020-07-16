package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import dao.entities.Site;
import dao.entities.StatutCancereux;

public class SiteDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public SiteDAO() {
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
    public  boolean ajouterSite(Site  site){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(site);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerForme(int site_id){
        EntityManager em = this.newEntityManager();
        try {
        	Site site = em.find(Site.class, site_id);
            em.remove(site);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierForme(int ancienSite_id, Site newSite){
        EntityManager em = this.newEntityManager();
        try {
        	Site ancienSite = em.find(Site.class, ancienSite_id);
        	em.detach(ancienSite);
        	ancienSite.setSite(newSite.getSite());
        	em.merge(ancienSite);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    public List<Site> listerSite(){
        EntityManager em = this.newEntityManager();
        TypedQuery<Site> query = em.createQuery("SELECT a FROM Site a", Site.class);
        List<Site> sites = query.getResultList();
        this.closeEntityManager(em);
        return sites;
    }
	
    public Site trouverSiteById(int id){
		 Site syndrome = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            syndrome = em.find(Site.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return syndrome;
	    }
}
