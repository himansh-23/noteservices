package com.user.notesapi.services;

import java.time.LocalDate;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.notesapi.dto.NotesDTO;
import com.user.notesapi.entity.Labels;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.repository.NotesRepository;
import com.user.notesapi.util.TokenVerify;

@Service
public class NotesServicesImpl implements NotesServices {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	NotesRepository notesRepository;
	
	@Autowired
	LabelsRepository labelRepository;
	public void createNote(String token,NotesDTO notesDTO)throws NoteException
	{
		long id=TokenVerify.tokenVerifing(token);
	//	System.out.println(id);
		notesDTO.setUserid(id);
		Notes note = modelMapper.map(notesDTO, Notes.class);
		note.setCreateStamp(LocalDate.now());
		note.setLastModifiedStamp(LocalDate.now());
		notesRepository.save(note);
	}
	
	public void updateNote(String token,Notes notes)throws NoteException
	{
		TokenVerify.tokenVerifing(token);
		notes.setLastModifiedStamp(LocalDate.now());
		notesRepository.save(notes);
	}
	
	public void deleteNote(String token,Notes notes)throws NoteException
	{
		TokenVerify.tokenVerifing(token);
	//	labelRepository
		notes.getLabels().clear();
		labelRepository.findAll().forEach(x ->this.removeNote(notes,x));
		notesRepository.delete(notes);
	}
	
	public List<Notes> listAllNotes(String token)throws NoteException //,String value
	{
		long id=TokenVerify.tokenVerifing(token);
		return notesRepository.findAllById(id).orElseThrow(() -> new NoteException(101,"No Note Found"));
	}
	
	private void removeNote(Notes notes,Labels l)
	{
		l.getNotes().remove(notes);
		notesRepository.save(notes);
	}
	
	/*public List<Notes> listAllPinnedNotes(String token)throws NoteException
	{
		long id=TokenVerify.tokenVerifing(token);
		return notesRepository.findAllPinnedNote(id).orElseThrow(() -> new NoteException(101,"No Note Found"));
	}
	public List<Notes> listAllArchiveNotes(String token)throws NoteException{
		long id=TokenVerify.tokenVerifing(token);
		return notesRepository.findAllArchiveNote(id).orElseThrow(() -> new NoteException(101,"No Note Found"));
	}
	public List<Notes> listAllTrashNotes(String token)throws NoteException{
		long id=TokenVerify.tokenVerifing(token);
		return notesRepository.findAllTrashNote(id).orElseThrow(() -> new NoteException(101,"No Note Found"));
	}*/
}
