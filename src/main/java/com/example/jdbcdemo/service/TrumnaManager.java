package com.example.jdbcdemo.service;



import java.sql.Connection;				//polaczenie z baza danych sql
import java.sql.DriverManager;
import java.sql.PreparedStatement;  	//obiekt który reprezentuje skomplikowany sql
import java.sql.ResultSet;				//tworzenie tabeli danych przechowującą to co jest w bazie danych
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.example.jdbcdemo.domain.Trumna;
public class TrumnaManager {
	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	
	private String createTableTrumna = "CREATE TABLE Trumna(numer integer, nazwa varchar(20), gatunek_drewna varchar(20))";
	
	private PreparedStatement addTrumna;
	private PreparedStatement dropTrumna;
	private PreparedStatement getTrumna;
	private PreparedStatement selectTrumna;
	private PreparedStatement updateTrumna;
	private PreparedStatement deleteTrumna;
	
	
	
	private Statement statement;
	
	public TrumnaManager (){
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null,null);

			//sprawdzenie czy tabela istnieje
			boolean tableExists = false;
			while (rs.next()) {
				if ("Trumna".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}
			if (!tableExists)
				statement.executeUpdate(createTableTrumna);

			addTrumna = connection.prepareStatement("INSERT INTO Trumna (numer, nazwa, gatunek_drewna) VALUES (?, ?, ?);");
			dropTrumna = connection.prepareStatement("DELETE FROM Trumna;");
			getTrumna = connection.prepareStatement("SELECT * FROM Trumna;");
			selectTrumna = connection.prepareStatement("SELECT * FROM Trumna WHERE numer = (?);");
			updateTrumna = connection.prepareStatement("UPDATE trumna SET nazwa = (?), gatunek_drewna = (?)  WHERE numer = (?);");
			deleteTrumna = connection.prepareStatement("DELETE FROM trumna where numer = (?);");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//polaczenie z tabela
	Connection getConnection() {
		return connection;
	}
	
	
	//drop tabeli
	void clearTrumna() {
		try {
			dropTrumna.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//insert do tabeli
	public void addTrumna(Trumna trumna) {
		
		try {
			addTrumna.setInt(1, trumna.getNumer());
			addTrumna.setString(2, trumna.getNazwa());
			addTrumna.setString(3, trumna.getGatunek_drewna());

			addTrumna.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	//select z tabeli
	public ArrayList<Trumna> getTrumna() {
		ArrayList<Trumna> trumna = new ArrayList<Trumna>();

		try {
			ResultSet rs = getTrumna.executeQuery();

			while (rs.next()) {
				Trumna t = new Trumna();
				t.setNumer(rs.getInt("numer"));
				t.setNazwa(rs.getString("nazwa"));
				t.setGatunek_drewna(rs.getString("gatunek_drewna"));
				trumna.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trumna;
	}
	
	// pobranie pojedynczej wartosci
	public Trumna selectTrumna(int numer) {
		Trumna trumna = new Trumna();

		try {
			selectTrumna.setInt(1, numer);
			ResultSet rs = selectTrumna.executeQuery();

			while (rs.next()) {
				trumna.setNumer(rs.getInt(1));
				trumna.setNazwa(rs.getString(2));
				trumna.setGatunek_drewna(rs.getString(3));
				
				return trumna;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateTrumna(Trumna trumna){
		try {
			updateTrumna.setString(1, trumna.getNazwa());
			updateTrumna.setString(2, trumna.getGatunek_drewna());
			updateTrumna.setInt(3, trumna.getNumer());
			updateTrumna.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteTrumna(Trumna trumna) {
		try {
			deleteTrumna.setInt(1, trumna.getNumer());
			deleteTrumna.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
}
