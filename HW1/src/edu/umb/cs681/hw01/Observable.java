package edu.umb.cs681.hw01;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable {
    private List<Observer> observers = new LinkedList<Observer>();

    private boolean changed = false;
    
    public void addObserver(Observer o) {
        observers.add(o);
    }
      
    public void deleteObserver(Observer o) {
        observers.remove(o);
    }
    
    protected void setChanged() {
        changed = true;
    }
    
    protected void clearChanged() {
        changed = false;
    }
    
    public boolean hasChanged() {
        return changed;
    }
      
    public void notifyObservers(Object event) {
        if (hasChanged()) {
            for (Observer o : observers) {
                o.update(this, event);
            }
            clearChanged();
        }
    }
}
