package org.pranav.java8.concepts.streams.splititerator;

public class SpliteratorConcepts {
/*
 * ==========java.util.stream.Spliterator==================================
 */
    /**public interface Spliterator<T>{..}
     * Returns a set of characteristics of this Spliterator and its elements. 
	   The result is represented as ORed values from
	   #ORDERED,  #DISTINCT,  #SORTED,  #SIZED,
       #NONNULL,  #IMMUTABLE,  #CONCURRENT, #SUBSIZED.
    int characteristics();
    boolean tryAdvance(Consumer<? super T> action);
	Spliterator<T> trySplit();
	long estimateSize();
    public static final int DISTINCT   = 0x00000001;//0b000000000000001
    public static final int SORTED     = 0x00000004;//0b000000000000100
    public static final int ORDERED    = 0x00000010;//0b000000000010000
    public static final int SIZED      = 0x00000040;//0b000000001000000
    public static final int NONNULL    = 0x00000100;//0b000000100000000
    public static final int IMMUTABLE  = 0x00000400;//0b000010000000000
	public static final int CONCURRENT = 0x00001000;//0b001000000000000
    public static final int SUBSIZED   = 0x00004000;//0b100000000000000

	//Few of default methods:
	//#forEachRemaining,#getExactSizeIfKnown,#hasCharacteristics,#getComparator 
    default void forEachRemaining(Consumer<? super T> action) {
        do { } while (tryAdvance(action));
    }  
    
    default long getExactSizeIfKnown() {
        return (characteristics() & SIZED) == 0 ? -1L : estimateSize();
    }

    default boolean hasCharacteristics(int characteristics) {
        return (characteristics() & characteristics) == characteristics;
    }

    default Comparator<? super T> getComparator() {
        throw new IllegalStateException();
    }

	public interface OfPrimitive<T, T_CONS, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>>{...}
 
	public interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt> {...}
 
	public interface OfLong extends OfPrimitive<Long, LongConsumer, OfLong> {...}
 
	public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, OfDouble> {...}
 * 
 */
}
