package com.user.notesapi.search;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.search.uhighlight.UnifiedHighlighter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.MultiSearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.notesapi.entity.Notes;

@Service
public class ElasticSearchImpl implements ElasticService {

	@Autowired
	private RestHighLevelClient client;
	
	 @Autowired
	  private ObjectMapper objectMapper;
	 
	 @Override
	public void save(Object message) {	
		Map dataMap = objectMapper.convertValue(message, Map.class);
		
		IndexRequest indexRequest = new IndexRequest("notesdata", "notes").id(dataMap.get("id")+"").source(dataMap);
		try {
			
		IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Notes> search(String searchContent) {
			
	//	SearR
	//	client.search(searchRequest, headers)
	        
		
		return null;
	}

}
