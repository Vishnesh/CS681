package edu.umb.cs.cs681.hw6;

import java.util.concurrent.locks.ReentrantLock;

class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}

	private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
            lock.lock();
            try {
                if (done)
                    return;
            } finally {
                lock.unlock();
            }
	    	if( divisor > 2 && isEven(divisor)) {
	    		divisor++;
	    		continue;
	    	}
		    if(dividend % divisor == 0) {
		        factors.add(divisor);
		        dividend /= divisor;
		    }else {
		    	if(divisor==2){ divisor++; }
		    	else{ divisor += 2; }
		    	
		    }
		}
	}
	
}
