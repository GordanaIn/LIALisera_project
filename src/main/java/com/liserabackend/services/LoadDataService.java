package com.liserabackend.services;

import com.liserabackend.entity.*;
import com.liserabackend.entity.repository.*;
import com.liserabackend.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoadDataService implements CommandLineRunner {
    @Autowired UserRepository userRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired InternshipAdvertRepository internshipAdvertRepository;
    @Autowired CompanyRepository companyRepository;
    @Autowired SchoolRepository schoolRepository;

    private void registerStudent() {
        if (userRepository.findAll().isEmpty()) {
           User eyuel= new User("eyuel@gmail.com", "eyuel@gmail.com","eyuel21",EnumRole.ROLE_STUDENT);
           User jafer=new User("jafer@gmail.com", "jafer@gmail.com","jafer21", EnumRole.ROLE_STUDENT);
           userRepository.save(eyuel);
           userRepository.save(jafer);

           if(studentRepository.findAll().isEmpty()){
               Student studentEyuel=new Student( "Eyuel", "Belay","0712345611", eyuel);
               studentEyuel.setLinkedInUrl("https://www.linkedin.com/in/eyuel-t-belay-633889167/");
               studentEyuel.setSchoolName("EC Utbildning");
               Student studentJafer=new Student( "Jafer", "Redi","0712345667",jafer);
               studentJafer.setLinkedInUrl("https://www.linkedin.com/in/adamjafer/");
               studentJafer.setSchoolName("Nackademin Utbildning");
               studentRepository.save(studentEyuel);
               studentRepository.save(studentJafer);
           }

       }
    }

    private void registerAdvert() {
       if(userRepository.findAll().stream().anyMatch(user -> !user.getRole().equals(EnumRole.ROLE_EMPLOYER))) {
           User microsoftUser= new User("helen@microsoft.com", "helen@microsoft.com","helen21",EnumRole.ROLE_EMPLOYER);
           userRepository.save(microsoftUser);
           User googleUser= new User("josefin@google.com", "josefin@google.com","jojo21",EnumRole.ROLE_EMPLOYER);
           userRepository.save(googleUser);
           if (companyRepository.findAll().isEmpty()) {
               Company companyMicrosoft = new Company("Microsoft", "microsoft101", "microsoft@microsoft.com",microsoftUser);
               Company companyGoogle = new Company("Google", "google101", "google@google.com",googleUser);
               companyRepository.save(companyMicrosoft);
               companyRepository.save(companyGoogle);
               if (internshipAdvertRepository.findAll().isEmpty()) {
                   InternshipAdvert internshipVacancyGoogle = new InternshipAdvert("Junior Java Developer", "Junior Java developer that has a good skill in react and springboot",
                           "5 month duration", LocalDate.of(2021, 10, 20), "Mr Google Expert", "+745672391", 2, companyGoogle);

                   InternshipAdvert internshipVacancyMicrosoft = new InternshipAdvert("Junior C# Developer", "Junior C# developer that has a good skill in react and typescript",
                           "5 month duration", LocalDate.of(2021, 10, 21), "Mr Microsoft Expert", " 08-7525600",1, companyMicrosoft);

                   companyGoogle.getInternshipVacancyList().add(internshipVacancyGoogle);
                   companyMicrosoft.getInternshipVacancyList().add(internshipVacancyMicrosoft);

                   internshipAdvertRepository.save(internshipVacancyGoogle);
                   internshipAdvertRepository.save(internshipVacancyMicrosoft);
               }
           }
       }
    }
    private void registerSchool(){
        if(userRepository.findAll().stream().anyMatch(user -> !user.getRole().equals(EnumRole.ROLE_SCHOOL))) {
            User ecUser= new User("ecUser@ec.com", "ecUser@ec.com","ecUser21",EnumRole.ROLE_SCHOOL);
            User nackademinUser= new User("ecUser@ec.com", "ecUser@ec.com","ecUser21",EnumRole.ROLE_SCHOOL);
            userRepository.save(ecUser);
            userRepository.save(nackademinUser);
            if (schoolRepository.findAll().isEmpty()) {
                School schoolEC = new School("EC Utbildning","040-6416300", "671285-5677"," info@ecutbildning.se", ecUser);
                School schoolNackademin = new School("Nackademin Utbildning", "08-466 60 00","601834-5677"," info@nackademin.se", nackademinUser);

                schoolRepository.save(schoolEC);
                schoolRepository.save(schoolNackademin);
            }
        }
    }

    @Override
    public void run(String... args) {
        registerStudent();
        registerAdvert();
        registerSchool();
   }
}
