package com.user.notesapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class CollaboratorNotes {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	private long noteId;
	
	@NotNull
	private long userId;
	
	public CollaboratorNotes()
	{
		
	}
	
	public CollaboratorNotes(long noteId,long userId)
	{
		this.noteId=noteId;
		this.userId=userId;
	}
	
}
