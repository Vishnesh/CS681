package edu.umb.cs681.hw01;

import java.util.LinkedList;

public abstract class Observable {
	protected boolean chn;
	protected LinkedList<Observer> obs = new LinkedList<Observer>();

	public void addObserver(Observer o) {
		if (!obs.contains(o)) {
			obs.add(o);
		}
	}

	public void deleteObserver(Observer o) {
		if (obs.contains(o)) {
			obs.remove(o);
		}
	}

	protected void setChanged() {
		chn = true;
	}
	
	protected int countObserver() {
		return obs.size();
	}
	
	protected boolean hasChanged() {
		return chn;
	}

	protected void clearChanged() {
		chn = false;
	}

	public void notifyObservers(Object object) {
		if (hasChanged()) {
			obs.forEach((Observer obs) -> obs.update(this, object));
			clearChanged();
		}
	}
}