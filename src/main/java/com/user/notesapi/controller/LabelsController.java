package com.user.notesapi.controller;

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
import com.user.notesapi.entity.Labels;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.repository.NotesRepository;
import com.user.notesapi.response.Response;
import com.user.notesapi.services.LabelService;
import com.user.notesapi.util.TokenVerify;

@RestController
@RequestMapping("api/label")
@CrossOrigin(origins= {"http://localhost:4200"},exposedHeaders= {"token"})
public class LabelsController {
	
	@Autowired
	LabelsRepository labelrepo;
	
	@Autowired
	NotesRepository noterepo;
	
	@Autowired
	LabelService labelService;
	
	@PostMapping()
	public ResponseEntity<Response> createLabel(@RequestBody Labels label,@RequestHeader String token) throws NoteException
	{
//		 long userid=0;
//		 userid=TokenVerify.tokenVerifing(token);
//		 label.setUserid(userid);
//		 System.out.println(label);
//		
//		labelrepo.save(label);
		
		labelService.createLabel(token, label);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label Created");
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity <Response> updateLabel(@RequestBody Labels label,@RequestHeader String token) throws NoteException
	{
		 	Response response=new Response();
			response.setStatusCode(166);
			response.setStatusMessage("Label Updated");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/addnotetolabel")
	public ResponseEntity<Response> labelAddToNote(@RequestParam long noteid,@RequestParam long labelid)
	{
		Labels l=labelrepo.findById(labelid).get();
		Notes n=noterepo.findById(noteid).get();
		
	//	System.out.println("here");
		n.getLabels().add(l);
	//	System.out.println("here");
		l.getNotes().add(n);
	//	System.out.println("here");
		noterepo.save(n);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label Added To Note");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletenotetolabel")
	public ResponseEntity<Response> labelDeleteToNote(@RequestParam long noteid,@RequestParam long labelid)
	{
		Labels l=labelrepo.findById(labelid).get();
		Notes n=noterepo.findById(noteid).get();
		n.getLabels().remove(l);
		l.getNotes().remove(n);
		noterepo.save(n);
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Label remove To Note");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
//	
	@GetMapping("/alllabels")
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
	
	public ResponseEntity<Response> deleteLabel(@RequestBody Labels labelname,@RequestHeader String token) throws NoteException
	{
		long userid=0;
		try {
			userid=TokenVerify.tokenVerifing(token);
			//labelrepo.delete(labelname);
			
		noterepo.findAllById(userid).get().stream().map(x -> x.getLabels().stream().filter(temp ->check(temp,labelname.getLabelName())));
			}
		catch(Exception e)
		{
			throw new NoteException(123,e.getMessage()); 
		}
			
		Response response=new Response();
		response.setStatusCode(166);
		response.setStatusMessage("Note Deleted");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	private boolean check(Labels lb,String removelabel)
	{
		if(lb.getLabelName().equals(removelabel))
		{
			
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	

}
