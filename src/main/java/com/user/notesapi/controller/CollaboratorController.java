package com.user.notesapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.response.Response;
import com.user.notesapi.services.CollaboratorService;

@RestController
@RequestMapping("/api/collab")
@CrossOrigin(origins="http://localhost:4200",exposedHeaders= {"token"})
public class CollaboratorController {
	
	@Autowired
	private CollaboratorService collabservice;
	
	@PostMapping
	public ResponseEntity<Response> addCollabrator(@RequestHeader("token") String token,
			@RequestParam long sharedUserId ,@RequestParam long sharedNoteId) throws NoteException
	{
		System.out.println(sharedNoteId+"  "+sharedUserId);
		long res=collabservice.addPersonToNote(token, sharedNoteId, sharedUserId);
		Response response=new Response();
		if(res==-1L)
		{
			response.setStatusCode(166);
			response.setStatusMessage("Person Already Exists");
		}
		else
		{
			response.setStatusCode(200);
			response.setStatusMessage("Shared Note added");
		}
		
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
/*	@GetMapping
	public ResponseEntity<List<Notes>> getCollaborateNotes(@RequestHeader("token")String token) throws NoteException
	{
		List<Notes> list=collabservice.getCollabNotes(token);
		return new ResponseEntity<List<Notes>>(list,HttpStatus.OK);
	}*/
	
	@DeleteMapping
	public ResponseEntity<Response> deleteCollabNote(@RequestHeader("token") String token,@RequestParam String noteId,@RequestParam String email) throws NoteException
	{
		System.out.println(noteId+"  "+email);
		boolean delete=collabservice.deleteCollabNote(token, Long.valueOf(noteId), email);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("You Can't remove");
		if(delete==true)
		{
			response.setStatusMessage("Collaboration Removed");
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	

}
