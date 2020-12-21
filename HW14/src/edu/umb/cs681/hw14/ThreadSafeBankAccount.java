package edu.umb.cs681.hw14;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount {
	private double balance = 0;
	private ReentrantLock lock;
	private Condition sufficientFundsCondition, belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount account;
	private volatile boolean done = false;

	public void setDone() {
		done = true;
	}

	public boolean getDone() {
		return done;
	}

	public ThreadSafeBankAccount() {
		lock = new ReentrantLock();
		sufficientFundsCondition = lock.newCondition();
		belowUpperLimitFundsCondition = lock.newCondition();
		account = this;
	}

	public void deposit(double amount) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + " Current balance: " + balance);
			while (balance >= 300) {
				System.out.println(Thread.currentThread().getId() + " Limit Exceeded");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() + " (After deposit)New balance: " + balance);
			sufficientFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void withdraw(double amount) {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getId() + " Current balance: " + balance);
			while (balance <= 0) {
				System.out.println(Thread.currentThread().getId() + " Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() + " (After withdraw)New balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
