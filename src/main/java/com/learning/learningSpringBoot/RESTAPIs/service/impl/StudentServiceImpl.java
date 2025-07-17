package com.learning.learningSpringBoot.RESTAPIs.service.impl;

import com.learning.learningSpringBoot.RESTAPIs.DTO.AddStudentDTO;
import com.learning.learningSpringBoot.RESTAPIs.DTO.StudentDTO;
import com.learning.learningSpringBoot.RESTAPIs.entity.Student;
import com.learning.learningSpringBoot.RESTAPIs.repository.StudentRepository;
import com.learning.learningSpringBoot.RESTAPIs.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    // Implement the methods from StudentService interface here
    // For example:
    // @Override
     public List<StudentDTO> getAllStudents() {
         // Logic to fetch all students
         List<Student>students= studentRepository.findAll();
            // Convert List<Student> to List<StudentDTO>
        return  students.stream()
//                 .map(student -> new StudentDTO(student.getId(), student.getName(), student.getEmail()))
                // Basically ModelPmapper is used to convert one object to another object  and make sure that Student and StudentDto have same type of field
                .map(student -> modelMapper.map(student, StudentDTO.class))

                 .toList();
//         return studentDTOs;
     }

    // Add other service methods as needed
    @Override
    public StudentDTO getStudentById(Long id) {
        // Logic to fetch a student by ID
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return new StudentDTO(student.getId(), student.getName(), student.getEmail());

     }

     @Override
    public StudentDTO createNewStudent(AddStudentDTO addStudentDTO) {
        // Logic to create a new student
        // Convert StudentDTO to Student entity
        Student student = modelMapper.map(addStudentDTO, Student.class);
        // Save the student entity to the repository
        Student savedStudent = studentRepository.save(student);
        // Convert the saved Student entity back to StudentDTO and return it
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        // Logic to delete a student by ID
        // Check if the student exists before deleting
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        // Delete the student from the repository
        studentRepository.deleteById(id);
    }
    @Override
    public StudentDTO updateStudent(Long id, AddStudentDTO addStudentDTO) {
        // Logic to update a student
        // Check if the student exists before updating
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Update the existing student's fields with the new values
        existingStudent.setName(addStudentDTO.getName());
        existingStudent.setEmail(addStudentDTO.getEmail());

        // Save the updated student entity to the repository
        Student updatedStudent = studentRepository.save(existingStudent);

        // Convert the updated Student entity back to StudentDTO and return it
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }
    @Override
    public StudentDTO updatePartial(Long id, AddStudentDTO addStudentDTO) {
        // Logic to partially update a student
        // Check if the student exists before updating
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        // Update only the fields that are provided in the DTO
        if (addStudentDTO.getName() != null) {
            existingStudent.setName(addStudentDTO.getName());
        }
        if (addStudentDTO.getEmail() != null) {
            existingStudent.setEmail(addStudentDTO.getEmail());
        }

        // Save the updated student entity to the repository
        Student updatedStudent = studentRepository.save(existingStudent);

        // Convert the updated Student entity back to StudentDTO and return it
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }
}
