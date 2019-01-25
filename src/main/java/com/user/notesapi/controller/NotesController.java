package com.user.notesapi.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.notesapi.dto.NotesDTO;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.response.Response;
import com.user.notesapi.services.NotesServices;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"token"})
public class NotesController {

	@Autowired
	NotesServices noteServices;

	@PostMapping("/createnote")
	public ResponseEntity<Response> createNote(@RequestBody NotesDTO notesDTO,@RequestHeader("token")String token)throws NoteException
	{
		//String token1=request.getHeader("token");
		System.out.println(token);
		//String token="";
		noteServices.createNote(token, notesDTO);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Note Created Successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/updatenote")
	public ResponseEntity<String> updateNote(@RequestBody Notes notes, HttpServletRequest request)throws NoteException
	{ 
		String token = request.getHeader("Authorization");
		noteServices.updateNote(token,notes);
		return new ResponseEntity<String>("Note Updated Successfully",HttpStatus.OK);
	}
	
	@PostMapping("/deletenote")
	public ResponseEntity<String> deleteNote(@RequestBody Notes notes, HttpServletRequest request)throws NoteException
	{
		String token = request.getHeader("Authorization");
		noteServices.deleteNote(token, notes);
		return new ResponseEntity<String>("Note Deleted Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/allnotes")
	public ResponseEntity<List<Notes>> listAllNotes(@RequestHeader("token")String token,HttpServletRequest request)throws NoteException //@PathVariable("value") String value,
	{
		//System.out.println(value);
//		String token = request.getHeader("Authorization");
		List<Notes> list = noteServices.listAllNotes(token);
		return new ResponseEntity<List<Notes>>(list,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping public ResponseEntity<List<Notes>>
	 * listOfPinnedNote(HttpServletRequest request)throws NoteException { String
	 * token = request.getHeader("Authorization"); List<Notes> list =
	 * noteServices.listAllNotes(token); return new
	 * ResponseEntity<List<Notes>>(list,HttpStatus.OK); }
	 */
}
