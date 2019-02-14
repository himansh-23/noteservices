package com.user.notesapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		//CollaboratorNotes collabNote=null;
		/*return collabRepository.findBy(noteId, userId).map(x -> {
			return (Long)x;
					}).orElseGet( () ->{
						 collabNote=new CollaboratorNotes(noteId,userId);
						collabRepository.save(collabNote);
						return (Long)-1L;
					});*/
		
		
  //       
	//	
		
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
	public void deleteCollabNote(String token, long noteId) throws NoteException {
		
		long userId=TokenVerify.tokenVerifing(token);
		userId=31;
		Long id	=collabRepository.findBy(noteId,userId).get();
		collabRepository.deleteById(id);
		
	}

}
