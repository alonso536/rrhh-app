package org.alonso.rrhhapp.models.helpers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.dto.UpdateEmployeeDTO;
import org.alonso.rrhhapp.models.entities.Address;
import org.alonso.rrhhapp.models.entities.City;
import org.alonso.rrhhapp.models.entities.Department;
import org.alonso.rrhhapp.models.entities.Employee;
import org.alonso.rrhhapp.models.entities.Job;

public class DataTestHelper {

        public static final List<Job> JOBS_TEST = Arrays.asList(
                        new Job(1L, "Software Developer", "description...", new Department(1L, "Development")),
                        new Job(2L, "QA Analyst", "description...", new Department(2L, "Quality Assurance")),
                        new Job(3L, "Data Scientist", "description...", new Department(3L, "Data")));

        public static final List<City> CITIES_TEST = Arrays.asList(
                        new City(1L, "Santiago"),
                        new City(2L, "Buenos aires"),
                        new City(3L, "New York"));

        public static final List<Employee> EMPLOYEES_BOSSES_TEST = Arrays.asList(
                        new Employee(8L, "John", "Roe", "john.roe@mail.com", "4365346",
                                        new Address(1L, "Wall street", 123, CITIES_TEST.get(0)), JOBS_TEST.get(0), null,
                                        LocalDate.of(1989, 2, 12), LocalDate.of(2021, 3, 13), null),
                        new Employee(9L, "Jane", "Roe", "jane,roe@mail.com", "8645788",
                                        new Address(2L, "Av Evergreen", 456, CITIES_TEST.get(1)), JOBS_TEST.get(1),
                                        null,
                                        LocalDate.of(1990, 11, 8), LocalDate.of(2022, 3, 5), null));

        public static final List<Employee> EMPLOYEES_TEST = Arrays.asList(
                        new Employee(1L, "John", "Doe", "john@mail.com", "4365346",
                                        new Address(1L, "Wall street", 123, CITIES_TEST.get(0)), JOBS_TEST.get(0),
                                        EMPLOYEES_BOSSES_TEST.get(0),
                                        LocalDate.of(1989, 2, 12), LocalDate.of(2021, 3, 13), null),
                        new Employee(2L, "Jane", "Doe", "jane@mail.com", "8645788",
                                        new Address(2L, "Av Evergreen", 456, CITIES_TEST.get(1)), JOBS_TEST.get(1),
                                        EMPLOYEES_BOSSES_TEST.get(0),
                                        LocalDate.of(1990, 11, 8), LocalDate.of(2022, 3, 5), null));

        public static final Address ADDRESS_TEST = new Address(null, "Mason", 987, CITIES_TEST.get(0));

        public static final Employee EMPLOYEE_TEST = new Employee(null, "Pepe", "Doe", "pepe@mail.com", "5676457",
                        ADDRESS_TEST, JOBS_TEST.get(0), EMPLOYEES_BOSSES_TEST.get(0),
                        LocalDate.of(1994, 6, 26), null, null);

        public static final Employee EMPLOYEE_FIRED_TEST = new Employee(2L, "Jane", "Doe", "jane@mail.com", "8645788",
                        new Address(2L, "Av Evergreen", 456, CITIES_TEST.get(1)), JOBS_TEST.get(1), null,
                        LocalDate.of(1990, 11, 8), LocalDate.of(2022, 3, 5), LocalDate.now());

        public static final CreateEmployeeDTO CREATE_EMPLOYEE_DTO_TEST = new CreateEmployeeDTO("Pepe", "Doe",
                        "pepe@mail.com", "5676457", "1994-06-26", 1L, "Mason", 987, 1L);

        public static final CreateEmployeeDTO CREATE_EMPLOYEE_DTO_BAD_JOB_TEST = new CreateEmployeeDTO("Pepe", "Doe",
                        "pepe@mail.com", "5676457", "1994-06-26", 52L, "Mason", 987, 1L);

        public static final CreateEmployeeDTO CREATE_EMPLOYEE_DTO_BAD_CITY_TEST = new CreateEmployeeDTO("Pepe", "Doe",
                        "pepe@mail.com", "5676457", "1994-06-26", 1L, "Mason", 987, 100L);

        public static final UpdateEmployeeDTO UPDATE_EMPLOYEE_DTO_TEST = new UpdateEmployeeDTO("John", "Doe",
                        "john@mail.com", "3252345");

        public static final Employee EMPLOYEE_BUILD_TEST = new Employee(45L, "Jane", "Doe", "jane@mail.com", "8645788",
                        new Address(2L, "Av Evergreen", 456, CITIES_TEST.get(1)), JOBS_TEST.get(1),
                        EMPLOYEES_BOSSES_TEST.get(1),
                        LocalDate.of(1990, 11, 8), LocalDate.of(2022, 3, 5), null);

        public static final EmployeeDTO UPDATE_EMPLOYEE_BUILD_TEST = new EmployeeDTO(45L, "Jane", "Doe",
                        "jane@mail.com", "8645788",
                        "Av Evergreen 456 Santiago", "Jane Roe", "QA Analyst", "Quality Assurance",
                        LocalDate.of(1990, 11, 8), LocalDate.of(2022, 3, 5), null);
}
