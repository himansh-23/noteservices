package com.user.notesapi.appconfig;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.mkyong.book.repository")
public class ElasticSearchConfig{
	
	@Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
	
	@Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
	
	
	
	
	 
}
