package com.pizzeria;

import org.junit.Rule;
import org.junit.Test;

import com.pizzeria.classe.Pizza;
import com.pizzeria.model.CategoriePizzaEnum;
import com.pizzeria.model.PizzaMemDao;

//import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	//Pizza ptest = new Pizza("RER","RER",10.0,CategoriePizzaEnum.AUTRE);
	
	@Rule PizzaMemDao pmd = new PizzaMemDao();
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	@Test
    public void pizzaExistsTest()
    {
    	assertEquals(true,pmd.pizzaExists("rer"));
    }

    /**
     * @return the suite of tests being tested
     */
   /* public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }*/

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
