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
	
	@Before
	public void before(){
		Trumna trumna1 = new Trumna("cos", "cos", 0);
        Trumna trumna2 = new Trumna("cos", "cos2", 1);
        sumaManager.addTrumna(trumna1);
        sumaManager.addTrumna(trumna2);
        Grabarz grabarz1 = new Grabarz("Jan", "Kowalski");
        Grabarz grabarz2 = new Grabarz("Adam", "Nowak");
        sumaManager.addGrabarz(grabarz1);
        sumaManager.addGrabarz(grabarz2);
	}
	
	
	@Test
	public void checkAdding(){
		
		
		Trumna trumna = new Trumna("cis", "cioas", 1);
		
		sumaManager.addTrumna(trumna);
		
		List<Trumna> trumny = sumaManager.getTrumna();
		Trumna tmp = trumny.get(0);
		
		
//		assertEquals("cos", tmp.getNazwa());
//		assertEquals("cos2", tmp.getGatunek_drewna());
		
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
