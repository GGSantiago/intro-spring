package com.mobbeel.on_boarding.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRespository studentRespository;

    private final StudentMapper studentMapper;

    public StudentService(StudentRespository studentRespository, StudentMapper studentMapper) {
        this.studentRespository = studentRespository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        Student student = this.studentMapper.toStudent(studentDto);
        studentRespository.save(student);
        return this.studentMapper.toStudentResponseDto(student);
    }

    public List<StudentResponseDto> getAllStudents() {
        return this.studentRespository.findAll()
                .stream()
                .map(this.studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public StudentResponseDto getStudentById(Integer studentId) {
        return this.studentRespository.findById(studentId)
                .map(this.studentMapper::toStudentResponseDto)
                .orElse(null);
    }

    public List<StudentResponseDto> findStudentsByFirstname(String firstname) {
        return this.studentRespository.findAllByFirstnameContaining(firstname)
                .stream()
                .map(this.studentMapper::toStudentResponseDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Integer studentId) {
        this.studentRespository.deleteById(studentId);
    }
}
