package com.example.jdbcdemo.domain;

public class Grabarz {
	private Long id;
	private String imie;
	private String nazwisko;
	
	public Grabarz() {
	
	}
	
	public Grabarz(Long id, String imie, String nazwisko) {
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

}
