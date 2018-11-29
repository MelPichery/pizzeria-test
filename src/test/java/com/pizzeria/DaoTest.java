package com.pizzeria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.pizzeria.classe.Pizza;
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
	
	//@Ignore
	@Test
	public void pizzaExistsTest() throws SavePizzaException {
		
		ptest.setCode("PIZ");
		
		assertEquals(false, pmd.pizzaExists("RER"));
		
	}
	
	//@Ignore
	@Test
	public void findPizzaByCodeTest() {
				
		assertNotNull("La pizza n'existe pas",pmd.findPizzaByCode("RER"));
		
	}
	
	
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void designationNotEmptyTest() throws SavePizzaException {
											
		pmd.saveNewPizza(ptestVide);
				
	}
	
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void saveNewPizzaNullTest() throws SavePizzaException {
				
		pmd.saveNewPizza(ptestNull);
		
	}
	
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void code3MajusculesTest() throws SavePizzaException {
		
		pmd.saveNewPizza(ptestCode);
		//assertTrue(ptest1.getCode().matches("[A-Z]{3}"));
		//assertTrue(ptest1.getCode().matches("^+[A-Z]+[A-Z]+[A-Z]?[A-Z]$"));
	}
	
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void prixPositifTest() throws SavePizzaException {
		
		pmd.saveNewPizza(ptestPrixPositif);
		//assertTrue(ptest.getPrice()>0);
	}
	
	//@Ignore
	@Test(expected=SavePizzaException.class)
	public void prixIntervalTest() throws SavePizzaException {
		
		pmd.saveNewPizza(ptestPrixInterval);
		//assertTrue(ptest.getPrice()>4 && ptest.getPrice()<20);
	}
	
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
