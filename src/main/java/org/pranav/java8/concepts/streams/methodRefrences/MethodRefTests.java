package org.pranav.java8.concepts.streams.methodRefrences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodRefTests {

	public static void main(String[] args) {
		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
		//<String> void java.util.Arrays.sort(String[] a, Comparator<? super String> c)
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		Arrays.sort(stringArray, (a,b)->a.compareToIgnoreCase(b));
		//arbitary type here means typeOf String as needed by Comparator 
		//(obj,args) -> obj.someInstanceMethod(args)
		// => obj'sType::someInstanceMethod
		//Also note : there is no existing Object refe created here.
		String first = "First";
		BiFunction<String,Integer,String> bf2 =  (t,u) -> t.substring(u);//here the lambda expression is a instanceMethod call of one of argList
		String subString2 = getSubstring(first,2,bf2);
		System.out.println(subString2);
		
		// Using Method reference
		BiFunction<String,Integer,String> bf3 = String::substring;
		String subString3 = getSubstring(first,2,bf3);
		System.out.println(subString3);
		System.out.println("--------");
		Stream.of(stringArray).forEach(x->System.out.println(x));
		Stream.of(stringArray).forEach(x->System.out.println(x));
		System.out.println("--------");
		Stream.of(stringArray).forEach(System.out::println);
		//Constructor call via method reference
		List<String> stringList = Arrays.asList(stringArray);
		HashSet<String> setFromList1 = stringList.stream().collect(Collectors.toCollection(()->new HashSet<>()));
		HashSet<String> setFromList = stringList.stream().collect(Collectors.toCollection(HashSet::new));
		System.out.println(setFromList.equals(setFromList1));
	}
	
	public static String getSubstring(String str1,int beginIndex,BiFunction<String,Integer,String> p)
	{
		return p.apply(str1, beginIndex);
		
	}

}
