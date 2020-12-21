package edu.umb.cs681.hw17;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class RequestHandler implements Runnable {
	public List<Path> results;
	private boolean done = false;
	public ReentrantLock lock = new ReentrantLock();

	public RequestHandler(List<Path> files) {
		this.results = files;
	}

	public void setDone() {
		done = true;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			try {
				if (done) {
					System.out.println("The Thread has been Stopped");
					break;
				}
				Random random = new Random();
				int RandomElement = random.nextInt(results.size());
				AccessCounter counter = AccessCounter.getInstance();
				System.out.println("File That is been currently processed: " + results.get(RandomElement));
				counter.increment(results.get(RandomElement));
				System.out.println("Access counter value: " + counter.getCount(results.get(RandomElement)));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					continue;
				}
			} finally {
				lock.unlock();
			}
		}
	}

	public static void main(String args[]) {
		List<Path> files = new ArrayList<Path>();
		try {
			files = Files.walk(Paths.get("./test/dummyfiles/")).filter(Files::isRegularFile)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Total number of files " + files.size());

		RequestHandler requestHandler1 = new RequestHandler(files);
		RequestHandler requestHandler2 = new RequestHandler(files);
		RequestHandler requestHandler3 = new RequestHandler(files);
		RequestHandler requestHandler4 = new RequestHandler(files);
		RequestHandler requestHandler5 = new RequestHandler(files);
		RequestHandler requestHandler6 = new RequestHandler(files);
		RequestHandler requestHandler7 = new RequestHandler(files);
		RequestHandler requestHandler8 = new RequestHandler(files);
		RequestHandler requestHandler9 = new RequestHandler(files);
		RequestHandler requestHandler10 = new RequestHandler(files);

		Thread thread1 = new Thread(requestHandler1);
		Thread thread2 = new Thread(requestHandler2);
		Thread thread3 = new Thread(requestHandler3);
		Thread thread4 = new Thread(requestHandler4);
		Thread thread5 = new Thread(requestHandler5);
		Thread thread6 = new Thread(requestHandler6);
		Thread thread7 = new Thread(requestHandler7);
		Thread thread8 = new Thread(requestHandler8);
		Thread thread9 = new Thread(requestHandler9);
		Thread thread10 = new Thread(requestHandler10);

		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		requestHandler1.setDone();
		requestHandler2.setDone();
		requestHandler3.setDone();
		requestHandler4.setDone();
		requestHandler5.setDone();
		requestHandler6.setDone();
		requestHandler7.setDone();
		requestHandler8.setDone();
		requestHandler9.setDone();
		requestHandler10.setDone();

		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
		thread4.interrupt();
		thread5.interrupt();
		thread6.interrupt();
		thread7.interrupt();
		thread8.interrupt();
		thread9.interrupt();
		thread10.interrupt();

		AccessCounter accessCounter = AccessCounter.getInstance();
		System.out.println("Access counter : " + accessCounter.counter);
	}
}
