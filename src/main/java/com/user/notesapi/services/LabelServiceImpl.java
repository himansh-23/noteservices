package com.user.notesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.notesapi.entity.Labels;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.util.TokenVerify;

public class LabelServiceImpl {

	@Autowired
	LabelsRepository labelrepo;
	
	public void createLabel(String token,Labels label) throws NoteException{
		
		long userid=TokenVerify.tokenVerifing(token);
		label.setUserid(userid);
		labelrepo.save(label);
	}
	public void updateLabel(String token,Labels label) throws NoteException{
		
		 TokenVerify.tokenVerifing(token);
		 labelrepo.save(label);
		
	}
	public void deleteLabel(String token,String labelName) throws NoteException{
		
		
	}
	public List<Labels> labelList(String token) throws NoteException{
		 long userid=TokenVerify.tokenVerifing(token);
		 List<Labels> list = labelrepo.findAllById(userid).get();
		return list;
	}
	
}
