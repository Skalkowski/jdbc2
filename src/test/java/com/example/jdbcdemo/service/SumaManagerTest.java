package com.example.jdbcdemo.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SumaManagerTest {
	SumaManager sumaManager = new SumaManager();
	
	//sprawdzenie polaczenia
	@Test
	public void checkConnection(){
		assertNotNull(sumaManager.getConnection());
	}
	
	@Test
	public void deleteAll(){
		sumaManager.clearTrumna();
		sumaManager.clearGrabarz();
	}
}
