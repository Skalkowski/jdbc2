package com.example.jdbcdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Grabarz;
import com.example.jdbcdemo.domain.Trumna;

public class SumaManagerTest {
	SumaManager sumaManager = new SumaManager();

	private final static String NAZWA_1 = "szczesliwa noc";
	private final static String GATUNEK_1 = "Debowa";
	private final static String NAZWA_2 = "przytulny zakatek";
	private final static String GATUNEK_2 = "sosna";
	private final static String NAZWA_3 = "podziemne zacisze";
	private final static String GATUNEK_3 = "Debowa";

	String[][] trumienka = { { "szczesliwa noc", "Debowa" },
			{ "przytulny zakatek", "sosna" }, { "podziemne zacisze", "Debowa" } };
	String[][] grabarzek = { { "Jan", "Kowalski" }, { "Adam", "Nowak" } };

	@Before
	public void addAll() {
		Grabarz grabarz1 = new Grabarz("Jan", "Kowalski");
		Grabarz grabarz2 = new Grabarz("Adam", "Nowak");
		sumaManager.addGrabarz(grabarz1);
		sumaManager.addGrabarz(grabarz2);
		Trumna trumna1 = new Trumna(NAZWA_1, GATUNEK_1, 0);
		Trumna trumna2 = new Trumna(NAZWA_2, GATUNEK_2, 1);
		Trumna trumna3 = new Trumna(NAZWA_3, GATUNEK_3, 1);
		sumaManager.addTrumna(trumna1);
		sumaManager.addTrumna(trumna2);
		sumaManager.addTrumna(trumna3);
	}

	// sprawdzenie selecta trumny
	@Test
	public void checkAddingTrumna() {

		List<Trumna> trumny = sumaManager.getTrumna();
		int i = 0;
		for (Trumna e : trumny) {
			trumienka[i][0].equals(e.getNazwa());
			assertEquals(trumienka[i][0], e.getNazwa());
			assertEquals(trumienka[i][1], e.getGatunek_drewna());
			++i;
		}
	}

	// sprawdzenie selecta grabarzy
	@Test
	public void checkAddingGrabarz() {
		List<Grabarz> grabarze = sumaManager.getGrabarz();
		int i = 0;
		for (Grabarz e : grabarze) {
			assertEquals(grabarzek[i][0], e.getImie());
			assertEquals(grabarzek[i][1], e.getNazwisko());
			++i;
		}
	}

	// sprawdzenie polaczenia
	@Test
	public void checkConnection() {
		assertNotNull(sumaManager.getConnection());
	}

	// usuwanie tabel po każdym tescie
	@After
	public void after() {
		sumaManager.clearTrumna();
		sumaManager.clearGrabarz();

	}
	
	@Test
	public void selectGrabarz() {
		Grabarz grabarz = new Grabarz();
		grabarz = sumaManager.selectGrabarz("Nowak");
		
		long idTmp = grabarz.getId();
		
		List<Trumna> trumny = sumaManager.getWhereTrumna(idTmp);
		
		//sprawdzenie czy sa 2 trumny
		assertEquals(2, trumny.size());
	}
	
	@Test
	public void deleteTrumna(){
		Grabarz grabarz = new Grabarz();
		grabarz = sumaManager.selectGrabarz("Nowak");
		
		long idTmp = grabarz.getId();
		sumaManager.deleteTrumny(idTmp);
		List<Trumna> trumny = sumaManager.getWhereTrumna(idTmp);
		assertEquals(0, trumny.size());
	}
}
