package org.pranav.java8.concepts.streams.lambdas;

public class LambdasConceptsDetail {
/*
=====================================
Syntax of Lambda Expressions:
=====================================
A lambda expression consists of the following:
1. A comma-separated list of formal parameters enclosed in parentheses.
	Ex: (arg1,arg2,..)->{//expression|Statementblock}
	Note: 
	- You can omit the data type of the parameters in a lambda expression (if compiler can figure out the target type of FI). 
	- In addition, you can omit the parentheses if there is only one parameter. 
	For example, the following lambda expression is also valid:
		p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25

2. The arrow token, ->

3. A body, which consists of a single expression or a statement block. 
	This example uses the following expression:
		p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25
	Note:	
	- If you specify a single expression, then the Java runtime 
		- evaluates the expression and then 
		- returns its value. 
	- In such cases : enclosing the single statement/expression in "braces:{}"is optional.
	- But for muti-line expression/statements : braces for defining the block is a must.
	- Also even a single non-void explicit use of return statement if used then it is not an expression; 
		So it becomes multi-line in a lambda expression, you must enclose statements in braces ({}).
		Fo Ex: Alternatively, you can use a return statement explicitly:
			p -> {return p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25; }
	- However, you do not have to enclose a void method invocation in braces. 
		For Ex:, the following is a valid lambda expression:
			email -> System.out.println(email)

Note:
  - A lambda expression looks a lot like a method declaration; 
	- you can consider lambda expressions as anonymous methods—methods without a name.

The following example, Calculator, is an example of lambda expressions that take more than one formal parameter:
public class Calculator {
  //It behaves as sam - FunctionalInterface and is Equivalent to FI - IntBinaryOperator : int apply(int,int);
    interface IntegerMath {
        int operation(int a, int b);  
    }
  
    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }
 
    public static void main(String... args) {
    
        Calculator myApp = new Calculator();
        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;
        System.out.println("40 + 2 = " +
            myApp.operateBinary(40, 2, addition));
		//(40,2)->{40+2}	
        System.out.println("20 - 10 = " +
            myApp.operateBinary(20, 10, subtraction));    
    }
}

The method operateBinary performs a mathematical operation on two integer operands. 
The operation itself is specified by an instance of IntegerMath. 
The example defines two operations with lambda expressions, addition and subtraction. 
The example prints the following:

40 + 2 = 42
20 - 10 = 10
=====================================================
Accessing Local Variables of the Enclosing Scope:
=====================================================
Like local and anonymous classes, lambda expressions can capture variables; 
they have the same access to local variables of the enclosing scope. 
However, unlike local and anonymous classes, lambda expressions do not have any shadowing issues (see Shadowing for more information). 
Lambda expressions are lexically scoped. This means that they do not inherit any names from a supertype or introduce a new level of scoping. 
Declarations in a lambda expression are interpreted just as they are in the enclosing environment. 
The following example, LambdaScopeTest, demonstrates this:

import java.util.function.Consumer;

public class LambdaScopeTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {
            
            // The following statement causes the compiler to generate
            // the error "local variables referenced from a lambda expression
            // must be final or effectively final" in statement A:
            //
            // x = 99;
            
            Consumer<Integer> myConsumer = (y) -> 
            {
                System.out.println("x = " + x); // Statement A
                System.out.println("y = " + y);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " +
                    LambdaScopeTest.this.x);
            };

            myConsumer.accept(x);

        }
    }

    public static void main(String... args) {
        LambdaScopeTest st = new LambdaScopeTest();
        LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}
This example generates the following output:

x = 23
y = 23
this.x = 1
LambdaScopeTest.this.x = 0
If you substitute the parameter x in place of y in the declaration of the lambda expression myConsumer, then the compiler generates an error:

Consumer<Integer> myConsumer = (x) -> {
    // ...
}
The compiler generates the error "variable x is already defined in method methodInFirstLevel(int)" 
because the lambda expression does not introduce a new level of scoping. 
Consequently, you can directly access fields, methods, and local variables of the enclosing scope. 
For example, the lambda expression directly accesses the parameter x of the method methodInFirstLevel. 
To access variables in the enclosing class, use the keyword this. 
In this example, this.x refers to the member variable FirstLevel.x.

However, like local and anonymous classes, a lambda expression can only access 
 - local variables and 
 - parameters of the enclosing block that are final or effectively final. 
For example, suppose that you add the following assignment statement immediately after the methodInFirstLevel definition statement:

void methodInFirstLevel(int x) {
    x = 99;
    // ...
}
Because of this assignment statement, the variable FirstLevel.x is not effectively final anymore. 
As a result, the Java compiler generates an error message similar to - 
"local variables referenced from a lambda expression must be final or effectively final" 
where the lambda expression myConsumer tries to access the FirstLevel.x variable:

System.out.println("x = " + x);
==========================================
Target Typing:
==========================================
How do you determine the type of a lambda expression? 
Recall the lambda expression that selected members who are male and between the ages 18 and 25 years:

p -> p.getGender() == Person.Sex.MALE
    && p.getAge() >= 18
    && p.getAge() <= 25
This lambda expression was used in the following two methods:

public static void printPersons(List<Person> roster, CheckPerson tester) in Approach 3: 
	Specify Search Criteria Code in a Local Class

public void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) in Approach 6: 
	Use Standard Functional Interfaces with Lambda Expressions

When the Java runtime invokes the method printPersons, it's expecting a data type of CheckPerson, so the lambda expression is of this type. 
However, when the Java runtime invokes the method printPersonsWithPredicate, 
it's expecting a data type of Predicate<Person>, so the lambda expression is of this type. 
The data type that these methods expect is called the "target type". 
To determine the type of a lambda expression, the Java compiler uses 
 - the target type of the context or situation 
 - in which the lambda expression was found. 
It follows that you can only use lambda expressions in situations in which the Java compiler can determine a target type:
- Variable declarations
- Assignments
- Return statements
- Array initializers
- Method or constructor arguments
- Lambda expression bodies
- Conditional expressions, ?:
- Cast expressions
- Target Types and Method Arguments

For method arguments, the Java compiler determines the target type with two other language features:
---------------------------------- 
1. overload resolution and 
2. type argument inference.
----------------------------------
Consider the following two functional interfaces ( java.lang.Runnable and java.util.concurrent.Callable<V>):

public interface Runnable {
    void run();
}

public interface Callable<V> {
    V call();
}
The method Runnable.run does not return a value, whereas Callable<V>.call does.

Suppose that you have overloaded the method invoke as follows (see Defining Methods for more information about overloading methods):

void invoke(Runnable r) {
    r.run();
}

<T> T invoke(Callable<T> c) {
    return c.call();
}
Which method will be invoked in the following statement?

String s = invoke(() -> "done");
The method invoke(Callable<T>) will be invoked because that method returns a value; the method invoke(Runnable) does not. 
In this case, the type of the lambda expression () -> "done" is Callable<T>.
================================
Serialization:
================================
You can serialize a lambda expression if its target type and its captured arguments are serializable. 
However, like inner classes, the serialization of lambda expressions is strongly discouraged.
 */
}
