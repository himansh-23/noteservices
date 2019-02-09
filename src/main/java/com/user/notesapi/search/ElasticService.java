package com.user.notesapi.search;

import java.util.List;

import com.user.notesapi.entity.Notes;

public interface ElasticService {

	void save(Object message);
	List<Notes> search(String searchContent);
	
	

}
