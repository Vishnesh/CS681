package edu.umb.cs681.hw14;

public class WithdrawRunnable implements Runnable {
	private ThreadSafeBankAccount account;

	public WithdrawRunnable(ThreadSafeBankAccount account) {
		this.account = account;
	}

	public void run() {
		while (!account.getDone()) {
			account.withdraw(100);
		}
		System.out.println(Thread.currentThread().getId());
	}
}
