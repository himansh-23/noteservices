package com.user.notesapi.services;

import java.util.List;

import com.user.notesapi.entity.Labels;
import com.user.notesapi.exception.NoteException;


public interface LabelService {
	
	public void createLabel(String token,Labels labelName) throws NoteException;
	public void updateLabel(String token,Labels labelName) throws NoteException;
	public void deleteLabel(String token,String labelId) throws NoteException;
	public void labelDeleteToNote(String token,String noteid,String labelid) throws NoteException;
	public List<Labels> listLabels(String token) throws NoteException;

}
