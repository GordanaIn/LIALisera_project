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

    @GetMapping("/{id}")
    public InternshipVacancyDTO InternshipVacancy(@PathVariable("id") String id){
        return internshipVacancyService.getInternshipVacancy(id).map(this::toInternshipVacancyDTO).get();
    }

    @PatchMapping("/editInternship/{id}")
    public InternshipVacancyDTO updateInternship(@PathVariable("id") String Id,
                                                 @RequestBody InternshipVacancy internship) throws UseException {
        return internshipVacancyService.updateInternship(Id,internship).map(this::toInternshipVacancyDTO).get();
    }
    @DeleteMapping("/deleteInternship/{id}")
    public void delete(@PathVariable("id") String id) throws Exception {
        internshipVacancyService.deleteInternship(id);
    }

    @PutMapping("/addFavorite/{id}/{internshipId}")
    public boolean addInternshipToList(@PathVariable("id") String id,
                                                    @PathVariable("internshipId") String internshipId) {
        return internshipVacancyService.addToList(id, internshipId);
    }

    @GetMapping("/favorites/{userId}")
    public List<InternshipVacancyDTO> getFavoritesList(@PathVariable("userId") String userId){
        return studentServiceImp.getFavoritesList(userId).map(this::toInternshipVacancyDTO).collect(Collectors.toList());
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
