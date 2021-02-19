package org.pranav.java8.concepts;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private static LogManager lgmngr = LogManager.getLogManager(); 
    
    // lgmngr now contains a reference to the log manager. 
    private static Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME); 

	@BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    @BeforeEach
    void init() {
        log.info("@BeforeEach - executes before each test method in this class");
    }
    
    @Test
	void givenOddNumber_whenCheckingIsNumberEven_thenFalse() {
		boolean result = 2>3;

		Assertions.assertFalse(result);
	}
}
