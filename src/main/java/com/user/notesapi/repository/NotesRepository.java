package com.user.notesapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.user.notesapi.entity.Notes;

public interface NotesRepository extends CrudRepository<Notes, Long> {

	@Query(value="select * from notes where userid= :userid ",nativeQuery=true) // :str = true
	Optional<List<Notes>> findAllById(@Param("userid")long userid); //@Param("str") String str
	
	/*
	 * @Query(value="select * from note where userid=?1 AND is_pinned=true"
	 * ,nativeQuery=true) Optional<List<Notes>> findAllPinnedNote(long userid);
	 * 
	 * @Query(value="select * from note where userid=?1 AND archive=true"
	 * ,nativeQuery=true) Optional<List<Notes>> findAllArchiveNote(long userid);
	 * 
	 * @Query(value="select * from note where userid=?1 AND trash=true",nativeQuery=
	 * true) Optional<List<Notes>> findAllTrashNote(long userid);
	 */
}
