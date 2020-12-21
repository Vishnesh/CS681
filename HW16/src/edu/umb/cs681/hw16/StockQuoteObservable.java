package edu.umb.cs681.hw16;

public class StockQuoteObservable extends Observable {
	
	public void setQuote() {
		this.setChanged();
	}
}
