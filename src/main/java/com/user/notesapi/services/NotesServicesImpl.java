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
	private ModelMapper modelMapper;
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private LabelsRepository labelRepository;
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
		System.out.println(notes.isArchive());
		
		notesRepository.save(notes);
	}
	
	public void deleteNote(String token,long notes) throws NoteException
	{
		TokenVerify.tokenVerifing(token);
	//	labelRepository
		Notes idnote=notesRepository.findById(notes).get();
		idnote.getLabels().clear();
		labelRepository.findAll().forEach(x ->this.removeNote(idnote,x));
		notesRepository.delete(idnote);
	}
	
	public List<Notes> listAllNotes(String token,String archive,String trash)throws NoteException //,String value
	{
		boolean archive1;
				boolean trash1;
		if(archive.equals("true"))
			archive1=true;
		else
			archive1=false;
		
		if(trash.equals("true"))
			trash1=true;
		else
			trash1=false;
		
		long id=TokenVerify.tokenVerifing(token);
		return notesRepository.findAllById(id,archive1,trash1).orElseThrow(() -> new NoteException(101,"No Note Found"));
	}
	
	private void removeNote(Notes notes,Labels l)
	{
		l.getNotes().remove(notes);
		notesRepository.save(notes);
	}
	
//	public void trashNote(String token,long notes) throws NoteException
//	{
//		TokenVerify.tokenVerifing(token);
//		Notes idnote=notesRepository.findById(notes).get();
//		idnote.setTrash(true);
//		notesRepository.save(idnote);
//
//	}
//	
//	public void restoreTrash(String token,long notes) throws NoteException
//	{
//		TokenVerify.tokenVerifing(token);
//		Notes idnote=notesRepository.findById(notes).get();
//		idnote.setTrash(false);
//		notesRepository.save(idnote);
//
//	}
	
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
