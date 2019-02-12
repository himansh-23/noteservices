package com.user.notesapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.user.notesapi.entity.CollaboratorNotes;

public interface CollabRepository extends JpaRepository<CollaboratorNotes, Long> {

	@Query(value="select noteId where userId= :userid ",nativeQuery=true)
	Optional<List<Long>> findAllById(@Param("userid")long userid);
}
