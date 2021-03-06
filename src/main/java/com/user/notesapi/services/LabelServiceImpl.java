package com.user.notesapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.notesapi.entity.Labels;
import com.user.notesapi.entity.Notes;
import com.user.notesapi.exception.NoteException;
import com.user.notesapi.repository.LabelsRepository;
import com.user.notesapi.repository.NotesRepository;
import com.user.notesapi.util.TokenVerify;
@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	LabelsRepository labelrepo;
	
	@Autowired
	NotesRepository noterepo;

	@Override
	public void createLabel(String token,Labels label) throws NoteException{
		long userid=TokenVerify.tokenVerifing(token);
		label.setUserid(userid);
		labelrepo.save(label);
		
	}
	@Override
	public void updateLabel(String token,Labels label) throws NoteException{
		 TokenVerify.tokenVerifing(token);
	//	 System.out.println(label.);
		 Labels updatedLabel=labelrepo.findById(label.getId()).get();
		 updatedLabel.setLabelName(label.getLabelName());
		 labelrepo.save(updatedLabel);
	}
	
	@Override
	public void deleteLabel(String token,String labelId) throws NoteException {
		 Labels singleLabel=labelrepo.findById(Long.parseLong(labelId)).get();
		 singleLabel.getNotes().clear();
	 	 labelrepo.save(singleLabel);
		 labelrepo.delete(singleLabel);
	}
	
	@Override
	public List<Labels> listLabels(String token) throws NoteException {
		 long userid=TokenVerify.tokenVerifing(token);
		 List<Labels> list = labelrepo.findAllById(userid).orElse(new ArrayList<Labels>());
		return list;
	}
	
	@Override
	public void labelDeleteToNote(String token,String noteid, String labelid) throws NoteException {
		
		long labelidd=Long.parseLong(labelid);
		long noteidd=Long.parseLong(noteid);		

		Labels singleLabel=labelrepo.findById(labelidd).get();
		Notes singleNote=noterepo.findById(noteidd).get();
		
		singleNote.getLabels().remove(singleLabel);
		singleLabel.getNotes().remove(singleNote);

		labelrepo.save(singleLabel);
		noterepo.save(singleNote);
		 TokenVerify.tokenVerifing(token);

		
	}
	
}
