package edu.umb.cs.cs681.hw9;

import java.util.concurrent.locks.ReentrantLock;

class RunnableInterruptiblePrimeGenerator extends RunnablePrimeGenerator {
    public RunnableInterruptiblePrimeGenerator(long from, long to) {
        super(from, to);
    }

    private ReentrantLock lock = new ReentrantLock();

    public ReentrantLock getLock() {
        return lock;
    }

    public void generatePrimes() {
        for( long n = from; n <= to; n++ ) {
            lock.lock();
            if(Thread.interrupted()) {
                System.out.println("Stopped...");
                this.primes.clear();
                break;
            }
            lock.unlock();
            if( isPrime(n) ) {
                this.primes.add(n);
            }
        }
    }

    public void run() {
        generatePrimes();
    }

	public static void main(String[] args) {
        RunnableInterruptiblePrimeGenerator gen = new RunnableInterruptiblePrimeGenerator(1, 200);
        Thread aThread = new Thread(gen);
        aThread.start();

        gen.getLock().lock();
        aThread.interrupt();
        gen.getLock().unlock();
	}
}
