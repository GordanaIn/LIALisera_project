package com.liserabackend.services;

import com.liserabackend.entity.*;
import com.liserabackend.entity.repository.*;
import com.liserabackend.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

//@Component
public class LoadDataService implements CommandLineRunner {
    @Autowired UserRepository userRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired InternshipVacancyRepository advertRepository;
    @Autowired CompanyRepository companyRepository;
    @Autowired SchoolRepository schoolRepository;
    @Autowired EducationRepository educationRepository;

    private void registerStudent() {
        if (userRepository.findAll().isEmpty()) {
           User eyuel= new User("eyuel@gmail.com", "eyuel@gmail.com","eyuel21",EnumRole.ROLE_STUDENT);
           User jafer=new User("jafer@gmail.com", "jafer@gmail.com","jafer21", EnumRole.ROLE_STUDENT);
           userRepository.save(eyuel);
           userRepository.save(jafer);
           if(studentRepository.findAll().isEmpty()){
               Student studentEyuel=new Student( "Eyuel", "Belay","0712345611", eyuel);
               Student studentJafer=new Student( "Jafer", "Redi","0712345667",jafer);

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
                 companyRepository.save(companyMicrosoft);
               if (advertRepository.findAll().isEmpty()) {
                   InternshipVacancy internshipVacancy1 = new com.liserabackend.entity.InternshipVacancy("Junior Java Developer", "Junior Java developer that has a good skill in react and springboot",
                           "5 month duration", InternshipVacancyStatus.OPEN, LocalDate.of(2021, 10, 20), "Jafer", "0745672391", companyMicrosoft);
                   internshipVacancy1.setRequiredProfession(EnumProfession.PROFESSION_JAVAUTVECKLARE);
                   InternshipVacancy internshipVacancy2 = new com.liserabackend.entity.InternshipVacancy("Junior C# Developer", "Junior C# developer that has a good skill in react and springboot",
                           "5 month duration", InternshipVacancyStatus.CLOSED, LocalDate.of(2021, 10, 21), "Selam", "0345672391",companyMicrosoft);
                   internshipVacancy2.setRequiredProfession(EnumProfession.PROFESSION_CSHARP);
                   advertRepository.save(internshipVacancy1);
                   advertRepository.save(internshipVacancy2);
               }
           }
       }
    }
    private void registerEductionForStudent(){
        //find a userId
      String userId=userRepository.findAll().stream().filter(u->u.getUsername().equals("eyuel@gmail.com")).map(User::getId).findAny().get();

     //find a student by a userId
      Student studentEyuel= studentRepository.findByUserId(userId).get();
      //register eduction associated with a given student
        if (educationRepository.findAll().isEmpty()) {
            Set<Education> educationSet=new HashSet<>();
            Education eductionEyuel1=new Education("Diploma in Java programming", SchoolName.SCHOOL_ECUTBILDNING, EducationType.DIPLOMA);
            Education eductionEyuel2=new Education("Diploma in software Testing", SchoolName.SCHOOL_JENSEN, EducationType.DIPLOMA);
            educationSet.add(eductionEyuel1);
            educationSet.add(eductionEyuel2);
            educationRepository.save(eductionEyuel1);
            educationRepository.save(eductionEyuel2);
            studentEyuel.setEducations(educationSet);
            studentRepository.save(studentEyuel);
        }
    }
    private void registerSchool(){
        if(userRepository.findAll().stream().anyMatch(user -> !user.getRole().equals(EnumRole.ROLE_SCHOOL))) {
            User ecUser= new User("ecUser@ec.com", "ecUser@ec.com","ecUser21",EnumRole.ROLE_SCHOOL);
            userRepository.save(ecUser);
            if (schoolRepository.findAll().isEmpty()) {
                School schoolEC = new School("EC Utbildning","040-6416300", "671285-5677"," info@ecutbildning.se", ecUser);
                schoolRepository.save(schoolEC);
            }
        }

    }
    @Override
    public void run(String... args) {
        registerStudent();
        registerAdvert();
        registerSchool();
        registerEductionForStudent();
    }
}
