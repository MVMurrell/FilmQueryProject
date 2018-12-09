package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
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
		Film film = db.getFilmById(6);
		System.out.println(film);
	}

	private void launch() {

		System.out.println("************************************");
		System.out.println("*************Welcome  To************");
		System.out.println("************************************");
		System.out.println("**********Hollywood Movies**********");
		System.out.println("************************************");
		System.out.println("************Movie Rentals***********");
		System.out.println("************************************");
		primaryMenu();

		input.close();
	}

	private void primaryMenu() {
		int select;
		do {
			proceed = true;
			System.out.println("\n\nWhat would you like to do?: ");
			System.out.println("1: Look up a film by its ID");
			System.out.println("2: Look up a film by a search Keyword");
			System.out.println("3: Exit the application");
			select = input.nextInt();
			if (select != 1 || select != 2 || select != 3) {
				System.out.println("That is not a valid selection please try again");
				proceed = false;
			}
		} while (proceed == false);

		switch (select) {
		case 1:
			filmIdLookUp();
			break;
		case 2:
			filmKeyWordLookUp();
			break;
		case 3:
			System.out.println("Goodbye");
			System.exit(0);
		}

	}

	private void filmIdLookUp() {
		int select;
		System.out.print("Please enter your film's ID: ");
		int filmId = input.nextInt();
		Film film = db.getFilmById(filmId);
		if (film != null) {
			System.out.println(film.getTitle() + " Year: " + film.getReleaseYear() + " Rating: " + film.getRating()
					+ "\nDescription: " + film.getDescription());
			System.out.println("What would you like to do next");
			do {
				proceed = true;
				System.out.println("1: Return to main Menu");
				System.out.println("2: View Film Details");
				System.out.print("Selection: ");
				select = input.nextInt();
				if (select != 1 || select != 2) {
					System.out.println("That is not a choice, try again");
					proceed = false;
				}
			} while (proceed == false);
			switch (select) {
			case 1:
				primaryMenu();
				break;
			case 2:
			System.out.println(film);
			primaryMenu();
				break;
			}
		}
	}

	private void filmKeyWordLookUp() {
		int select;
		List<Film> films = new ArrayList<>();
		System.out.print("\nPlease enter the keyword you would like t search by: ");
		String keyword = input.nextLine();
		films = db.getFilmByKeyWord(keyword);
		if (films != null) {
			for (Film film : films) {
			System.out.println(film.getTitle() + " Year: " + film.getReleaseYear() + " Rating: " + film.getRating()
					+ "\nDescription: " + film.getDescription());
			}
			primaryMenu();
		}

	}

	private void filmIdLookU() {

	}

}

//		  startUserInterface(input);
//  private void startUserInterface(Scanner input) {
//	  
//	  }