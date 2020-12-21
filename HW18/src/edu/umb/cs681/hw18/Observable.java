package edu.umb.cs681.hw18;

import java.util.concurrent.atomic.AtomicBoolean;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Observable {

	private ConcurrentLinkedQueue<Observer> observers;

	private AtomicBoolean changed;

	public Observable() {
		observers = new ConcurrentLinkedQueue<Observer>();
		changed = new AtomicBoolean();
	}

	public void addObserver(Observer o) {
		if (o == null)
			throw new NullPointerException();
		observers.add(o);
	}

	public void deleteObserver(Observer o) {
		if (observers.remove(o)) {
			System.out.println("Observer has removed");
		} else {
			System.out.println("This observer is not existed");
		}
	}

	public void deleteObservers() {
		observers.clear();
	}

	public int countObservers() {
		return observers.size();
	}

	protected void setChanged() {
		changed.set(true);
	}

	protected void clearChanged() {
		changed.set(false);
	}

	public boolean hasChanged() {
		return changed.get();
	}

	public void notifyObservers() {
		notifyObservers(null);
	}

	public void notifyObservers(Object obj) {
		if (!changed.get()) {
			return;
		}
		observers.forEach(n -> n.update(this, obj));
		clearChanged();
	}
}