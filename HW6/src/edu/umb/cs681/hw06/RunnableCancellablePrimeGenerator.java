package edu.umb.cs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();

	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void setDone() {
		lock.lock();
		try {
			done = false;
		} finally {
			lock.unlock();
		}
	}

	public void generatePrimes() {
		for (long number = from; number <= to; number++) {
			lock.lock();
			try {
				if (done) {	break;}
				if (isPrime(number)) { this.primes.add(number);}
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator runnableCancellablePrimeGenerator = new RunnableCancellablePrimeGenerator(1, 200);
		Thread t1 = new Thread(runnableCancellablePrimeGenerator);
		t1.start();
		runnableCancellablePrimeGenerator.setDone();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		runnableCancellablePrimeGenerator.getPrimes().forEach((Long prime) -> System.out.print(prime + ","));
		System.out.println("\nTotal number of Prime Numbers : " + runnableCancellablePrimeGenerator.getPrimes().size());
	}
}
