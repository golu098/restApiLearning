package com.learning.learningSpringBoot.RESTAPIs.service;

import com.learning.learningSpringBoot.RESTAPIs.DTO.AddStudentDTO;
import com.learning.learningSpringBoot.RESTAPIs.DTO.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(AddStudentDTO addstudentDTO);

    void deleteStudentById(Long id);


    StudentDTO updateStudent(Long id, AddStudentDTO addStudentDTO);

    StudentDTO updatePartial(Long id, AddStudentDTO addStudentDTO);
}
