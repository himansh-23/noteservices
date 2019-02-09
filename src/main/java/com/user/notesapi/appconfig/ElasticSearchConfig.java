package com.user.notesapi.appconfig;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.elasticsearch.common.transport.TransportAddress;

@Configuration
public class ElasticSearchConfig{
	
	@Value("${elasticsearch.host:localhost}") 
    public String host;
    @Value("${elasticsearch.port:9200}") 
    public int port;
	
	/*
	 * @SuppressWarnings("resource")
	 * 
	 * @Bean public Client client(){ TransportClient client = null; try{
	 * System.out.println("host:"+ host+"port:"+port); client = new
	 * PreBuiltTransportClient(Settings.EMPTY) .addTransportAddress(new
	 * TransportAddress(InetAddress.getByName(host), port)); } catch
	 * (UnknownHostException e) { e.printStackTrace(); } return client; }
	 */
	 
}
