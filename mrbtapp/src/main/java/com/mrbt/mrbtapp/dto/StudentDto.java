package com.mrbt.mrbtapp.dto;

import java.util.List;

import com.mrbt.mrbtapp.entity.Subject;

import lombok.Data;

@Data
public class StudentDto {
	private int id;
	private int studentId;
	private String studentName;
	private String studentAddress;
	private List<Subject> subject;

}
