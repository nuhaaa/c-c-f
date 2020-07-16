package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.entities.Chimiotherapie;
import dao.entities.Traitement;


public class ChimiotherapieDAO {
	private final String  PU_NAME = "CHU";
	private EntityManagerFactory factory = null;
	public ChimiotherapieDAO() {
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
	public boolean ajoutTraitChimio(Chimiotherapie traitChimio){
        try {
            EntityManager em = this.newEntityManager();
            Traitement trait = traitChimio.getTraitement();
            trait.setChimiotherapie(traitChimio);
            em.persist(traitChimio);
            this.closeEntityManager(em);
            System.out.println(" bien enregistre au platfrome !");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement !!!");
            return false;
        }
	} 
}
