package org.pranav.java8.concepts.core.concurrency;

/*
============Static[Class Field] vs Volatile[Instance Field]========================
Both are not cached by threads.
static:
 - Static fields are common to all threads and get stored in Method Area. 
 - Static with volatile no use. 
 - Static field can't be serialized.
volatile:
 - Volatile mainly used with instance variable which get stored in heap area. 
 - The main use of volatile is to maintain updated value over all the Threads.
 - instance volatile field can be Serialized.		
===================================================================================
 */
/*
==============================Properties of volatile===========================================
volatile:
	 - volatile is a keyword. 
	 - volatile forces all threads to get latest value of the variable from main memory 
		instead of CPU's cache/registers. 
	 - No locking is required to access volatile variables. 
	 - All threads can access volatile variable value at same time.
     - Using volatile variables reduces the risk of memory consistency errors, 
		because any write to a volatile variable establishes a happens-before relationship 
		with subsequent reads of that same variable(but can't rely on compiler re-ordering/optimizations).
	 - This means that changes to a volatile variable are always visible to other threads. 
	 - A read of a volatile variable always returns the most recent write by any thread.
	 - Not an alternative to:
	 	- Atomicxxx(race conditions on inc/dec) 
	   nor to 
		- synchronized(multiple lines of logic(not only data transformation) and 
								also over may be multiple shared variables)
==================================================================================================								
*/

/*============Thread Safety using volatile is only guaranteed in below situation:================								
Both of the following criteria must be met for volatile variables to provide the desired thread-safety:
	- Writes to the variable do not depend on its current value.
	- The variable does not participate in invariants with other variables.
	Basically, these conditions state that:
		- the set of valid values that can be written to a volatile variable: 
			1> is independent of any other program state, 
			2> including the variable's current state.
i.e The variable is truly independent of both other variables and its own prior values -- 
					you can sometimes simplify code by using volatile instead of synchronized.	 
=================================================================================================	 
 */
public class VolatileConcepts {

	class TestVolatile extends Thread{
		/*
		 * Explanation:
			without volatile on keepRunning the thread hangs forever. 
			Once you mark keepRunning as volatile - it stops after t.keepRunning = false;
		 */
	    //volatile
	    boolean keepRunning = true;

	    public void run() {
	        long count=0;
	        while (keepRunning) {
	            count++;
	        }

	        System.out.println("Thread terminated." + count);
	    }
	}
	
    public static void main(String[] args) throws InterruptedException {
    	VolatileConcepts enclosingClass = new VolatileConcepts();
        TestVolatile t = enclosingClass.new TestVolatile();
        t.start();
        Thread.sleep(1000);
        System.out.println("after sleeping in main");
        //this will only be viewed if read from main memory instead of cpu's cache/register
        // - only 1 writer thread here i.e main 
        // - this update is not dependent on any other invariant/not even on same variable .  
        //if non-volatile then below write will be missed
        t.keepRunning = false;
        t.join();//main thread or currentThread is now joined and wait for t to die.
        System.out.println("keepRunning set to " + t.keepRunning);
    }
}
