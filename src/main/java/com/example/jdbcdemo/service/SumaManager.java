package com.example.jdbcdemo.service;

import java.sql.Connection;				//polaczenie z baza danych sql
import java.sql.DriverManager;
import java.sql.PreparedStatement;  	//obiekt który reprezentuje skomplikowany sql
import java.sql.ResultSet;				//tworzenie tabeli danych przechowującą to co jest w bazie danych
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
	
	private String createTableTrumna = "CREATE TABLE Trumna(id bigint IDENTITY, nazwa varchar(20), gatunek_drewna varchar(20), grabarz int REFERENCES grabarz(id_grabarz) null)";
	private String createTableGrabarz = "CREATE TABLE Grabarz(id_grabarz bigint IDENTITY, imie varchar(20), nazwisko varchar(20))";
	
	private PreparedStatement addTrumna;
	private PreparedStatement addGrabarz;
	private PreparedStatement dropTrumna;
	private PreparedStatement dropGrabarz;
	private PreparedStatement getTrumna;

	
	
	private Statement statement;
	
	
	
	public SumaManager(){
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			
			ResultSet rs = connection.getMetaData().getTables(null, null, null,null);

			//sprawdzenie czy tabela istnieje
			
			while (rs.next()) {
				if ("Trumna".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					TrumnaTableExists = true;
					
				}
				else if ("Grabarz".equalsIgnoreCase(rs.getString("TABLE_NAME"))){
					GrabarzTableExists = true;
					
					
				}
			}
			if (!GrabarzTableExists)
				statement.executeUpdate(createTableGrabarz);
			
			if (!TrumnaTableExists)
				statement.executeUpdate(createTableTrumna);
				
			
		
				
			
			
			addTrumna = connection.prepareStatement("INSERT INTO Trumna (nazwa, gatunek_drewna, grabarz) VALUES (?, ?, ?);");
			addGrabarz = connection.prepareStatement("INSERT INTO Grabarz (imie, nazwisko) VALUES (?, ?);");
			dropTrumna = connection.prepareStatement("DROP TABLE Trumna;");
			dropGrabarz = connection.prepareStatement("DROP TABLE Grabarz;");
			getTrumna = connection.prepareStatement("SELECT * FROM Trumna;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//polaczenie z tabela
	Connection getConnection() {
		return connection;
	}
	
	//drop tabeli trumna
	void clearTrumna() {
		try {
			dropTrumna.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void clearGrabarz() {
		try {
			dropGrabarz.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
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
	
	public void addGrabarz(Grabarz grabarz) {
		
		try {
			addGrabarz.setString(1, grabarz.getImie());
			addGrabarz.setString(2, grabarz.getNazwisko());

			addGrabarz.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
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

	
	public String nazwaToString(int nr){
		return "NAZWA_"+nr;
	}
	public String gatunekToString(int nr){
		return "GATUNEK_"+nr;
	}
	
}
