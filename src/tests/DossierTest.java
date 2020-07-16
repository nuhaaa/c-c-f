package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DossierDAO;
import dao.entities.DossierMedicale;


public class DossierTest {
	protected DossierDAO dosDAO;

	@Before
	public void setUp() throws Exception {
		dosDAO = new DossierDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void test() {
//		Date date = new Date();
//		assertTrue(dosDAO.creerDossierMedical(date, 2));
//	}
	@Test
	public void test() {
		assertTrue(dosDAO.supprimerDossier(101));
	}

}
