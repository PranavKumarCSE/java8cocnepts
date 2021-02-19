package org.pranav.java8.concepts.core.concurrency;

public class ThreadLocalConcepts {
	static ThreadLocal<Boolean> flag1 = ThreadLocal.withInitial(()->true);
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {run_threadLocal();}
	}
	
	public static void run_threadLocal() {
		Runnable t1 = new Runnable() {
			@Override
			public void run() {
				while(flag1.get()) {
						System.out.println("Name :"+ Thread.currentThread().getName());
						//exit in single loop
						//no race condition on flag1 reading/writing as 
						//this is not shared variable but 
						//each thread will have there own copy- local to stack
						flag1.set(false);
					}
				flag1.remove();
				}
				
		};
		//Below guarantees each thread to run and print once its name:
		//Name :Thread-0 to Thread-19 in any order
		Thread th1 = new Thread(t1);
		Thread th2 = new Thread(t1);
		th1.start();
		th2.start();
	}

}
