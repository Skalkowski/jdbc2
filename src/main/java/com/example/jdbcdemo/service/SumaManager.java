package com.example.jdbcdemo.service;

import java.sql.Connection;				//polaczenie z baza danych sql
import java.sql.DriverManager;
import java.sql.PreparedStatement;  	//obiekt który reprezentuje skomplikowany sql
import java.sql.ResultSet;				//tworzenie tabeli danych przechowującą to co jest w bazie danych
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;







import com.example.jdbcdemo.domain.Trumna;
public class SumaManager {
	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";
	private boolean TrumnaTableExists = false;
	private boolean GrabarzTableExists = false;
	
	private String createTableTrumna = "CREATE TABLE Trumna(id bigint IDENTITY, nazwa varchar(20), gatunek_drewna varchar(20))";
	private String createTableGrabarz = "CREATE TABLE Grabarz(id bigint IDENTITY, imie varchar(20), nazwisko varchar(20))";
	
	private PreparedStatement addTrumna;
	private PreparedStatement addGrabarz;
	private PreparedStatement dropTrumna;
	private PreparedStatement dropGrabarz;

	
	
	
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
			if (!TrumnaTableExists){
				statement.executeUpdate(createTableTrumna);
			}
			if (!GrabarzTableExists){
				statement.executeUpdate(createTableGrabarz);
			}
			
			addTrumna = connection.prepareStatement("INSERT INTO Trumna (nazwa, gatunek_drewna) VALUES (?, ?);");
			addGrabarz = connection.prepareStatement("INSERT INTO Trumna (imie, nazwisko) VALUES (?, ?);");
			dropTrumna = connection.prepareStatement("DELETE FROM Trumna;");
			dropGrabarz = connection.prepareStatement("DELETE FROM Trumna;");
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
			addTrumna.setInt(1, trumna.getNumer());
			addTrumna.setString(2, trumna.getNazwa());
			addTrumna.setString(3, trumna.getGatunek_drewna());

			addTrumna.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
