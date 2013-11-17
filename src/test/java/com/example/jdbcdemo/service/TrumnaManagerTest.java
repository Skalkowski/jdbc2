package com.example.jdbcdemo.service;



import static org.junit.Assert.*;

import java.util.List;


import org.junit.Test;

import com.example.jdbcdemo.domain.Trumna;


public class TrumnaManagerTest {
	TrumnaManager trumnaManager = new TrumnaManager();
	
	private final static int NUMER_1 = 0001;
	private final static String NAZWA_1 = "szczęśliwa noc";
	private final static String GATUNEK_1 = "Dębowa";
	
	
	//sprawdzenie polaczenia
	@Test
	public void checkConnection(){
		assertNotNull(trumnaManager.getConnection());
	}
	
	//sprawdzenie dodania
	@Test
	public void checkAdding(){
		
		Trumna trumna = new Trumna(NUMER_1, NAZWA_1, GATUNEK_1);
		Trumna trumna2 = new Trumna(2, "cis", "cioas");
		//drop tabeli
		trumnaManager.clearTrumna();
		
		//create tabeli
		trumnaManager.addTrumna(trumna);
		trumnaManager.addTrumna(trumna2);
		
		List<Trumna> trumny = trumnaManager.getTrumna();
		Trumna tmp = trumny.get(0);
		
		assertEquals(NUMER_1, tmp.getNumer());
		assertEquals(NAZWA_1, tmp.getNazwa());
		assertEquals(GATUNEK_1, tmp.getGatunek_drewna());
		
	}

	//sprawdzenie pojedynczego dodania
	
	@Test
	public void checkUpdate() {
		Trumna trumna = new Trumna(1, "cos", "mksao");

		trumnaManager.updateTrumna(trumna);

		Trumna tmp = trumnaManager.selectTrumna(trumna.getNumer());
		assertEquals(trumna.getNumer(), tmp.getNumer());
		assertEquals(trumna.getNazwa(), tmp.getNazwa());
		assertEquals(trumna.getGatunek_drewna(), tmp.getGatunek_drewna());
		
	}
	
	@Test
	public void deleteTrumna() {
		Trumna trumna = new Trumna();
		int numer;
		trumna = trumnaManager.selectTrumna(1);
		numer = trumna.getNumer();
		trumnaManager.deleteTrumna(trumna);

		assertNull(trumnaManager.selectTrumna(numer));
	}
	
	
}
