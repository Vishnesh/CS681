package edu.umb.cs681.hw17;

import java.util.Map;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AccessCounter {

	public Map<Path, AtomicInteger> counter = new ConcurrentHashMap<>();

	private AccessCounter() {
	};

	private static AccessCounter instance = null;

	public static AccessCounter getInstance() {
		if (instance == null) {
			instance = new AccessCounter();
		}
		return instance;
	}

	public void increment(Path file) {
		counter.computeIfAbsent(file, (Path k) -> {
			return new AtomicInteger(1);
		});
		counter.putIfAbsent(file, new AtomicInteger(1));
	}

	public int getCount(Path file) {
		return counter.get(file).incrementAndGet();
	}
}