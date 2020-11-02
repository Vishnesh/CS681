package edu.umb.cs681.hw05;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		// Single thread
		RunnablePrimeGenerator runnablePrimeGenerator1 = new RunnablePrimeGenerator(1L, 2000000L);
		Thread thread1 = new Thread(runnablePrimeGenerator1);

		long startTime = System.currentTimeMillis();
		thread1.start();

		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		float time1 = (endTime - startTime) / 1000F;
		long primeNum = runnablePrimeGenerator1.getPrimes().size();

		System.out.println("Total number of prime numbers found with Single thread: " + primeNum);
		System.out.println("Time taken for a Single thread : " + time1 + " secs");
		System.out.println("");
		
		// Double thread
		RunnablePrimeGenerator runnablePrimeGenerator21 = new RunnablePrimeGenerator(1L, 1000000L);
		RunnablePrimeGenerator runnablePrimeGenerator22 = new RunnablePrimeGenerator(1000000L, 2000000L);
		Thread thread21 = new Thread(runnablePrimeGenerator21);
		Thread thread22 = new Thread(runnablePrimeGenerator22);

		startTime = System.currentTimeMillis();
		thread21.start();
		thread22.start();

		try {
			thread21.join();
			thread22.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		endTime = System.currentTimeMillis();
		float time2 = (endTime - startTime) / 1000F;

		long primeNumWithTwoThreads = runnablePrimeGenerator21.getPrimes().size()
				+ runnablePrimeGenerator22.getPrimes().size();
		System.out.println("Total number of prime numbers found with Two threads : " + primeNumWithTwoThreads);
		System.out.println("Time taken for Two threads : " + time2 + " secs");
		System.out.println("");
		
		// 4 threads
		RunnablePrimeGenerator runnablePrimeGenerator41 = new RunnablePrimeGenerator(1L, 500000L);
		RunnablePrimeGenerator runnablePrimeGenerator42 = new RunnablePrimeGenerator(500000L, 1000000L);
		RunnablePrimeGenerator runnablePrimeGenerator43 = new RunnablePrimeGenerator(1000000L, 1500000L);
		RunnablePrimeGenerator runnablePrimeGenerator44 = new RunnablePrimeGenerator(1500000L, 2000000L);

		Thread thread41 = new Thread(runnablePrimeGenerator41);
		Thread thread42 = new Thread(runnablePrimeGenerator42);
		Thread thread43 = new Thread(runnablePrimeGenerator43);
		Thread thread44 = new Thread(runnablePrimeGenerator44);

		startTime = System.currentTimeMillis();
		thread41.start();
		thread42.start();
		thread43.start();
		thread44.start();

		try {
			thread41.join();
			thread42.join();
			thread43.join();
			thread44.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		endTime = System.currentTimeMillis();
		float time4 = (endTime - startTime) / 1000F;

		long primeNumWithFourThreads = runnablePrimeGenerator41.getPrimes().size()
				+ runnablePrimeGenerator42.getPrimes().size() + runnablePrimeGenerator43.getPrimes().size()
				+ runnablePrimeGenerator44.getPrimes().size();
		System.out.println("Total number of prime numbers found with four Threads: " + primeNumWithFourThreads);
		System.out.println("Time taken for Four threads : " + time4 + " secs");
	}
}