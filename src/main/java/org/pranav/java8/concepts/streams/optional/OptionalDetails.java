package org.pranav.java8.concepts.streams.optional;

public class OptionalDetails {
	/*
	*=========================================================================================================================
	*	----->utility methods after get() default behavior incase of null/empty value contained in Optional<------------
	*    public <X extends Throwable> T orElseThrow				(Supplier<? extends X> exceptionSupplier) throws X {}
	*	 public T 						orElseGet				(Supplier<? extends T> other) {}
	*    public T 						orElse					(T other) {}
	*    ------------------------>utility methods over value contained in Optional<--------------------------------------
	*    public<U> Optional<U> 			flatMap					(Function<? super T, Optional<U>> mapper) {}
	*    public<U> Optional<U> 			map						(Function<? super T, ? extends U> mapper) {}
	*    public Optional<T> 			filter					(Predicate<? super T> predicate) {}
	*    --------------------------->checking if non-null and non-empty<-------------------------------------------------
	*    public void 					ifPresent				(Consumer<? super T> consumer) {}
	*    public boolean 				isPresent				() {}
	*    ------------------------>getting actual instance wrapped in optional<-------------------------------------------
	*    public T 						get						() {}
	*    -------------------------->instance without any static '.of()' methods<-----------------------------------------
	*    public static <T> Optional<T>  ofNullable				(T value) {}
	*    public static <T> Optional<T>  of						(T value) {}
	*    public static<T> Optional<T> 	empty					() {}
	*    -------------->private constructor so can't create any instance without any static '.of()' methods<------------- 
	*    private 						Optional				(T value) {}
	*    private 						Optional				() {} 
	*===========================================================================================================================
	 * UseCase:
	 * Let’s start with a simple use-case. Before Java 8, any number of operations involving accessing an object’s methods or properties could result in a NullPointerException:
	 *----------------------------------------------------------------------------		
	 *String isocode = user.getAddress().getCountry().getIsocode().toUpperCase();
	 *----------------------------------------------------------------------------
	 *If we wanted to make sure we won’t hit the exception in this short example, we would need to do explicit checks for every value before accessing it:
	 *-------------------------------------------------------------
		if (user != null) {
    		Address address = user.getAddress();
    		if (address != null) {
        		Country country = address.getCountry();
        			if (country != null) {
            			String isocode = country.getIsocode();
            			if (isocode != null) {
                			isocode = isocode.toUpperCase();
            			}
        			}
    		}
		}
	*----------------------------------------------------------------
	*As you can see, this can easily become cumbersome and hard to maintain.
	*To ease this process, let’s take a look at how we can use the Optional class instead, from creating and verifying an instance, 
	*	to using the different methods it provides and combining it with other methods that return the same type, 
	*		the latter being where the true power of Optional lies.
	*
	*
	*
	*Creating Optional Instances
	*	To reiterate, an object of this type can contain a value or be empty. You can create an empty Optional by using the method with the same name:
	*-----------------------------------------------------------
	@Test(expected = NoSuchElementException.class)
	public void whenCreateEmptyOptional_thenNull() {
    	Optional<User> emptyOpt = Optional.empty();
    	emptyOpt.get();//calling get() - without checking ifPresent() or isPresent()
	}
	*-----------------------------------------------------------
	*Not surprisingly, attempting to access the value of the emptyOpt variable results in a NoSuchElementException.
	*
	*
	*
	*To create an Optional object that can contain a value – you can use the of() and ofNullable() methods. 
	*The difference between the two is that the of() method will throw a NullPointerException if you pass it a null value as an argument:
	*----------------------------------------------------------------
		@Test(expected = NullPointerException.class)
		public void whenCreateOfEmptyOptional_thenNullPointerException() {
    		Optional<User> opt = Optional.of(user);
		}
	*-----------------------------------------------------------------	
	****As you can see, we’re not completely rid of the NullPointerException. For this reason, you should only use of() when you are sure the object is not null.
	*
	*
	*
	*If the object can be both null or not-null, then you should instead choose the ofNullable() method:
	*----------------------------------------------------------------
	*Optional<User> opt = Optional.ofNullable(user);
	*----------------------------------------------------------------
	*
	*To avoid this exception(NoSuchElementException|NullPointerException), you can choose to first verify if a value is present or not:
	*----------------------------------------------------------------
	@Test
	public void whenCheckIfPresent_thenOk() {
    	User user = new User("john@gmail.com", "1234");
    	Optional<User> opt = Optional.ofNullable(user);
    	assertTrue(opt.isPresent());
    	assertEquals(user.getEmail(), opt.get().getEmail());
	}
	*----------------------------------------------------------------
	*
	*
	*Another option for checking the presence of a value is the ifPresent() method. 
	*In addition to performing the check, 
	*this method also takes a Consumer argument and executes the lambda expression if the object is not empty:
	*----------------------------------------------------------------
	opt.ifPresent( u -> assertEquals(user.getEmail(), u.getEmail()));
	*----------------------------------------------------------------
	*Above the assertion is only executed if the user object is not null.
	*
	*
	*
	***Returning Default Values:1. orElse(T other) 2. orElseGet(Supplier<? extends T> other) 3. orElseThrow(Supplier<? extends X> exceptionSupplier)  
	*Difference between orElse and orElseGet 
	* - orElse(T other) -> It will create the other instance or execute the lambda/method-creation_Of_T even if get() has non-null value.
	* - orElseGet(Supplier<? extends T> other) -> Will only be executed if get() has null/non-empty value.
	* 
	* 
	* 
	* Transforming Values:
	* Optional values can be transformed in a number of ways:1> map() 2> flatMap() methods.
	* 
	* First, let’s see an example that uses the map() API:
	*-------------------------------------------------------------- 
	@Test
	public void whenMap_thenOk() {
    	User user = new User("anna@gmail.com", "1234");
    	String email = Optional.ofNullable(user)
      		.map(u -> u.getEmail()).orElse("default@gmail.com");
    	assertEquals(email, user.getEmail());
	}
	*-------------------------------------------------------------- 
	*map() applies the Function argument to the value, then returns the result wrapped in an Optional. 
	*This makes it possible to apply and chain further operations on the response – such orElse() here.
	*
	*
	*By comparison, flatMap() also takes a Function argument that is applied to an Optional value, and then returns the result directly.
	*To see this in action, let’s add a method that returns an Optional to the User class:
	*-------------------------------------------------------------- 
	public class User {    
    	private String position;
    	public Optional<String> getPosition() {
        	return Optional.ofNullable(position);
    	}    
    	//...
	}
	*-------------------------------------------------------------- 	
	*Since the getter method returns an Optional of String value, you can use as the argument for flatMap(), where this is called for an Optional User object. 
	*The return will be the unwrapped String value:
	*--------------------------------------------------------------
	@Test
	public void whenFlatMap_thenOk() {
    	User user = new User("anna@gmail.com", "1234");
    	user.setPosition("Developer");
    	String position = Optional.ofNullable(user)
      		.flatMap(u -> u.getPosition()).orElse("default");//flatMap -> flattens optional<String> to String
      	assertEquals(position, user.getPosition().get());
	}
	*-------------------------------------------------------------- 
	* 
	* 
	* 
	* Filtering Values : 1.> filter(Predicate<? super T> predicate)
	* Optional class also offers the possibility to “filter” them based on a condition.
	* The filter() method takes a Predicate as an argument and 
	* 	returns the value as it is if the test evaluates to true. 
	* 		Otherwise, if the test is false, the returned value is an empty Optional.
	* 
	* Let’s see an example of accepting or rejecting a User based on a very basic email verification:
	@Test
	public void whenFilter_thenOk() {
    	User user = new User("anna@gmail.com", "1234");
    	Optional<User> result = Optional.ofNullable(user)
      		.filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
    	assertTrue(result.isPresent());
	}
	*The result object will contain a non-null value as a result of it passing the filter test.
	*
	*Chaining Methods of the Optional class.
	*For more powerful uses of Optional, you can also chain different combinations of most of its methods, 
	*given that most of them return objects of the same type.
	*Let’s rewrite the example in the introduction using Optional.
	*
	*First, let’s refactor the classes so that the getter methods return Optional references:
	*---------------------------------------------------------
	public class User {
    	private Address address;
    	public Optional<Address> getAddress() {
        	return Optional.ofNullable(address);
    	}
    	// ...
	}
	public class Address {
    	private Country country;
        public Optional<Country> getCountry() {
        	return Optional.ofNullable(country);
    	}
    	// ...
	}
	*---------------------------------------------------------
	*The structure above can be visually represented as a nested set:
	*Now you can remove the null checks and use the Optional methods instead:
	**---------------------------------------------------------
	@Test
	public void whenChaining_thenOk() {
    	User user = new User("anna@gmail.com", "1234");
    	String result = Optional.ofNullable(user)
      		.flatMap(u -> u.getAddress())
      		.flatMap(a -> a.getCountry())
      		.map(c -> c.getIsocode())
      		.orElse("default");
    	assertEquals(result, "default");
	}
	*The code above can be further reduced by using method references:
	*--------------------------------------------------------
	String result = Optional.ofNullable(user)
  		.flatMap(User::getAddress)
  		.flatMap(Address::getCountry)
  		.map(Country::getIsocode)
  		.orElse("default");
	As a result, the code looks much cleaner than our early cumbersome, conditional-driven version.
	*
	*Notes:https://dzone.com/articles/using-optional-correctly-is-not-optional
	*1> Optional is meant to be used for returnTypes of a method(ex - getters) and not suited for argument,constructor,field etc.
	*2> Prefer the use .ofNullable(T) over .of(T) for testing the passed non-optional parameters in a method to avoid exception(NoSuchElementException|NullPointerException).
	*3> For providing defaults prefer use of orElseGet() over orElse() incase of heavier calls like dbfetch/ apiFetch etc.
	*4> PreferOptional.empty()to initialize anOptionalinstead of anullvalue.Optionalis just a container/box and it is pointless to initialize it with null.
	*5> Do Not Use Optional to Return Empty Collections or Arrays.
	*6> Do Not Confuse Optional.of() and Optional.ofNullable()
	*	- Optional.of(null)will throwNullPointerException
	*	- Optional.ofNullable(null)will result in an Optional.empty
	*7>Avoid Optional <T> and Choose Non-Generic OptionalInt, OptionalLong, or OptionalDouble
	*	- OptionalInt price = OptionalInt.of(50) //unwrap via getAsInt()
	*	- OptionalLong price = OptionalLong.of(50L) //unwrap via getAsLong()
	*	- OptionalDouble price = OptionalDouble.of(50.43d) //unwrap via getAsDouble()	
	 */
	
}
