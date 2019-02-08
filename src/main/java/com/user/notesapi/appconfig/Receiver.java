package com.user.notesapi.appconfig;

import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class Receiver {
	
	 private final String INDEX = "notesdata";
	  private final String TYPE = "note";  
	  
	 
	  @Autowired
	  private ObjectMapper mapper;
	  
	 private CountDownLatch latch = new CountDownLatch(1);

	    public void receiveMessage(String message) {
	    	
	    	System.out.println(message);
	    	System.out.println("Received <" + message + ">");
	    	
	        latch.countDown();
	    }

	    public CountDownLatch getLatch() {
	        return latch;
	    }

}
