package com.user.notesapi.services;

import java.util.List;
import com.user.notesapi.dto.NotesDTO;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;

public interface NotesServices {

	public void createNote(String token,NotesDTO notesDTO) throws NoteException;
	public void updateNote(String token,Notes notes)throws NoteException;
	public void deleteNote(String token,Notes notes)throws NoteException;
	public List<Notes> listAllNotes(String token)throws NoteException;//,String value
	/*
	 * public List<Notes> listAllPinnedNotes(String token)throws NoteException;
	 * public List<Notes> listAllArchiveNotes(String token)throws NoteException;
	 * public List<Notes> listAllTrashNotes(String token)throws NoteException;
	 */
	
	/*
	 * findAllPinnedNote(long userid); findAllArchiveNote findAllTrashNote(long
	 * userid);
	 */
}
