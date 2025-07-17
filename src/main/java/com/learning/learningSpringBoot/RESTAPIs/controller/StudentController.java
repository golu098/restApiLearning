package com.learning.learningSpringBoot.RESTAPIs.controller;

import com.learning.learningSpringBoot.RESTAPIs.DTO.AddStudentDTO;
import com.learning.learningSpringBoot.RESTAPIs.DTO.StudentDTO;
import com.learning.learningSpringBoot.RESTAPIs.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students") // Base URL for the API
public class StudentController {

    // Define your REST API endpoints here

    // Example endpoint
    // after making service layer, we will not use it
//    private final StudentRepository studentRepository;

    private final StudentService studentService;

//    Comment it out bcz i am using now required args constructor
    //    public StudentController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
     @GetMapping("/")
     public ResponseEntity<List<StudentDTO>> getAllStudents() {
//         return new StudentDTO(5L, "Shiv Kant", "shiv@gmail.com");
//        return studentRepository.findAll(); // after service layer, we will not use it
//         return studentService.getAllStudents(); // Now using the service layer to get all students

                 // Instead of returning a simple list, we will return a ResponseEntity
                 // ResponseEntity is used to represent the whole HTTP response, including status code and body
                 // It allows us to customize the response status and headers if needed

                 // If you want to set a specific status code, you can do it like this:
                // Now i will use ResponseEntity to return the response
    //   return ResponseEntity.status(200).body(studentService.getAllStudents());
         return ResponseEntity.ok(studentService.getAllStudents());

    }

    // Add more endpoints as needed
//    @GetMapping("/{id}")
//    public StudentDTO getStudentById(Long id) {
//        // Logic to fetch a student by ID
//        // For example, you can call a service method to get the student details
//        return studentService.getAllStudents().stream()
//                .filter(student -> student.getId().equals(id))
//                .findFirst()
//                .orElse(null); // Return null or throw an exception if not found
//    }

//    @GetMapping("/student/{id}")
//    public String getStudentById(@PathVariable Long id) {
//        // Logic to fetch a student by ID
//        // For example, you can call a service method to get the student details
//        return "Path Variable"+id ;
//     }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody @Valid AddStudentDTO addstudentDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(studentService.createNewStudent(addstudentDTO));
        } catch (Exception e) {
            e.printStackTrace(); // See logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
        // Logic to delete a student by ID
        // For example, you can call a service method to delete the student
        // Assuming you have a delete method in your service
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody AddStudentDTO addStudentDTO) {
        try {
            StudentDTO updatedStudent = studentService.updateStudent(id, addStudentDTO);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            e.printStackTrace(); // See logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> patchStudent(@PathVariable Long id, @RequestBody AddStudentDTO addStudentDTO) {
        try {
            StudentDTO updatedStudent = studentService.updatePartial(id, addStudentDTO);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            e.printStackTrace(); // See logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
