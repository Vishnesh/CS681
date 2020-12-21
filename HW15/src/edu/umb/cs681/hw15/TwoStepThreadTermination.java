package edu.umb.cs681.hw15;

public class TwoStepThreadTermination {

	public static void main(String[] args) {
		System.out.println("\nMain: Note that max occupancy is 5");
		AdmissionMonitor monitor = new AdmissionMonitor();

		Runnable exit = () -> {
			System.out.printf("\tMain: Attempting exit on thread %d...\n", Thread.currentThread().getId());
			monitor.exit();
		};
		Runnable enter = () -> {
			System.out.printf("\tMain: Attempting entrance on thread %d...\n", Thread.currentThread().getId());
			monitor.enter();
		};

		enter.run();
		enter.run();
		enter.run();

		System.out.println("\tMain: Next visitor will need to wait");
		Thread t = new Thread(enter);
		t.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		exit.run();
		System.out.printf("\tNow visitor on thread %d can enter\n", t.getId());
		try {
			t.join();
		} catch (InterruptedException e) {
		}

		t = new Thread(enter);
		t.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		;
		System.out.printf("\tMain: Interrupting thread %d\n", t.getId());
		t.interrupt();
		try {
			t.join();
		} catch (InterruptedException e) {
		}
		System.out.println("\t\nMain: Last entrance attempt was interrupted while awaiting an exit");
		exit.run();
		System.out.println("\t\nMain: Nobody was waiting to enter, so we are done");
	}
}
