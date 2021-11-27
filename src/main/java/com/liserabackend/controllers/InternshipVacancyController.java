package com.liserabackend.controllers;

import com.liserabackend.dto.CompanyDTO;
import com.liserabackend.dto.CreateInternship;
import com.liserabackend.dto.InternshipVacancyDTO;
import com.liserabackend.dto.UserDTO;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.services.InternshipVacancyServiceImp;
import com.liserabackend.services.StudentServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.constant.Constable;
import java.util.List;
import java.util.Optional;
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
    @PostMapping()
    public Optional<InternshipVacancyDTO> addInternship(@RequestBody CreateInternship createInternship) throws UseException {
        return internshipVacancyService.addInternship(createInternship).map(this::toInternshipVacancyDTO);
    }
    @PatchMapping("/editInternship/{id}")
    public InternshipVacancyDTO updateInternship(@PathVariable("id") String Id,
                                                 @RequestBody InternshipVacancy internship) throws UseException {
        return internshipVacancyService.updateInternship(Id,internship).map(this::toInternshipVacancyDTO).get();
    }
    @DeleteMapping("/deleteInternship/{employerId}/{internshipId}")
    public void delete(@PathVariable("employerId") String employerId,
                       @PathVariable("internshipId") String internshipId) throws Exception {
        internshipVacancyService.deleteInternship(employerId, internshipId);
    }

    @PatchMapping("/addFavorite/{userId}/{internshipId}")
    public boolean addFavorite(@PathVariable("userId") String userId,
                                                    @PathVariable("internshipId") String internshipId) throws UseException {
      return internshipVacancyService.addFavorite(userId, internshipId);
    }

    @GetMapping("/favorites/{userId}")
    public List<InternshipVacancyDTO> getFavoritesList(@PathVariable("userId") String userId){
        return studentServiceImp.getFavoritesList(userId).map(this::toInternshipVacancyDTO).collect(Collectors.toList());
    }

    public InternshipVacancyDTO toInternshipVacancyDTO(InternshipVacancy internshipVacancy){
        Company company=internshipVacancy.getCompany();
        return new InternshipVacancyDTO(
                internshipVacancy.getId(),
                internshipVacancy.getContactEmployer(),
                internshipVacancy.getTitle(),
                internshipVacancy.getDescription(),
                internshipVacancy.getStatus().toString(),
                internshipVacancy.getDatePosted(),
                internshipVacancy.getContactPhone(),
                internshipVacancy.getDuration(),
                internshipVacancy.getRequiredNumber(),
                company.getName(),
                company.getOrgNumber()
        );
    }

}
