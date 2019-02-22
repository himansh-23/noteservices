package com.user.notesapi.services;

import java.util.List;

import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;

public interface CollaboratorService {

	Long addPersonToNote(String token,Long noteId,Long sharedNote) throws NoteException;
	List<Notes> getCollabNotes(String token) throws NoteException;
	boolean deleteCollabNote(String token,long noteId,String email) throws NoteException;
}
