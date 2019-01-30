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

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
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
	
	@Override
	public String toString() {
		return "Notes [id=" + id + ", userid=" + userid + ", title=" + title + ", content=" + content + ", createStamp="
				+ createStamp + ", lastModifiedStamp=" + lastModifiedStamp + "]";
	}

}
