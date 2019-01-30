package com.user.notesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.notesapi.entity.Labels;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.response.Response;

@RestController
@RequestMapping("api/label")
public class LabelsController {
	
	@Autowired
	LabelsRepository labelrepo;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createLabel(@RequestBody Labels label)
	{
		labelrepo.save(label);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label Created");
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

}
