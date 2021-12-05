package com.liserabackend.services;

import com.liserabackend.entity.repository.RoleRepositories;
import com.liserabackend.entity.*;
import com.liserabackend.entity.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LoadDataService implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    InternshipAdvertRepository internshipAdvertRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RoleRepositories roleRepositories;

    private void registerRole() {
        if (roleRepositories.findAll().isEmpty()) {
            Role roleStudent = new Role("ROLE_STUDENT");
            Role roleEmployee = new Role("ROLE_EMPLOYEE");
            Role roleAdmin = new Role("ROLE_ADMIN");
            roleRepositories.save(roleStudent);
            roleRepositories.save(roleEmployee);
            roleRepositories.save(roleAdmin);
            registerUser(roleStudent, roleEmployee);
        }
    }

    private void registerUser(Role roleStudent, Role roleEmployee) {
        if (userRepository.findAll().isEmpty()) {
            //create user
            User eyuel = new User("eyuel@gmail.com", "eyuel21");
            User jafer = new User("jafer@gmail.com", "jafer21");
            User microsoftUser = new User("helen@microsoft.com", "helen21");
            User googleUser = new User("josefin@google.com", "jojo21");
            //add roles

            eyuel.getRoles().add(roleStudent);
            jafer.getRoles().add(roleStudent);
            microsoftUser.getRoles().add(roleEmployee);
            googleUser.getRoles().add(roleEmployee);
            //save in repo
            userRepository.save(eyuel);
            userRepository.save(jafer);
            userRepository.save(microsoftUser);
            userRepository.save(googleUser);

            registerStudent(eyuel, jafer);
            registerCompany();

        }
    }

    private void registerStudent(User eyuel, User jafer) {
        if (studentRepository.findAll().isEmpty()) {
            Student studentEyuel = new Student("Eyuel", "Belay", "0712345611", eyuel);
            studentEyuel.setLinkedInUrl("https://www.linkedin.com/in/eyuel-t-belay-633889167/");
            studentEyuel.setSchoolName("EC Utbildning");

            Student studentJafer = new Student("Jafer", "Redi", "0712345667", jafer);
            studentJafer.setLinkedInUrl("https://www.linkedin.com/in/adamjafer/");
            studentJafer.setSchoolName("Nackademin Utbildning");

            studentRepository.save(studentEyuel);
            studentRepository.save(studentJafer);
        }
    }

    private void registerEmployee(Company companyGoogle, Company companyMicrosoft) {
        final List<Role> role_employee = roleRepositories.findAll().stream().filter(role -> role.getName().equals("ROLE_EMPLOYEE")).collect(Collectors.toList());
        final Stream<User> userStream = userRepository.findAll().stream().filter(user -> user.getRoles().equals(role_employee));
        final var microsoftUser = userStream.filter(u -> u.getUsername().contains("microsoft.com")).findFirst().get();
        final var googleUser = userStream.filter(u -> u.getUsername().contains("google.com")).findFirst().get();
        if (employeeRepository.findAll().isEmpty()) {
            //

            Employee employeeMicrosoft = new Employee("Lisa", "Johansson", "lias@microsoft.com", microsoftUser);
            Employee employeeGoogle = new Employee("Linda", "Eriksson", "linda@google.com", googleUser);

            employeeGoogle.setCompany(companyGoogle);
            employeeMicrosoft.setCompany(companyMicrosoft);
            employeeRepository.save(employeeMicrosoft);
            employeeRepository.save(employeeGoogle);
            // add employees in company
            final var googleCompany = companyRepository.findAll().stream().filter(c -> c.getName().contains("Google")).findFirst().get();
            final var microsoftCompany = companyRepository.findAll().stream().filter(c -> c.getName().contains("Microsoft")).findFirst().get();
            companyGoogle.getEmployees().add(employeeGoogle);
            companyMicrosoft.getEmployees().add(employeeMicrosoft);
            companyRepository.save(googleCompany);
            companyRepository.save(microsoftCompany);
        }
    }

    private void registerCompany() {
        if (companyRepository.findAll().isEmpty()) {
            Company companyMicrosoft = new Company("Microsoft", "microsoft101");
            Company companyGoogle = new Company("Google", "google101");
            companyRepository.save(companyMicrosoft);
            companyRepository.save(companyGoogle);

            registerEmployee(companyGoogle, companyMicrosoft);
            registerAdvert(companyMicrosoft, companyGoogle);
        }
    }


    private void registerAdvert(Company companyMicrosoft, Company companyGoogle) {

        if (internshipAdvertRepository.findAll().isEmpty()) {
            InternshipAdvert internshipVacancyGoogle = new InternshipAdvert("Junior Java Developer", "Junior Java developer that has a good skill in react and springboot",
                    "5 month duration", LocalDate.of(2021, 10, 20), "Mr Google Expert", "+745672391", 2, companyGoogle);

            InternshipAdvert internshipVacancyMicrosoft = new InternshipAdvert("Junior C# Developer", "Junior C# developer that has a good skill in react and typescript",
                    "5 month duration", LocalDate.of(2021, 10, 21), "Mr Microsoft Expert", " 08-7525600", 1, companyMicrosoft);

            companyGoogle.getInternshipAdvertList().add(internshipVacancyGoogle);
            companyMicrosoft.getInternshipAdvertList().add(internshipVacancyMicrosoft);

            internshipAdvertRepository.save(internshipVacancyGoogle);
            internshipAdvertRepository.save(internshipVacancyMicrosoft);
        }

    }

    @Override
    public void run(String... args) {
        registerRole();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
