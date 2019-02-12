package com.user.notesapi.services;

import com.user.notesapi.exception.NoteException;

public interface CollaboratorService {

	void addPersonToNote(String token,Long noteId,Long sharedNote) throws NoteException;
	
}
