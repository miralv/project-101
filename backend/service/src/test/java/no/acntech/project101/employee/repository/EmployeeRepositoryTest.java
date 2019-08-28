package no.acntech.project101.employee.repository;

import no.acntech.project101.company.config.CompanyDatabaseConfig;
import no.acntech.project101.company.repository.CompanyRepository;
import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.config.EmployeeDatabaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import({CompanyDatabaseConfig.class, EmployeeDatabaseConfig.class})
@ContextConfiguration(classes = EmployeeRepository.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void save() {
        //TODO: implement, and add necessary annotations for the test to run.
        final Employee employee = new Employee("Mira", "Vik", LocalDate.of(1994,03,29));
        final Employee savedEmployee = employeeRepository.save(employee);
        assertThat(savedEmployee.getFirstName() == employee.getFirstName());
        assertThat(savedEmployee.getLastName() == employee.getLastName());
        assertThat(savedEmployee.getDateOfBirth() == employee.getDateOfBirth());
    }
}