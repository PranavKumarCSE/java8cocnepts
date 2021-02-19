package org.pranav.java8.concepts.core.concurrency;
/*
>A thread is a thread of execution in a program. 
>The JavaVirtual Machine allows an application to have 
	- multiple threads of execution running concurrently. 
>Every thread has a priority. 
>Threads with higher priority are executed in preference to threads with lower priority. 
>Each thread may or may not also be marked as a daemon. 
>When code running in some thread creates a new Thread object, 
	- the new thread has its priority initially set equal to the priority of the creating thread, 
	- and is a daemon thread if and only if the creating thread is a daemon. 
>Every thread has a name for identification purposes. 
>More than one thread may have the same name. 
>If a name is not specified when a thread is created, a new name is generated for it. 
>Unless otherwise noted, passing a null argument to a constructor or method in 
	this class will cause a NullPointerException to be thrown.
>When a Java Virtual Machine starts up, there is usually a single non-daemon thread 
 (which typically calls the method named main of some designated class). 
 The Java VirtualMachine continues to execute threads until either of the following occurs: 
	•The exit method of class Runtime has been called and 
		the security manager has permitted the exit operation to take place. 
	•All threads that are not daemon threads have died either, 
		by returning from the call to the run method or,
		by throwing an exception that propagates beyond the run method. 

>There are two ways to create a new thread of execution. One is todeclare a class to be a subclass of Thread. Thissubclass should override the run method of class Thread. An instance of the subclass can then beallocated and started. For example, a thread that computes primeslarger than a stated value could be written as follows: 

----------------------------------------------------------------------------------------
     class PrimeThread extends Thread {
         long minPrime;
         PrimeThread(long minPrime) {
             this.minPrime = minPrime;
         }

         public void run() {
             // compute primes larger than minPrime
              . . .
         }
     }
The following code would then create a thread and start it running: 

     PrimeThread p = new PrimeThread(143);
     p.start();
 
----------------------------------------------------------------------------------------
The other way to create a thread is to declare a class that implements the Runnable interface. 
That class then implements the run method. 
An instance of the class can then be allocated, 
passed as an argument when creating Thread, and started. 
The same example in this otherstyle looks like the following: 
-------------------------------------------------------------------------------------

     class PrimeRun implements Runnable {
         long minPrime;
         PrimeRun(long minPrime) {
             this.minPrime = minPrime;
         }

         public void run() {
             // compute primes larger than minPrime
              . . .
         }
     }
The following code would then create a thread and start it running: 

     PrimeRun p = new PrimeRun(143);
     new Thread(p).start();
 
----------------------------------------------------------------------------------------

 * 
 */

public class ThreadConcepts extends Thread{
/*
==========================Member and method details of Thread================================
Thread.State()
=========================
NEW
RUNNABLE
BLOCKED
WAITING
TIMED_WAITING
TERMINATED
==========================
java.lang.Thread
=============public constructors===============================
Thread()
Thread(String name)
Thread(Runnable)
Thread(Runnable, String name)
Thread(ThreadGroup, String name)
Thread(ThreadGroup, Runnable)
Thread(ThreadGroup, Runnable, String name)
Thread(ThreadGroup, Runnable, String name, long stackSize)
============public final static====================================================
MIN_PRIORITY 		=01:public final static int 
NORM_PRIORITY		=05 :public final static int
MAX_PRIORITY		=10 :public final static int
currentThread()			:public static Thread
/*Causes the currently executing thread to sleep (temporarily cease execution) 
for the specified number of milliseconds, subject to the precision and accuracy of
    - system timers and 
	- schedulers.
The thread does not lose ownership of any monitors.
sleep for millis or millis+nanos
*//*
sleep(long millis)		:public static void > throws InterruptedException			
sleep(long millis, int) :public static void > throws InterruptedException
/*
Causes this thread to begin execution; the JVM calls the run method of this thread. 
The result is that two threads are running concurrently: 
	- the current thread (which returns from the call to the start method) and 
	- the other thread (which executes its run method). 
It is never legal to start a thread more than once.
In particular, a thread may not be restarted once it has completedexecution.
Throws:IllegalThreadStateException - if the thread was alreadystarted.
*//*
start()
/*
If this thread was constructed using a separate Runnable run object, 
- then that Runnable object's run method is called;
- otherwise, this method does nothing and returns. 
Subclasses of Thread should override this method.
*//*
run()
/*
- Interrupts this thread. 
- Unless the current thread is interrupting itself, which isalways permitted, 
	the checkAccess methodof this thread is invoked, 
	which may cause a SecurityException to be thrown. 
- If this thread is blocked in an invocation of the 
	wait(), wait(long), or wait(long, int) methods of the Objectclass, or 
	of the join(), join(long), join(long, int), 
		sleep(long), or sleep(long, int),methods of this class, 
	then its interrupt status will be cleared and it will receive an InterruptedException. 
- If this thread is blocked in an I/O operation upon an InterruptibleChannel 
	then the channel will be closed, the thread's interrupt status will be set, 
	and the thread will receive a java.nio.channels.ClosedByInterruptException. 
- If this thread is blocked in a java.nio.channels.Selector 
	then the thread's interrupt status will be set and 
	it will return immediately from the selection operation, 
	possibly with a non-zero value, just as if the selector's wakeup method were invoked. 
- If none of the previous conditions hold then this thread's interrupt status will be set. 
- Interrupting a thread that is not alive need not have any effect.
- Throws:SecurityException - if the current thread cannot modify this thread
*//*
interrupt()						: public void 
/*
- Tests whether this thread has been interrupted. 
- The interrupted status of the thread is unaffected by this method. 
- A thread interruption ignored because a thread was not alive at the time of the interrupt 
	will be reflected by this method returning false.
- Returns:true if this thread has been interrupted; false otherwise.
*//*
interrupted()					: public static boolean 
/*
Tests whether the current thread has been interrupted. 
The interrupted status of the thread is cleared by this method. 
In other words, if this method were to be called twice in succession, 
the second call would return false 
(unless the current thread were interrupted again, 
	after the first call had cleared its interrupted status and 
	before the second call had examined it). 
A thread interruption ignored because a thread was not alive at the time of the interrupt 
will be reflected by this method returning false.
Returns:true if the current thread has been interrupted; false otherwise.
*//*
isInterrupted()					: public boolean isInterrupted()
/*
Waits at most millis milliseconds for this thread to die. 
A timeout of 0 means to wait forever. 
This implementation uses a loop of this.wait calls conditioned on this.isAlive. 
As a thread terminates the this.notifyAll method is invoked. 
It is recommended that applications not use wait, notify, or notifyAll on Thread instances.
Parameters:millis the time to wait in milliseconds 
Throws:
	IllegalArgumentException - if the value of millis is negative
	InterruptedException - if any thread has interrupted the current thread. 
		The interrupted status of the current thread iscleared when this exception is thrown.
 *//*
join(long)
/*same as join but wait for millis+nanos
Parameters:
	millis the time to wait in milliseconds 
	nanos 0-999999 additional nanoseconds to wait
*//*
join(long, int)
/*
Waits for this thread to die forever in a loop until. 
An invocation of this method behaves in exactly the sameway as the invocation 
join(0) 
Throws:InterruptedException - if any thread has interrupted the current thread. 
The interrupted status of the current thread iscleared when this exception is thrown.
 *//*
join()
setPriority(int)
getPriority()
setName(String)
getName()
getThreadGroup()
activeCount()
setDaemon(boolean)
isDaemon()
getId()
getState()
toString()
yield()					:public static void      //non-optimal - A hint to the scheduler that the current thread is willing to yield its current use of a processor. The scheduler is free to ignore this hint.
=======deprecated ones====================================
stop()
stop(Throwable)
destroy()
isAlive()
suspend()
resume()
======not much of direct use==============================
enumerate(Thread[])
countStackFrames()
dumpStack()
checkAccess()
getContextClassLoader()
setContextClassLoader(ClassLoader)
getStackTrace()
getAllStackTraces()
dumpThreads(Thread[])
UncaughtExceptionHandler
=========================================================	

Marks this thread as either a daemon thread or a user thread. 
The JVM exits when the only threads running are all daemon threads. 
This method must be invoked before the thread is started.
Parameters:on if true, marks this thread as a daemon thread
Throws:
	IllegalThreadStateException - if this thread is alive
	SecurityException - if checkAccess determines that the current thread cannot modify this thread

Returns the state of this thread.
This method is designed for use in monitoring of the system state,not for synchronization control.

Tests if this thread is alive. A thread is alive if it has been started and has not yet died.
Returns:true if this thread is alive; false otherwise.

Changes the priority of this thread. 
First the checkAccess method of this thread is called with no arguments. This may result in throwing a SecurityException. 
Otherwise, the priority of this thread is set to the smaller of the specified newPriority and the maximum permitted priority of the thread's thread group.
Parameters:newPriority priority to set this thread to
Throws:
	IllegalArgumentException - If the priority is not in the range MIN_PRIORITY to MAX_PRIORITY.
	SecurityException - if the current thread cannot modify this thread.
	
Returns this thread's priority.
Returns:this thread's priority.
ReturnType: int

Changes the name of this thread to be equal to the argument name. 
First the checkAccess method of this thread is called with no arguments. 
This may result in throwing a SecurityException.
Parameters:name the new name for this thread.
Throws:
	SecurityException - if the current thread cannot modify thisthread.See Also:	

Returns this thread's name.
Returns:this thread's name.
Return Type: String

Returns the thread group to which this thread belongs.
This method returns null if this thread has died(been stopped).
Returns:this thread's thread group.

Returns an estimate of the number of active threads 
in the currentthread's thread group and its subgroups. 
Recursively iterates over all subgroups in the currentthread's thread group. 

The value returned is only an estimate because the number of threads may change 
dynamically while this method traverses internal data structures, and 
might be affected by the presence of certain system threads. 
This method is intended primarily for debugging and monitoring purposes.
Returns:an estimate of the number of active threads in the currentthread's thread group and in any other thread group thathas the current thread's thread group as an ancestor.

Copies into the specified array every active thread in the currentthread's thread group and its subgroups. This method simplyinvokes the java.lang.ThreadGroup.enumerate(Thread [])method of the current thread's thread group. 
An application might use the activeCountmethod to get an estimate of how big the array should be, however if the array is too short to hold all the threads, the extra threadsare silently ignored. If it is critical to obtain every activethread in the current thread's thread group and its subgroups, theinvoker should verify that the returned int value is strictly lessthan the length of tarray. 
Due to the inherent race condition in this method, it is recommended 
that the method only be used for debugging and monitoring purposes.
=======================================================================================

 * 
 * 
 */
}
