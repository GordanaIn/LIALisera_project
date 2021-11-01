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
    private final InternshipVacancyServiceImp advertService;
    private final StudentServiceImp studentServiceImp;

    @GetMapping()
    public List<InternshipVacancyDTO> getAllInternship(){
        return advertService.getAllInternships().map(this::toInternshipVacancyDTO).collect(Collectors.toList());
    }
    @PutMapping("/addInternship/{id}")
    public InternshipVacancyDTO addInternshipToList(@PathVariable("id") String id, @RequestBody CreateInternship createInternship ) {
        return advertService.addToList(id, createInternship).map(this::toInternshipVacancyDTO).get();
    }

    @PatchMapping("/editInternship/{Id}")
    public InternshipVacancyDTO updateInternship(@PathVariable("Id") String Id,
                                                 @RequestBody InternshipVacancy internship) throws UseException {
        return advertService.updateInternship(Id,internship).map(this::toInternshipVacancyDTO).get();
    }
    @GetMapping("/{userId}")
    public List<InternshipVacancyDTO> getFavoritesList(@PathVariable("userId") String userId){
        return studentServiceImp.getFavoritesList(userId).map(this::toInternshipVacancyDTO).collect(Collectors.toList());
    }
    /**
     * How to know the favourite is a particular student??
     */
    @GetMapping("/getFavList")
    public List<InternshipVacancyDTO> getFavList(){
        return advertService.getFavorite().map(this::toInternshipVacancyDTO).collect(Collectors.toList());
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
