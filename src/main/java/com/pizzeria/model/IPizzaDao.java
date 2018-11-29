package com.pizzeria.model;

import java.util.List;

import com.pizzeria.classe.Pizza;
import com.pizzeria.exception.DeletePizzaException;
import com.pizzeria.exception.SavePizzaException;
import com.pizzeria.exception.UpdatePizzaException;

public interface IPizzaDao {
	
	List<Pizza> findAllPizzas();
	void saveNewPizza(Pizza pizza) throws SavePizzaException;
	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;
	void deletePizza(String codePizza) throws DeletePizzaException;
	Pizza findPizzaByCode(String codePizza);
	boolean pizzaExists(String codePizza);
	
	
}