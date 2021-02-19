package org.pranav.java8.concepts.streams.functionalinterfaces;

import java.util.function.Consumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import org.omg.PortableInterceptor.InterceptorOperations;

public class BasicTests {

	public static void main(String[] args) {
		IntBinaryOperator func1 = (a,b)->a+b;
		consolePrinter("hello!",System.out::println);
		consolePrinter(109l,System.out::println);
		consolePrinter(109l,x->System.out.println(x));
		consolePrinter(109l,(Long x)->System.out.println(x));
		consolePrinter(109l,(Long x)->{System.out.println(x);});
		System.out.println(intBinaryOperations(4, 20,func1));
		System.out.println(intBinaryOperations(4, 20,(a,b)->a-b));
		System.out.println(intBinaryOperations(4, 20,(a,b)->a*b));
		System.out.println(intBinaryOperations(4, 20,(a,b)->b/a));
		System.out.println(intUnaryOperations(4, (a)->-a));
		System.out.println(intUnaryOperations(-4, (a)->{return -a;}));
		//if explicit return statement is used and have to provide braces and ';' as mandatory 
		//System.out.println(intUnaryOperations(-4, (a)->{return -a})); //CE:Syntax error, insert ";" to complete BlockStatements
		//System.out.println(intUnaryOperations(-4, (a)->return -a;));//CE:Syntax error on token "->", { expected after this token
		measure(()->printSumTo10k());
		
	}
	
	static int intBinaryOperations(int a, int b, IntBinaryOperator function) {
		return function.applyAsInt(a, b);
	}
	
	static <T> void consolePrinter(T t,Consumer<T> consumer) {
		consumer.accept(t);
	}
	
	static int intUnaryOperations(int a, IntUnaryOperator function) {
		return function.applyAsInt(a);
	}
	
	static void printSumTo10k() {
		System.out.println(IntStream.range(0, 10000).reduce(BasicTests::integerSum).orElse(0));
	}
	
	static int integerSum(int x, int y) {
		return x+y;
	}
	
	static void measure(Runnable run) {
		long t0 = System.nanoTime();
		run.run();
		long t1 = System.nanoTime();
		System.out.println("Time taken in millis:"+((t1-t0)/(60*60)));
	}
	
}
