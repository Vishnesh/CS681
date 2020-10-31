package edu.umb.cs681.hw01;

public class Test {

	public static void main(String args[]) {

		DJIAQuoteObservable dQObs = new DJIAQuoteObservable();
		dQObs.addObserver((Observable o, Object obj) -> {
			System.out.println("First DJIA Observer has been notified");
		});
		dQObs.changeQuote("Test1", 1);

		StockQuoteObservable sQObs = new StockQuoteObservable();
		sQObs.addObserver((Observable o, Object obj) -> {
			System.out.println("First Stock Observer has been notified.");
		});
		sQObs.changeQuote("Test2", 2);
	}
}