package edu.umb.cs681.hw18;

public class StockQuoteObservable extends Observable {
	
	public void setQuote() {
		this.setChanged();
	}
}
