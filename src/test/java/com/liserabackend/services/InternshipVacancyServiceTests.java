package com.liserabackend.services;

import com.liserabackend.entity.Company;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.enums.EnumRole;
import com.liserabackend.enums.EnumStatus;
import com.liserabackend.enums.InternshipVacancyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
public class InternshipVacancyServiceTests {
    @MockBean
    InternshipVacancyRepository internshipVacancyRepository;
    @Autowired
    InternshipVacancyServiceImp internshipVacancyService;
    InternshipVacancy internshipVacancy1;
    InternshipVacancy internshipVacancy2;
    @BeforeEach
    @Transactional
    void setUp() {
        User jafer= new User("jafer@gmail.com", "jafer@gmail.com","jafer21", EnumRole.ROLE_EMPLOYER);
        Company microsoft=new Company("Microsoft","Microsoft-12345", jafer);
        microsoft.setStatus(EnumStatus.APPROVED);

        internshipVacancy1 = new InternshipVacancy("Java Full-stack", "Javakunnig person med intresse för frontend","4 months" , InternshipVacancyStatus.OPEN, LocalDate.of(2021,11,20),"Ms.Tsion","0718123456",microsoft);
        internshipVacancy2 = new InternshipVacancy("C# Full-stack", "C# FULL-STACK med intresse för frontend","3 months", InternshipVacancyStatus.OPEN,LocalDate.of(2021,11,20),"Ms.Tsion","0718123123",microsoft);
        internshipVacancyRepository.save(internshipVacancy1);
        internshipVacancyRepository.save(internshipVacancy1);
    }
    @Test
    void test_getAllInternships_success(){
        //Given
        when(internshipVacancyRepository.findAll()).thenReturn(List.of(internshipVacancy1));

        //When
        List<InternshipVacancy> allInternships = internshipVacancyService.getAllInternships().collect(Collectors.toList());

        //Then
        assertEquals(1, allInternships.size());
        assertEquals(internshipVacancy1.getId(), allInternships.get(0).getId());
        assertEquals(internshipVacancy1.getContactEmployer(), allInternships.get(0).getContactEmployer());
    }
}
