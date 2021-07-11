package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	private Airport a1;
	private Airport a2;
	private double dist;
	
	public Rotta(Airport a1, Airport a2, double dist) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.dist = dist;
	}

	public Airport getA1() {
		return a1;
	}

	public void setA1(Airport a1) {
		this.a1 = a1;
	}

	public Airport getA2() {
		return a2;
	}

	public void setA2(Airport a2) {
		this.a2 = a2;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	@Override
	public String toString() {
		return a1.getAirportName() + " - " + a2.getAirportName() + " : " + dist;
	}
	
	

}
