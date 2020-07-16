package dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Forme;
import dao.entities.Individu;
import dao.entities.M;
import dao.entities.N;
import dao.entities.PriseEnCharge;
import dao.entities.RendezVous;
import dao.entities.Site;
import dao.entities.StatutCancereux;
import dao.entities.T;
import dao.entities.Type;
import dao.entities.TypeStatut;

public class StatutDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;

	
	public StatutDAO() {
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
// ajouter les statuts
	
	
	
//	ajouter ccr
	public void ajouterStatutCCR(TypeStatut cancer, String annee, int age,Type type, Site site, T t, M m, N n,
			List<PriseEnCharge> priseEncharge,Individu  patient){
		EntityManager em = this.newEntityManager();
		try {
			StatutCancereux ccr = new  StatutCancereux(cancer, annee, age, type, site, t, m, n, priseEncharge);
			
				
					em.detach(patient);
					patient.addCancer(ccr);
					em.merge(patient);
					ccr.addIndividu(patient);
//					ccr.addAllPriseEnCharge(priseEncharge);
					em.persist(ccr);
				
			
			
			
            this.closeEntityManager(em);
        } catch (Exception e) {
        	System.out.println("Erreur !!!");
            this.closeEntityManager(em);    
        }
	}

//	ajouter cec
	public void ajouterStatutCEC(TypeStatut cancer, String annee, int age,Type type, Site site, T t, M m, N n,
			List<PriseEnCharge> priseEncharge, Individu  patient){
		EntityManager em = this.newEntityManager();
		try {
			StatutCancereux cec = new  StatutCancereux(cancer, annee, age, type, site, t, m, n, priseEncharge);
			em.detach(patient);
			patient.addCancer(cec);
			em.merge(patient);
			cec.addIndividu(patient);
			
			em.persist(cec);
            this.closeEntityManager(em);
        } catch (Exception e) {
        	System.out.println("Erreur !!!");
            this.closeEntityManager(em);    
        }
	}

//	ajouter p
	public void ajouterStatutP(TypeStatut cancer, String annee, int age,Forme forme, 
			List<PriseEnCharge> priseEncharge, Individu patient){
		EntityManager em = this.newEntityManager();
		try {
			StatutCancereux p = new  StatutCancereux(cancer, annee, age, forme, priseEncharge);
			em.detach(patient);
			patient.addCancer(p);
			p.addIndividu(patient);
			em.merge(patient);
			em.persist(p);
            this.closeEntityManager(em);
        } catch (Exception e) {
        	System.out.println("Erreur !!!");
            this.closeEntityManager(em);    
        }
	}
//	ajouter mec
	public void ajouterStatutMEC(TypeStatut cancer, String annee, int age,Type  type, 
			List<PriseEnCharge> priseEncharge,Individu patient){
		EntityManager em = this.newEntityManager();
		try {
			StatutCancereux mec = new  StatutCancereux(cancer, annee, age, type, priseEncharge);
			em.detach(patient);
			patient.addCancer(mec);
			mec.addIndividu(patient);
//			mec.addAllPriseEnCharge(priseEncharge);
			em.merge(patient);
			em.persist(mec);
            this.closeEntityManager(em);
        } catch (Exception e) {
        	System.out.println("Erreur !!!");
            this.closeEntityManager(em);    
        }
	}
//	Supprimer un statut 
	 public boolean supprimerStatut(int id_Statut){
		    EntityManager em = this.newEntityManager();
		        try {
		            StatutCancereux statut = em.find(StatutCancereux.class, id_Statut);
		            em.detach(statut);
		            statut.setForme(null);
		            statut.setSite(null);
		            statut.setType(null);
		            statut.setPriseEncharge(null);
		            em.remove(statut);
		            this.closeEntityManager(em);
		            return true;
		        } catch (Exception e) {
		            this.closeEntityManager(em);
		            return false; 
		        }
		 }
//	 liste des individus par statut
	 public List<Individu> listerIndividuParStatut(int id_Statut){
	        EntityManager em = this.newEntityManager();
	        StatutCancereux statut = em.find(StatutCancereux.class, id_Statut);
	        TypedQuery<Individu> requete = em.createQuery("SELECT DISTINCT i FROM Individu i JOIN FETCH i.cancers s  WHERE s.getId() = :sid", Individu.class);
	        requete.setParameter("sid", statut.getId());
	        List<Individu> individus = requete.getResultList();
	        this.closeEntityManager(em);
	        return individus;
	    }
	 public List<StatutCancereux> listerStatutParIndividu(int id_Patient){
	        EntityManager em = this.newEntityManager();
	        Individu ind = em.find(Individu.class, id_Patient);
	        TypedQuery<StatutCancereux> requete = em.createQuery("SELECT DISTINCT s FROM StatutCancereux s JOIN FETCH s.individus i  WHERE i.id_Individu = :iid", StatutCancereux.class);
	        requete.setParameter("iid", ind.getId());
	        List<StatutCancereux> statuts = requete.getResultList();
	        this.closeEntityManager(em);
	        return statuts;
	    }
	
	 public StatutCancereux trouverStautById(int id){
		 StatutCancereux syndrome = null;
		        try {
		            EntityManager em = this.newEntityManager();
		            syndrome = em.find(StatutCancereux.class, id);
		            this.closeEntityManager(em);
		        } catch (NoResultException e) {
		            System.out.println("Erreur"+ e.getMessage());
		        }
		        return syndrome;
	    }
	 public boolean modifierStatut(int id_AncienStatut,StatutCancereux newStatut){
		    EntityManager em = this.newEntityManager();
		        try {
		        	StatutCancereux AncienStatut = em.find(StatutCancereux.class, id_AncienStatut);
		        	em.detach(AncienStatut);
		        	AncienStatut.setTypeStatut(newStatut.getTypeStatut());
		        	AncienStatut.setAnnee(newStatut.getAnnee());
		        	AncienStatut.setAge(newStatut.getAge());
		        	AncienStatut.setSite(newStatut.getSite());
		        	AncienStatut.setType(newStatut.getType());
		        	AncienStatut.setT(newStatut.getT());
		        	AncienStatut.setM(newStatut.getM());
		        	AncienStatut.setN(newStatut.getN());
		        	AncienStatut.setPriseEncharge(newStatut.getPriseEncharge());
		        	em.merge(AncienStatut);
		            this.closeEntityManager(em);
		            return true;
		        } catch (Exception e) {
		            this.closeEntityManager(em);
		            return false; 
		        }
	}
}
