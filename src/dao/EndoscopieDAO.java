package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.AspectMacro;
import dao.entities.DossierMedicale;
import dao.entities.Endoscopie;

import dao.entities.Individu;


public class EndoscopieDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	public EndoscopieDAO() {
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
	public boolean ajouterEndoscopie(Endoscopie endo){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = endo.getDossier();
            dossier.addExamenMedicale(endo);
            em.persist(endo);
            this.closeEntityManager(em);
            System.out.println(" enregistre!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement !!!");
            return false;
        }
	}
	 public boolean supprimerEndoscopie(int endo_id){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Endoscopie endo = em.find(Endoscopie.class, endo_id);
	        	DossierMedicale doss = endo.getDossier();
	        	doss.removeExamenMedicale(endo);
	            em.remove(endo);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	  }
	 public boolean modifierEndoscopie(int ancienEndo_id, Endoscopie newEndo){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Endoscopie ancienEndo = em.find(Endoscopie.class, ancienEndo_id);
	        	em.detach(ancienEndo);
	        	ancienEndo.setAnestesie(newEndo.getAnestesie());
	        	ancienEndo.setDateEndo(newEndo.getDateEndo());
	        	ancienEndo.setHopital(newEndo.getHopital());
	        	ancienEndo.setNumero(newEndo.getNumero());
	        	ancienEndo.setTypeEndo(newEndo.getTypeEndo());
	       
	        	em.merge(ancienEndo);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	// lister les endoscopies normals par patient
	 
	    public List<Endoscopie> listerAndoscopieParPatient(Individu patient){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<Endoscopie> query = em.createQuery("SELECT b FROM Endoscopie b WHERE b.getDossier().getPatient = patient ", Endoscopie.class);
	        List<Endoscopie> endos = query.getResultList();
	        this.closeEntityManager(em);
	        return endos;
	    }
	    public Endoscopie trouverEndoById(int id){
			EntityManager em = this.newEntityManager();
			Endoscopie categorie = em.find(Endoscopie.class, id);
	        this.closeEntityManager(em);
	        return categorie;
			
		}
}
