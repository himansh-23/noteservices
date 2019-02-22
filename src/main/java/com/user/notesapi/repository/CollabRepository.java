package com.user.notesapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.user.notesapi.entity.CollaboratorNotes;

public interface CollabRepository extends JpaRepository<CollaboratorNotes, Long> {

	@Query(value = "select note_id from collaborator_notes where user_id= :userid ", nativeQuery = true)
	Optional<List<Long>> findAllById(@Param("userid") long userid);

	@Query(value = "select id from collaborator_notes where user_id=:userid and note_id=:noteid", nativeQuery = true)
	Optional<Long> findBy(@Param("noteid") long noteId, @Param("userid") long userId);

	@Query(value = "select user_id from collaborator_notes where note_id=:noteId", nativeQuery = true)
	Optional<List<Object>> findAllUsersOfNote(@Param("noteId") long noteId);

}
