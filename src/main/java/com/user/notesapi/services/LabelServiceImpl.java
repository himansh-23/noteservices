package com.user.notesapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.notesapi.entity.Labels;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.util.TokenVerify;

@Service
public class LabelServiceImpl implements LabelService {

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
	
	
	@Override
	public List<Labels> listLabels(String token) throws NoteException {
		// TODO Auto-generated method stub
		 long userid=TokenVerify.tokenVerifing(token);
		 List<Labels> list = labelrepo.findAllById(userid).get();
		return list;
	}
	
}
