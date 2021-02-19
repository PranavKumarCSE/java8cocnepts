package org.pranav.java8.concepts.streams.functionalinterfaces;

public class FuntionalInterfacesConcepts {
/*
 * Functional Interface:
 * 	Any interface with SAM(Single abstract method).
 * 	- can have any number of default methods.
 *  - can have any number of static utility methods(to be used in default methods or, for implementors)
 * 
 * 
 * ===========java.util.function package and all defined Functional Interface
 * 
 * Consumer -  {Bi|Double|Int|Long|''|
 * 				ObjDouble|ObjInt|ObjLong
 * 			   }Consumer
 * Function  - {Bi|Double|Int|Long|''|
 * 				DoubleToInt|DoubleToLong|IntToDouble|IntToLong|LongToInt|LongToDouble|
 * 				ToDoubleBi|ToDouble|ToIntBi|ToInt|ToLongBi|ToLong
 * 				}Function
 * Predicate - {Bi|Double|Int|Long|''}Predicate
 * Supplier  - {Boolean|Double|Int|Long|''}Supplier 
 * Operators - {Binary|Unary|
 * 				DoubleBinary|DoubleUnary
 * 				IntBinary|IntUnary
 * 				LongBinary|LongUnary
 * 			   }Operator
============================================================================
@FunctionalInterface
public interface Consumer<T>
1. Performs this operation on the given argument-T t.
 - Represents an operation that accepts a single input argument and returns no result. 
 - Unlike most other functional interfaces, {@code Consumer} is expected to operate via side-effects.
--------------------------------------------------------------------
    void accept(T t);
--------------------------------------------------------------------
2. Returns a composed {@code Consumer}- that performs, in sequence, this operation followed by the {@code after} operation. 
 - If performing either operation throws an exception, it is relayed to the caller of the composed operation.  
 - If performing this operation throws an exception, the {@code after} operation will not be performed.
 - It can throw NullPointerException if {@code after} is null
-------------------------------------------------------------------- 
    default Consumer<T> andThen(Consumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
--------------------------------------------------------------------
===============================================================================
 * 
 * 
===============================================================================
Represents a function that accepts one argument and produces a result.
@FunctionalInterface
public interface Function<T, R> {  
1. Applies this function(suplied transformation logic on T to return R) to the given argument.
 - @param t the function argument
 - @return the function result
--------------------------------------------------------------------  
    R apply(T t);
--------------------------------------------------------------------
2. Returns a composed function that first applies the {@code before} function to its input, and then applies this function to the result.
 - If evaluation of either function throws an exception, it is relayed to the caller of the composed function.
 - @param <V>: the type of input to the {@code before} function, and to the composed function
 - @param before: the function to apply before this function is applied
 - @return a composed function: that first applies the {@code before} function
     - and then applies this function
 - @throws NullPointerException if before is null
---------------------------------------------------------------------  
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
--------------------------------------------------------------------  
3. Returns a composed function that first applies this function to its input, and then applies the {@code after} function to the result.
 - If evaluation of either function throws an exception, it is relayed to the caller of the composed function.
 - @param <V>: the type of input to the {@code before} function, and to the composed function
 - @param after: the function to apply apply after this function is applied
 - @return a composed function: that first applies this function
     - and then applies the {@code after} function
 - @throws NullPointerException if before is null
---------------------------------------------------------------------  
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
--------------------------------------------------------------------- 
4. Returns a function that always returns its input argument.
 - @param <T>: the type of the input and output objects to the function
 - @return a function that always returns its input argument
---------------------------------------------------------------------- 
    static <T> Function<T, T> identity() {
        return t -> t;
    }
----------------------------------------------------------------------
===============================================================================
*
*
*
*
*===============================================================================
Represents a predicate (boolean-valued function) of one argument.
@param <T> the type of the input to the predicate
@FunctionalInterface
public interface Predicate<T> {
1. Evaluates this predicate on the given argument.
 - @param t the input argument
     - @return {@code true} if the input argument matches the predicate,
     - otherwise {@code false}
-----------------------------------------------
    boolean test(T t);
-----------------------------------------------
2. Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.  
   - When evaluating the composed predicate, 
	- if this predicate is {@code false}, then the {@code other} predicate is not evaluated.
   - Any exceptions thrown during evaluation of either predicate are relayed to the caller; 
     - if evaluation of this predicate throws an exception, the {@code other} predicate will not be evaluated.
   - @param other: a predicate that will be logically-ANDed with this predicate
   - @return a composed predicate: that represents the short-circuiting logical AND of this predicate and the {@code other} predicate
   - @throws NullPointerException if other is null
-------------------------------------------------------------
    default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }
-------------------------------------------------------------
3. Returns a predicate that represents the logical negation of this predicate.
   - @return a predicate that represents the logical negation of this predicate
--------------------------------------------------------------
    default Predicate<T> negate() {
        return (t) -> !test(t);
    }
--------------------------------------------------------------
4. Returns a composed predicate that represents a short-circuiting logical OR of this predicate and another.  
   - When evaluating the composed predicate, 
     - if this predicate is {@code true}, then the {@code other} predicate is not evaluated.
   - Any exceptions thrown during evaluation of either predicate are relayed to the caller; 
     - if evaluation of this predicate throws an exception, the {@code other} predicate will not be evaluated.
   -@param other a predicate that will be logically-ORed with this predicate
   -@return a composed predicate that represents the short-circuiting logical OR of this predicate and the {@code other} predicate
   -@throws NullPointerException if other is null
---------------------------------------------------------------
    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }
----------------------------------------------------------------
5. Returns a predicate that tests if two arguments are equal according to {@link Objects#equals(Object, Object)}.
   - @param <T>: the type of arguments to the predicate
   - @param targetRef: the object reference with which to compare for equality, which may be {@code null}
   - @return a predicate that tests if two arguments are equal according to {@link Objects#equals(Object, Object)}
---------------------------------------------------------------
    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }
---------------------------------------------------------------	
*===============================================================================
*
Represents a supplier of results.
- There is no requirement that a new or distinct result be returned each time the supplier is invoked.
-@param <T> the type of results supplied by this supplier
@FunctionalInterface
public interface Supplier<T> {
1. Gets a result.
   - @return a result of Type T
----------------------------------
    T get();
----------------------------------
*===============================================================================
*
*BiConsumer<T, U>(accepts two input arguments and returns no result.),  						void accept(T t, U u);
*BiFunction<T,U,R>(Represents a function that accepts two arguments and produces a result.), 	R apply(T t, U u);
*BiPredicate<T, U>(Represents a predicate (boolean-valued function) of two arguments.)			boolean test(T t, U u); 
*BooleanSupplier(Represents a supplier of {@code boolean}-valued results.)						boolean getAsBoolean();
*
*
*
*=============================ObjXXXConsumer<T> : void accept(T t, xxx value);====================
*ObjDoubleConsumer<T>
	(Represents an operation that accepts 
		an object-valued and 
		a {@code double}-valued argument, 
	and 
		returns no result.)
	void accept(T t, double value);
	
*ObjIntConsumer<T>
	(Represents an operation that accepts 
		an object-valued and 
		a {@code integer}-valued argument, 
	and 
		returns no result.)
	void accept(T t, int value);

*ObjLongConsumer<T>
	(Represents an operation that accepts 
		an object-valued and 
		a {@code long}-valued argument, 
	and 
		returns no result.)
	void accept(T t, long value);	
*======================================================================================================	
*
*
*
*=================================UnaryOperator<T> extends Function<T, T>===============================================
*<T> the type of the operand and result of the operator
*static <T> UnaryOperator<T> identity() {
        return t -> t;
    }
*
*
*=================================BinaryOperator<T> extends BiFunction<T,T,T>===============================================
1. Returns a {@link BinaryOperator} which returns the lesser of two elements according to the specified {@code Comparator}.
   - @param <T> the type of the input arguments of the comparator
   - @param comparator a {@code Comparator} for comparing the two values
   - @return a {@code BinaryOperator} which 
		- returns the lesser of its operands, according to the supplied {@code Comparator}
   - @throws NullPointerException if the argument is null
-----------------------------------------------------------------------------------------
    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }
-----------------------------------------------------------------------------------------
2. Returns a {@link BinaryOperator} which returns the greater of two elements according to the specified {@code Comparator}.
   - @param <T> the type of the input arguments of the comparator
   - @param comparator a {@code Comparator} for comparing the two values
   - @return a {@code BinaryOperator} which 
		- returns the greater of its operands, according to the supplied {@code Comparator}
   - @throws NullPointerException if the argument is null
-----------------------------------------------------------------------------------------
    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }
------------------------------------------------------------------------------------------
*
*=========================ToXXXFunction<T> : xxxx applyAsXXXX(T t);==================================
*ToDoubleFunction<T>
	(Represents a function that accepts an arg-object of declared Type T. 
	and apply the this function to 
		returns {double-valued} result.)
	double applyAsDouble(T t);
	
*ToIntFunction<T>
	(Represents a function that accepts an arg-object of declared Type T. 
	and apply the this function to 
		returns {int-valued} result.)
	int applyAsInt(T t);
	
*ToLongFunction<T>
	(Represents a function that accepts an arg-object of declared Type T. 
	and apply the this function to 
		returns {long-valued} result.)
	long applyAsLong(T t);
===========================================================================================================	
*
*
*
*=========================ToXXXBiFunction<T> : xxxx applyAsXXXX(T t, U u);==================================
*ToDoubleFunction<T>
	(Represents a function that accepts two arguments of Types U and V. 
	and apply the this function to 
		returns {double-valued} result.)
	double applyAsDouble(T t, U u);
	
*ToIntFunction<T>
	(Represents a function that accepts two arguments of Types U and V. 
	and apply the this function to 
		returns {int-valued} result.)
	int applyAsInt(T t, U u);
	
*ToLongFunction<T>
	(Represents a function that accepts two arguments of Types U and V. 
	and apply the this function to 
		returns {long-valued} result.)
	long applyAsLong(T t, U u);
===========================================================================================================	
*
*
*
*
=========================================XXXXX#########=====================================================
XXXXConsumer			
	void accept(xxxx value);  
	default XXXXXConsumer andThen(XXXXXConsumer after){Objects.requireNonNull(after);  return (double t) -> { accept(t); after.accept(t); };}
XXXXFunction<R>			
	R apply(xxxx value);
XXXXPredicate			
	boolean test(xxxx value);
	default XXXXPredicate and(XXXXPredicate other) {Objects.requireNonNull(other); return (value) -> test(value) && other.test(value);} 
	default XXXXXPredicate negate() {return (value) -> !test(value);}
	default XXXXXPredicate or(XXXXXPredicate other) {Objects.requireNonNull(other); return (value) -> test(value) || other.test(value);}
XXXXSupplier			
	xxxx getAsXXXXX();
XXXXToYYYYFunction		
	yyyyy applyAsYYYY(XXXX value);
XXXXToZZZZFunction		
	zzzzz applyAsZZZZ(XXXX value);
XXXXUnaryOperator
	xxxx applyAsXXXXX(xxxx operand);
	default XXXXXUnaryOperator compose(XXXXXUnaryOperator before) { Objects.requireNonNull(before); return (xxxx v) -> applyAsXXXXX(before.applyAsXXXXX(v));}
	default XXXXXUnaryOperator andThen(XXXXXUnaryOperator after) { Objects.requireNonNull(after);  return (xxxx t) -> after.applyAsXXXXX(applyAsXXXXX(t));}
	static XXXXXUnaryOperator identity() {return t -> t;}
XXXXBinaryOperator  
	double applyAsDouble(double left, double right);
Notes: 
XXXX:{Double,Int,Long}
xxxx:{double,int,long}
-----------------------------------------------------------------------------------------------------------------	
Consumer<T> 									: void accept(T t); 
												  default Consumer<T> andThen(Consumer<? super T> after)
-----------------------------------------------------------------------------------------------------------------
Function<T,R>									: R apply(T t);
												  default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {}
												  default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {}
												  static <T> Function<T, T> identity() {{return t -> t;}
-----------------------------------------------------------------------------------------------------------------
Predicate<T>									: boolean test(T t); 
												  default Predicate<T> and(Predicate<? super T> other) {}, 
												  default Predicate<T> or(Predicate<? super T> other) {}, 
												  default Predicate<T> negate(Predicate<? super T> other) {}, 
												  static <T> Predicate<T> isEqual(Object targetRef) {}
-----------------------------------------------------------------------------------------------------------------
Supplier<T>										: T get();
-----------------------------------------------------------------------------------------------------------------
UnaryOperator<T> extends Function<T,T>			: static <T> UnaryOperator<T> identity() {return t -> t;}
-----------------------------------------------------------------------------------------------------------------
BinaryOperator<T> extends BiFunction<T,T,T> 	: static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {Objects.requireNonNull(comparator); return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;} , 
												  static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {Objects.requireNonNull(comparator); return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;}
-----------------------------------------------------------------------------------------------------------------

*
*
 */
}
