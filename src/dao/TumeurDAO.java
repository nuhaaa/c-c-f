package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.DossierMedicale;
import dao.entities.Tumeur;
import dao.entities.Individu;
import dao.entities.Polype;

public class TumeurDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	public TumeurDAO() {
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
	public boolean ajouterTumeur(Tumeur tumeur){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = tumeur.getDossier();
            dossier.addExamenMedicale(tumeur);
            em.persist(tumeur);
            this.closeEntityManager(em);
            System.out.println(" enregistre!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement !!!");
            return false;
        }
	}
	 public boolean supprimerTumeur(int tumeur_id){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Tumeur tumeur = em.find(Tumeur.class, tumeur_id);
	        	DossierMedicale dossier = tumeur.getDossier();
	        	dossier.removeExamenMedicale(tumeur);
	            em.remove(tumeur);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	  }
	 public boolean modifierTumeur(int ancienTumeur_id, Tumeur newTumeur){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Tumeur ancienTumeur = em.find(Tumeur.class, ancienTumeur_id);
	        	em.detach(ancienTumeur);
	        	ancienTumeur.setDatePrel(newTumeur.getDatePrel());
	        	ancienTumeur.setTypePrel(newTumeur.getTypePrel());
	        	ancienTumeur.setRefAnaPath(newTumeur.getRefAnaPath());
	        	ancienTumeur.setSiege(newTumeur.getSiege());
	        	ancienTumeur.setDifferentiation(newTumeur.getDifferentiation());
	        	ancienTumeur.setTypeHysto(newTumeur.getTypeHysto());
	        	ancienTumeur.setCompColMuq(newTumeur.getCompColMuq());
	        	ancienTumeur.setCompCelInd(newTumeur.getCompCelInd());
	        	ancienTumeur.setStroma(newTumeur.getStroma());
	        	ancienTumeur.setLesion(newTumeur.getLesion());
	        	ancienTumeur.setEmbolVasc(newTumeur.getEmbolVasc());
	        	ancienTumeur.setEngaiPeri(newTumeur.getEngaiPeri());
	        	ancienTumeur.setLimiteChirgPro(newTumeur.getLimiteChirgPro());
	        	ancienTumeur.setLimiteChirgDist(newTumeur.getLimiteChirgDist());
	        	ancienTumeur.setNbreGanglions(newTumeur.getNbreGanglions());
	        	ancienTumeur.setLocalPerit(newTumeur.isLocalPerit());
	        	ancienTumeur.setNiveauInvasion(newTumeur.getNiveauInvasion());
	        	ancienTumeur.setHopital(newTumeur.getHopital());
	       
	        	em.merge(ancienTumeur);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	// lister 
	 
	    public List<Tumeur> listerTumeurParPatient(Individu patient){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<Tumeur> query = em.createQuery("SELECT b FROM Tumeur b WHERE b.getDossier().getPatient = patient ", Tumeur.class);
	        List<Tumeur> tumeurs = query.getResultList();
	        this.closeEntityManager(em);
	        return tumeurs;
	    }
	    public Tumeur trouverTumeurById(int id){
			EntityManager em = this.newEntityManager();
			Tumeur categorie = em.find(Tumeur.class, id);
	        
	        this.closeEntityManager(em);
	        return categorie;
			
		}
}
