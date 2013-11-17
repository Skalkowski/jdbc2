package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;





import com.example.jdbcdemo.domain.Trumna;
import com.example.jdbcdemo.domain.Grabarz;

import org.junit.Test;

public class SumaManagerTest {
	SumaManager sumaManager = new SumaManager();
	
	
	
	@Test
	public void checkAdding(){
		
		
		Trumna trumna = new Trumna(2, "cis", "cioas");
		//drop tabeli
	//	sumaManager.clearTrumna();
		
		//create tabeli
		sumaManager.addTrumna(trumna);
		
//		List<Trumna> trumny = sumaManager.getTrumna();
//		Trumna tmp = trumny.get(0);
		
//		assertEquals(NUMER_1, tmp.getNumer());
//		assertEquals(NAZWA_1, tmp.getNazwa());
//		assertEquals(GATUNEK_1, tmp.getGatunek_drewna());
		
	}

	
	//sprawdzenie polaczenia
	@Test
	public void checkConnection(){
		assertNotNull(sumaManager.getConnection());
	}
	
	
	public void deleteAll(){
		sumaManager.clearTrumna();
		sumaManager.clearGrabarz();
	}
	
	
	
}
