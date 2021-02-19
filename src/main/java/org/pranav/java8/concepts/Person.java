package org.pranav.java8.concepts;


import java.time.MonthDay;

import lombok.Data;

@Data
public 	class Person {
	private long empId;
	private String name;
	private MonthDay dob;// = ZonedDateTime.of(1988, 06, 30, 00, 00, 00, 00, ZoneOffset.UTC);
	private double salaryinINR;
	private String department;
}