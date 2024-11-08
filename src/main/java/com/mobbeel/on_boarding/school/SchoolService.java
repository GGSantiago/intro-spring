package com.mobbeel.on_boarding.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public List<SchoolDto> getAll() {
        return this.schoolRepository.findAll()
                .stream()
                .map(this.schoolMapper::toSchoolDto)
                .collect(Collectors.toList());
    }

    public SchoolDto saveSchool(SchoolDto schoolDto) {
        School school = new School();
        school.setName(schoolDto.name());
        this.schoolRepository.save(school);
        return schoolDto;
    }
}
