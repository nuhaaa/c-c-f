package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Administrateur;
import dao.entities.Infirmier;

public class AdminDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;

	
    
	public  AdminDAO() {
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
    public Administrateur anthentification(String login, String password){
        EntityManager em = this.newEntityManager();
        try {
            TypedQuery<Administrateur> query = em.createQuery("SELECT p FROM Administrateur p  WHERE p.login = :log AND p.password = :pass", Administrateur.class);
            query.setParameter("log", login);
            query.setParameter("pass", password);
            Administrateur admin = query.getSingleResult();
            System.out.println("---------------------AdminDAO----------------");
            System.out.println("admin conn = "+admin);
            this.closeEntityManager(em);
            return admin;
        } catch (NoResultException e) {
            System.out.println("Erreur authentification "+ e.getMessage());
            this.closeEntityManager(em);
            return null;
        }
    }
}
