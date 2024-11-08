package com.mobbeel.on_boarding.school;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {


    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/schools")
    public List<SchoolDto> getAll() {
        return this.schoolService.getAll();
    }

    @PostMapping("/schools")
    public SchoolDto create(
            @RequestBody SchoolDto schoolDto
    ) {
        return this.schoolService.saveSchool(schoolDto);
    }
}
