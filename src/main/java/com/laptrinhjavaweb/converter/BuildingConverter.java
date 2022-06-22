package com.laptrinhjavaweb.converter;


import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.request.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchResquestDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.ValidateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingDTO convertToDto (BuildingEntity entity,String rentArea){
        BuildingDTO result = modelMapper.map(entity, BuildingDTO.class);
        result.setTypes(entity.getTypes().split(","));
        result.setRentarea(rentArea);
        return result;
    }

    public BuildingResponseDTO convertToDtoRespone (BuildingEntity entity){
        StringBuilder district = new StringBuilder();
        if (!entity.getDistrict().isEmpty() && entity.getDistrict() != null){
            district.append(DistrictsEnum.valueOf(entity.getDistrict()).getDistrictValue());
        }
        BuildingResponseDTO result = modelMapper.map(entity, BuildingResponseDTO.class);
        result.setDistrict(entity.getStreet()+" - "+entity.getWard()+" - "+district.toString());
        return result;
    }

    public BuildingEntity convertToEntity (BuildingDTO dto){
        BuildingEntity buildingEntity = modelMapper.map(dto, BuildingEntity.class);
        buildingEntity.setTypes(String.join(",", dto.getTypes()));
        if (!dto.getRentarea().isEmpty()){
            List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
            String [] values = dto.getRentarea().split(",");
            for (String i:values) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setValue(Integer.parseInt(i));
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaEntities.add(rentAreaEntity);
            }
            buildingEntity.setRentAreas(rentAreaEntities);
        }
        return buildingEntity;
    }

    /*public List<AssignmentBuildingEntity> convertToEntity (Long buildingID , List<Long> staffID){
        List<AssignmentBuildingEntity> results = new ArrayList<>();
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity.setId(buildingID);
        for (Long i : staffID) {
            AssignmentBuildingEntity entity = new AssignmentBuildingEntity();
            UserEntity userEntity = new UserEntity();
            userEntity.setId(i);
            entity.setBuilding(buildingEntity);
            entity.setUser(userEntity);
            results.add(entity);
        }
        return results;
    }*/
    public BuildingSearchBuilder convertToBuilder(BuildingSearchResquestDTO dto){
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
                                        .setAreaRentFrom(dto.getAreaFrom())
                                        .setAreaRentTo(dto.getAreaTo())
                                        .setTypes(dto.getTypes())
                                        .setCostRentTo(dto.getRentTo())
                                        .setCostRentFrom(dto.getRentTo())
                                        .setDistrict(dto.getDistrict())
                                        .setNumberOfBasement(dto.getNumberOfBasement())
                                        .setName(dto.getName())
                                        .setStaffId(dto.getStaffID())
                                        .setStreet(dto.getStreet())
                                        .setWard(dto.getWard())
                                        .setManagerName(dto.getManagerName())
                                        .setManagerPhone(dto.getManagerPhone())
                                        .build();
        return result;
    }
}
