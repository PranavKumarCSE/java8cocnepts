package org.pranav.java8.concepts.streams.collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsTest {
	public static void main(String args[]) {
		ArrayList<Integer> iList = new ArrayList<>(Arrays.asList(new Integer[] {5,7,8,9}));
		List<Integer> result = iList.stream().collect(Collectors.toList());
		iList.add(10);
		System.out.println(iList.toString());
		System.out.println(result.toString());
		List<Integer> list2 = new ArrayList<>();
		iList.parallelStream().forEach(x->{x=x+5;list2.add(x);});
		List<Integer> list3 = iList.parallelStream().map(x->x+5).collect(Collectors.toList());
		System.out.println(iList.toString());
	}
}
