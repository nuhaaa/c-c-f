package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.AspectMacro;
import dao.entities.DossierMedicale;
import dao.entities.Individu;
import dao.entities.Polype;

public class PolypeDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	public PolypeDAO() {
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
	public boolean ajouterPolype(Polype polype){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = polype.getDossier();
            dossier.addExamenMedicale(polype);
            em.persist(polype);
            this.closeEntityManager(em);
            System.out.println(" enregistre!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement !!!");
            return false;
        }
	}
	 public boolean supprimerPolype(int polype_id){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Polype polype = em.find(Polype.class, polype_id);
	        	 DossierMedicale dossier = polype.getDossier();
	        	 dossier.removeExamenMedicale(polype);
	            em.remove(polype);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	  }
	 public boolean removePolype(Polype polype){
	        EntityManager em = this.newEntityManager();
	        try {
	            em.remove(polype);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	  }
	 public boolean modifierPolype(int ancienPolype_id, Polype newPolype){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Polype ancienPolype = em.find(Polype.class, ancienPolype_id);
	        	em.detach(ancienPolype);
	        	ancienPolype.setDatePrel(newPolype.getDatePrel());
	        	ancienPolype.setTypePrel(newPolype.getTypePrel());
	        	ancienPolype.setRefAnaPath(newPolype.getRefAnaPath());
	        	ancienPolype.setSiege(newPolype.getSiege());
	        	ancienPolype.setDisplasie(newPolype.getDisplasie());;
	        	ancienPolype.setTupeHyst(newPolype.getTupeHyst());;
	        	ancienPolype.setNombre(newPolype.getNombre());;
	        	ancienPolype.setLimite(newPolype.getLimite());
	        	ancienPolype.setHopital(newPolype.getHopital());
	        	ancienPolype.setSousType(newPolype.getSousType());
	        	
	        	em.merge(ancienPolype);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	// lister 
	 
	    public List<Polype> listerPolypeParPatient(Individu patient){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<Polype> query = em.createQuery("SELECT b FROM Polype b WHERE b.getDossier().getPatient = patient ", Polype.class);
	        List<Polype> polypes = query.getResultList();
	        this.closeEntityManager(em);
	        return polypes;
	    }
	    public Polype trouverPolypeById(int id){
			EntityManager em = this.newEntityManager();
			Polype categorie = em.find(Polype.class, id);
	        
	        this.closeEntityManager(em);
	        return categorie;
			
		}
}
