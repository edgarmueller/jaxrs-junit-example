package com.eclipsesource.restfuse.example.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

@Path( "/hello" )
@XmlRootElement
public class SampleService implements ISampleService {
	
	@GET
	public String sayHello() {
		return "hello";
	}

}
