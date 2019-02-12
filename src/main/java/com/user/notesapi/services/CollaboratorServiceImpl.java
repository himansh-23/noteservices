package com.user.notesapi.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.notesapi.entity.CollaboratorNotes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.CollabRepository;
import com.user.notesapi.util.TokenVerify;

public class CollaboratorServiceImpl implements CollaboratorService {

	@Autowired
	CollabRepository collabRepository;
	
	@Override
	public void addPersonToNote(String token, Long noteId, Long userId)  throws NoteException
	{
		TokenVerify.tokenVerifing(token);
		CollaboratorNotes collabNote=new CollaboratorNotes(1,noteId,userId);
		collabRepository.save(collabNote);
		
	}

}
