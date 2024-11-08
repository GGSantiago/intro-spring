package com.mobbeel.on_boarding.student;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }
    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto(
                "John",
                "Doe",
                "john@mail.com",
                1
        );

        Student student = this.studentMapper.toStudent(dto);

        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenStudentDtoIsNull() {
        var exception = assertThrows(NullPointerException.class, () -> this.studentMapper.toStudent(null));
        assertEquals("The student Dto is null", exception.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student(
                "John",
                "Doe",
                "john@mail.com",
                18
        );

        StudentResponseDto dto = this.studentMapper.toStudentResponseDto(student);

        assertEquals(student.getEmail(), dto.email());
        assertEquals(student.getFirstname(), dto.firstname());
        assertEquals(student.getLastname(), dto.lastname());
    }
}