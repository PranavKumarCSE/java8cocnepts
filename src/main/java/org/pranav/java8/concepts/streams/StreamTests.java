package org.pranav.java8.concepts.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests {

	public static void main(String[] args) {
		String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
	    List<String> strings = Stream.of(stringArray).map(Object::toString)
                 .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	    List<String> strings1 = Stream.of(stringArray).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	    System.out.println("1: "+strings);
	    System.out.println("2: "+strings1);
	    List<String> list1 = Arrays.asList("a","b","c");
	    List<String> list2 = Arrays.asList("c","d","e");
	    List<List<String>> nestedList_2Level = new ArrayList<>();
	    nestedList_2Level.add(list1);
	    nestedList_2Level.add(list2);
	    String result1 = nestedList_2Level.parallelStream().flatMap(x->x.stream()).collect(Collectors.joining());
	    System.out.println(result1);
	    //Note via map we couldn't fold 2 Lists but just streamThem 
	    //then merge 2 streams using Stream.concat
	    //then in next step collect using Collectors.joining()
		Stream<String> mergedStringStreams = nestedList_2Level.parallelStream()
				.map(x -> x.stream())
				.reduce((a, b) -> Stream.concat(a, b))
				.orElse(Stream.of(""));
		String result2 = mergedStringStreams.collect(Collectors.joining());
		System.out.println(result2);
		List<List<String>> nestedList_2Level2 = new ArrayList<>();
	    nestedList_2Level2.add(list1);
	    nestedList_2Level2.add(list2);
	    List<List<List<String>>> nestedList_3Level = new ArrayList<>();
	    nestedList_3Level.add(nestedList_2Level);
	    nestedList_3Level.add(nestedList_2Level2);
	    String result3 = nestedList_3Level.parallelStream().
	    		flatMap(x->x.stream()).
	    		flatMap(x->x.stream()).
	    		collect(Collectors.joining());
	    System.out.println(result3);
	    String result4 = nestedList_3Level.parallelStream().
	    		flatMap(x->x.stream()).
	    		map(x->x.stream()).
	    		reduce((a, b) -> Stream.concat(a, b)).orElse(Stream.of("")).
	    		collect(Collectors.joining());
	    System.out.println(result4);
	    
	    Integer[] arr=new Integer[]{1,2,3,4,3,2,4,2};
        List<Integer> listWithDuplicates = Arrays.asList(arr);
        Integer[] resultArr = new Integer[arr.length];
        listWithDuplicates.stream().distinct().collect(Collectors.toList()).toArray(resultArr);
        Stream.of(resultArr).forEach(System.out::println);
        List<Integer> resNonDup = listWithDuplicates.stream().distinct().collect(Collectors.toList());
        resNonDup.forEach(System.out::println);
	}

}
