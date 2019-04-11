package com.user.notesapi.appconfig;

import java.util.concurrent.CountDownLatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.search.ElasticService;

/**
 * 
 * @author administrator
 *	@Purpose Receiver All RabbitMQ Queued Message
 *	@Version 1.3
 *	
 * */
@Component
public class Receiver {
	
	 private final String INDEX = "notesdata";
	  private final String TYPE = "note";  
	  
	 @Autowired
	 private ElasticService service;
	  @Autowired
	  private ObjectMapper mapper;
	  
/**
 * @Purpose All Data Coming With message
 * @param message
 */
	    public void receiveMessage(Notes message) {
	    	
	    	System.out.println(message);
	    	System.out.println("Received <" + message + ">");
	        // Inserting Data To Elastic Search
	        service.save(message);
	    }


}
