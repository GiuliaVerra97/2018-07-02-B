package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	int a1;
	int a2;
	double durata;
	public int getA1() {
		return a1;
	}
	public void setA1(int a1) {
		this.a1 = a1;
	}
	public int getA2() {
		return a2;
	}
	public void setA2(int a2) {
		this.a2 = a2;
	}
	public double getDurata() {
		return durata;
	}
	public void setDurata(double durata) {
		this.durata = durata;
	}
	public Rotta(int a1, int a2, double durata) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.durata = durata;
	}
	
	
	
	
}
