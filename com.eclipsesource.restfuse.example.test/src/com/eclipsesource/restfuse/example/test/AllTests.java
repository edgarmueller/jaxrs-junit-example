package com.eclipsesource.restfuse.example.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith( Suite.class )
@SuiteClasses( {
	SampleServiceTest.class
} )
public class AllTests {
	public static String BASE_URL = "http://localhost:" + System.getProperty( "org.osgi.service.http.port" );

}
