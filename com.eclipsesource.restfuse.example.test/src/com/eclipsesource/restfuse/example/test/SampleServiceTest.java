package com.eclipsesource.restfuse.example.test;

import static com.eclipsesource.restfuse.example.test.AllTests.BASE_URL;
import static junit.framework.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import com.eclipsesource.restfuse.Destination;
import com.eclipsesource.restfuse.HttpJUnitRunner;
import com.eclipsesource.restfuse.Method;
import com.eclipsesource.restfuse.Response;
import com.eclipsesource.restfuse.annotation.Context;
import com.eclipsesource.restfuse.annotation.HttpTest;
import com.eclipsesource.restfuse.example.service.SampleService;

@RunWith(HttpJUnitRunner.class)
public class SampleServiceTest {

	@Rule
	public Destination destination = new Destination(this, BASE_URL);

	@Context
	private Response response;

	private SampleService service;

	private static CountDownLatch dependencyLatch = new CountDownLatch(1);

	@Before
	public void dependencyCheck() throws InterruptedException {
		// Wait for OSGi dependencies
		dependencyLatch.await(10, TimeUnit.SECONDS);
	}

	public synchronized void setQuote(SampleService service) {
		this.service = service;
		dependencyLatch.countDown();
	}

	public synchronized void unsetQuote(SampleService service) {
		if (this.service == service) {
			this.service = null;
		}
	}

	@SuppressWarnings("deprecation")
	@HttpTest(path = "/services/hello", method = Method.GET)
	public void testBody() {
		String body = response.getBody(String.class);
		assertEquals("hello", body);
	}
}
