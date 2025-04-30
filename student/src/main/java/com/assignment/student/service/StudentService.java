package com.assignment.student.service;

import com.assignment.student.model.Student;
import com.assignment.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        // Check if the email already exists
        if (isEmailExist(student.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        // Check if the student ID already exists
        if (isStudentIdExist(student.getStudentId())) {
            throw new IllegalArgumentException("Student ID already exists");
        }
        
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAllByOrderByStudentIdAsc();
    }
    
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        
        // Check if the email already exists for another student (excluding the current student)
        if (isEmailExistForUpdate(id, studentDetails.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Check if the student ID already exists for another student (excluding the current student)
        if (isStudentIdExistForUpdate(id, studentDetails.getStudentId())) {
            throw new IllegalArgumentException("Student ID already exists");
        }

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setDob(studentDetails.getDob());
        student.setEmail(studentDetails.getEmail());
        student.setStudentId(studentDetails.getStudentId());
        
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudents(String query) {
        return studentRepository.findByNameContainingIgnoreCaseOrStudentIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
                query, query, query);
    }
    

    // Check if email exists in the database
    public boolean isEmailExist(String email) {
        return studentRepository.existsByEmail(email);
    }

    // Check if email exists for the update scenario, excluding the current student
    public boolean isEmailExistForUpdate(Long studentId, String email) {
        return studentRepository.existsByEmailAndIdNot(email, studentId);
    }

    // Check if student ID exists in the database
    public boolean isStudentIdExist(String studentId) {
        return studentRepository.existsByStudentId(studentId);
    }

    // Check if student ID exists for the update scenario, excluding the current student
    public boolean isStudentIdExistForUpdate(Long studentId, String studentIdToCheck) {
        return studentRepository.existsByStudentIdAndIdNot(studentIdToCheck, studentId);
    }
}
