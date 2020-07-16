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
import dao.entities.Genetique;
import dao.entities.Individu;



public class GenetiqueDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public GenetiqueDAO() {
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
    public  boolean ajouterGenetique(Genetique  genetique){
        try {
            EntityManager em = this.newEntityManager();
            DossierMedicale dossier = genetique.getDossier();
            dossier.addExamenMedicale(genetique);
            em.persist(genetique);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerGenetique(int genetique_id){
        EntityManager em = this.newEntityManager();
        try {
        	Genetique gen = em.find(Genetique.class, genetique_id);
        	DossierMedicale doss = gen.getDossier();
        	doss.removeExamenMedicale(gen);
            em.remove(gen);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierAnalyse(int ancienGen_id, Genetique newGen){
        EntityManager em = this.newEntityManager();
        try {
        	Genetique ancienGenetique = em.find(Genetique.class, ancienGen_id);
        	em.detach(ancienGenetique);
        	ancienGenetique.setHopital(newGen.getHopital());
        	ancienGenetique.setNumeroDossierGenetique(newGen.getNumeroDossierGenetique());
        	ancienGenetique.setInstabiliteMacroscopique(newGen.getInstabiliteMacroscopique());
        	ancienGenetique.setMutaBRAF(newGen.getMutaBRAF());
        	ancienGenetique.setMutaAPC(newGen.getMutaAPC());
        	ancienGenetique.setMutaKras(newGen.getMutaKras());
        	ancienGenetique.setMutaMYH(newGen.getMutaMYH());
        	ancienGenetique.setAutre(newGen.getAutre());
           	em.merge(ancienGenetique);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour : "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
// lister les analyses biologiques par patient
    public List<Genetique> listerlesGenetiquesParPatient(Individu patient){
        EntityManager em = this.newEntityManager();
        TypedQuery<Genetique> query = em.createQuery("SELECT b FROM Genetique b WHERE b.getDossier().getPatient = patient ", Genetique.class);
        List<Genetique> genetiques = query.getResultList();
        this.closeEntityManager(em);
        return genetiques;
    }
    public Genetique trouverGenetiqueById(int id){
		EntityManager em = this.newEntityManager();
		Genetique genetique = em.find(Genetique.class, id);
        
        this.closeEntityManager(em);
        return genetique;
		
	}
}
