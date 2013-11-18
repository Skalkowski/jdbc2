package com.example.jdbcdemo.service;

import java.sql.Connection; //polaczenie z baza danych sql
import java.sql.DriverManager;
import java.sql.PreparedStatement; //obiekt który reprezentuje skomplikowany sql
import java.sql.ResultSet; //tworzenie tabeli danych przechowującą to co jest w bazie danych
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.jdbcdemo.domain.Grabarz;
import com.example.jdbcdemo.domain.Trumna;

public class SumaManager {
	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private boolean TrumnaTableExists = false;
	private boolean GrabarzTableExists = false;

	private String createTableTrumna = "CREATE TABLE Trumna(id bigint IDENTITY, nazwa varchar(20), gatunek_drewna varchar(20), grabarz bigint REFERENCES grabarz(id_grabarz) null)";
	private String createTableGrabarz = "CREATE TABLE Grabarz(id_grabarz bigint IDENTITY, imie varchar(20), nazwisko varchar(20))";

	private PreparedStatement addTrumna;
	private PreparedStatement addGrabarz;
	private PreparedStatement dropTrumna;
	private PreparedStatement dropGrabarz;
	private PreparedStatement getTrumna;
	private PreparedStatement getGrabarz;
	private PreparedStatement selectTrumna;
	private PreparedStatement selectGrabarz;
	private PreparedStatement getWhereTrumna;
	private PreparedStatement deleteTrumna;

	private Statement statement;

	public SumaManager() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);

			// sprawdzenie czy tabela istnieje

			while (rs.next()) {
				if ("Trumna".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					TrumnaTableExists = true;

				} else if ("Grabarz".equalsIgnoreCase(rs
						.getString("TABLE_NAME"))) {
					GrabarzTableExists = true;

				}
			}
			if (!GrabarzTableExists)
				statement.executeUpdate(createTableGrabarz);

			if (!TrumnaTableExists)
				statement.executeUpdate(createTableTrumna);

			addTrumna = connection
					.prepareStatement("INSERT INTO Trumna (nazwa, gatunek_drewna, grabarz) VALUES (?, ?, ?);");
			addGrabarz = connection
					.prepareStatement("INSERT INTO Grabarz (imie, nazwisko) VALUES (?, ?);");
			dropTrumna = connection.prepareStatement("DROP TABLE Trumna;");
			dropGrabarz = connection.prepareStatement("DROP TABLE Grabarz;");
			getTrumna = connection.prepareStatement("SELECT * FROM Trumna;");
			getGrabarz = connection.prepareStatement("SELECT * FROM Grabarz;");
			getWhereTrumna = connection.prepareStatement("SELECT * FROM Trumna WHERE grabarz = (?);");
			selectTrumna = connection.prepareStatement("SELECT * FROM Trumna WHERE nazwa = (?);");
			selectGrabarz = connection.prepareStatement("SELECT * FROM Grabarz WHERE nazwisko = (?);");
			deleteTrumna = connection.prepareStatement("DELETE FROM Trumna WHERE grabarz = (?);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// polaczenie z tabela
	Connection getConnection() {
		return connection;
	}

	// drop tabeli trumna
	void clearTrumna() {
		try {
			dropTrumna.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// drop tabeli grabarz
	void clearGrabarz() {
		try {
			dropGrabarz.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// dodanie trumny
	public void addTrumna(Trumna trumna) {

		try {
			addTrumna.setString(1, trumna.getNazwa());
			addTrumna.setString(2, trumna.getGatunek_drewna());
			addTrumna.setInt(3, trumna.getGrabarz());
			addTrumna.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// dodagnie grabarza
	public void addGrabarz(Grabarz grabarz) {

		try {
			addGrabarz.setString(1, grabarz.getImie());
			addGrabarz.setString(2, grabarz.getNazwisko());

			addGrabarz.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Select calkowity z tabeli trumna
	public ArrayList<Trumna> getTrumna() {
		ArrayList<Trumna> trumna = new ArrayList<Trumna>();

		try {
			ResultSet rs = getTrumna.executeQuery();

			while (rs.next()) {
				Trumna t = new Trumna();
				t.setNumer(rs.getInt("id"));
				t.setNazwa(rs.getString("nazwa"));
				t.setGatunek_drewna(rs.getString("gatunek_drewna"));
				trumna.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trumna;
	}

	// select calkowity z tabeli grabarz
	public ArrayList<Grabarz> getGrabarz() {
		ArrayList<Grabarz> grabarz = new ArrayList<Grabarz>();

		try {
			ResultSet rs = getGrabarz.executeQuery();

			while (rs.next()) {
				Grabarz g = new Grabarz();
				g.setId(rs.getLong("id_grabarz"));
				g.setImie(rs.getString("imie"));
				g.setNazwisko(rs.getString("nazwisko"));
				grabarz.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return grabarz;
	}

	// pobranie pojedynczej trumny
	public ArrayList<Trumna> getWhereTrumna(Long id_grabarz) {
		ArrayList<Trumna> trumna = new ArrayList<Trumna>();

		try {
			getWhereTrumna.setLong(1, id_grabarz);
			ResultSet rs = getWhereTrumna.executeQuery();

			while (rs.next()) {
				Trumna t = new Trumna();
				t.setNumer(rs.getInt(1));
				t.setNazwa(rs.getString(2));
				t.setGatunek_drewna(rs.getString(3));
				t.setGrabarz(rs.getInt(4));
				trumna.add(t);
				
			}
			return trumna;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// pobranie pojedynczej grabarza
	public Grabarz selectGrabarz(String nazwisko) {
		Grabarz grabarz = new Grabarz();

		try {
			selectGrabarz.setString(1, nazwisko);
			ResultSet rs = selectGrabarz.executeQuery();

			while (rs.next()) {
				grabarz.setId(rs.getLong(1));
				grabarz.setImie(rs.getString(2));
				grabarz.setNazwisko(rs.getString(3));
			
				return grabarz;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void deleteTrumny(long grabarz){
		
		try {
			deleteTrumna.setLong(1, grabarz);
			
			deleteTrumna.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
