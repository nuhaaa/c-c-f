package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AspectMacroDAO;
import dao.entities.*;

public class AspectTest {
	protected AspectMacroDAO aspectDAO;

	@Before
	public void setUp() throws Exception {
		aspectDAO =  new AspectMacroDAO();
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void ajouterAspectTest() throws Exception {
//		AspectMacro aspect = new AspectMacro(3, "test02");
//		assertTrue(aspectDAO.ajouterAspect(aspect));
//	}

//	@Test
//	public void suppAspectTest() throws Exception {
//		assertTrue(aspectDAO.supprimerAspect(1));
//	}
//	
//	@Test
//	public void modAspectTest() throws Exception {
//		AspectMacro aspect = new AspectMacro(2, "test");
//		assertTrue(aspectDAO.modifierAspect(2, aspect));
//	}
	
	@Test
	public void listeAspectTest() throws Exception {
//		assertTrue(aspectDAO.listerAspect());
	}
	
}
