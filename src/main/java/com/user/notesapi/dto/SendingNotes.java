package com.user.notesapi.dto;

import java.math.BigInteger;
import java.util.List;
import com.user.notesapi.entity.Notes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SendingNotes {
	
	private Notes note;
	private List<CollabUserDetails> collabList;
	
	public SendingNotes(Notes note,List<CollabUserDetails> collabList)
	{
		this.note=note;
		this.collabList=collabList;
	}
	
}
