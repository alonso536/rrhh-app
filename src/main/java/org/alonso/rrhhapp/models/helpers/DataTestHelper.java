package org.alonso.rrhhapp.models.helpers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.alonso.rrhhapp.models.dto.CreateEmployeeDTO;
import org.alonso.rrhhapp.models.dto.UpdateEmployeeDTO;
import org.alonso.rrhhapp.models.entities.Address;
import org.alonso.rrhhapp.models.entities.City;
import org.alonso.rrhhapp.models.entities.Department;
import org.alonso.rrhhapp.models.entities.Employee;
import org.alonso.rrhhapp.models.entities.Job;
import org.alonso.rrhhapp.models.entities.UserEntity;

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
            Employee.builder().id(8L)
                    .name("John")
                    .lastname("Roe")
                    .email("john.roe@mail.com")
                    .phone("4365346")
                    .address(new Address(1L, "Wall street", 123, CITIES_TEST.get(0)))
                    .job(JOBS_TEST.get(0)).boss(null)
                    .birthdate(LocalDate.of(1989, 2, 12))
                    .hiredate(LocalDate.of(2021, 3, 13))
                    .firedate(null)
                    .build(),

            Employee.builder().id(8L)
                    .name("Jane")
                    .lastname("Roe")
                    .email("jane.roe@mail.com")
                    .phone("8645788")
                    .address(new Address(1L, "Av Evergreem", 456, CITIES_TEST.get(1)))
                    .job(JOBS_TEST.get(1)).boss(null)
                    .birthdate(LocalDate.of(1990, 6, 20))
                    .hiredate(LocalDate.of(2021, 3, 13))
                    .firedate(null)
                    .build());

    public static final List<Employee> EMPLOYEES_TEST = Arrays.asList(
            Employee.builder()
                    .id(1L)
                    .name("John")
                    .lastname("Doe")
                    .email("john@mail.com")
                    .phone("4365346")
                    .address(new Address(1L, "Wall street", 123, CITIES_TEST.get(0)))
                    .job(JOBS_TEST.get(0))
                    .boss(EMPLOYEES_BOSSES_TEST.get(0))
                    .birthdate(LocalDate.of(1984, 7, 17))
                    .hiredate(LocalDate.of(2021, 5, 25))
                    .firedate(null)
                    .build(),

            Employee.builder()
                    .id(2L)
                    .name("Jane")
                    .lastname("Doe")
                    .email("jane@mail.com")
                    .phone("8645788")
                    .address(new Address(1L, "Av Evergreen", 789, CITIES_TEST.get(2)))
                    .job(JOBS_TEST.get(0))
                    .boss(EMPLOYEES_BOSSES_TEST.get(0))
                    .birthdate(LocalDate.of(1992, 1, 9))
                    .hiredate(LocalDate.of(2023, 4, 16))
                    .firedate(null)
                    .build());

    public static final Address ADDRESS_TEST = new Address(null, "Mason", 987, CITIES_TEST.get(0));

    public static final Employee EMPLOYEE_TEST = Employee.builder()
            .id(null)
            .name("Pepe")
            .lastname("Doe")
            .email("pepe@mail.com")
            .phone("5676457")
            .address(ADDRESS_TEST)
            .job(JOBS_TEST.get(0))
            .boss(EMPLOYEES_BOSSES_TEST.get(0))
            .birthdate(LocalDate.of(1994, 6, 26))
            .hiredate(null)
            .firedate(null)
            .build();

    public static final Employee EMPLOYEE_FIRED_TEST = Employee.builder()
            .id(2L)
            .name("Jane")
            .lastname("Doe")
            .email("jane@mail.com")
            .phone("8645788")
            .address(new Address(1L, "Av Evergreen", 789, CITIES_TEST.get(1)))
            .job(JOBS_TEST.get(0))
            .boss(EMPLOYEES_BOSSES_TEST.get(0))
            .birthdate(LocalDate.of(1992, 1, 9))
            .hiredate(LocalDate.of(2023, 4, 16))
            .firedate(LocalDate.now())
            .build();

    public static final CreateEmployeeDTO CREATE_EMPLOYEE_DTO_TEST = CreateEmployeeDTO.builder()
            .name("Pepe")
            .lastname("Doe")
            .email("pepe@mail.com")
            .phone("5676457")
            .birthdate("1994-06-26")
            .job(1L)
            .street("Mason")
            .number(987)
            .city(1L)
            .build();

    public static final CreateEmployeeDTO CREATE_EMPLOYEE_DTO_BAD_JOB_TEST = CreateEmployeeDTO.builder()
            .name("Pepe")
            .lastname("Doe")
            .email("pepe@mail.com")
            .phone("5676457")
            .birthdate("1994-06-26")
            .job(52L)
            .street("Mason")
            .number(987)
            .city(1L)
            .build();

    public static final CreateEmployeeDTO CREATE_EMPLOYEE_DTO_BAD_CITY_TEST = CreateEmployeeDTO.builder()
            .name("Pepe")
            .lastname("Doe")
            .email("pepe@mail.com")
            .phone("5676457")
            .birthdate("1994-06-26")
            .job(1L)
            .street("Mason")
            .number(987)
            .city(100L)
            .build();

    public static final UpdateEmployeeDTO UPDATE_EMPLOYEE_DTO_TEST = UpdateEmployeeDTO
            .builder()
            .name("John")
            .lastname("Doe")
            .email("john@mail.com")
            .phone("3252345")
            .build();

    public static final Employee EMPLOYEE_BUILD_TEST = Employee.builder()
            .id(45L).name("Jane")
            .lastname("Doe")
            .email("jane@mail.com")
            .phone("8645788")
            .address(new Address(2L, "Av Evergreen", 456, CITIES_TEST.get(1)))
            .job(JOBS_TEST.get(1))
            .boss(EMPLOYEES_BOSSES_TEST.get(1))
            .birthdate(LocalDate.of(1990, 11, 8))
            .hiredate(LocalDate.of(2022, 3, 5))
            .firedate(null)
            .build();

    public static final List<UserEntity> USERS_TEST = Arrays.asList(
            UserEntity.builder()
                    .id(1L)
                    .username("admin")
                    .email("admin@admin.com")
                    .password("$2a$12$1yfuob3oWlOqrzSkk1D2G.DujYZaejtO3A.K7Iiy14MmrCqi4QtzW")
                    .active(true)
                    .google(false)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),

            UserEntity.builder()
                    .id(2L)
                    .username("alonso")
                    .email("alonso@mail.com")
                    .password("$2a$12$aEugJucPsYK5cSbVF2Q8IeCWsl3YXiWzjgjjgZ2dQjWaRJkrjBOSS")
                    .active(true)
                    .google(false)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build());

    public static final UserEntity USER_DELETED_TEST = UserEntity.builder()
            .id(2L)
            .username("alonso")
            .email("alonso@mail.com")
            .password("$2a$12$aEugJucPsYK5cSbVF2Q8IeCWsl3YXiWzjgjjgZ2dQjWaRJkrjBOSS")
            .active(false)
            .google(false)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
}
