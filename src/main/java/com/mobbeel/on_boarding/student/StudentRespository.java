package com.mobbeel.on_boarding.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRespository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstnameContaining(String p);
}
