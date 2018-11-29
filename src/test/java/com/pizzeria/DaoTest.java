package com.pizzeria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.pizzeria.classe.Pizza;
import com.pizzeria.exception.FindPizzaByCodeException;
import com.pizzeria.exception.SavePizzaException;
import com.pizzeria.model.CategoriePizzaEnum;
import com.pizzeria.model.PizzaMemDao;


public class DaoTest {

	protected PizzaMemDao pmd ;
	protected Pizza ptest;
	protected Pizza ptestNull;
	protected Pizza ptestVide ;
	protected Pizza ptestCode;
	protected Pizza ptestPrixPositif ;
	protected Pizza ptestPrixInterval;
	
	@Before
	public void setUp() throws SavePizzaException {
		
		pmd = new PizzaMemDao();
		ptest = new Pizza("RER","rer",10.0,CategoriePizzaEnum.AUTRE);
		ptestNull = new Pizza();
		ptestVide = new Pizza("RER","",10.0,CategoriePizzaEnum.AUTRE);
		ptestCode = new Pizza("RERE","rer",10.0,CategoriePizzaEnum.AUTRE);
		ptestPrixPositif = new Pizza("RER","rer",-10.0,CategoriePizzaEnum.AUTRE);
		ptestPrixInterval = new Pizza("RER","rer",102.0,CategoriePizzaEnum.AUTRE);
		pmd.saveNewPizza(ptest);
	}

	@After
	public void tearDown() {
		
	}
	
	/**
	 * Test de la méthode pizzaExists
	 */
	//@Ignore
	@Test
	public void pizzaExistsTest() {
		
		ptest.setCode("PIZ");
		
		assertEquals(false, pmd.pizzaExists("RER"));
		
	}
	
	/**
	 * Test de l'envoi de l'exception de la méthode findPizzaByCode en cas de null
	 * @throws SavePizzaException 
	 */
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void findPizzaByCodeTest() throws FindPizzaByCodeException {
				
		pmd.findPizzaByCode(null);
		
	}
	
	/**
	 * Test de l'envoi de l'exception de la méthode saveNewPizza en cas de désignation vide
	 * @throws SavePizzaException
	 */
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void designationNotEmptyTest() throws SavePizzaException {
											
		pmd.saveNewPizza(ptestVide);
				
	}
	
	/**
	 * Test d'envoi de l'exception de la méthode saveNewPizza en cas de pizza null
	 * @throws SavePizzaException
	 */
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void saveNewPizzaNullTest() throws SavePizzaException {
				
		pmd.saveNewPizza(ptestNull);
		
	}
	
	/**
	 * Test d'envoi de l'exception de la méthode saveNewPizza en cas de code pizza qui ne contient pas 3 majuscules
	 * @throws SavePizzaException
	 */
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void code3MajusculesTest() throws SavePizzaException {
		
		pmd.saveNewPizza(ptestCode);
		//assertTrue(ptest1.getCode().matches("[A-Z]{3}"));
		//assertTrue(ptest1.getCode().matches("^+[A-Z]+[A-Z]+[A-Z]?[A-Z]$"));
	}
	
	/**
	 * Test d'envoi de l'exception avec le bon message pour la méthode saveNewPizza en cas de pizza avec un prix négatif
	 */
	//@Ignore
	@Test
	public void prixPositifSaveNewPizzaTest() {
		
		try {
			
			pmd.saveNewPizza(ptestPrixPositif);
			
		} catch (SavePizzaException e) {
			
			assert(e.getMessage().contains("Le prix doit être renseigné"));
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Test d'envoi de l'exception sur la méthode saveNewPizza pour le cas où le prix n'appartient pas à l'interval
	 * @throws SavePizzaException
	 */
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void prixIntervalTest() throws SavePizzaException {
		
		pmd.saveNewPizza(ptestPrixInterval);
		//assertTrue(ptest.getPrice()>4 && ptest.getPrice()<20);
	}
	
	/**
	 * Test s'il y a des doublons au niveau de la designation dans la liste de pizza
	 */
	//@Ignore
	@Test
	public void designationDoublonTest() {
		
		assertFalse(isDesignationDuplicate());
		
	}
	
	public Boolean isDesignationDuplicate() {
		Set<String> set = new HashSet<String>();
		
		for(Pizza pizza: pmd.findAllPizzas())
		{
			if(!set.add(pizza.getDesignation()))
			{
				return true;
			}
		}
		return false;
		
		
	}
	
	/**
	 * Test s'il y a des doublons au niveau du code dans la liste de pizza
	 */
	//@Ignore
	@Test
	public void codeDoublonTest() {
		
		assertFalse(isCodeDuplicate());
		
	}
	
	public Boolean isCodeDuplicate() {
		Set<String> set = new HashSet<String>();
		
		for(Pizza pizza: pmd.findAllPizzas())
		{
			if(!set.add(pizza.getCode()))
			{
				return true;
			}
		}
		return false;
		
		
	}
	
}
