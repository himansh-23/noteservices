package com.user.notesapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.user.notesapi.entity.Notes;

public interface NotesRepository extends CrudRepository<Notes, Long> {

	@Query(value="select * from notes where userid= :userid AND is_archive=:archive AND is_trash =:trash",nativeQuery=true) // :str = true
	Optional<List<Notes>> findAllById(@Param("userid")long userid,@Param("archive") boolean archive,@Param("trash") boolean trash); //@Param("str") String str
	
	@Query(value="select * from notes where id IN (:ids)",nativeQuery=true) 
	Optional<List<Notes>> findAllCollabNotes(@Param("ids") List<Long> allNotesId);
}
