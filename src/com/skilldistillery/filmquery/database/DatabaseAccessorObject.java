package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	String user = "student";
	String pass = "student";
//	public JDBCTest() throws ClassNotFoundException {
//		Class.forName("com.mysql.jdbc.Driver");
//	}

	@Override
	public Film getFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year," + "language_id,rental_duration,";
			sql += " rental_rate, length, replacement_cost, rating, special_features " + " FROM film  WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
//			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String title = rs.getString(2);
				String desc = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int langId = rs.getInt(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(10);
				List<Actor> actors = getActorsByFilmId(filmId);
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, 						repCost, rating,features, actors);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	
	
	@Override
	public List<Film> getFilmByKeyWord(String keyWord) {
		List<Film> filmList = new ArrayList<>();
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, title, description, release_year," + "language_id,rental_duration,";
			sql += " rental_rate, length, replacement_cost, rating, special_features " + " FROM film  WHERE title OR description Like ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyWord + "%");
//			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String title = rs.getString(2);
				String desc = rs.getString(3);
				int releaseYear = rs.getInt(4);
				int langId = rs.getInt(5);
				int rentDur = rs.getInt(6);
				double rate = rs.getDouble(7);
				int length = rs.getInt(8);
				double repCost = rs.getDouble(9);
				String rating = rs.getString(10);
				String features = rs.getString(10);
				List<Actor> actors = getActorsByFilmId(id);
				film = new Film(id, title, desc, releaseYear, langId, rentDur, rate, length, 						repCost, rating,features, actors);
				filmList.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	@Override
	public Actor getActorById(int actorId) {
		Actor actor = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);
			ResultSet actorResult = stmt.executeQuery();
			if (actorResult.next()) {
				int id = actorResult.getInt(1);
				String first_name = actorResult.getString(2);
				String last_name = actorResult.getString(3);
				actor = new Actor(id, first_name, last_name);

			}
			actorResult.close();
			stmt.close();
			conn.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return actor;

	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		Actor actor = null;

		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT act.id, act.first_name, act.last_name FROM actor act JOIN film_actor fa ON act.id = fa.actor_id JOIN film ON fa.film_id = film.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet actorResult = stmt.executeQuery();
			while (actorResult.next()) {
				int id = actorResult.getInt(1);
				String firstName = actorResult.getString(2);
				String lastName = actorResult.getString(3);
				actor = new Actor(id, firstName, lastName);
				actors.add(actor);
			}
			actorResult.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;

	}
	
	public String getLanguage(int id) {
			String language = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			
			String sql = "SELECT name From language where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet langResult = stmt.executeQuery();
			language = langResult.getString(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return language;
	}
//	If the user looks up a film by id, they are prompted to enter the film id. If the film is not found, they see a message saying so. If the film is found, its title, year, rating, and description are displayed.
	
	

}
