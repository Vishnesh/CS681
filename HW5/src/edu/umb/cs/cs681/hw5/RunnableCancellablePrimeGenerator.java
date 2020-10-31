package edu.umb.cs.cs681.hw5;

import java.util.concurrent.locks.ReentrantLock;

class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
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

    public void generatePrimes() {
        for( long n = from; n <= to; n++ ) {
            lock.lock();
            try {
                boolean done = this.done;
            } finally {
                lock.unlock();
            }
            if(done) {
                System.out.println("Stopped...");
                this.primes.clear();
                break;
            }
            if( isPrime(n) ) {
                this.primes.add(n);
            }
        }
    }

    public void run() {
        generatePrimes();
    }

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 200);
		RunnableCancellablePrimeGenerator gen2 = new RunnableCancellablePrimeGenerator(201, 400);
		Thread t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
		t1.start();
		t2.start();
		try {
            gen1.setDone();
            gen1.setDone();
            gen1.setDone();
			t2.join();
		} catch (InterruptedException e) {}

		gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
        System.out.println();
		gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		
		long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size();
		System.out.println("\n" + primeNum + " prime numbers are found in total.");
        

	}
}
