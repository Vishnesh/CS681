package edu.umb.cs681.hw01;

public class StockEvent {

	public String t;
	public float q;

	public StockEvent(String t, float q) {
		this.t = t;
		this.q = q;
	}

	public String getTicker() {
		return t;
	}

	public float getQuote() {
		return q;
	}
}