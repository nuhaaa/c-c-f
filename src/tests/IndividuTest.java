package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.FamilleDAO;
import dao.IndividuDAO;
import dao.SyndromeFamilleDAO;
import dao.entities.Famille;
import dao.entities.Individu;
import dao.entities.SyndromeFamille;

public class IndividuTest {

	protected IndividuDAO indDAO;
	protected FamilleDAO familleDAO;
	protected SyndromeFamilleDAO syndDAO;
	@Before
	public void setUp() throws Exception {
		indDAO = new IndividuDAO();
		familleDAO = new FamilleDAO();
		syndDAO = new SyndromeFamilleDAO();
		
	}

	@After
	public void tearDown() throws Exception {
	}
// il ya unn probleme avec cet ajout famille individu il ne tient pas compte le cas index
	
	@Test
	public void ajoutIndividutest() {
		SyndromeFamille syndrome = new SyndromeFamille(30, "syndX");
//		assertTrue(syndDAO.ajouterDiagnostic(syndrome));
		Famille famille = new Famille("Douc", syndrome);
		assertTrue(familleDAO.ajouterFamille(famille));
		
	}
//	@Test
//	public void suppIndTest() throws Exception {
//		assertTrue(indDAO.supprimerIndividu(2));
//	}
//	@Test
//	public void ajouterConsentTest() throws Exception {
//		Date date = new Date();
//		assertTrue(indDAO.ajoutConsentement(1, date));
//	}
//	
//	il me modifie pas le champs membre dans la table individu
//	@Test
//	public void ajouterCoupleTest() throws Exception {
//		Famille famille = new Famille(1, "Chaibi", "SyndromeX");
//		Individu mere = new Individu(2, "aa", "bbb", "F", "Fes", famille);
//		Individu pere = new Individu(1, "a", "bb", "M", "Fes", famille);
//		assertTrue(indDAO.ajoutCouple(mere, pere));
//	}
//	@Test
//	public void ajouterFilsTest() throws Exception {
//		Famille famille = new Famille(1, "Chaibi", "SyndromeX");
//		Individu ind = new Individu(2, "Aa", "Bb", "M", "Fes", famille);
//		assertTrue(indDAO.ajoutFilsCouple(ind, 51));
//	}
	

}
