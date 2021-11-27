package com.liserabackend.services;

import com.liserabackend.entity.*;
import com.liserabackend.entity.repository.*;
import com.liserabackend.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Component
public class LoadDataService implements CommandLineRunner {
    @Autowired UserRepository userRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired InternshipVacancyRepository advertRepository;
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

               Student studentJafer=new Student( "Jafer", "Redi","0712345667",jafer);
               studentJafer.setLinkedInUrl("https://www.linkedin.com/in/adamjafer/");
            studentRepository.save(studentEyuel);
            studentRepository.save(studentJafer);
           }

       }
    }

    private void registerAdvert() {
       if(userRepository.findAll().stream().anyMatch(user -> !user.getRole().equals(EnumRole.ROLE_EMPLOYER))) {
           User microsoftUser= new User("helen@microsoft.com", "helen@microsoft.com","helen21",EnumRole.ROLE_EMPLOYER);
           userRepository.save(microsoftUser);
           if (companyRepository.findAll().isEmpty()) {
               Company companyMicrosoft = new Company("Microsoft", "microsoft101", "microsoft@microsoft.com",microsoftUser);
               Company companyGoogle = new Company("Google", "google101", "google@google.com",microsoftUser);
               companyRepository.save(companyMicrosoft);
               companyRepository.save(companyGoogle);
               if (advertRepository.findAll().isEmpty()) {
                   InternshipVacancy internshipVacancy1 = new com.liserabackend.entity.InternshipVacancy("Junior Java Developer", "Junior Java developer that has a good skill in react and springboot",
                           "5 month duration", LocalDate.of(2021, 10, 20), "Mr Google Expert", "+745672391", 2, companyGoogle);

                   InternshipVacancy internshipVacancy2 = new com.liserabackend.entity.InternshipVacancy("Junior C# Developer", "Junior C# developer that has a good skill in react and typescript",
                           "5 month duration", LocalDate.of(2021, 10, 21), "Mr Microsoft Expert", " 08-7525600",1, companyMicrosoft);
                   advertRepository.save(internshipVacancy1);
                   advertRepository.save(internshipVacancy2);
               }
           }
       }
    }

    @Override
    public void run(String... args) {
        registerStudent();
        registerAdvert();
   }
}
