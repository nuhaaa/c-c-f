package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.Chimiotherapie;
import dao.entities.Chirurgie;
import dao.entities.DossierMedicale;
import dao.entities.Individu;
import dao.entities.Radiotherapie;
import dao.entities.Traitement;
import dao.entities.TraitementEndoscopique;
import dao.entities.Tumeur;

public class TraitementDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;
	public TraitementDAO() {
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
	public boolean addTraitement(Traitement trait){
		 try {
	            EntityManager em = this.newEntityManager();
//	            Chirurgie chirurgie= trait.getChirurgie();
//	            TraitementEndoscopique TraitEndo = trait.getTraitEndo();
//	            Chimiotherapie chimiotherapie = trait.getChimiotherapie();
//	            Radiotherapie radiotherapie = trait.getRadiotherapie();
	            
	            DossierMedicale dossier = trait.getDossier();
	            dossier.addTraitement(trait);
	            
	            em.persist(trait);
//	            em.persist(chirurgie);
//	            em.persist(TraitEndo);
//	            em.persist(chimiotherapie);
//	            em.persist(radiotherapie);
//	            
	            this.closeEntityManager(em);
	            System.out.println("Traitement bien enregistre au platfrome !");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur  individu !!!");
	            return false;
	        }
	}
	
	public boolean supprimerTraitement(int  id_trait){
		 EntityManager em = this.newEntityManager();
	        try {
	        	Traitement trait= em.find(Traitement.class, id_trait);
//	        	Chirurgie chirurgie= trait.getChirurgie();
//	            TraitementEndoscopique TraitEndo = trait.getTraitEndo();
//	            Chimiotherapie chimiotherapie = trait.getChimiotherapie();
//	            Radiotherapie radiotherapie = trait.getRadiotherapie();
	            
	        	
	        	trait.setChimiotherapie(null);
	        	trait.setChirurgie(null);
	        	trait.setTraitEndo(null);
	        	trait.setRadiotherapie(null);
	        	

//	            em.remove(radiotherapie);
//	            em.remove(chimiotherapie);
//	            em.remove(TraitEndo);
//	            em.remove(chirurgie);
//	        	
	        	DossierMedicale dossier = trait.getDossier();
	        	dossier.removeTraitement(trait);
	        	
	            em.remove(trait);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            this.closeEntityManager(em);
	            return false; 
	        }
	}
	
	 public boolean modifierAnalyse(int ancienTrait_id, Traitement newtrait){
	        EntityManager em = this.newEntityManager();
	        try {
	        	Traitement ancienTrait = em.find(Traitement.class, ancienTrait_id);
	        	em.detach(ancienTrait);
	        	ancienTrait.setChirurgie(newtrait.getChirurgie());
	        	ancienTrait.setChimiotherapie(newtrait.getChimiotherapie());
	        	ancienTrait.setRadiotherapie(newtrait.getRadiotherapie());
	        	ancienTrait.setDate(newtrait.getDate());
	        	ancienTrait.setIndication(newtrait.getIndication());
	        	ancienTrait.setTraitEndo(newtrait.getTraitEndo());
	        	ancienTrait.setHopital(newtrait.getHopital());
	        	em.merge(ancienTrait);
	            this.closeEntityManager(em);
	            return true;
	        } catch (Exception e) {
	            System.out.println("Erreur de mise à jour : "+e.getMessage());
	            this.closeEntityManager(em);
	            return false; 
	        }
	    }
	// lister les analyses biologiques par patient
	    public List<Traitement> listerlesTraitementParPatient(Individu patient){
	        EntityManager em = this.newEntityManager();
	        TypedQuery<Traitement> query = em.createQuery("SELECT t FROM Traitement t WHERE t.getDossier().getPatient = patient ", Traitement.class);
	        List<Traitement> traitements = query.getResultList();
	        this.closeEntityManager(em);
	        return traitements;
	    }
	    public Traitement trouverTumeurById(int id){
			EntityManager em = this.newEntityManager();
			Traitement categorie = em.find(Traitement.class, id);
	        System.out.println("traitement");
	        this.closeEntityManager(em);
	        return categorie;
			
		}
	    
}
