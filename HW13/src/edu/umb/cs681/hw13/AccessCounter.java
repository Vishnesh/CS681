package edu.umb.cs681.hw13;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.nio.file.Path;

public class AccessCounter {

	public Map<Path, Integer> counter = new HashMap<>();
	public ReentrantLock lock = new ReentrantLock();
	public static ReentrantLock staticlock = new ReentrantLock();
	private static AccessCounter instance = null;
	
	private AccessCounter() {
	};

	public static AccessCounter getInstance() {
		staticlock.lock();
		try {
			if (instance == null) {
				instance = new AccessCounter();
			}
			return instance;
		} finally {
			staticlock.unlock();
		}
	}

	public void increment(Path file) {
		lock.lock();
		try {
			if (counter.containsKey(file)) {
				Integer count = counter.get(file);
				counter.put(file, count + 1);
			} else {
				counter.put(file, 1);
			}
		} finally {
			lock.unlock();
		}
	}

	public int getCount(Path file) {
		lock.lock();
		int value = 0;
		try {
			value = counter.get(file);
		} finally {
			lock.unlock();
		}
		return value;
	}
}