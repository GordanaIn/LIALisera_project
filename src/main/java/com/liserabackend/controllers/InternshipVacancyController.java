package com.liserabackend.controllers;

import com.liserabackend.dto.CreateInternship;
import com.liserabackend.dto.InternshipVacancyDTO;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.services.InternshipVacancyServiceImp;
import com.liserabackend.services.StudentServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/internship")

public class InternshipVacancyController {
    private InternshipVacancyServiceImp internshipVacancyService;
    private StudentServiceImp studentServiceImp;

    @GetMapping()
    public List<InternshipVacancyDTO> getAllInternship(){
        return internshipVacancyService.getAllInternships().map(this::toInternshipVacancyDTO).collect(Collectors.toList());
    }
    @PutMapping("/addInternship/{id}")
    public InternshipVacancyDTO addInternshipToList(@PathVariable("id") String id, @RequestBody CreateInternship createInternship ) {
        return internshipVacancyService.addToList(id, createInternship).map(this::toInternshipVacancyDTO).get();
    }

    @PatchMapping("/editInternship/{Id}")
    public InternshipVacancyDTO updateInternship(@PathVariable("Id") String Id,
                                                 @RequestBody InternshipVacancy internship) throws UseException {
        return internshipVacancyService.updateInternship(Id,internship).map(this::toInternshipVacancyDTO).get();
    }
    @GetMapping("/favorites/{userId}")
    public List<InternshipVacancyDTO> getFavoritesList(@PathVariable("userId") String userId){
        return studentServiceImp.getFavoritesList(userId).map(this::toInternshipVacancyDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public InternshipVacancyDTO InternshipVacancy(@PathVariable("id") String id){
        return internshipVacancyService.getInternshipVacancy(id).map(this::toInternshipVacancyDTO).get();
    }
    public InternshipVacancyDTO toInternshipVacancyDTO(InternshipVacancy internshipVacancy){
        return new InternshipVacancyDTO(
                internshipVacancy.getId(),
                internshipVacancy.getContactEmployer(),
                internshipVacancy.getTitle(),
                internshipVacancy.getDescription(),
                internshipVacancy.getStatus().toString(),
                internshipVacancy.getDatePosted().toString(),
                internshipVacancy.getContactPhone(),
                internshipVacancy.getDuration()
        );

    }
}
