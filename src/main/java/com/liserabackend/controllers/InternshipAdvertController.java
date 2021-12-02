package com.liserabackend.controllers;

import com.liserabackend.dto.InternshipAdvertDTO;
import com.liserabackend.services.InternshipAdvertService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/internship")

public class InternshipAdvertController {
    private InternshipAdvertService internshipVacancyService;

    @GetMapping()
    public List<InternshipAdvertDTO> getAllInternship(){
       return internshipVacancyService.getAllInternships().map(InternshipAdvertEntityToDTO::getInternshipAdvertDTO).collect(Collectors.toList());
       //return null;
    }

    @GetMapping("/{id}")
    public InternshipAdvertDTO InternshipVacancy(@PathVariable("id") String id){
        return internshipVacancyService.getInternshipVacancy(id).map(InternshipAdvertEntityToDTO::getInternshipAdvertDTO).get();
        //return null;
    }





}
