package edu.umb.cs681.hw12;

public class CustomerAddressTest implements Runnable {
	public void run() {
		Customer customer = new Customer(new Address("1 South Point Drive", "Boston", "MA", 02125));
		System.out.println("Customer's Current Address: " + customer.getAddress());
		customer.setAddress(customer.getAddress().change("375 Mount Vernon Street", "Boston", "MA", 02125));
		System.out.println("Customer's New Address: "+ customer.getAddress());
	}

	public static void main(String[] args) {
		Thread T1 = new Thread(new CustomerAddressTest());
		T1.start();
		try {
			T1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
