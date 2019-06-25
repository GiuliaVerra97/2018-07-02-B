package it.polito.tdp.extflightdelays.model;

import java.util.Comparator;

public class Ordina implements Comparable<Ordina> {
	
	private Airport a;
	private int durata;		//peso
	



	public Ordina(Airport a, int durata) {
		super();
		this.a = a;
		this.durata = durata;
	}




	public Airport getA() {
		return a;
	}




	public void setA(Airport a) {
		this.a = a;
	}




	public int getDurata() {
		return durata;
	}




	public void setDurata(int durata) {
		this.durata = durata;
	}




	




	@Override
	public int compareTo(Ordina o2) {
		return -(this.durata-o2.getDurata());
	}
	

}
