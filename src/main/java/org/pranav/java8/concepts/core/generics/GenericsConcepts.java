package org.pranav.java8.concepts.core.generics;

public class GenericsConcepts {

}
/*
1. Making a Class,Interface Typed/Parameterized/GenericTyped

class ClassName<T,E,P...>{
Fields and methods args,parameters can be of Declared Parameterized Types


}
===========================================
Remember PECS: "Producer Extends, Consumer Super".

"Producer Extends" - If you need a List to produce T values (you want to read Ts from the list), 
		you need to declare it with ? extends T, e.g. List<? extends Integer>. 
		But you cannot add to this list.

"Consumer Super" - If you need a List to consume T values (you want to write Ts into the list), 
		you need to declare it with ? super T, e.g. List<? super Integer>. 
		But there are no guarantees what type of object you may read from this list.

If you need to both read from and write to a list, you need to declare it 
exactly with no wildcards, e.g. List<Integer>.
===============================================
class A { }
class B extends A { }
class C extends B { }
List<? extends T> - reading and assigning:

|-------------------------|-------------------|---------------------------------|
|         wildcard        |        get        |              assign             |
|-------------------------|-------------------|---------------------------------|
|    List<? extends C>    |    A    B    C    |                       List<C>   |
|-------------------------|-------------------|---------------------------------|
|    List<? extends B>    |    A    B         |             List<B>   List<C>   |
|-------------------------|-------------------|---------------------------------|
|    List<? extends A>    |    A              |   List<A>   List<B>   List<C>   |
|-------------------------|-------------------|---------------------------------|
List<? super T> - writing and assigning:

|-------------------------|-------------------|-------------------------------------------|
|         wildcard        |        add        |                   assign                  |
|-------------------------|-------------------|-------------------------------------------|
|     List<? super C>     |              C    |  List<Object>  List<A>  List<B>  List<C>  |
|-------------------------|-------------------|-------------------------------------------|
|     List<? super B>     |         B    C    |  List<Object>  List<A>  List<B>           |
|-------------------------|-------------------|-------------------------------------------|
|     List<? super A>     |    A    B    C    |  List<Object>  List<A>                    |
|-------------------------|-------------------|-------------------------------------------|
In all of the cases:

you can always get Object from a list regardless of the wildcard.
you can always add null to a mutable list regardless of the wildcard.

*/