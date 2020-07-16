package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.AnaPathologie;
import dao.entities.AspectMacro;
import dao.entities.Biologie;
import dao.entities.DossierMedicale;
import dao.entities.Endoscopie;
import dao.entities.ExamenClinique;
import dao.entities.ExamenMedical;
import dao.entities.ExamenPostOp;
import dao.entities.ExamenPreOp;
import dao.entities.ExamenPreOpAnormal;
import dao.entities.Famille;
import dao.entities.Genetique;
import dao.entities.Imagerie;
import dao.entities.Individu;
import dao.entities.Polype;
import dao.entities.Traitement;
import dao.entities.Tumeur;


public class DossierDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;
	public DossierDAO() {
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
// creation d'un nouveau dossier
	
	public boolean creerDossier(Date dateCreation, int id_Ind){
		 try {
	            EntityManager em = this.newEntityManager();
	            Individu patient = em.find(Individu.class, id_Ind);
	            DossierMedicale dossier = new DossierMedicale(dateCreation, patient);
	            patient.setDossier(dossier);
	            em.persist(dossier);
	            em.detach(dossier);
	            dossier.setPatient(patient);
	            em.merge(dossier);
	            this.closeEntityManager(em);
	            System.out.println("le dossier est bien crée !");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de creation !!!");
	            return false;
	        }
	}
	
	public boolean creerDossierMedical(DossierMedicale doss){
		 try {
	            EntityManager em = this.newEntityManager();
	            Individu patient = doss.getPatient();
	            patient.setDossier(doss);
	            em.persist(doss);
	            this.closeEntityManager(em);
	            System.out.println("le dossier est bien crée !");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de creation !!!");
	            return false;
	        }
	}
	  public boolean modifierDossier(int ancienDossier_id, DossierMedicale newDossier){
	        EntityManager em = this.newEntityManager();
	        try {
	        	DossierMedicale ancienDossier = em.find(DossierMedicale.class, ancienDossier_id);
	        	em.detach(ancienDossier);
	        	ancienDossier.setDateCreation(newDossier.getDateCreation());;
	        	em.merge(ancienDossier);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise Ã  jour: "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	
//	Supprimer un dossier 
	 public boolean supprimerDossier(int id_dos){
		    EntityManager em = this.newEntityManager();
		        try {
		        	DossierMedicale dos = em.find(DossierMedicale.class, id_dos);
		        	dos.setPatient(null);
		        	Individu patient = dos.getPatient();
		        	patient.setDossier(null);
		            em.remove(dos);
		            this.closeEntityManager(em);
		            return true;
		        } catch (Exception e) {
		            this.closeEntityManager(em);
		            return false; 
		        }
	}
	 
	public DossierMedicale trouverDossierById(int id_dossier){
		EntityManager em = this.newEntityManager();
		DossierMedicale dossier = em.find(DossierMedicale.class, id_dossier);
        
        this.closeEntityManager(em);
        return dossier;
		
	}
//	 Lister les examens medicals par individus
	 public List<ExamenMedical> listerExamenParPatient(int id_patient){
	        EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<ExamenMedical> requete = em.createQuery("SELECT DISTINCT e FROM ExamenMedical e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", ExamenMedical.class);
	        requete.setParameter("iid", patient.getId());
	        List<ExamenMedical> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<ExamenPostOp> listerExamenPostOPParPatient(int id_patient){
	        EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<ExamenPostOp> requete = em.createQuery("SELECT DISTINCT e FROM ExamenPostOp e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", ExamenPostOp.class);
	        requete.setParameter("iid", patient.getId());
	        List<ExamenPostOp> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<ExamenPreOp> listerExamenPreOpParPatient(int id_patient){
	        EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<ExamenPreOp> requete = em.createQuery("SELECT DISTINCT e FROM ExamenPreOp e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", ExamenPreOp.class);
	        requete.setParameter("iid", patient.getId());
	        List<ExamenPreOp> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<ExamenPreOpAnormal> listerExamenPreOpAnormalParPatient(int id_patient){
	        EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<ExamenPreOpAnormal> requete = em.createQuery("SELECT DISTINCT e FROM ExamenPreOpAnormal e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", ExamenPreOpAnormal.class);
	        requete.setParameter("iid", patient.getId());
	        List<ExamenPreOpAnormal> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
//	 Lister les examens biologiques par individus
	 public List<Biologie> listerBiologieParPatient(int id_patient){
	        EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        System.out.println("patient "+patient);
	        TypedQuery<Biologie> requete = em.createQuery("SELECT DISTINCT e FROM Biologie e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", Biologie.class);
	        requete.setParameter("iid", patient.getId());
	        List<Biologie> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
//	 Lister les examens imagerie par individus
	 public List<Imagerie> listerExamenImagParPatient(int id_patient){
		 EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<Imagerie> requete = em.createQuery("SELECT DISTINCT e FROM Imagerie e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", Imagerie.class);
	        requete.setParameter("iid", patient.getId());
	        List<Imagerie> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<Genetique> listerExamenGenetiqueParPatient(int id_patient){
		 EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<Genetique> requete = em.createQuery("SELECT DISTINCT e FROM Genetique e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", Genetique.class);
	        requete.setParameter("iid", patient.getId());
	        List<Genetique> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
//	 Lister les examens endoscopie par individus
	 public List<Endoscopie> listerExamenEndoscopieParPatient(int id_patient){
		 EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<Endoscopie> requete = em.createQuery("SELECT DISTINCT e FROM Endoscopie e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", Endoscopie.class);
	        requete.setParameter("iid", patient.getId());
	        List<Endoscopie> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
//	 Lister les examens clinique par individus
	 public List<ExamenClinique> listerExamenCliniqueParPatient(int id_patient){
		 EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<ExamenClinique> requete = em.createQuery("SELECT DISTINCT e FROM ExamenClinique e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", ExamenClinique.class);
	        requete.setParameter("iid", patient.getId());
	        List<ExamenClinique> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
//	 Lister les examens anapath par individus
	 public List<AnaPathologie> listerExamenAnapathParPatient(int id_patient){
		 EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<AnaPathologie> requete = em.createQuery("SELECT DISTINCT e FROM AnaPathologie e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", AnaPathologie.class);
	        requete.setParameter("iid", patient.getId());
	        List<AnaPathologie> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<Tumeur> listerTumeurParPatient(int id_patient){
		 EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<Tumeur> requete = em.createQuery("SELECT DISTINCT e FROM Tumeur e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", Tumeur.class);
	        requete.setParameter("iid", patient.getId());
	        List<Tumeur> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<Polype> listerPolypeParPatient(int id_patient){
		 EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<Polype> requete = em.createQuery("SELECT DISTINCT e FROM Polype e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", Polype.class);
	        requete.setParameter("iid", patient.getId());
	        List<Polype> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<Traitement> listerTraitementParPatient(int id_patient){
		    EntityManager em = this.newEntityManager();
	        Individu patient = em.find(Individu.class, id_patient);
	        TypedQuery<Traitement> requete = em.createQuery("SELECT DISTINCT e FROM Traitement e  JOIN e.dossier d JOIN d.patient p Where p.id_Individu =:iid", Traitement.class);
	        requete.setParameter("iid", patient.getId());
	        List<Traitement> examens = requete.getResultList();
	        this.closeEntityManager(em);
	        return examens;
	    }
	 public List<DossierMedicale> listerLesDossier(){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<DossierMedicale> query = em.createQuery("SELECT a FROM DossierMedicale a", DossierMedicale.class);
	        List<DossierMedicale> dossiers= query.getResultList();
	        this.closeEntityManager(em);
	        return dossiers;
	    }
	 public boolean listerLesDossier(int id_patient){
		 	
	        List<DossierMedicale> dossiers= listerLesDossier();
	        for(int i=0;i<dossiers.size();i++){
	        	
	        	DossierMedicale d=dossiers.get(i);
	        	if (d.getPatient().getId()==id_patient)  return true;
	        }
	        
	        return false;
	    }
//	 public Individu trouverPatientByDossier(){
//		 
//	 }
	 public List<DossierMedicale> listeDossierByFamille(int id_famille){
		 List<DossierMedicale> dossiers = new ArrayList<>();
		 	EntityManager em = this.newEntityManager();
	        Famille famille = em.find(Famille.class, id_famille);
	        TypedQuery<DossierMedicale> requete = em.createQuery("SELECT DISTINCT d FROM DossierMedicale d  JOIN FETCH d.patient i JOIN i.famille f WHERE f.id_Famille =:id", DossierMedicale.class);
	        requete.setParameter("id", famille.getId());
	        dossiers = requete.getResultList();
	        this.closeEntityManager(em);
	        return dossiers;
	 }
	 public List<DossierMedicale> listeDossierByNonFamille(String nom){
		 List<DossierMedicale> dossiers = new ArrayList<>();
		 	EntityManager em = this.newEntityManager();
	        TypedQuery<DossierMedicale> requete = em.createQuery("SELECT DISTINCT d FROM DossierMedicale d  JOIN FETCH d.patient i JOIN i.famille f WHERE f.nomFamille =:nom", DossierMedicale.class);
	        requete.setParameter("nom", nom);
	        dossiers = requete.getResultList();
	        this.closeEntityManager(em);
	        return dossiers;
	 }
}
