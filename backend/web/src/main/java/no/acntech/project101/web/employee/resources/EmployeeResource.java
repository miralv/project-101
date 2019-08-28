package no.acntech.project101.web.employee.resources;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.acntech.project101.employee.Employee;
import no.acntech.project101.employee.service.EmployeeService;
import no.acntech.project101.web.employee.resources.converter.EmployeeConverter;
import no.acntech.project101.web.employee.resources.converter.EmployeeDtoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:3000")
//TODO This is a REST controler and should receive request on path employees
@RestController()
@RequestMapping("employees")

public class EmployeeResource {

    //TODO The constructor needs to accept the required dependencies and assign them to class variables
    private final EmployeeService employeeService;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final EmployeeConverter employeeConverter;

    public EmployeeResource(EmployeeService employeeService, EmployeeDtoConverter employeeDtoConverter, EmployeeConverter employeeConverter) {
        this.employeeService = employeeService;
        this.employeeDtoConverter = employeeDtoConverter;
        this.employeeConverter = employeeConverter;
    }



    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        //TODO create a GET endpoint find all employees in the database and return them
        /*
        final EmployeeDto myEmployee = new EmployeeDto(12L, "Mira", "Vik", LocalDate.of(1994,03,29), 33333333L);
        final EmployeeDto myEmployee2 = new EmployeeDto(11L, "Mira2", "Vik", LocalDate.of(1994,03,29), 33333333L);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeDtos.add(myEmployee);
        employeeDtos.add(myEmployee2);
        */
        final List<Employee> employees = employeeService.findAll();
        final List<EmployeeDto> collect = employees.stream()
                .map(employeeDtoConverter::convert)
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable final Long id) {
        // TODO create a GET endpoint that fetches a spesific employee based on its ID
        // final EmployeeDto myEmp = new EmployeeDto(id, "Mira3", "Vik", LocalDate.of(2003,01,23), 44444444L);
        final Optional<Employee> employee = employeeService.findById(id);

        if (employee.isPresent()) {
            final EmployeeDto employeeDto = employeeDtoConverter.convert(employee.get());
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    /*
    @GetMapping("{ids}")
    public ResponseEntity<List<EmployeeDto>> findByIds(@PathVariable final List<Long> ids) {
        // antar at jeg får inn 1 eller flere id-er.
        // vil returnere en liste med ønskede employees.
        final EmployeeDto myEmployee = new EmployeeDto(ids.get(0), "Mira", "Vik", LocalDate.of(1994,03,29), 33333333L);
        final EmployeeDto myEmployee2 = new EmployeeDto(ids.get(1), "Mira2", "Vik", LocalDate.of(1994,03,29), 33333333L);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employeeDtos.add(myEmployee);
        employeeDtos.add(myEmployee2);

        return ResponseEntity.ok(employeeDtos);
    }*/

    @PostMapping
    public ResponseEntity createEmployee(@RequestBody final EmployeeDto employeeDto) {
        //TODO Create a POST endpoint that accepts an employeeDTO and saves it in the database
    flinal URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(2)
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEmployee(@PathVariable final Long id) {
        // TODO Create a DELETE endpoint that deletes a specific employee
    boolean deleteSuccessfull = true;
    if (deleteSuccessfull) {
        return ResponseEntity.accepted().build();
    } else {
        return ResponseEntity.notFound().build();
    }
    }

    @PatchMapping({"{id}"})
    public ResponseEntity updateEmployee(@PathVariable final Long id, @RequestBody final EmployeeDto employeeDto) {
        //TODO Create a PATCH endpoint that updates an employee with new values
        final EmployeeDto empDto = new EmployeeDto(id, employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getDateOfBirth(), employeeDto.getCompanyId());

        return ResponseEntity.ok(empDto);
    }
l}
