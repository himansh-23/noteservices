package com.user.notesapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.notesapi.appconfig.ApplicationConfiguration;
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
	
	@Autowired
	private ObjectMapper obj;
	 
	 @Autowired
	 private RabbitTemplate rabbitTemplate;
	
	
	public void createNote(String token,NotesDTO notesDTO)throws NoteException
	{
		long id=TokenVerify.tokenVerifing(token);
		notesDTO.setUserid(id);
		Notes note = modelMapper.map(notesDTO, Notes.class);
		note.setCreateStamp(LocalDate.now());
		note.setLastModifiedStamp(LocalDate.now());
		try 
		{
			rabbitTemplate.convertAndSend(ApplicationConfiguration.queueName, obj.writeValueAsString(note));
			System.out.println(obj.writeValueAsString(note));
			
		}
		catch(JsonProcessingException e)
		{
			throw new NoteException(122,e.getMessage());
		}
	}
	
	@Override
	public void updateNote(String token,Notes notes)throws NoteException
	{
		TokenVerify.tokenVerifing(token);
		notes.setLastModifiedStamp(LocalDate.now());
		System.out.println(notes.isArchive());
		
		notesRepository.save(notes);
	}
	
	@Override
	public void deleteNote(String token,long notes) throws NoteException
	{
		TokenVerify.tokenVerifing(token);
	//	labelRepository
		Notes idnote=notesRepository.findById(notes).get();
		idnote.getLabels().clear();
		labelRepository.findAll().forEach(x ->this.removeNote(idnote,x));
		notesRepository.delete(idnote);
	}
	
	@Override
	public List<Notes> listAllNotes(String token,String archive,String trash)throws NoteException //,String value
	{	
		long id=TokenVerify.tokenVerifing(token);
		
		return notesRepository.findAllById(id,Boolean.valueOf(archive),Boolean.valueOf(trash)).orElse( new ArrayList<Notes>());
	}							// NoteException(101,"No Note Found") orElseThrow(() -> new ArrayList<Notes>()
	
	private void removeNote(Notes notes,Labels l)
	{
		l.getNotes().remove(notes);
		notesRepository.save(notes);
	}
// eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJJRCI6MzV9.l7OjtnAX5rSlX06Cu-SN_xGwRH8sKrPrmtfWT_JAkJI
}
