package org.pranav.java8.concepts.core.generics;

import java.util.ArrayList;
import java.util.List;
/**PCES 
 *Producer Extends to produce - any of type itself or any childType but can only consume nulls
 *Consumer consumes -  any of type itself or any parentType but can produce only Objects*/
public class GenericsTest {
	static class A { }
	static class B extends A { }
	static class C extends B { }
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		C c = new C();
		List<? extends A>  producerOfAs = new ArrayList<>();
		a = producerOfAs.get(0);
		//producerOfAs.add(a);
		Object obj = new Object();
		//producerOfAs.add(obj);//Error
		producerOfAs.add(null);
		//B b1 = producerOfAs.get(0);//CE not sure if needed return of B|C
		/*But if we want to produce any Of Types - Object,C,B,A 
		then mark the list to extend the last child
		As with extends its guaranteed that any of object in this list
			must be parent of C or C hence any of Parent-A|B can be TypeCasted 
			internally over the child C
		*/
		List<? extends C>  producerOfAsBsCs = new ArrayList<>();
		A a2 = producerOfAsBsCs.get(0);
		B b2 = producerOfAsBsCs.get(0);
		C c2 = producerOfAsBsCs.get(0);
		//below all have compile error as compiler don't know 
		// exactly what exactType can be added to this list -A|B|C 
		/*producerOfAs.add(a);
		 *producerOfAs.add(b);
		 *producerOfAs.add(c);*/
		/*
		  With <? super T> it allows to consume-add() any type which is superType or sameType of T
		  	But can't guarantee the get() to return anyOfType except T
		 */
		List<? super A>  consumerOfAsBsCs = new ArrayList<>();
		consumerOfAsBsCs.add(a);
		consumerOfAsBsCs.add(b);
		consumerOfAsBsCs.add(c);
		
		//A a4 = consumerOfAsBsCs.get(0); //CE cast a4 to Object
		obj = consumerOfAsBsCs.get(0); //allowed
		//Can only produce any subclass of A- A|B|C upon typeCast
		A a4 = (A)consumerOfAsBsCs.get(0);
		B b4 = (B)consumerOfAsBsCs.get(0);
		C c4 = (C)consumerOfAsBsCs.get(0);
		
	}

}
