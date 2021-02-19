package org.pranav.java8.concepts;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
/*
In abstraction, implementation complexities are hidden using 
 - abstract classes and 
 - interfaces. 
While in encapsulation, the data is hidden using 
 - methods of getters and setters. 
 The objects that help to perform abstraction are encapsulated. 
 Whereas the objects that result in encapsulation need not be abstracted.
 =========================
 Data Abstraction:
 =========================
 End-User/API-User will only know of provided APIs list of AbstractClasses/Interfaces
 but in reality there could be a lot of implementing class having their own
 internal methods and states.
 Implementing an interface allows a class to become more formal about 
 the behavior it promises to provide. 
 Interfaces form a contract between 
 	-	the class and 
 	-	the outside world, 
 and this contract is enforced at build time by the compiler.
 ============================
 Data encapsulation:
 ============================
 Data i.e fields and their values can only accessed via access modifier applied 
 over the getters and setters.
 - Hiding internal state and 
 - requiring all interaction to be performed through an object's methods 
 is known as data encapsulation 
 
*/