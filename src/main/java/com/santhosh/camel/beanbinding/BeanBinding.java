package com.santhosh.camel.beanbinding;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.EndpointInject;
import org.apache.camel.InOnly;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.language.XPath;

public class BeanBinding {
	
	
	
	//Drop a message into the below queue and sysout is printed on the console.no need for manual route configuration.
		@Consume(uri = "activemq:queue:inputQueue")
		public void consumeFromQueue(@Body
		String body) {
		
			System.out.println("message Received "+body);
		}

		//Same as above.Can use xpath.
		@Consume(uri = "activemq:queue:inputQueueXpath")
		public void doSomething(@XPath("/foo/bar/text()") String xpathValue, @Body
		String body) {
		
			System.out.println("The xpath value is"+xpathValue+" and body is"+body);
			sendToRouteQueue(body);
		}
		
		
		//To  send  messages from POJOs 

		@EndpointInject(uri="activemq:queue:routeQ")
		ProducerTemplate producer;

		@InOnly
		public void sendToRouteQueue(String body) {
		// lets send a message
			
			producer.sendBody(body);
		}


		
		

}
