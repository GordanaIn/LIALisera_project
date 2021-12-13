package com.liserabackend;

import com.liserabackend.entity.*;
import com.liserabackend.services.CompanyServiceImpl;
import com.liserabackend.services.InternshipAdvertService;
import com.liserabackend.services.StudentService;
import com.liserabackend.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LiserabackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiserabackendApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();  }

    @Bean
    CommandLineRunner run(UserService userService, StudentService studentService, CompanyServiceImpl companyService, InternshipAdvertService internshipAdvertService) {
        return args -> {
            try {
                //add role
                userService.saveRole(new Role("ROLE_STUDENT"));
                userService.saveRole(new Role("ROLE_EMPLOYEE"));
                userService.saveRole(new Role("ROLE_ADMIN"));
                //add user
                userService.saveUser(new User("eyuel@gmail.com", "eyuel21"));
                userService.saveUser(new User("jafer@gmail.com", "jafer21"));
                userService.saveUser(new User("helen@microsoft.com", "helen21"));
                userService.saveUser(new User("josefin@google.com", "jojo21"));
                //add role to user
                userService.addRoleToUser("eyuel@gmail.com", "ROLE_STUDENT");
                userService.addRoleToUser("jafer@gmail.com", "ROLE_STUDENT");
                userService.addRoleToUser("helen@microsoft.com", "ROLE_EMPLOYEE");
                userService.addRoleToUser("josefin@google.com", "ROLE_EMPLOYEE");

                //add student
                Student studentEyuel = new Student("Eyuel", "Belay", "0712345611", userService.getUserByUserName("eyuel@gmail.com").get() );
                studentEyuel.setLinkedInUrl("https://www.linkedin.com/in/eyuel-t-belay-633889167/");
                studentEyuel.setSchoolName("EC Utbildning");

                Student studentJafer = new Student("Jafer", "Redi", "0712345667",  userService.getUserByUserName("jafer@gmail.com").get());
                studentJafer.setLinkedInUrl("https://www.linkedin.com/in/adamjafer/");
                studentJafer.setSchoolName("Nackademin Utbildning");
                studentService.saveStudent(studentEyuel);
                studentService.saveStudent(studentJafer);

                Company companyMicrosoft = new Company("Microsoft", "microsoft101");
                Company companyGoogle = new Company("Google", "google101");
                companyService.saveCompany(companyGoogle);
                companyService.saveCompany(companyMicrosoft);


                Employee employeeMicrosoft = new Employee("Lisa", "Johansson", "lias@microsoft.com", userService.getUserByUserName("helen@microsoft.com").get());
                Employee employeeGoogle = new Employee("Linda", "Eriksson", "linda@google.com", userService.getUserByUserName("josefin@google.com").get());

                employeeGoogle.setCompany(companyGoogle);
                employeeMicrosoft.setCompany(companyMicrosoft);
                companyService.saveEmployee(employeeMicrosoft);
                companyService.saveEmployee(employeeGoogle);

           /* final var google = companyService.getCompanyByName("Google");
            final var microsoft = companyService.getCompanyByName("Microsoft");
            google.getEmployees().add(employeeGoogle);
            microsoft.getEmployees().add(employeeMicrosoft);
            companyService.saveCompany(google);
            companyService.saveCompany(microsoft);*/

                InternshipAdvert internshipVacancyGoogle = new InternshipAdvert("Junior Java Developer", "Junior Java developer that has a good skill in react and springboot",
                        "5 month duration", LocalDate.of(2021, 10, 20), "Mr Google Expert", "+745672391", 2, companyGoogle);

                InternshipAdvert internshipVacancyMicrosoft = new InternshipAdvert("Junior C# Developer", "Junior C# developer that has a good skill in react and typescript",
                        "5 month duration", LocalDate.of(2021, 10, 21), "Mr Microsoft Expert", " 08-7525600", 1, companyMicrosoft);

                companyGoogle.getInternshipAdvertList().add(internshipVacancyGoogle);
                companyMicrosoft.getInternshipAdvertList().add(internshipVacancyMicrosoft);

                internshipAdvertService.addInternshipAdvert(internshipVacancyGoogle);
                internshipAdvertService.addInternshipAdvert(internshipVacancyMicrosoft);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
