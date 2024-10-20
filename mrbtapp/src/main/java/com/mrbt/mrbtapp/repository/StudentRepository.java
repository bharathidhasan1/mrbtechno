package com.mrbt.mrbtapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrbt.mrbtapp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
