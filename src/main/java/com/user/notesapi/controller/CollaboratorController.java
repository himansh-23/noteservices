package com.user.notesapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.response.Response;

@RestController
@RequestMapping("/api/collab")
public class CollaboratorController {
	
	@PostMapping
	public ResponseEntity<Response> addCollabrator(@RequestHeader("token")String token,
			@RequestParam long sharedUserId ,@RequestParam long sharedNoteId) throws NoteException
	{
		
		
		return null;
	}

}
