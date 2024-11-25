package edu.miu.cse.vsms.dto.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record EmployeeResponseDto(
        Long id,
        String name,
        String email,
        String phone,
        LocalDate hireDate,
        List<VehicleServiceResponseDto> services
) {
}
