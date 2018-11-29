package com.pizzeria.model;
import java.util.Comparator;

import com.pizzeria.classe.Pizza;

public class PizzaPrixDecroissantComparator implements Comparator<Pizza> {

	public int compare(Pizza pizza1, Pizza pizza2) {

		if (pizza1.getPrice() < pizza2.getPrice())
		{
			return 1;
		} 
		else if (pizza1.getPrice() > pizza2.getPrice())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	
}