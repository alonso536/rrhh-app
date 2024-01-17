package org.alonso.rrhhapp.models.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.alonso.rrhhapp.models.helpers.DataTestHelper.*;

import static org.alonso.rrhhapp.models.helpers.EmployeeHelper.*;

import org.alonso.rrhhapp.models.dto.EmployeeDTO;
import org.alonso.rrhhapp.models.entities.Employee;
import org.junit.jupiter.api.Test;

public class EmployeeHelperTest {

    @Test
    void testBuildEmployee() {
        Employee employeeGiven = EMPLOYEE_BUILD_TEST;
        EmployeeDTO employeeReal = buildEmployee(employeeGiven);

        assertNotNull(employeeReal);
        assertTrue(employeeReal instanceof EmployeeDTO);
        assertTrue(employeeReal.getId() > 0);
        assertTrue(employeeReal.getAddress() instanceof String);
        assertTrue(employeeReal.getBoss() instanceof String);
        assertNull(employeeReal.getFiredate());
    }

    @Test
    void testFormatBirthdate() {
        String birthdateGiven = "1998-06-12";
        LocalDate birthdateExpected = LocalDate.of(1998, 6, 12);
        LocalDate birthdateReal = formatBirthdate(birthdateGiven);

        assertTrue(birthdateReal instanceof LocalDate);
        assertEquals(birthdateExpected, birthdateReal);
    }

    @Test
    void testFormatBirthdateBad() {
        String birthdateGiven = "hola";

        assertThrows(DateTimeParseException.class, () -> {
            formatBirthdate(birthdateGiven);
        });
    }
}
