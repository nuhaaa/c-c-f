package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.InfirmierDao;
import dao.UtilisateurDAO;
import dao.entities.Infirmier;
import dao.entities.Utilisateur;

public class UtilisateurTest {
	
	protected UtilisateurDAO utDAO;

	@Before
	public void setUp() throws Exception {
		utDAO = new UtilisateurDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void test() {
//	Utilisateur ut = new Infirmier("Inf", "Inf", "Inf", "infirmier", "infirmier");
////		Utilisateur ut=  utDAO.anthentification("infirmier", "infirmier");
//	assertTrue(utDAO.creerNouveauCompte(ut));
//	System.out.println(" "+ut+" ");
//	}

}
