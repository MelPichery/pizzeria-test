package com.pizzeria.appli;

import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Scanner;


import com.pizzeria.classe.Pizza;
import com.pizzeria.exception.DeletePizzaException;
import com.pizzeria.exception.SavePizzaException;
import com.pizzeria.exception.UpdatePizzaException;
import com.pizzeria.model.CategoriePizzaEnum;
import com.pizzeria.model.PizzaMemDao;

import com.pizzeria.model.PizzaPrixCroissantComparator;
import com.pizzeria.model.PizzaPrixDecroissantComparator;
import com.pizzeria.model.PizzaPrixCroissantComparator;


public class PizzeriaAdminConsoleApp {

	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		PizzaMemDao pizzaMemDao = new PizzaMemDao();
		
		//pour mettre fin au programme
		boolean stopP = false;
		
		// affichage du menu 
		displayMenu();
		
		
		String code = null;
		String name = null;
		Double price = null;
		
		System.out.println(CategoriePizzaEnum.AUTRE);
		
		/**
		 * boucle tant pour afficher le menu tant que l'utilisateur ne tape pas 99 
		 * */
		//scan.nextLine();
		while(!stopP) {
			
			System.out.println("----------------------");
			
			// saisie de l'utilisateur
			int value = sc.nextInt();
			
			// affiche la liste des pizzas
			if (value == 1) {
			
				System.out.println("Affichage des pizzas:");
				pizzaMemDao.displayAllPizza();
				displayMenu();
			}
			// ajoute une nouvelle pizza
			else if (value == 2)
			{
				try {
					System.out.println("Ajout d’une nouvelle pizza");
					sc.nextLine();
					System.out.println("Veuillez saisir le code");
					code = sc.nextLine();
					System.out.println("Veuillez saisir le nom (sans espace) :");
					name = sc.nextLine();
					System.out.println("Veuillez saisir le prix:");
					price = Double.valueOf(sc.nextLine());
					
					System.out.println(CategoriePizzaEnum.toStringAll());
					System.out.println("Veuillez saisir la catégorie :");
					String idCategorie = sc.nextLine().toUpperCase();
										
					Pizza newPizza = new Pizza(code, name, price, CategoriePizzaEnum.valueOf(idCategorie));
					
					pizzaMemDao.saveNewPizza(newPizza);
				} 
				catch (SavePizzaException e) {
					System.out.println(e.getMessage());
				}
				finally
				{
					displayMenu();
				}
			}
			// mise a jour d'une pizza	
			else if (value == 3)
			{
				try {
					System.out.println("Mise à jour d’une pizza");
					pizzaMemDao.displayAllPizza();
					sc.nextLine();
					System.out.println("Veuillez saisir le code de la pizza à modifier");
					String oldCode = sc.nextLine();
					System.out.println("Veuillez saisir le nouveau code");
					code = sc.nextLine();
					System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
					name = sc.nextLine();
					System.out.println("Veuillez le nouveau prix:");
					price = Double.valueOf(sc.nextLine());
					System.out.println("Veuillez saisir le ID de la catégorie :\r\n");

					System.out.println(CategoriePizzaEnum.toStringAll());
					String idCategorie = sc.nextLine().toUpperCase();
										
					Pizza newPizza = new Pizza(code, name, price, CategoriePizzaEnum.valueOf(idCategorie));
					
					pizzaMemDao.updatePizza(oldCode, newPizza);
				}
				catch (UpdatePizzaException e) {
					System.out.println(e.getMessage());
				}
				finally
				{
					displayMenu();
				}
			}
			// suppression d'une pizza
			else if (value == 4)
			{
				
				try {
					System.out.println("Suppression d’une pizza");
					pizzaMemDao.displayAllPizza();
					sc.nextLine();
					System.out.println("Veuillez saisir le code de la pizza à supprimer");
					code = sc.nextLine();
					
					pizzaMemDao.deletePizza(code);
				}
				catch (DeletePizzaException e) {
					System.out.println(e.getMessage());
				}
				finally
				{
					displayMenu();
				}
			}
			else if (value == 5)
			{
				System.out.println("Affichage des pizzas par prix croissants");
				List<Pizza> pizzas = pizzaMemDao.findAllPizzas();
				Collections.sort(pizzas, new PizzaPrixCroissantComparator());
				PizzaMemDao.displayAllPizza(pizzas);
				displayMenu();
			}
			else if (value == 6)
			{
				System.out.println("Affichage des pizzas par prix décroissants");
				List<Pizza> pizzas = pizzaMemDao.findAllPizzas();
				Collections.sort(pizzas, new PizzaPrixDecroissantComparator());
				PizzaMemDao.displayAllPizza(pizzas);
				displayMenu();
			}
			// demande de sortie du programme
			else if (value == 99)
			{
				System.out.println("Au revoir");
				
				stopP = true;
			}
			
		}
	}
	
	
	public static void displayMenu() {
		String menu = "\r\n\r\n====================================="
				+ "\r\n\r\n***** Pizzeria Administration *****\r\n" 
				+ "1. Afficher les pizzas\r\n"
				+ "2. Ajouter une nouvelle pizza\r\n" 
				+ "3. Mettre à jour une pizza\r\n" 
				+ "4. Supprimer une pizza\r\n"
				+ "5. Afficher les pizzas par prix croissants\r\n"
				+ "6. Afficher les pizzas par prix décroissants\r\n"
				+ "99. Sortir\r\n"
				+ "=====================================\r\n\r\n";
		
		
		System.out.println(menu);
		
		
		
	}

}
