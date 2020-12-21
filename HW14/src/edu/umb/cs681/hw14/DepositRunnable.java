package edu.umb.cs681.hw14;

public class DepositRunnable implements Runnable {
	private ThreadSafeBankAccount account;

	public DepositRunnable(ThreadSafeBankAccount account) {
		this.account = account;
	}

	public void run() {
		while (!account.getDone()) {
			account.deposit(100);
		}
		System.out.println(Thread.currentThread().getId());
	}
}
