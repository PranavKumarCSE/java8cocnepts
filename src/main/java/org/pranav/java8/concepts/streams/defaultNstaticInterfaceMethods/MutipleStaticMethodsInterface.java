package org.pranav.java8.concepts.streams.defaultNstaticInterfaceMethods;

@FunctionalInterface
public interface MutipleStaticMethodsInterface {
	void abstarctMethod();
	static String utility1() {
		return "utility1";
	}
	public boolean equals(Object o);
	public int hashCode();
	public String toString();
	//public final native Class<?> getClass();
	//public final native Class<?> getClass();
	//public final native void notify();
	//public final native void notifyAll();
	//public final native void wait(long timeout) throws InterruptedException;
	//public final void wait(long timeout, int nanos) throws InterruptedException
	//public final void wait() throws InterruptedException
	//protected Object clone() throws CloneNotSupportedException;
	//protected void finalize() throws Throwable { }
	static String utility2() {
		return "utility2";
	}
	
	default String combiner() {
		System.out.println(utility1() + utility2());
		return MutipleStaticMethodsInterface.utility1() + MutipleStaticMethodsInterface.utility2();
	}
}
