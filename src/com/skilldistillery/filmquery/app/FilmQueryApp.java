package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
	boolean proceed = false;
	DatabaseAccessor db = new DatabaseAccessorObject();
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.test();
//    app.launch();
	}

	private void test() {
		List<Film> film = db.getFilmByKeyWord("th");
		System.out.println(film);
	}

	private void launch() {

		System.out.println("************************************");
		System.out.println("*************Welcome**To************");
		System.out.println("************************************");
		System.out.println("*************Best Movies************");
		System.out.println("************************************");
		System.out.println("************Movie Rentals***********");
		System.out.println("************************************");
		primaryMenu();
		

		input.close();
	}

	private void primaryMenu() {
		int select = input.nextInt();
		do {
		System.out.println("\n\nWhat would you like to do?: ");
		System.out.println("1: Look up a film by its id");
		System.out.println("2: Look up a film by a search Keyword");
		System.out.println("3: Exit the application");
		if (select != 1 || select != 2 || select != 3) {
			System.out.println("That is not a valid selection please try again");
		}
		}while (select != 1 || select != 2 || select != 3);
		
		
		switch (select) {
		case 1:
			filmIdLookUp();
			break;
		case 2:
			
			break;
		case 3:
			System.out.println("Goodbye");
			System.exit(0);
		}
		
		
	}
	private void filmIdLookUp() {
		System.out.printl("Please enter your film's ID: ");
		int filmId = input.nextInt();
		db.getFilmById(filmId);
		
	}
	private void filmKeyWordLookUp() {
		
	}
	private void filmIdLookU() {
		
	}
	
}

//		  startUserInterface(input);
//  private void startUserInterface(Scanner input) {
//	  
//	  }