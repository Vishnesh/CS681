package edu.umb.cs681.hw11;

public class ConcurrentSingletonRunnable implements Runnable {
	public void run() {
		try {
			for (int i = 0; i < 2; i++) {
				System.out.println(ConcurrentSingleton.getInstance());
				Thread.sleep(2);
			}
		} catch (InterruptedException ex) {
			System.out.println(ex);
		}
	}

	public static void main(String[] args) {
		new Thread(new ConcurrentSingletonRunnable()).start();
	}
}
