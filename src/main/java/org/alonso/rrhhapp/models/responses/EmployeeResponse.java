package org.alonso.rrhhapp.models.responses;

import java.util.List;

import lombok.Data;

@Data
public class EmployeeResponse {
    private Integer statusCode;
    private Object payload;
    private List<String> errors;
}
