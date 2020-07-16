package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import dao.entities.Individu;
import dao.entities.RendezVous;

public class RendezDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;
	public RendezDAO() {
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
	// creation d'un nouveau rendez vous
	
		public boolean ajouterRendez(RendezVous rendezVous){
			 try {
		            EntityManager em = this.newEntityManager();
		            Individu patient = rendezVous.getPatient();
		            em.detach(patient);
		            patient.addRendezVous(rendezVous);
		            em.merge(patient);
		            em.persist(rendezVous);
		            this.closeEntityManager(em);
		            System.out.println("le rendez vous est bien ajouté !");
		            return true;
		        } catch (Exception e) {
		            System.out.println("Erreur  !!!");
		            return false;
		        }
		}
//		Supprimer un Rendez vous 
		 public boolean supprimerRendez(int id_rendez){
			    EntityManager em = this.newEntityManager();
			        try {
			        	RendezVous rendezVous = em.find(RendezVous.class, id_rendez);
			        	rendezVous.setPatient(null);
			            em.remove(rendezVous);
			            this.closeEntityManager(em);
			            return true;
			        } catch (Exception e) {
			            this.closeEntityManager(em);
			            return false; 
			        }
		}
//			Modifier un Rendez vous 
			 public boolean modifierRendez(int id_AncienRendez,RendezVous newRendezVous){
				    EntityManager em = this.newEntityManager();
				        try {
				        	RendezVous AncienRendezVous = em.find(RendezVous.class, id_AncienRendez);
				        	em.detach(AncienRendezVous);
				        	AncienRendezVous.setDateRendezVous(newRendezVous.getDateRendezVous());
				        	AncienRendezVous.setHeureRendez(newRendezVous.getHeureRendez());
				        	AncienRendezVous.setNote(newRendezVous.getNote());
				        	AncienRendezVous.setObjet(newRendezVous.getObjet());
				        	AncienRendezVous.setPatient(newRendezVous.getPatient());
				        	em.merge(AncienRendezVous);
				            this.closeEntityManager(em);
				            return true;
				        } catch (Exception e) {
				            this.closeEntityManager(em);
				            return false; 
				        }
			}
//		 Lister les rendezVous 
		 public List<RendezVous> listerRendezVous(){
		        EntityManager em = this.newEntityManager();
		        TypedQuery<RendezVous> requete = em.createQuery("SELECT DISTINCT r FROM RendezVous r ", RendezVous.class);
		        List<RendezVous> examens = requete.getResultList();
		        this.closeEntityManager(em);
		        return examens;
		    }
//		 lister les rendez vous par patient
		 public List<RendezVous> listerRendezVousParPatient(int id_patient){
		        EntityManager em = this.newEntityManager();
		        Individu patient = em.find(Individu.class, id_patient);
		        TypedQuery<RendezVous> requete = em.createQuery("SELECT DISTINCT r FROM RendezVous r  WHERE r.patient.id_Individu = :id", RendezVous.class);
		        requete.setParameter("id", patient.getId());
		        List<RendezVous> rendez = requete.getResultList();
		        this.closeEntityManager(em);
		        return rendez;
		    }
//		 lister les rendez-vous par date
		 public List<RendezVous> listerRendezVousParDate(Date date){
		        EntityManager em = this.newEntityManager();
		        TypedQuery<RendezVous> requete = em.createQuery("SELECT DISTINCT r FROM RendezVous r  WHERE r.getDateRendezVous() = date", RendezVous.class);
		        List<RendezVous> rendez = requete.getResultList();
		        this.closeEntityManager(em);
		        return rendez;
		    }
		 public RendezVous trouverRendezVousById(int id){
			 RendezVous syndrome = null;
				        try {
				            EntityManager em = this.newEntityManager();
				            syndrome = em.find(RendezVous.class, id);
				            this.closeEntityManager(em);
				        } catch (NoResultException e) {
				            System.out.println("Erreur"+ e.getMessage());
				        }
				        return syndrome;
			    }	
}
