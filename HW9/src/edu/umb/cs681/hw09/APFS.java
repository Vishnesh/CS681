package edu.umb.cs681.hw09;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem implements Runnable {

	private String ownerName;
	private LocalDateTime lastModified;
	private static APFS instance = null;

	public APFS(String ownerName) {
		this.ownerName = ownerName;
		this.lastModified = LocalDateTime.now();
	}

	public APFS getInstance() {
		if (instance == null) {
			instance = new APFS(ownerName);
		}
		return instance;
	}

	@Override
	protected FSElement createDefaultRoot() {
		return new ApfsDirectory(null, "root");
	}

	public ApfsDirectory getRootDir() {
		ApfsDirectory root = (ApfsDirectory) this.getRoot();
		return root;
	}

	public void setRootDir(ApfsDirectory root) {
		super.setRoot(root);
	}

	public String getOwnerName() {
		return this.ownerName;
	}

	public LocalDateTime getLastModified() {
		return this.lastModified;
	}

	public void run() {
		System.out.println("\nThread #" + Thread.currentThread().getId());
		System.out.println("Size of " + getRootDir().getName() + " dir: " + getRootDir().getTotalSize());
		LinkedList<ApfsElement> rootChildren = getRootDir().getChildren();
		for (ApfsElement child : rootChildren) {
			System.out.println("\n--->" + child.getName());
			LinkedList<ApfsElement> child1Children = child.getChildren();
			for (ApfsElement child1 : child1Children) {
				System.out.println("--->" + child1.getName());
				LinkedList<ApfsElement> child2Children = child1.getChildren();
				for (ApfsElement child2 : child2Children) {
					System.out.println("--->" + child2.getName());
				}
			}
		}
	}

	public static void main(String args[]) {
		APFS apfs = new APFS("New FileSystem");

		apfs.initFileSystem("Local Disk", 10000);
		ApfsDirectory root = apfs.getRootDir();

		ApfsDirectory applications = new ApfsDirectory(root, "applications");
		root.appendChild(applications);
		ApfsFile first, second;
		first = new ApfsFile(applications, "First", 100);
		second = new ApfsFile(applications, "Second", 85);
		applications.appendChild(first);
		applications.appendChild(second);

		Thread thread = new Thread(apfs);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		ApfsDirectory home = new ApfsDirectory(root, "home");
		root.appendChild((home));
		ApfsFile third, fourth;
		third = new ApfsFile(home, "Third", 150);
		fourth = new ApfsFile(home, "Fourth", 120);
		home.appendChild(third);
		home.appendChild(fourth);

		Thread thread2 = new Thread(apfs);
		thread2.start();
		try {
			thread2.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}