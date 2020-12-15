package edu.umb.cs681.hw11;

public class ConcurrentSingletonRunnable implements Runnable {
	public void run() {
		System.out.println(ConcurrentSingleton.getInstance());
	}

	public static void main(String[] args) {
		new Thread(new ConcurrentSingletonRunnable()).start();
	}
}
