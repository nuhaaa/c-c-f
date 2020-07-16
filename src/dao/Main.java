/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.entities.Hopital;

/**
 *
 * @author noufa
 */
public class Main {

    private HopitalDAO hopDAO = null;
    
    public Main() {
        hopDAO = new HopitalDAO();
    }
    
    public void ajouterHopital(){
        Hopital hop = new Hopital(1, "CHU");
        hopDAO.enregistrementHopital(hop);
    }
    
    public static void main(String[] args) {
        new Main();
        System.err.println(" la creation de la base de données est terminé!!!");
    }
    
}
