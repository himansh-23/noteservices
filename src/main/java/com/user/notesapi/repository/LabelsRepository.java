package com.user.notesapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.user.notesapi.entity.Labels;


public interface LabelsRepository extends JpaRepository<Labels, Long>{

	@Query(value="select * from labels where userid= :userid ",nativeQuery=true) // :str = true
	Optional<List<Labels>> findAllById(@Param("userid")long userid); //@Param("str") String str
	
	@Query(value="select id from labels where label_name =:labelname",nativeQuery=true)
	Optional<Long> findIdByLabelName (@Param("labelname") String labelName);
	
}
