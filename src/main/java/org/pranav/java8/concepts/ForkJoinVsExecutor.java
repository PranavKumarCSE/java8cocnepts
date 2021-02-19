package org.pranav.java8.concepts;

public class ForkJoinVsExecutor {
/*
Fork/Join addresses the need for divide-and-conquer, or recursive task-processing in Java programs.
Fork/Join's logic is very simple: 
	(1) separate (fork) each large task into smaller tasks; 
	(2) process each task in a separate thread (separating those into even smaller tasks if necessary); 
	(3) join the results.
Fork/Join offers serious gains for solving problems that involve recursion. 
Because recursion is fundamental to parallel programming on multicore platforms.


The Executor Framework provides several classes e.g. 
Executor, 
ExecutorService, and 
Executors for execution and creating thread pools. 
It also provides several built-in, ready to use thread pools like a pool of 
fixed threads, 
cached thread pool which can expand itself, spawn new threads if required due to heavy load.
The Job of that thread pool is to accept the task and execute if there is a free worker thread available.
but 
ForJoinPool is a special kind of thread pool. 
They use a work-stealing pattern. 
All threads in a fork-join pool attempt to 
 - find and execute tasks submitted to the pool and/or 
 - created by other active tasks.

This enables efficient processing when most tasks spawn other subtasks (as do most ForkJoinTasks like recursive actions),
 as well as when many small tasks are submitted to the pool from external clients.

In short, the main difference between the Executor framework and ForkJoinPool is that the 
former provides a general-purpose thread pool, 
while the latter provides a special implementation that uses a work-stealing pattern 
for efficient processing of ForkJoinTask.

some difference between ForkJoinPool and ThreadPoolExecutor class.


1) The main difference between ForkJoinPool and ThreadPoolExecutor is that 
    ForkJoinPool is designed to accept and execute ForkJoinTask, which is a lightweight version of FutureTask, 
while 
    ThreadPoolExecutor is designed to provide a normal thread pool which executes 
	each submitted task using one of possibly several pooled threads.

2) ForkJoinPool uses a work-stealing pattern, which means one thread can also execute a pending task from another thread. 
   This improves efficiency in the case of ForkJoinTask as most of the 
   ForkJoinTask algorithm spawn new tasks. 

The fork-join framework supports several styles of ForkJoinTasks, including those that 
require explicit completions and those executing cyclically. 
The RecursiveAction class used here directly supports the style of parallel recursive decomposition 
for non-result-bearing tasks; 
the RecursiveTask class addresses the same problem for result-bearing tasks. 
(Other fork-join task classes include CyclicAction, AsyncAction, and LinkedAsyncAction;


Choosing Runtime.availableProcessors() as the number of worker threads generally offers close to optimal results, 
as tasks executed in fork-join pools are supposed to be CPU-bound, but again, 
results tend to not be very sensitive to this parameter so long as you avoid sizing 
the pool way too large or way too small.   

No explicit synchronization is required in the MaxWithFJ class. 
The data it operates on is constant for the lifetime of the problem, and 
there is sufficient internal synchronization within the ForkJoinExecutor to guarantee visibility 
of the problem data to subtasks, as well as to 
guarantee visibility of subtask results to the tasks that join with them.

public class MaxWithFJ extends RecursiveAction {
    private final int threshold;
    private final SelectMaxProblem problem;
    public int result;
 
    public MaxWithFJ(SelectMaxProblem problem, int threshold) {
        this.problem = problem;
        this.threshold = threshold;
    }
 
    protected void compute() {
        if (problem.size < threshold)
            result = problem.solveSequentially();
        else {
            int midpoint = problem.size / 2;
            MaxWithFJ left = new MaxWithFJ(problem.subproblem(0, midpoint), threshold);
            MaxWithFJ right = new MaxWithFJ(problem.subproblem(midpoint + 
              1, problem.size), threshold);
            coInvoke(left, right);
            result = Math.max(left.result, right.result);
        }
    }
 
    public static void main(String[] args) {
	    SelectMaxProblem problem = ...
        int threshold = ...
        int nThreads = ...
        MaxWithFJ mfj = new MaxWithFJ(problem, threshold);
        ForkJoinExecutor fjPool = new ForkJoinPool(nThreads);
        fjPool.invoke(mfj);
        int result = mfj.result;
    }
}
 class SelectMaxProblem {
    private final int[] numbers;
    private final int start;
    private final int end;
    public final int size;
 
    // constructors elided 
 
    public int solveSequentially() {
        int max = Integer.MIN_VALUE;
        for (int i=start; i<end; i++) {
            int n = numbers[i];
            if (n > max)
                max = n;
        }
        return max;
    }
 
    public SelectMaxProblem subproblem(int subStart, int subEnd) {
        return new SelectMaxProblem(numbers, start + subStart, 
                                    start + subEnd);
    }
}
 */
}
