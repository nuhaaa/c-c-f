package dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.AspectMacro;
import dao.entities.Biologie;
import dao.entities.DossierMedicale;

import dao.entities.Individu;



public class BiologieDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public BiologieDAO() {
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
    public  boolean ajouterAnalyse(Biologie  analyse){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = analyse.getDossier();
            dossier.addExamenMedicale(analyse);
            em.persist(analyse);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerAnalyse(int analyse_id){
        EntityManager em = this.newEntityManager();
        try {
        	Biologie analyse = em.find(Biologie.class, analyse_id);
        	DossierMedicale doss = analyse.getDossier();
        	doss.removeExamenMedicale(analyse);
            em.remove(analyse);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierAnalyse(int ancienAnalyse_id, Biologie newAnalyse){
        EntityManager em = this.newEntityManager();
        try {
        	Biologie ancienAnalyse = em.find(Biologie.class, ancienAnalyse_id);
        	em.detach(ancienAnalyse);
        	ancienAnalyse.setAnalyse(newAnalyse.getAnalyse());
        	ancienAnalyse.setDataeAnalyse(newAnalyse.getDataeAnalyse());
        	ancienAnalyse.setValeur(newAnalyse.getValeur());
        	ancienAnalyse.setHopital(newAnalyse.getHopital());
        	em.merge(ancienAnalyse);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
// lister les analyses biologiques par patient
    public List<Biologie> listerlesAnalysesParPatient(Individu patient){
        EntityManager em = this.newEntityManager();
        TypedQuery<Biologie> query = em.createQuery("SELECT b FROM Biologie b WHERE b.getDossier().getPatient = patient ", Biologie.class);
        List<Biologie> analyses = query.getResultList();
        this.closeEntityManager(em);
        return analyses;
    }
    public Biologie trouverBiologieById(int id){
		EntityManager em = this.newEntityManager();
		Biologie categorie = em.find(Biologie.class, id);
        
        this.closeEntityManager(em);
        return categorie;
		
	}
}
