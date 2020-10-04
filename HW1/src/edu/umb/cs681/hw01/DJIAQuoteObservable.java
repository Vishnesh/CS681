package edu.umb.cs681.hw01;

public class DJIAQuoteObservable extends Observable {
	
	public void changeQuote(float g) {
		notifyObservers(new DJIAEvent(g));
	}

}
