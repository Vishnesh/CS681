package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();

	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}

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
		while (dividend != 1 && divisor <= to) {
			lock.lock();
			try {
				if (done) {
					break;
				}
				if (divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if (dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} else {
					if (divisor == 2) {
						divisor++;
					} else {
						divisor += 2;
					}
				}
			} finally {
				lock.unlock();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}

	public static void main(String[] args) {
		RunnableCancellableInterruptiblePrimeFactorizer runnableCancellableInterruptiblePrimeFactorizer = new RunnableCancellableInterruptiblePrimeFactorizer(84,
				2, 12);

		Thread thread1 = new Thread(runnableCancellableInterruptiblePrimeFactorizer);

		thread1.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		runnableCancellableInterruptiblePrimeFactorizer.setDone();
		thread1.interrupt();

		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.print("Factors are : ");
		runnableCancellableInterruptiblePrimeFactorizer.getPrimeFactors().forEach((Long l) -> System.out.print(l + ","));

	}
}
