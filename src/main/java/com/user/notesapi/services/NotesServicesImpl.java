package com.user.notesapi.services;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.notesapi.appconfig.ApplicationConfiguration;
import com.user.notesapi.dto.CollabUserDetails;
import com.user.notesapi.dto.NotesDTO;
import com.user.notesapi.dto.SendingNotes;
import com.user.notesapi.entity.Labels;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.CollabRepository;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.repository.NotesRepository;
import com.user.notesapi.search.ElasticService;
import com.user.notesapi.util.TokenVerify;

@Service
@PropertySource("classpath:application.properties")
public class NotesServicesImpl implements NotesServices {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private LabelsRepository labelRepository;
	
	@Autowired
	private CollaboratorService collabservice;
	
	@Autowired
	private CollabRepository collabRepo;
	
	@Autowired
	private ElasticService elasticService;
	 
	 @Autowired
	 private RabbitTemplate rabbitTemplate;
	 
	 @Autowired
	 private ElasticService service;
	 
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Value("${spring.ROOT_URI}")
	 private String ROOT_URI; 
	
	public void createNote(String token,NotesDTO notesDTO)throws NoteException
	{
		long id=TokenVerify.tokenVerifing(token);
		notesDTO.setUserid(id);
		Notes note = modelMapper.map(notesDTO, Notes.class);
		note.setCreateStamp(LocalDate.now());
		note.setLastModifiedStamp(LocalDate.now());
		note=notesRepository.save(note);
			rabbitTemplate.convertAndSend(ApplicationConfiguration.queueName, note);
			//System.out.println(obj.writeValueAsString(note));
	}
	
	@Override
	public void updateNote(String token,Notes notes)throws NoteException
	{
		TokenVerify.tokenVerifing(token);
		notes.setLastModifiedStamp(LocalDate.now());
		System.out.println(notes.isArchive());
		Notes updateNote=notesRepository.save(notes);
		elasticService.update(updateNote);
	}
	
	@Override
	public void deleteNote(String token,long notesId) throws NoteException
	{
		TokenVerify.tokenVerifing(token);
		Notes idnote=notesRepository.findById(notesId).get();
		idnote.getLabels().clear();
		labelRepository.findAll().forEach(x ->this.removeNote(idnote,x));
		notesRepository.delete(idnote);
		elasticService.delete(notesId);
	}
	
	@Override
	public/* List<Notes>*/ List<SendingNotes> listAllNotes(String token,String archive,String trash)throws NoteException //,String value
	{	
		long id=TokenVerify.tokenVerifing(token);
		//List<Notes> notes=;
		//return notesRepository.findAllById(id,Boolean.valueOf(archive),Boolean.valueOf(trash)).orElse( new ArrayList<Notes>());
		List<Notes> notesList=notesRepository.findAllById(id,Boolean.valueOf(archive),Boolean.valueOf(trash)).orElse( new ArrayList<Notes>());
		notesList.addAll(collabservice.getCollabNotes(token));
		
		List<SendingNotes> xyz=new ArrayList<SendingNotes>();
	
		for(int i=0;i<notesList.size();i++)
		{
			List<BigInteger> ll=new ArrayList<BigInteger>();
			Optional<List<Object>> optionalList=collabRepo.findAllUsersOfNote(notesList.get(i).getId());
			SendingNotes zz=null;
			if(optionalList.isPresent())
			{
				optionalList.get().stream().forEach(x -> ll.add((BigInteger)x));
				ResponseEntity<CollabUserDetails[]> response = restTemplate.postForEntity(ROOT_URI,ll,CollabUserDetails[].class);
				zz=new SendingNotes(notesList.get(i),Arrays.asList(response.getBody())); //ll at response.getBody()
			}
			else {
				zz=new SendingNotes(notesList.get(i),new ArrayList<CollabUserDetails>()); 
				
			}
			xyz.add(zz);	
		}
			
			return xyz;
		
//		return notesList;				//ArrayList<Notes::new
	}					
	
	
	
	/*Optional<List<Long>> noteIds=collabRepository.findAllById(userId);
	if(noteIds.isPresent())
	{
		return notesRepository.findAllCollabNotes(noteIds.get()).get();
	}
	else
	{
		return new ArrayList<Notes>();
	}*/
	
	
	private void removeNote(Notes notes,Labels l)
	{
		l.getNotes().remove(notes);
		notesRepository.save(notes);
	}
// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJJRCI6MzV9.l7OjtnAX5rSlX06Cu-SN_xGwRH8sKrPrmtfWT_JAkJI
	
	@Override
	public List<Notes> matchedNotes(String token, String searchContent,boolean isArchive,boolean isTrash) throws NoteException
	{
			long userid=TokenVerify.tokenVerifing(token);
			Map<String,Float> fields=new HashMap<String, Float>();
			fields.put("title", 3.0f);
			fields.put("content", 2.0f);
			
			Map<String,Object> restriction=new HashMap<String, Object>();
			restriction.put("archive", isArchive);
			restriction.put("trash",isTrash);
			restriction.put("userid",userid);
			List<Notes> list=service.multipleFieldQuery(fields, searchContent, restriction, "notesdata", "notes");
			return list;
	}
}
