package edu.miu.cse.vsms.mapper;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.model.Employee;



public class EmployeeMapper {

    private EmployeeMapper() {}

    public static Employee employeeRequestToEmployee(EmployeeRequestDto employeeRequestDto) {
        return Employee.builder()
                .phone(employeeRequestDto.phone())
                .email(employeeRequestDto.email())
                .name(employeeRequestDto.name())
                .hireDate(employeeRequestDto.hireDate())
                .build();
    }
}
