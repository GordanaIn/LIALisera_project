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
    @Autowired EmployeeRepository employeeRepository;

    private void registerUser(){
        if (userRepository.findAll().isEmpty()) {
            User eyuel= new User("eyuel@gmail.com", "eyuel@gmail.com","eyuel21",EnumRole.ROLE_STUDENT);
            User jafer=new User("jafer@gmail.com", "jafer@gmail.com","jafer21", EnumRole.ROLE_STUDENT);

            userRepository.save(eyuel);
            userRepository.save(jafer);
            registerStudent(eyuel, jafer);
            registerCompany();

        }
    }
    private void registerStudent(User eyuel, User jafer) {
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
    private void registerEmployee( Company companyGoogle, Company companyMicrosoft){
        User microsoftUser = new User("helen@microsoft.com", "helen@microsoft.com","helen21",EnumRole.ROLE_EMPLOYEE);
        User googleUser = new User("josefin@google.com", "josefin@google.com","jojo21",EnumRole.ROLE_EMPLOYEE);
        userRepository.save(microsoftUser);
        userRepository.save(googleUser);
        if(employeeRepository.findAll().isEmpty()){

            Employee employeeMicrosoft = new Employee("Lisa", "Johansson", "lias@microsoft.com", microsoftUser);
            Employee employeeGoogle = new Employee("Linda", "Eriksson", "linda@google.com",googleUser);

            employeeGoogle.setCompany(companyGoogle);
            employeeMicrosoft.setCompany(companyMicrosoft);
            employeeRepository.save(employeeMicrosoft);
            employeeRepository.save(employeeGoogle);


        }
    }
   private void registerCompany(){
       if (companyRepository.findAll().isEmpty()) {
           Company companyMicrosoft = new Company("Microsoft", "microsoft101");
           Company companyGoogle = new Company("Google", "google101");
           companyRepository.save(companyMicrosoft);
           companyRepository.save(companyGoogle);

           registerEmployee(companyGoogle, companyMicrosoft);
           registerAdvert(companyMicrosoft, companyGoogle);
       }
   }


    private void registerAdvert(Company companyMicrosoft, Company companyGoogle  ) {

               if (internshipAdvertRepository.findAll().isEmpty()) {
                   InternshipAdvert internshipVacancyGoogle = new InternshipAdvert("Junior Java Developer", "Junior Java developer that has a good skill in react and springboot",
                           "5 month duration", LocalDate.of(2021, 10, 20), "Mr Google Expert", "+745672391", 2, companyGoogle);

                   InternshipAdvert internshipVacancyMicrosoft = new InternshipAdvert("Junior C# Developer", "Junior C# developer that has a good skill in react and typescript",
                           "5 month duration", LocalDate.of(2021, 10, 21), "Mr Microsoft Expert", " 08-7525600",1, companyMicrosoft);

                   companyGoogle.getInternshipAdvertList().add(internshipVacancyGoogle);
                   companyMicrosoft.getInternshipAdvertList().add(internshipVacancyMicrosoft);

                   internshipAdvertRepository.save(internshipVacancyGoogle);
                   internshipAdvertRepository.save(internshipVacancyMicrosoft);
               }

    }
    @Override
    public void run(String... args) {
        registerUser();

   }
}
