package com.user.notesapi.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
import com.user.notesapi.entity.Labels;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.repository.NotesRepository;
import com.user.notesapi.response.Response;
import com.user.notesapi.services.LabelService;
import com.user.notesapi.appconfig.*;
/**
 * 
 * @author administrator
 * @Purpose CRUD OPeration Of Label
 *
 */
@RestController
@RequestMapping("/api/label")
@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"token"})
public class LabelsController {
	
	@Autowired
	LabelsRepository labelrepo;
	
	@Autowired
	NotesRepository noterepo;
	
	@Autowired
	LabelService labelService;
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	/**
	 * 
	 * @param label
	 * @param token
	 * @return crete a Note in Data-Base With PArticular Name 
	 * @throws NoteException
	 */
	@PostMapping()
	public ResponseEntity<Response> createLabel(@RequestBody Labels label,@RequestHeader String token) throws NoteException
	{
		
		labelService.createLabel(token, label);
		Response response=new Response();
		response.setStatusCode(166);
		
		response.setStatusMessage("Label Created");
		rabbitTemplate.convertAndSend(ApplicationConfiguration.queueName,"th is mesdfsd");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param label
	 * @param token
	 * @return response to whether note updated or not
	 * @throws NoteException
	 */
	@PutMapping()
	public ResponseEntity <Response> updateLabel(@RequestBody Labels label,@RequestHeader String token) throws NoteException
	{
		//System.out.println(label);
			labelService.updateLabel(token, label);
		 	Response response=new Response();
			response.setStatusCode(166);
			response.setStatusMessage("Label Updated");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param noteid
	 * @param labelid
	 * @return response whether note added to label or not
	 */
	@PostMapping("/addnotetolabel")
	public ResponseEntity<Response> labelAddToNote(@RequestParam String noteid,@RequestParam String labelid)
	{
		System.out.println(noteid+"  "+labelid);

		long noteidd=Long.parseLong(labelid);
		long labelidd=Long.parseLong(noteid);
		
		Labels singleLabel=labelrepo.findById(labelidd).get();
		Notes singleNote=noterepo.findById(noteidd).get();
	
		singleNote.getLabels().add(singleLabel);
		singleLabel.getNotes().add(singleNote);
		labelrepo.save(singleLabel);
		noterepo.save(singleNote);
		
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label Added To Note");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param token
	 * @param noteid
	 * @param labelid
	 * @return Success message if note is Deleted
	 * @throws NoteException
	 */
	@DeleteMapping("/deletenotetolabel")
	public ResponseEntity<Response> labelDeleteToNote(@RequestHeader String token,@RequestParam String noteid,@RequestParam String labelid) throws NoteException
	{
		System.out.println(noteid+"  "+labelid);
		
	//	long noteidd=Long.parseLong(labelid);
	//	long labelidd=Long.parseLong(noteid);

		labelService.labelDeleteToNote(token, labelid, noteid);
		
//		Labels singleLabel=labelrepo.findById(labelidd).get();
//		Notes singleNote=noterepo.findById(noteidd).get();
//		
//		singleNote.getLabels().remove(singleLabel);
//		singleLabel.getNotes().remove(singleNote);
//		labelrepo.save(singleLabel);
//		noterepo.save(singleNote);

		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label remove To Note");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	/**
	 * 
	 * @param token
	 * @return List of All Labels Of A Particular User_id.
	 * @throws NoteException
	 */
	@GetMapping
	public ResponseEntity<List<Labels>> getAllLabels(@RequestHeader String token) throws NoteException
	{
////		long userid=0;
////		try {
////		 userid=TokenVerify.tokenVerifing(token);
////		}
////		catch(Exception e)
////		{
////			throw new NoteException(123,e.getMessage()); 
////		}
////		List<Labels> list = labelrepo.findAllById(userid).get();
//			labelService.listLabels(token);
			List<Labels> list=labelService.listLabels(token);
		return new ResponseEntity<List<Labels>>(list,HttpStatus.OK);
	}
	/**
	 * 
	 * @param labelid
	 * @return 
	 * @throws NoteException
	 */
	@DeleteMapping
	public ResponseEntity<Response> deleteLabel(@RequestHeader String token,@RequestParam String labelId) throws NoteException
	{
		System.out.println("api hit");
			labelService.deleteLabel(token, labelId);
			Response response = new Response();
			response.setStatusCode(166);
			response.setStatusMessage("Label Deleted Successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	  


}
