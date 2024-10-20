package com.mrbt.mrbtapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrbt.mrbtapp.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	Optional<Subject> findBySubjectId(Integer subjectId);
}
