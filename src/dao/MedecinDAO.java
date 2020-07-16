package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import dao.entities.Medecin;

public class MedecinDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;

	
    
	public MedecinDAO() {
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
    public Medecin anthentification(String login, String password){
        EntityManager em = this.newEntityManager();
        try {
            TypedQuery<Medecin> query = em.createQuery("SELECT p FROM Medecin p  WHERE p.login = :log AND p.password = :pass", Medecin.class);
            query.setParameter("log", login);
            query.setParameter("pass", password);
            Medecin med = query.getSingleResult();
            System.out.println("---------------------MedecinDAO----------------");
            System.out.println("admin conn = "+med);
            this.closeEntityManager(em);
            return med;
        } catch (NoResultException e) {
            System.out.println("Erreur authentification "+ e.getMessage());
            this.closeEntityManager(em);
            return null;
        }
    }
    public List<Object[]> listerLesMedecin(){
        EntityManager em = this.newEntityManager();
        List<Object[]> medecins = em.createQuery("SELECT p.id, p.nom, p.prenom FROM Medecin p").getResultList();
       
        //List<Medecin> hopitaux= query.;
        this.closeEntityManager(em);
        return medecins;
    }
	
}
