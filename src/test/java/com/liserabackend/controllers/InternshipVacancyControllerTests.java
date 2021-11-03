package com.liserabackend.controllers;

import com.liserabackend.dto.InternshipVacancyDTO;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.CompanyRepository;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.enums.EnumRole;
import com.liserabackend.enums.EnumStatus;
import com.liserabackend.enums.InternshipVacancyStatus;
import com.liserabackend.services.InternshipVacancyServiceImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationtest")
public class InternshipVacancyControllerTests {
    @MockBean
    InternshipVacancyServiceImp internshipVacancyService;
    @LocalServerPort
    int port;
    @Autowired
    InternshipVacancyRepository internshipVacancyRepository;
    InternshipVacancyDTO internshipVacancyDTO1;
    InternshipVacancyDTO internshipVacancyDTO2;
    InternshipVacancy internshipVacancy1;
    InternshipVacancy internshipVacancy2;
    @Autowired InternshipVacancyController internshipVacancyController;
    @Autowired CompanyRepository companyRepository;
    @Autowired UserRepository userRepository;
    @BeforeEach
    @Transactional
    void setUp() {
        User jafer= new User("jafer@gmail.com", "jafer@gmail.com","jafer21", EnumRole.ROLE_EMPLOYER);
        userRepository.save(jafer);
        Company microsoft=new Company("Microsoft","Microsoft-12345", jafer);
        companyRepository.save(microsoft);

        internshipVacancy1 = new InternshipVacancy("Java Full-stack", "Javakunnig person med intresse för frontend","4 months" ,InternshipVacancyStatus.OPEN,LocalDate.of(2021,11,20),"Ms.Tsion","0718123456",microsoft);
        internshipVacancyRepository.save(internshipVacancy1);
        internshipVacancy2 = new InternshipVacancy("C# Full-stack", "C# FULL-STACK med intresse för frontend","3 months", InternshipVacancyStatus.OPEN,LocalDate.of(2021,11,20),"Ms.Tsion","0718123123",microsoft);
        internshipVacancyRepository.save(internshipVacancy2);

        internshipVacancyDTO1 = internshipVacancyController.toInternshipVacancyDTO(internshipVacancy1);
        internshipVacancyDTO2 = internshipVacancyController.toInternshipVacancyDTO(internshipVacancy2);
    }
    @AfterEach
    void tearDown() {
        internshipVacancyRepository.deleteAll();
    }

    @Test
    void test_getInternships_success() {
        when(internshipVacancyService.getAllInternships()).thenReturn(Stream.of(internshipVacancy1, internshipVacancy2));
        List<InternshipVacancyDTO> internships = getInternship("");
        //Then
        assertEquals(List.of(internshipVacancyDTO1,internshipVacancyDTO2),internships);
        assertEquals(2,internships.size());

    }
    @Test
    void test_getAllInternship_success() {
        //Given
        when(internshipVacancyService.getAllInternships()).thenReturn(Stream.of(internshipVacancy1));
        List<InternshipVacancyDTO> internships = getInternship("");
        //Then
        assertEquals(1, internships.size());
        assertEquals(List.of(internshipVacancyDTO1), internships);
    }

    private List<InternshipVacancyDTO> getInternship(String url) {
        WebClient webClient = WebClient.create("http://localhost:" + port + "/api/internship" + url);
        return webClient.get()
                .retrieve()
                .bodyToFlux(InternshipVacancyDTO.class)
                .collectList()
                .block();
    }
}
