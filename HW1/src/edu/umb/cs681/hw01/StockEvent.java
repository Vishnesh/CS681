package edu.umb.cs681.hw01;

public class StockEvent {

	private String ticker; 
	private float quote;
	
	StockEvent() {
		System.out.println("StockEvent created");
	}
	
	StockEvent(String ticker, float quote) {
		System.out.println("StockEvent created");
		this.ticker = ticker;
		this.quote = quote;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public float getQuote() {
		return quote;
	}
}
