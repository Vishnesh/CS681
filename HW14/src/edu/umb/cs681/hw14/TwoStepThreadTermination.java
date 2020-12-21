package edu.umb.cs681.hw14;

import java.util.ArrayList;

public class TwoStepThreadTermination {
	public static void main(String[] args) {
		ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();
		int tcount = 5;

		ArrayList<Thread> threads = new ArrayList<Thread>(tcount);
		for (int i = 0; i < tcount - 1; i++) {
			Thread t1 = new Thread(new DepositRunnable(bankAccount));
			threads.add(t1);
			t1.start();
		}

		Thread t1 = new Thread(new WithdrawRunnable(bankAccount));
		threads.add(t1);
		t1.start();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("Interuption");

		for (int i = 0; i < tcount; i++) {
			threads.get(i).interrupt();
		}

		bankAccount.setDone();
		System.out.println("Job Done");

		for (int i = 0; i < tcount; i++) {
			try {
				threads.get(i).join();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("End Of Two Step Thread Termination Process");
	}
}
