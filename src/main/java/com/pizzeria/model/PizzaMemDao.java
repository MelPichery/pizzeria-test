package com.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import com.pizzeria.classe.Pizza;
import com.pizzeria.exception.DeletePizzaException;
import com.pizzeria.exception.FindPizzaByCodeException;
import com.pizzeria.exception.SavePizzaException;
import com.pizzeria.exception.StockageException;
import com.pizzeria.exception.UpdatePizzaException;

public class PizzaMemDao implements IPizzaDao {
	
	private List<Pizza> pizzas = null;
	
	public PizzaMemDao()
	{
		pizzas = new ArrayList<Pizza>();
		
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizzaEnum.AUTRE));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizzaEnum.FROMAGE));
		pizzas.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizzaEnum.FROMAGE));	
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizzaEnum.FROMAGE));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizzaEnum.VIANDE));
		pizzas.add(new Pizza("ORI","L'orientale", 13.50, CategoriePizzaEnum.VIANDE));
		pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizzaEnum.AUTRE));
		//pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizzaEnum.AUTRE));
	}
	





	public List<Pizza> findAllPizzas()
	{
		return pizzas;
	}



	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		
		try 
		{
			pizza.dataControl();
			pizzas.add(pizza);
		} 
		catch (StockageException e)
		{
			String message = "CREATION DE LA PIZZA => " + pizza.toString() + "\r\n";
			message += e.getMessage();
			
			throw new SavePizzaException(message);
		}
		
		
	}



	public boolean pizzaExists(String codePizza) {
		
		for (Pizza pizza : pizzas)
		{
			if (pizza.getCode().equals(codePizza))
			{
				return true;
			}
		}
		
		return false;
	}

	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		
		boolean trouveP = false;
		try 
		{
			pizza.dataControl();
			
			for (int i = 0; i < pizzas.size(); i++)
			{
				Pizza pizzaTmp = pizzas.get(i);
				
				if (pizzaTmp.getCode().equals(codePizza))
				{
					trouveP = true;
					pizzas.set(i, pizza);
				}
			}
		} 
		catch (StockageException e)
		{
			String message = "MODIFICATION DE LA PIZZA => " + pizza.toString() + "\r\n";
			message += e.getMessage();
			
			throw new UpdatePizzaException(message);
		}
		
		if (trouveP == false)
		{
			String message = "MODIFICATION DE LA PIZZA => " + codePizza + "\r\n";
			message += "La pizza à modifier n'existe pas";
			
			throw new UpdatePizzaException(message);
		}
		

	}


	public void deletePizza(String codePizza) throws DeletePizzaException {
		
		
		boolean trouveP = false;
		for (int i = 0; i < pizzas.size(); i++)
		{
			Pizza pizzaTmp = pizzas.get(i);
			
			if (pizzaTmp.getCode().equals(codePizza))
			{
				trouveP = true;
				pizzas.remove(i);
			}
		}
		
		if (trouveP == false)
		{
			String message = "SUPPRESSION DE LA PIZZA => " + codePizza + "\r\n";
			message += "La pizza à supprimer n'existe pas";
			
			throw new DeletePizzaException(message);
		}
		
	}


	public Pizza findPizzaByCode(String codePizza) throws FindPizzaByCodeException {

		

		for (int i = 0; i < pizzas.size(); i++)
		{
			Pizza pizzaTmp = pizzas.get(i);
			
			if (pizzaTmp.getCode().equals(codePizza))
			{
				return pizzaTmp;
			}
		}
		
		throw new FindPizzaByCodeException("Pas trouver de pizza correspondant à ce code");
		
		
	}
	
	public void displayAllPizza() {

		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());

		}
	}

	
	public static void displayAllPizza(List<Pizza> pizzas) {

		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());

		}
	}

}
