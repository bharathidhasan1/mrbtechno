package com.mrbt.mrbtapp.service.serviceimplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mrbt.mrbtapp.dto.SubjectDto;
import com.mrbt.mrbtapp.entity.Subject;
import com.mrbt.mrbtapp.exception.GlobalException;
import com.mrbt.mrbtapp.repository.SubjectRepository;
import com.mrbt.mrbtapp.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	private ModelMapper modelMapper;

	ObjectMapper mapper = new ObjectMapper();
	

	// Converter Entity to DTO
	public SubjectDto employeeToDto(Subject subject) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(subject, SubjectDto.class);
	}

	@Override
	public List<SubjectDto> getAllSubjects() {

		List<Subject> subjectEntity = subjectRepository.findAll();

		if (subjectEntity.isEmpty()) {
			throw new GlobalException("Subject Data Not Found");
		}
		return subjectEntity.stream().map(this::employeeToDto).collect(Collectors.toList());
	}

	@Override
	public SubjectDto getSubjectById(Integer subjectId) {
		Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new GlobalException("Subject not found with ID: " + subjectId));
		return modelMapper.map(subject, SubjectDto.class);
	}

	@Override
	public JsonNode deleteSubjectById(Integer subjectId) throws Exception {
		Optional<Subject> subject = subjectRepository.findBySubjectId(subjectId);
		if (subject.isPresent()) {
			subjectRepository.deleteById(subject.get().getId());
			ObjectNode node = mapper.createObjectNode();
			node.put("status", "Deleted");
			node.put("SubjectName", subject.get().getSubjectName());
			return node;
		} else {
			throw new GlobalException("SubjectId Not Found");
		}
	}

	@Override
	public ResponseEntity<Object> saveSubjects(SubjectDto subjectDto) {
		Optional<Subject> subjectOptional = subjectRepository.findBySubjectId(subjectDto.getSubjectId());
		if (!subjectOptional.isPresent()) {
			Subject subject = modelMapper.map(subjectDto, Subject.class);
			subjectRepository.save(subject);
			return ResponseEntity.status(HttpStatus.CREATED).body("Subject created successfully.");
		} else {
			ObjectNode node = mapper.createObjectNode();
			node.put("status", "Failed");
			node.put("message", "Subject Id already exists.");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(node);
		}
	}

	@Override
	public ResponseEntity<Object> editSubjects(SubjectDto subjectDto) {
		Optional<Subject> subjectOptional = subjectRepository.findBySubjectId(subjectDto.getSubjectId());
	
		if (subjectOptional.isPresent()) {
			Subject subject = subjectOptional.get();

			subject.setSubjectId(subjectDto.getSubjectId());
			subject.setSubjectName(subjectDto.getSubjectName());
			subject.setSubjectCode(subjectDto.getSubjectCode());

			subjectRepository.save(subject);
			return ResponseEntity.ok("Subject updated successfully.");
		} else {
			ObjectNode node = mapper.createObjectNode();
			node.put("status", "Failed");
			node.put("message", "Subject Id does not exist.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(node);
		}
	}

}
