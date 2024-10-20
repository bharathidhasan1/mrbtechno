package com.mrbt.mrbtapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.mrbt.mrbtapp.dto.SubjectDto;

public interface SubjectService {

	List<SubjectDto> getAllSubjects();

	SubjectDto getSubjectById(Integer subjectId);

	JsonNode deleteSubjectById(Integer subjectId) throws Exception;

	ResponseEntity<Object> saveSubjects(SubjectDto subjectDto);

	ResponseEntity<Object> editSubjects(SubjectDto subjectDto);
}
