package edu.miu.cse.vsms.service.impl;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.exception.ResourceNotFoundException;
import edu.miu.cse.vsms.mapper.EmployeeMapper;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.repository.EmployeeRepository;
import edu.miu.cse.vsms.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto request) {
        Employee employee = this.employeeRepository.save(EmployeeMapper.employeeRequestToEmployee(request));
        return mapToResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        return this.employeeRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No Employee found with ID : "+id));
        return mapToResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto partiallyUpdateEmployee(Long id, Map<String, Object> updates) {
        // Fetch the employee by ID or throw an exception if not found
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));

        // Apply each update based on the key
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    employee.setName(value.toString());
                    break;
                case "email":
                    employee.setEmail(value.toString());
                    break;
                case "phone":
                    employee.setPhone(value.toString());
                    break;
                case "hireDate":
                    employee.setHireDate((LocalDate) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
        Employee updatedEmployee = this.employeeRepository.save(employee);
        return mapToResponseDto(updatedEmployee);
    }

    private EmployeeResponseDto mapToResponseDto(Employee employee) {
        if(employee.getVServices()==null) employee.setVServices(new ArrayList<>());
        List<VehicleServiceResponseDto> serviceDtos = employee.getVServices().stream()
                .map(service -> new VehicleServiceResponseDto(
                        service.getId(),
                        service.getServiceName(),
                        service.getCost(),
                        service.getVehicleType()
                )).toList();

        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getHireDate(),
                serviceDtos
        );
    }
}
