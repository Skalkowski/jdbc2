package com.example.jdbcdemo.domain;

public class Trumna {
	private int numer;
	private String nazwa;
	private String gatunek_drewna;
	private int grabarz;
	
	public Trumna() {
	}
	
/*	public Trumna(int numer, String nazwa, String gatunek_drewna) {
		this.numer = numer;
		this.nazwa = nazwa;
		this.gatunek_drewna = gatunek_drewna;
		
	}
*/
	public Trumna(String gatunek_drewna, String nazwa, int grabarz) {
		this.nazwa = nazwa;
		this.gatunek_drewna = gatunek_drewna;
		this.grabarz = grabarz;
	}
	public int getNumer() {
		return numer;
	}
	public void setNumer(int numer) {
		this.numer = numer;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getGatunek_drewna() {
		return gatunek_drewna;
	}
	public void setGatunek_drewna(String gatunek_drewna) {
		this.gatunek_drewna = gatunek_drewna;
	}

	public int getGrabarz() {
		return grabarz;
	}

	public void setGrabarz(int grabarz) {
		this.grabarz = grabarz;
	}
	
	
}
