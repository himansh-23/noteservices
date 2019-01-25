package com.user.notesapi.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class NotesDTO {

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

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public LocalDateTime getRemainder() {
		return remainder;
	}

	public void setRemainder(LocalDateTime remainder) {
		this.remainder = remainder;
	}
	
}
