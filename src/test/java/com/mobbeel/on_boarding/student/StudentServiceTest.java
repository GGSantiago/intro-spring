package com.mobbeel.on_boarding.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRespository studentRespository;

    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSuccessfullySaveAStudent() {
        //Give  n
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@mail.com",
                1
        );
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                18
        );
        // Mock calls to dependencies
        when(this.studentMapper.toStudent(dto)).thenReturn(student);
        when(this.studentRespository.save(student)).thenReturn(student);
        when(this.studentMapper.toStudentResponseDto(student)).thenReturn(new StudentResponseDto(
                "John",
                "Doe",
                "john@mail.com"
        ));
        // When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());
        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRespository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(student);
    }

    @Test
    public void shouldReturnAllStudents() {
        //Given
        List<Student> students = new ArrayList<>();

        students.add(new Student(
                "John",
                "Doe",
                "john@mail.com",
                18
        ));

        // Mock the calls

        when(studentRespository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@example.com"
                ));

        List<StudentResponseDto> responseDtos = this.studentService.getAllStudents();

        assertEquals(students.size(), responseDtos.size());
    }

    @Test
    public void shouldReturnStudentById() {
        // Given
        Integer studentId = 1;
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                18
        );

        // When

        when(this.studentRespository.findById(studentId)).thenReturn(Optional.of(student));
        when(this.studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        StudentResponseDto responseDto = this.studentService.getStudentById(studentId);

        // Then
        assertEquals(student.getFirstname(), responseDto.firstname());
        assertEquals(student.getLastname(), responseDto.lastname());
        assertEquals(student.getEmail(), responseDto.email());

    }

    @Test
    public void shouldReturnStudentByName() {
        // Given
        String studentName = "John";
        List<Student> students = new ArrayList<Student>();
        students.add(new Student(
                studentName,
                "Doe",
                "john@mail.com",
                18
        ));

        // When
        when(this.studentRespository.findAllByFirstnameContaining(studentName)).thenReturn(students);
        when(this.studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto(
                        "John",
                        "Doe",
                        "john@mail.com"
                ));

        List<StudentResponseDto> responseDtos = this.studentService.findStudentsByFirstname(studentName);

        // Then

        assertEquals(students.size(), responseDtos.size());
        verify(studentRespository, times(1)).findAllByFirstnameContaining(studentName);

    }
}