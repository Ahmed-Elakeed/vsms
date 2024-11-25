package edu.miu.cse.vsms.controller;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // Add a new employee
    @PostMapping
    public ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody EmployeeRequestDto request) {
        EmployeeResponseDto employeeResponseDto = employeeService.addEmployee(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employeeResponseDto.id()).toUri();
        return ResponseEntity.created(location).body(employeeResponseDto);
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(this.employeeService.getAllEmployees());
    }

    // Get a specific employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(this.employeeService.getEmployeeById(id));
    }

    // Update partially an existing employee
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> partiallyUpdateEmployee(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        return ResponseEntity.ok(this.employeeService.partiallyUpdateEmployee(id, updates));
    }
}
