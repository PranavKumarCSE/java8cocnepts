package org.pranav.java8.concepts.core.concurrency;

/*@ThreadSafe*/
public class VolatileAsCheapLock {
    // Employs the cheap read-write lock trick
    // All mutative operations MUST be done with the 'this' lock held
    //@GuardedBy("this") 
	private volatile int value;
	
	/*Lock Free Access for readers*/
    public int getValue() { return value; }
    
    /*Writer must use locking for writing*/
    public synchronized int increment() {
        return ++value;
    }
    public synchronized int decrement() {
        return --value;
    }
	public static void main(String[] args) throws Exception{
		/*Acting as Shared counter*/
		VolatileAsCheapLock lock = new VolatileAsCheapLock();
		Runnable readerTask = new Runnable() {
			@Override
			public void run() {
				while(lock.getValue()<10) {
				// Simply read and print the value of counter
				System.out.println(Thread.currentThread().getName() + "read value:"+lock.getValue());
				try {
					Thread.sleep(100l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
				System.out.println(Thread.currentThread().getName() + "read value:"+lock.getValue());
			}
		};
		
		Runnable writerIncTask = new Runnable() {
			@Override
			public void run() {
				while(lock.getValue()<10) {
				// Simply increment the counter
				System.out.println(Thread.currentThread().getName() + "updated value:"+lock.increment());
				try {
					Thread.sleep(1000l);
					//Note: sleeps 10X more compared to reader 
					// making writes less frequent then reading.
					//As reader can read with actual lock - so cheaper and faster
					//Also updates will be viewed most of time correctly although 
					// race is still there due to ++value;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		};
		
		Runnable writerDecTask = new Runnable() {
			@Override
			public void run() {
				while(lock.getValue()>=-1) {
				// Simply increment the counter
				System.out.println(Thread.currentThread().getName() + "updated value:"+lock.decrement());
				try {
					Thread.sleep(1000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				}
			}
		};
		
		Thread r0 = new Thread(readerTask);
		r0.setName("r0");
		Thread r1 = new Thread(readerTask);
		r1.setName("r1");
		Thread r2 = new Thread(readerTask);
		r2.setName("r2");
		Thread w0 = new Thread(writerIncTask);
		w0.setName("w0");
		Thread w2 = new Thread(writerIncTask);
		w2.setName("w2");
		Thread w3 = new Thread(writerIncTask);
		w3.setName("w3");
		Thread w1 = new Thread(writerDecTask);
		w1.setName("w1");
		r0.start();
		r1.start();
		r2.start();
		w0.start();
		//w1.start();
		w2.start();
		w3.start();
		
	}

}
