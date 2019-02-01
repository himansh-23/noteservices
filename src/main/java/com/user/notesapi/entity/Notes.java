package com.user.notesapi.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
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
	
	@ManyToMany(mappedBy="notes")
	private Set<Labels> labels;
	
//	public Notes(Notes notes)
//	{
//		this.userid=notes.getUserid();
//		this.title=notes.title;
//		this.content=notes.content;
//		this.color=notes.color;
//		this.createStamp=notes.createStamp;
//		this.lastModifiedStamp=notes.lastModifiedStamp;
//		this.image=notes.image;
//		this.trash=notes.trash;
//		this.remainder=notes.remainder;
//		this.isPinned=notes.isPinned;
//		this.archive=notes.archive;
//				
//	}
	

}
