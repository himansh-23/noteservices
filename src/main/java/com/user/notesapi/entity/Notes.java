package com.user.notesapi.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Notes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	private long userid;
	
	@NotNull
	private String title;
	
	@NotNull
	@Column(length=2000)
	private String content;
	
	@NotNull
	private LocalDate createStamp;

	@NotNull
	private LocalDate lastModifiedStamp;
	
	@Column(columnDefinition="tinyint(1) default 0 not null")
	private boolean isPinned;
	
	@Column(columnDefinition="varchar(20) ")
	private String color="white";
	
	@Column(columnDefinition="varchar(500)")
	private String image;
	
	@Column(columnDefinition="tinyint(1) default 0 not null")
	private boolean archive;
	
	@Column(columnDefinition="tinyint(1) default 0 not null")
	private boolean trash;
	
	private LocalDateTime remainder; 
	
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

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

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

	public LocalDate getcreateStamp() {
		return createStamp;
	}

	public void setcreateStamp(LocalDate createStamp) {
		this.createStamp = createStamp;
	}

	public LocalDate getlastModifiedStamp() {
		return lastModifiedStamp;
	}

	public void setlastModifiedStamp(LocalDate lastModifiedStamp) {
		this.lastModifiedStamp = lastModifiedStamp;
	}

	@Override
	public String toString() {
		return "Notes [id=" + id + ", userid=" + userid + ", title=" + title + ", content=" + content + ", createStamp="
				+ createStamp + ", lastModifiedStamp=" + lastModifiedStamp + "]";
	}

}
