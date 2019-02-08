package com.user.notesapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class NotesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userid;
	
	@NotNull
	private String title;
	
	@NotNull
	private String content;
	
	//@Column(columnDefinition="tinyint(1) default 0 not null")
	private boolean isPinned;
	
	//@Column(columnDefinition="varchar(20)")
	private String color="white";
	
	//@Column(columnDefinition="varchar(500)")
	private String image;
	
	//@Column(columnDefinition="tinyint(1) default 0 not null")
	private boolean archive;
	
	//@Column(columnDefinition="tinyint(1) default 0 not null")
	private boolean trash;
	
	private LocalDateTime remainder;

	
	
}
