package com.learning.learningSpringBoot.RESTAPIs.repository;

import com.learning.learningSpringBoot.RESTAPIs.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository// optional, but it's a good practice to annotate your repository interfaces with @Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // This interface will automatically provide CRUD operations for the Student entity
    // No need to implement any methods, JpaRepository provides all the necessary methods
    // like save, findById, findAll, deleteById, etc.
}
