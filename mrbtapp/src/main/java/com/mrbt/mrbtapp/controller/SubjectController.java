package com.mrbt.mrbtapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.mrbt.mrbtapp.dto.SubjectDto;
import com.mrbt.mrbtapp.service.SubjectService;

@RestController
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @GetMapping("/get-subjects")
    public List<SubjectDto> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/get-subject-by-id/{subject_id}")
    public SubjectDto getAllSubjects(@PathVariable Integer subject_id) {
        return subjectService.getSubjectById(subject_id);
    }

    @DeleteMapping("/delete-subject-by-id/{subject_id}")
    public ResponseEntity<JsonNode> deleteSubjectById(@PathVariable Integer subject_id) throws Exception {
        return ResponseEntity.ok().body(subjectService.deleteSubjectById(subject_id));
    }

    @PostMapping("/save-subjects")
    public ResponseEntity<Object> saveSubjects(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok().body(subjectService.saveSubjects(subjectDto));
    }
    @PutMapping("/edit-subjects")
    public ResponseEntity<Object> editSubjects(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok().body(subjectService.editSubjects(subjectDto));
    }
}
