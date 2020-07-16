package dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.DossierMedicale;
import dao.entities.Forme;
import dao.entities.Imagerie;
import dao.entities.Individu;

public class ImagerieDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public ImagerieDAO() {
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
    public  boolean ajouterRadio(Imagerie  radio){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = radio.getDossier();
            dossier.addExamenMedicale(radio);
            em.persist(radio);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerRadio(int radio_id){
        EntityManager em = this.newEntityManager();
        try {
        	Imagerie radio = em.find(Imagerie.class, radio_id);
        	DossierMedicale dossier = radio.getDossier();
        	dossier.removeExamenMedicale(radio);
            em.remove(radio);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierRadio(int ancienRadio_id, Imagerie newRadio){
        EntityManager em = this.newEntityManager();
        try {
        	Imagerie ancienRadio = em.find(Imagerie.class, ancienRadio_id);
        	em.detach(ancienRadio);
        	ancienRadio.setDateRadio(newRadio.getDateRadio());
        	ancienRadio.setFile(newRadio.getFile());
        	ancienRadio.setExamen(newRadio.getExamen());
        	ancienRadio.setHopital(newRadio.getHopital());
        	em.merge(ancienRadio);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
//    lister les examens radiologique par patient
    public List<Imagerie> listerlesRadiosParPatient(Individu patient){
        EntityManager em = this.newEntityManager();
        TypedQuery<Imagerie> query = em.createQuery("SELECT i FROM Imagerie i WHERE i.getDossier().getPatient = patient ", Imagerie.class);
        List<Imagerie> radios = query.getResultList();
        this.closeEntityManager(em);
        return radios;
    }	
    public Imagerie trouverImagerieById(int id){
    	Imagerie syndrome = null;
	        try {
	            EntityManager em = this.newEntityManager();
	            syndrome = em.find(Imagerie.class, id);
	            this.closeEntityManager(em);
	        } catch (NoResultException e) {
	            System.out.println("Erreur"+ e.getMessage());
	        }
	        return syndrome;
    }
	
	
}
