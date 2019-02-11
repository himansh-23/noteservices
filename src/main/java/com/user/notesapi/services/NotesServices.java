package com.user.notesapi.services;

import java.util.List;
import com.user.notesapi.dto.NotesDTO;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
public interface NotesServices {

	 void createNote(String token,NotesDTO notesDTO) throws NoteException;
	 void updateNote(String token,Notes notes)throws NoteException;
	 void deleteNote(String token,long id)throws NoteException;
	 List<Notes> listAllNotes(String token,String archive,String trash)throws NoteException;
	 public List<Notes> matchedNotes(String token, String searchContent,boolean isArchive,boolean isTrash) throws NoteException;
}

