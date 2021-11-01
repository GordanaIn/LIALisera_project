package com.liserabackend.controllers;

import com.liserabackend.dto.CreateInternship;
import com.liserabackend.dto.AdvertDTO;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.services.AdvertServiceImp;
import com.liserabackend.services.StudentServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/advert")
public class AdvertController {
    private final AdvertServiceImp advertService;
    private final StudentServiceImp studentServiceImp;
    @GetMapping()
    public List<AdvertDTO> getAllInternship(){
        return advertService.getAllInternships().map(this::toAdvertDTO).collect(Collectors.toList());
    }
    @PutMapping("/addInternship/{id}")
    public AdvertDTO addInternshipToList(@PathVariable("id") String id, @RequestBody CreateInternship createInternship ) {
        return advertService.addToList(id, createInternship).map(this::toAdvertDTO).get();
    }

    @PatchMapping("/editInternship/{Id}")
    public AdvertDTO updateInternship(@PathVariable("Id") String Id,
                                      @RequestBody InternshipVacancy internship) throws UseException {
        return advertService.updateInternship(Id,internship).map(this::toAdvertDTO).get();
    }
    @GetMapping("/{userId}")
    public List<AdvertDTO> getFavoritesList(@PathVariable("userId") String userId){
        return studentServiceImp.getFavoritesList(userId).map(this::toAdvertDTO).collect(Collectors.toList());
    }
    /**
     * How to know the favourite is a particular student??
     */
    @GetMapping("/getFavList")
    public List<AdvertDTO> getFavList(){
        return advertService.getFavorite().map(this::toAdvertDTO).collect(Collectors.toList());
    }
    private AdvertDTO toAdvertDTO(InternshipVacancy internshipVacancy){
        return new AdvertDTO(
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
