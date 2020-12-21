package edu.umb.cs681.hw12;

import java.util.concurrent.locks.ReentrantLock;

public class Customer {
	public ReentrantLock lock = new ReentrantLock();
	private Address address;

	public Customer(Address address) {
		this.address = address;
	}

	public Address setAddress(Address address) {
		lock.lock();
		try {
			this.address = address;
		} finally {
			lock.unlock();
		}
		return address;
	}

	public Address getAddress() {
		return address;
	}
}
