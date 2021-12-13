package com.liserabackend.dto;

import com.liserabackend.dto.InternshipAdvertDTO;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.InternshipAdvert;


public class InternshipAdvertEntityToDTO {

    public static InternshipAdvertDTO getInternshipAdvertDTO(InternshipAdvert internshipAdvert){
        Company company=internshipAdvert.getCompany();
        return new InternshipAdvertDTO(
                internshipAdvert.getId(),
                internshipAdvert.getContactEmployer(),
                internshipAdvert.getTitle(),
                internshipAdvert.getDescription(),
                internshipAdvert.getStatus().toString(),
                internshipAdvert.getDatePosted(),
                internshipAdvert.getContactPhone(),
                internshipAdvert.getDuration(),
                internshipAdvert.getNumberAvailablePositions(),
                company.getName(),
                company.getOrgNumber()
        );
    }
}
