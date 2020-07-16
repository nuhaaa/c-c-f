package dao;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import dao.entities.Detail;

public class DetailDAO {
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;
	public DetailDAO() {
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
    public  boolean ajouterDetail(Detail detail){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(detail);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    public boolean supprimerDetail(int detail_id){
        EntityManager em = this.newEntityManager();
        try {
        	Detail detail = em.find(Detail.class, detail_id);
            em.remove(detail);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            this.closeEntityManager(em);
            return false; 
        }
    }
    public boolean modifierDetail(int detail_id, Detail newDetail){
        EntityManager em = this.newEntityManager();
        try {
        	Detail ancienDetail = em.find(Detail.class, detail_id);
        	em.detach(ancienDetail);
        	ancienDetail.setDistance(newDetail.getDistance());
        	ancienDetail.setSiege(newDetail.getSiege());
        	ancienDetail.setSphinecter(newDetail.getSphinecter());
        	em.merge(ancienDetail);
            this.closeEntityManager(em);
            return true;
        } catch (Exception e) {
            System.out.println("Erreur de mise à jour: "+e.getMessage());
            this.closeEntityManager(em);
            return false; 
        }
    }
}
