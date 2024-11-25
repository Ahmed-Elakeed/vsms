package edu.miu.cse.vsms.mapper;

import edu.miu.cse.vsms.dto.response.VehicleServiceResponseDto;
import edu.miu.cse.vsms.model.VService;

public class VServiceMapper {

    private VServiceMapper() {}

    public static VehicleServiceResponseDto VServiceToVehicleServiceResponseDto(VService vService) {
        return VehicleServiceResponseDto.builder()
                .serviceName(vService.getServiceName())
                .cost(vService.getCost())
                .id(vService.getId())
                .vehicleType(vService.getVehicleType())
                .build();
    }
}
