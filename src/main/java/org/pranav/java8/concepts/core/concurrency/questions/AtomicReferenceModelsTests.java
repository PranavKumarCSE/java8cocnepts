package org.pranav.java8.concepts.core.concurrency.questions;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceModelsTests {

	public static void main(String[] args) {
		AllMutableAndAtomic ref1 = new AllMutableAndAtomic();
		AtomicReference<AllMutableAndAtomic> aref = new AtomicReference<>();
		aref.set(ref1);
		System.out.println(aref.get()==ref1);
		ref1.setCount(new AtomicInteger(5));
		System.out.println(aref.get()==ref1);
		ref1.setName(new AtomicReference<String>("String"));
		System.out.println(aref.get()==ref1);
	}

}

class AllMutableAndAtomic{

	private AtomicInteger count;
	private AtomicReference<String> name;
	
	public AllMutableAndAtomic() {
		
	}
	
	public AllMutableAndAtomic(AtomicInteger count, AtomicReference<String> name) {
		super();
		this.count = count;
		this.name = name;
	}
	
	public AtomicInteger getCount() {
		return count;
	}
	public void setCount(AtomicInteger count) {
		this.count = count;
	}
	public AtomicReference<String> getName() {
		return name;
	}
	public void setName(AtomicReference<String> name) {
		this.name = name;
	}
	
	
}