package com.example.jdbcdemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.example.jdbcdemo.domain.Trumna;
import com.example.jdbcdemo.domain.Grabarz;

public class SumaManagerTest {
	SumaManager sumaManager = new SumaManager();
	
	private final static String NAZWA_1 = "szczęśliwa noc";
	private final static String GATUNEK_1 = "Dębowa";
	private final static String NAZWA_2 = "przytulny zakatek";
	private final static String GATUNEK_2 = "sosna";
	
	
	
	@Test
	public void before(){
		
		
       
	}
	
	
	@Test
	public void checkAddingTrumna(){
		 Grabarz grabarz1 = new Grabarz("Jan", "Kowalski");
		 Grabarz grabarz2 = new Grabarz("Adam", "Nowak");
	        sumaManager.addGrabarz(grabarz1);
	        sumaManager.addGrabarz(grabarz2);
	    Trumna trumna1 = new Trumna(NAZWA_1, GATUNEK_1, 0);
        Trumna trumna2 = new Trumna(NAZWA_2, GATUNEK_2, 1);
        sumaManager.addTrumna(trumna1);
        sumaManager.addTrumna(trumna2);
		
		
		
		List<Trumna> trumny = sumaManager.getTrumna();
		
		
		for (Trumna e : trumny) {
		
		
		
		
		
		assertEquals(sumaManager.nazwaToString(1), e.getNazwa());
		assertEquals(sumaManager.gatunekToString(1), e.getGatunek_drewna());
		assertEquals(sumaManager.nazwaToString(2), e.getNazwa());
		assertEquals(sumaManager.gatunekToString(2), e.getGatunek_drewna());
		System.out.println("cos");
		}
	}

	
	//sprawdzenie polaczenia
	@Test
	public void checkConnection(){
		assertNotNull(sumaManager.getConnection());
	}
	
	
	
	
	
	

	
	
	
	
	
}
