package org.pranav.java8.concepts.streams.optional;

import java.time.MonthDay;
import java.util.Optional;

import org.pranav.java8.concepts.Person;

public class OptionalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Optional<Integer> opionalInt1 = Optional.of(78);
		System.out.println(opionalInt1.get());
		Optional<Integer> opionalInt2 = Optional.empty();
		//System.out.println(opionalInt2.get());//java.util.NoSuchElementException
		if(opionalInt2.isPresent()) {
			System.out.println(opionalInt2.get());
		}
		opionalInt2.ifPresent(System.out::println);
		//Optional<Integer> opionalInt3 = Optional.of(null);//java.lang.NullPointerException from its pvt constructor
		String name = Optional.ofNullable(getPerson()).map(Person::getName).orElse("default");
		Optional.ofNullable(getPerson()).map(Person::getSalaryinINR).
				orElseThrow(IllegalArgumentException::new);
		System.out.println(name);
		Optional<Person> name3 = Optional.ofNullable(getPerson());
		if(name3.isPresent()) {
			
		}
		//String name = Optional.ofNullable(getPerson()).map(Person::getName).orElseGet("default");
		
	}

	public static Person getPerson() {
		Person p1 = new Person();
		p1.setDepartment("dpt");
		p1.setDob(MonthDay.of(06 , 30));
		p1.setSalaryinINR(10000.50d);
		p1.setName("Pranav");
		return null;
	}
	
	public static Person getPersonAfterCheck(Person p) {
		if(p.getSalaryinINR()<10000) {
			return p;
		}
		return null;
	}
	
	public static Optional<Person> getPersonAfterCheck2(Person p) {
		Person p1 = null;
		if(p.getSalaryinINR()<10000) {
			p1 =  p;
		}
		return Optional.ofNullable(p1);
	}
}
