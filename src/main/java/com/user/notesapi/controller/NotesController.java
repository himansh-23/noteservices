
package com.user.notesapi.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.notesapi.dto.NotesDTO;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.response.Response;
import com.user.notesapi.services.NotesServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Himanshu
 * @Purpose For CRUD Operation of Note And Searching Of Notes Through Elastic Search
 *
 */
@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"token"})
public class NotesController {

	 @Autowired
	 NotesServices noteServices;
	  
	 static Logger logger=LoggerFactory.getLogger(NotesController.class);
	/**
	 * 
	 * @param notesDTO
	 * @param token
	 * @return
	 * @throws NoteException
	 */
	@PostMapping
	public ResponseEntity<Response> createNote(@RequestBody NotesDTO notesDTO,@RequestHeader("token")String token)throws NoteException,Exception
	{
		//RabbitMq Server Send
		
		logger.info("Rabbit Template Send");
		
		noteServices.createNote(token, notesDTO);
		logger.info("Data Send To DataBase");
		Response response=new Response();
		
		response.setStatusCode(166);
		response.setStatusMessage("Note Created Successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param notes
	 * @param token
	 * @return
	 * @throws NoteException
	 */
	@PutMapping
	public ResponseEntity<Response> updateNote(@RequestBody Notes notes,@RequestHeader("token")String token)throws NoteException
	{ 
		//String token = request.getHeader("Authorization");d
		noteServices.updateNote(token,notes);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Note Updated Successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	/**
	 * 
	 * @param id
	 * @param token
	 * @return response whether Note Deleted or Not
	 * @throws NoteException
	 */
	@DeleteMapping
	public ResponseEntity<Response> deleteNote(@RequestParam int id,@RequestHeader("token")String token)throws NoteException
	{
		
		noteServices.deleteNote(token, id);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Note Deleted Successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param token
	 * @param archive
	 * @param trash
	 * @return Return All Notes Of A Particular Token
	 * @throws NoteException
	 */
	@GetMapping
	public ResponseEntity<List<Notes>> listAllNotes(@RequestHeader("token")String token,@RequestParam String archive,@RequestParam String trash)throws NoteException //@PathVariable("value") String value,
	{
		
		List<Notes> list = noteServices.listAllNotes(token,archive,trash);
		return new ResponseEntity<List<Notes>>(list,HttpStatus.OK);
	} 
	
	
	@GetMapping("/search")
	public ResponseEntity<List<Notes>> searchNotes(@RequestHeader("token")String token,@RequestParam String searchContent) throws NoteException
	{
		System.out.println("coming");
		noteServices.matchedNotes(token,searchContent);
		System.out.println("Exit");
		return new ResponseEntity(new ArrayList<Notes>(),HttpStatus.OK);
	}
	
}
