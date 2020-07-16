/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import dao.entities.Hopital;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


/**
 *
 * @author noufa
 */
public class HopitalDAO {
    
	private final String  PU_NAME = "CHU";
    private EntityManagerFactory factory = null;

    public HopitalDAO() {
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
    public  boolean enregistrementHopital(Hopital hopital){
        try {
            EntityManager em = this.newEntityManager();
            em.persist(hopital);
            this.closeEntityManager(em);
            System.out.println("bien enregistré!");
            return true;
        } catch (Exception e) {
            System.out.println("Erreur d'enregistrement!!!");
            return false;
        }
    }
    
    public List<Hopital> listerLesHopitaux(){
        EntityManager em = this.newEntityManager();
        TypedQuery<Hopital> query = em.createQuery("SELECT a FROM Hopital a", Hopital.class);
        List<Hopital> hopitaux= query.getResultList();
        this.closeEntityManager(em);
        return hopitaux;
    }
    
    public Hopital trouverHopitalById(int id){
		EntityManager em = this.newEntityManager();
		Hopital hop = em.find(Hopital.class, id);
        
        this.closeEntityManager(em);
        return hop;
		
	}
    
}
