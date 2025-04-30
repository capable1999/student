package com.assignment.student.repository;

import com.assignment.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long studentId);
    boolean existsByStudentId(String studentId);
    boolean existsByStudentIdAndIdNot(String studentId, Long idNot);

    List<Student> findByNameContainingIgnoreCaseOrStudentIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
        String name, String studentId, String email);

    List<Student> findAllByOrderByStudentIdAsc();
}
