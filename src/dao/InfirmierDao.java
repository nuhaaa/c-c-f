package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dao.entities.Infirmier;

public class InfirmierDao {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;

	
    
	public InfirmierDao() {
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
    public Infirmier anthentification(String login, String password){
        EntityManager em = this.newEntityManager();
        try {
            TypedQuery<Infirmier> query = em.createQuery("SELECT p FROM Infirmier p  WHERE p.login = :log AND p.password = :pass", Infirmier.class);
            query.setParameter("log", login);
            query.setParameter("pass", password);
            Infirmier inf = query.getSingleResult();
            System.out.println("---------------------InfirmierDAO----------------");
            System.out.println("inf conn = "+inf);
            this.closeEntityManager(em);
            return inf;
        } catch (NoResultException e) {
            System.out.println("Erreur authentification "+ e.getMessage());
            this.closeEntityManager(em);
            return null;
        }
    }
    public boolean anthentifier(String login, String password){
        EntityManager em = this.newEntityManager();
        try {
            TypedQuery<Infirmier> query = em.createQuery("SELECT p FROM Infirmier p  WHERE p.login = :log AND p.password = :pass", Infirmier.class);
            query.setParameter("log", login);
            query.setParameter("pass", password);
            Infirmier inf = query.getSingleResult();
            this.closeEntityManager(em);
            return true;
        } catch (NoResultException e) {
            System.out.println("Erreur authentification "+ e.getMessage());
            this.closeEntityManager(em);
            return false;
        }
    }
}
