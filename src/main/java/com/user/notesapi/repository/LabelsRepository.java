package com.user.notesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.notesapi.entity.Labels;

public interface LabelsRepository extends JpaRepository<Labels, Long>{

}
