package com.user.notesapi.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Labels implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String labelName;
	
	@NotNull
	private long userid;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Note_Labels" ,
	joinColumns= @JoinColumn(name="label_id",referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name ="notes_id",referencedColumnName="id"))
	private Set<Notes> notes;
	
}
