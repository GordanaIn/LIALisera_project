package com.liserabackend.services;

import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.entity.repository.StudentRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.enums.InternshipVacancyStatus;
import com.liserabackend.enums.EnumProfession;
import com.liserabackend.enums.EnumRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoadDataService implements CommandLineRunner {
    @Autowired UserRepository userRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired InternshipVacancyRepository advertRepository;

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
       //studentRepository.findAll().forEach(System.out::println);
    }

    private void registerAdvert() {
        if(advertRepository.findAll().isEmpty()){
            com.liserabackend.entity.InternshipVacancy internshipVacancy1 = new com.liserabackend.entity.InternshipVacancy("Junior Java Developer", "Junior Java developer that has a good skill in react and springboot",
                    "5 month duration", InternshipVacancyStatus.OPEN, LocalDate.of(2021,10,20), "Jafer", "0745672391");
            internshipVacancy1.setRequiredProfession(EnumProfession.PROFESSION_JAVAUTVECKLARE);
            com.liserabackend.entity.InternshipVacancy internshipVacancy2 = new com.liserabackend.entity.InternshipVacancy("Junior C# Developer", "Junior C# developer that has a good skill in react and springboot",
                    "5 month duration", InternshipVacancyStatus.CLOSED,LocalDate.of(2021,10,21),"Selam", "0345672391");
            internshipVacancy2.setRequiredProfession(EnumProfession.PROFESSION_CSHARP);
            advertRepository.save(internshipVacancy1);
            advertRepository.save(internshipVacancy2);
            //advertRepository.findAll().forEach(System.out::println);
        }

    }
    @Override
    public void run(String... args) {
        registerStudent();
        registerAdvert();
    }
}
