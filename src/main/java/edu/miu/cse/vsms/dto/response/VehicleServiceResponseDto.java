package edu.miu.cse.vsms.dto.response;


import lombok.Builder;

@Builder
public record VehicleServiceResponseDto(
        Long id,
        String serviceName,
        double cost,
        String vehicleType
) {
}
