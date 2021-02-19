package org.pranav.java8.concepts.core.concurrency;
/*
 * 
Usecases: for AtomicRefrences:
It will prevent any need of other type of synchronization for immutable types 
each modification means a new Object Creation and copying into reference
i.e Reference will now point to a different object i.e it has been changed.
Here the values are checked based on == and not by equals method.

But in-case of mutable object same objects's modifier can mutate the object
without changing the reference. So either the modification methods needs to be synchronized as well
and this condition it nullify the case of tracking just the reference.

But if requirement is to always get the latest updated object(even if mutated internaly) 
- then its fine to use the AtomicRefrence<> : given all set is also done via same.

Also one model is to have all mutable/Atomic fields for non-blocking algos.
=============================================================================
AtomicReference
The AtomicReference class provides a way to 
- atomically read and set an object reference. 
It is generally used to tie together more than one atomically 
accessed variable by wrapping them in an immutable object instance 
(that is, one whose fields are final).

Suppose, for example, that in a web server application, 
we want to count the number of page accesses and 
also the number of accesses that failed due to an error. 
We can do this by creating a class to encapsulate the current statistics, and 
then create and update an AtomicReference to an instance of this class:

public class AccessStatistics {
  private final int noPages, noErrors;
  public AccessStatistics(int noPages, int noErrors) {
    this.noPages = noPages;
    this.noErrors = noErrors;
  }
  public int getNoPages() { return noPages; }
  public int getNoErrors() { return noErrors; }
}

Now, to update the statistics, we can use a method such as the following:
public class UpdateAccessStatisticsAtomically { 
private final AtomicReference<AccessStatistics> stats =
  new AtomicReference<AccessStatistics>(new AccessStatistics(0, 0));

	public void incrementPageCount(boolean wasError) {
		AccessStatistics prev, newValue;
		do {
		prev = stats.get();
		int noPages = prev.getNoPages() + 1;
		int noErrors = prev.getNoErrors;
		if (wasError) {
			noErrors++;
		}
		newValue = new AccessStatistics(noPages, noErrors);
		} while (!stats.compareAndSet(prev, newValue));
	}
}
Atomic references (and atomic classes in general) have the same memory semantics 
as volatile variables. 
So when the reference to the new AccessStatistics is set to the AtomicReference, 
at that moment, the JVM must ensure that the variables set 
during the constructor are visible to other threads.

In other words, it isn't strictly necessary in this case to make the fields on 
AccessStatistics final. 
Nonetheless, it's good practice to mark fields on immutable classes as final, and we do so here.

Other uses for AtomicReference
The AtomicReference class can also be useful in cases where you have 
a structure which is basically read-only but which you 
very occasionally want to update. 
On the occasional update, you can replace the old copy with a brand new copy.

AtomicMarkableReference and AtomicStampedReference
If the two variables that you want to 'couple together' are 
(1) an object reference and 
(2) a boolean or integer, then 
two ready-made classes are available: 
AtomicMarkableReference and 
AtomicStampedReference to couple an object reference with a boolean and integer respectively. 
Sometimes these combinations are useful in creating data structures: 
for example, the boolean flag could indicate whether a node in a tree is marked for deletion; 
the stamped reference can be used in cases where setting a reference to A, then to B, then back to A again 
doesn't count as undoing the change.

In principle, these cases could be optimized with help from the JVM. 
For example, if it is known that an object reference requires 31 bits 
(some 32-bit systems have a maximum user-mode address space of 2^31 bytes, 
i.e. no memory pointer needs to be bigger than 31 bits), 
then the extra bit of a 32-bit word could be used for the boolean flag. 
And some processors provide CAS instructions that can work on two 32-bit words simultaneously, 
which could be used to optimize the reference-plus-integer case 
(or the reference-plus-boolean on a system where a memory pointer needed up to 63 bits).

In practice, no such optimization exists, 
at least in Sun's implementation. 
Current versions simply implement these two classes in a very similar way 
to our AccessStatistics updater above: 
each time either the object reference or the boolean/integer is changed, 
a new instance of an immutable inner class is created to store the two values. 
So even where you need the functionality of these classes, 
it may be more convenient, and no less efficient, 
to roll your own immutable class that has exactly the variables you need.


 * 
 */
public class AtomicRefrencesConcepts {

}
