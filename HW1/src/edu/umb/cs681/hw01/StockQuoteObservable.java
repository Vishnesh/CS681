package edu.umb.cs681.hw01;

public class StockQuoteObservable extends Observable {
	
    public void changeQuote(String ticker, float g) {
        setChanged();
        notifyObservers(new StockEvent(ticker, g));
    }
    
    public static void main(String args[]) {
        StockQuoteObservable observable = new StockQuoteObservable();
        observable.addObserver((source, event) -> {
                StockEvent ev = (StockEvent) event;
                System.out.println("Received new ticker: " + ev.getTicker() + " - " + Float.toString(ev.getQuote()));
        });
        observable.changeQuote("TEST", 10);
        observable.changeQuote("TEST", 11);
        observable.changeQuote("TEST", 5);
    }

}
