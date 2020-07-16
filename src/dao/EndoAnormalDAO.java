package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.EndoAnormal;
import dao.entities.Endoscopie;
import dao.entities.Hopital;
import dao.entities.Anesthesie;
import dao.entities.AspectMacro;
import dao.entities.Complication;
import dao.entities.DossierMedicale;
import dao.entities.Individu;
import dao.entities.MasseTumorale;
import dao.entities.Polypose;
import dao.entities.TypeAndoscopie;


public class EndoAnormalDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public EndoAnormalDAO() {
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
    public  boolean ajouterEndoAnormal(EndoAnormal  endoAnormal){
        try {
            EntityManager em = this.newEntityManager();
            
            DossierMedicale dossier = endoAnormal.getDossier();
            dossier.addExamenMedicale(endoAnormal);
            
            em.persist(endoAnormal);
       
            this.closeEntityManager(em);
            System.out.println("bien enregistr√©!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerEndoAnormal(int endoAnormal_id){
        EntityManager em = this.newEntityManager();
        try {
        	
        	
        	EndoAnormal endoAnormal = em.find(EndoAnormal.class, endoAnormal_id);
        	DossierMedicale dossier = endoAnormal.getDossier();
        	dossier.removeExamenMedicale(endoAnormal);
        	

            em.remove(endoAnormal);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierEndoAnormal(int ancienEndoAnormal_id, EndoAnormal newEndoAnormal){
    	EntityManager em = factory.createEntityManager();
    	em.getTransaction().begin();
        try {
           	EndoAnormal ancienEndoAnormal = em.find(EndoAnormal.class, ancienEndoAnormal_id);
        	em.detach(ancienEndoAnormal);
        	ancienEndoAnormal.setAnestesie(newEndoAnormal.getAnestesie());
        	ancienEndoAnormal.setComplcations(newEndoAnormal.getComplcations());
        	ancienEndoAnormal.setDateEndo(newEndoAnormal.getDateEndo());
        	ancienEndoAnormal.setHopital(newEndoAnormal.getHopital());
        	ancienEndoAnormal.setNumero(newEndoAnormal.getNumero());
        	ancienEndoAnormal.setTypeEndo(newEndoAnormal.getTypeEndo());
        	ancienEndoAnormal.setPolypes(newEndoAnormal.getPolypes());
        	ancienEndoAnormal.setMasseTumorales(newEndoAnormal.getMasseTumorales());
        	em.merge(ancienEndoAnormal);
        	em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise ‡ jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    
    public boolean modifierEndoscopie(int ancien_id, Endoscopie newEndoscopie){
    	EntityManager em = factory.createEntityManager();
    	em.getTransaction().begin();
        try {
        	System.out.println("##oldID :: "+ancien_id);
        	System.out.println("##newEnd :: "+newEndoscopie);
        	Endoscopie ancienEndoscopie = em.find(Endoscopie.class, ancien_id);
        	System.out.println("##oldEnd :: "+ancienEndoscopie);
        	em.detach(ancienEndoscopie);
        	ancienEndoscopie.setAnestesie(newEndoscopie.getAnestesie());
        	ancienEndoscopie.setDateEndo(newEndoscopie.getDateEndo());
        	
        	ancienEndoscopie.setHopital(newEndoscopie.getHopital());
        	ancienEndoscopie.setNumero(newEndoscopie.getNumero());
        	ancienEndoscopie.setTypeEndo(newEndoscopie.getTypeEndo());
        	
        	
        	if(newEndoscopie instanceof EndoAnormal){
        		if(!(ancienEndoscopie instanceof EndoAnormal)){
        			Hopital hopital = newEndoscopie.getHopital();
                	DossierMedicale dossier = newEndoscopie.getDossier();
                	Date dateEndo = newEndoscopie.getDateEndo();
                	TypeAndoscopie typeEndo = newEndoscopie.getTypeEndo();
                	int numero = newEndoscopie.getNumero();
                	Anesthesie anestesie = newEndoscopie.getAnestesie();
                	List<MasseTumorale> masseTumorales = ((EndoAnormal) newEndoscopie).getMasseTumorales();
                	List<Polypose> polyposes = ((EndoAnormal) newEndoscopie).getPolypes();
                	List<Complication> complications =((EndoAnormal) newEndoscopie).getComplcations();
                	String file = ((EndoAnormal) newEndoscopie).getFile();
        			em.remove(ancienEndoscopie);
        			ancienEndoscopie = new EndoAnormal(hopital, dossier, dateEndo, typeEndo, numero, 
        					anestesie, masseTumorales, polyposes, complications,file);
        		}
        		((EndoAnormal)ancienEndoscopie).setComplcations(((EndoAnormal)newEndoscopie).getComplcations());
        		((EndoAnormal)ancienEndoscopie).setPolypes(((EndoAnormal)newEndoscopie).getPolypes());
        		((EndoAnormal)ancienEndoscopie).setMasseTumorales(((EndoAnormal)newEndoscopie).getMasseTumorales());
        	}
        	em.merge(ancienEndoscopie);
        	em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise ‡ jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
    
// lister 
    public List<EndoAnormal> listerAndoscopieAnormalParPatient(Individu patient){
        EntityManager em = this.newEntityManager();
        TypedQuery<EndoAnormal> query = em.createQuery("SELECT b FROM EndoAnormal b WHERE b.getDossier().getPatient = patient ", EndoAnormal.class);
        List<EndoAnormal> endoAnormals = query.getResultList();
        this.closeEntityManager(em);
        return endoAnormals;
    }
    public EndoAnormal trouverEndoAnormalById(int id){
		EntityManager em = this.newEntityManager();
		EndoAnormal categorie = em.find(EndoAnormal.class, id);
        this.closeEntityManager(em);
        return categorie;
	}
}
