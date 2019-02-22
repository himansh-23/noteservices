package com.user.notesapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.notesapi.entity.CollaboratorNotes;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.CollabRepository;
import com.user.notesapi.repository.NotesRepository;
import com.user.notesapi.util.TokenVerify;

@Service
public class CollaboratorServiceImpl implements CollaboratorService {

	@Autowired
	CollabRepository collabRepository;
	
	@Autowired
	NotesRepository notesRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${spring.ROOT_URI2}")
	private String ROOT_URI2;
	
	@Override
	public Long addPersonToNote(String token, Long noteId, Long userId)  throws NoteException
	{
	//	TokenVerify.tokenVerifing(token);
//		long id=-1;
		Optional<Long> value=collabRepository.findBy(noteId, userId);
		if(value.isPresent())
		{
			return -1L;
		}
		else
		{
			CollaboratorNotes collabNote=new CollaboratorNotes(noteId,userId);
			collabRepository.save(collabNote);
			return 1L;
		}
		
	}

	@Override
	public List<Notes> getCollabNotes(String token) throws NoteException {
		long userId=TokenVerify.tokenVerifing(token);
		
		//List<Long> noteIds=collabRepository.findAllById(userId).get();
		Optional<List<Long>> noteIds=collabRepository.findAllById(userId);
		if(noteIds.isPresent())
		{
			return notesRepository.findAllCollabNotes(noteIds.get()).get();
		}
		else
		{
			return new ArrayList<Notes>();
		}
	}

	@Override
	public boolean deleteCollabNote(String token, long noteId,String email) throws NoteException {
		
		boolean delete=false;
		long userId=TokenVerify.tokenVerifing(token);
		
		Notes note=notesRepository.findById(noteId).get();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("email", email);
		ResponseEntity<Long> response=restTemplate.getForEntity(ROOT_URI2+"?email="+email, Long.class);
		long userIdFromEmail=response.getBody();
		if(userIdFromEmail == userId )
		{
		 long collabid=collabRepository.findBy(note.getId(), userIdFromEmail).get();
		 collabRepository.deleteById(collabid);
		 delete=true;
		}
		
		else if(note.getUserid() == userId)
		{
			long collabid=collabRepository.findBy(note.getId(), userIdFromEmail).get();
			 collabRepository.deleteById(collabid);
			 delete=true;
		}
		return delete;
	}

}
