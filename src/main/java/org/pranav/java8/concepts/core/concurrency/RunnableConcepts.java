package org.pranav.java8.concepts.core.concurrency;
/*
This as acts as TaskDefinition to be run by any number of Threads in async. 
=================================================================================
@FunctionalInterface
public interface Runnable {
	public abstract void run();
}
=================================================================================
1> create a class implementing Runnable.
	- For complex task which needs more number of shared variables/ lengthy/complicated logic
		involving multiple other Types of Objects.
2> For smaller tasks-definitions 
    - use anonymous instantiation  or Lambdas directly
3> Can acts as a FunctionalInterface - which receives nothing and returns nothing.  
=================================================================================
The Runnable interface should be implemented by anyclass whose 
 - instances are intended to be executed by a thread. 
The class must define a method of no arguments- public void run(){//task def}. 
This interface is designed to provide a common protocol for objects 
	that wish to execute code while they are active. 
For example, Runnable is implemented by class Thread.
Being active simply means that a thread has been started 
	and has not yet been stopped. 
In addition, Runnable provides the means for 
 - a class to be active while not subclassing Thread. 
A class that implements Runnable can run 
- without subclassing Thread by instantiating a Thread instance and 
- passing itself in as the target. 
In most cases, the Runnable interface should be used:
- if you are only planning to override the run() method and no other Thread methods.
This is important because classes should not be subclassed 
- unless the programmer intends on modifying or,
- enhancing the fundamental behavior of the class.
Ex: Runnable taskToRunByAnyNumberOfThreads = new Runnable(){public void run(){//async task}};

 * 
 * 
 */

public class RunnableConcepts{

}
