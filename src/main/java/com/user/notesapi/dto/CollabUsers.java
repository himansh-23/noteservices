package com.user.notesapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CollabUsers {
	
	long id;
	String email;
	String image;
	
	public CollabUsers(long id)
	{
		this.id=id;
	}

}
