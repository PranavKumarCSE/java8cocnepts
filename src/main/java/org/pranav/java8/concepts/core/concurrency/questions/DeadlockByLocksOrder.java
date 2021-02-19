package org.pranav.java8.concepts.core.concurrency.questions;

public class DeadlockByLocksOrder {
	private final Object sharedResource1 = new Object();
	private final Object sharedResource2 = new Object();

	public void lockOnRsc1First() {
		synchronized (sharedResource1) {
			System.out.println("Got monitor lock on sharedResource1 from lockOnRsc1First()");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (sharedResource2) {
				System.out.println("Got monitor lock on sharedResource2 from lockOnRsc1First()");
			}
		}
		System.out.println("Executed lockOnRsc1First()->lockOnRsc2First()");
	}

	public void lockOnRsc2First() {
		synchronized (sharedResource2) {
			System.out.println("Got monitor lock on sharedResource2 from lockOnRsc2First()");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (sharedResource1) {
				System.out.println("Got monitor lock on sharedResource1 from lockOnRsc2First()");
			}
		}
		System.out.println("Executed lockOnRsc2First()->lockOnRsc1First()");
	}

	public static void main(String[] args) throws InterruptedException {
		DeadlockByLocksOrder testObj = new DeadlockByLocksOrder();
		Runnable task1 = new Runnable() {
			public void run() {
				testObj.lockOnRsc1First();
			}
		};
		Runnable task2 = new Runnable() {
			public void run() {
				testObj.lockOnRsc2First();
			}
		};
		Thread thread1 = new Thread(task1, "Thr-task1");
		Thread thread2 = new Thread(task2, "Thr-task2");
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();
		System.out.println("No deadLock happened");
	}

}
