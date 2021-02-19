package org.pranav.java8.concepts.streams.collectors;
/*
/*Various public static methods from Collectors class*/
/*
toCollection(Supplier<C> collectionFactory)
toList()
toSet()
joining()
joining(CharSequence delimiter)
joining(CharSequence delimiter,CharSequence prefix,CharSequence suffix)
mapping(Function<? super T, ? extends U> mapper,Collector<? super U, A, R> downstream)
collectingAndThen(Collector<T,A,R> downstream,Function<R,RR> finisher) : Collector<T,A,RR>
counting()	: Collector<T, ?, Long>
minBy(Comparator<? super T> comparator)	:Collector<T, ?, Optional<T>> 
maxBy(Comparator<? super T> comparator) :Collector<T, ?, Optional<T>>
summingInt(ToIntFunction<? super T> mapper)
summingLong(ToLongFunction<? super T> mapper)
summingDouble(ToDoubleFunction<? super T> mapper)
averagingInt(ToIntFunction<? super T> mapper)
averagingLong(ToLongFunction<? super T> mapper)
averagingDouble(ToDoubleFunction<? super T> mapper)
reducing(T identity, BinaryOperator<T> op)
reducing(BinaryOperator<T> op)
reducing(U identity,Function<? super T, ? extends U> mapper,BinaryOperator<U> op)
groupingBy(Function<? super T, ? extends K> classifier)
groupingBy(Function<? super T, ? extends K> classifier,
                                          Collector<? super T, A, D> downstream)
groupingBy(Function<? super T, ? extends K> classifier,
                                  Supplier<M> mapFactory,
                                  Collector<? super T, A, D> downstream)
groupingBy(Function<? super T, ? extends K> classifier)
groupingByConcurrent(Function<? super T, ? extends K> classifier)
groupingByConcurrent(Function<? super T, ? extends K> classifier,Collector<? super T, A, D> downstream)
groupingByConcurrent(Function<? super T, ? extends K> classifier,
                                            Supplier<M> mapFactory,
                                            Collector<? super T, A, D> downstream)
partitioningBy(Predicate<? super T> predicate)
partitioningBy(Predicate<? super T> predicate,Collector<? super T, A, D> downstream)
toMap(Function<? super T, ? extends K> keyMapper,
                                    Function<? super T, ? extends U> valueMapper)
toMap(Function<? super T, ? extends K> keyMapper,
                                    Function<? super T, ? extends U> valueMapper,
                                    BinaryOperator<U> mergeFunction)
toMap(Function<? super T, ? extends K> keyMapper,
                                Function<? super T, ? extends U> valueMapper,
                                BinaryOperator<U> mergeFunction,
                                Supplier<M> mapSupplier)
toConcurrentMap(Function<? super T, ? extends K> keyMapper,
                                                        Function<? super T, ? extends U> valueMapper)
toConcurrentMap(Function<? super T, ? extends K> keyMapper,
					Function<? super T, ? extends U> valueMapper,
                    BinaryOperator<U> mergeFunction)
toConcurrentMap(Function<? super T, ? extends K> keyMapper,Function<? super T, ? extends U> valueMapper,
                                       BinaryOperator<U> mergeFunction,Supplier<M> mapSupplier)
summarizingInt(ToIntFunction<? super T> mapper) : Collector<T, ?, IntSummaryStatistics> 
summarizingLong(ToLongFunction<? super T> mapper) : Collector<T, ?, LongSummaryStatistics> 
summarizingDouble(ToDoubleFunction<? super T> mapper) : Collector<T, ?, DoubleSummaryStatistics> 
======================================XXXXXXXXXX=============================================
*/
/*==================Abstract methods for Collector.class=======================================
    /**
     * Perform the final transformation from the intermediate accumulation type
     * {@code A} to the final result type {@code R}.
     *
     * <p>If the characteristic {@code IDENTITY_TRANSFORM} is
     * set, this function may be presumed to be an identity transform with an
     * unchecked cast from {@code A} to {@code R}.
     *
     * @return a function which transforms the intermediate result to the final
     * result
     *//*
    Function<A, R> finisher();
    // @return a function which returns a new, mutable result container
    Supplier<A> supplier();
    // @return a function which folds a value into a mutable result container
    BiConsumer<A, T> accumulator();
    // @return a function which combines two partial results into a combined result
    BinaryOperator<A> combiner();
    // @return an immutable set of collector characteristics
    Set<Characteristics> characteristics();
=============================================================================================
 */
public class CollectorsConcepts {

}
