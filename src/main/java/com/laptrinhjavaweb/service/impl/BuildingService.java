package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.request.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchResquestDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepsitory;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public BuildingDTO findByID(Long id) {
        return buildingConverter.convertToDto(buildingRepsitory.findOne(id),getArentares(id));
    }

    @Override
    public List<BuildingResponseDTO> find(BuildingSearchResquestDTO buildingDTO) {
        List<BuildingEntity> entities = buildingRepsitory.find(buildingConverter.convertToBuilder(buildingDTO));
        List<BuildingResponseDTO> result = entities.stream()
                                            .map(item-> buildingConverter.convertToDtoRespone(item))
                                            .collect(Collectors.toList());
        return result;
    }
    @Override
    @Transactional
    public void save(BuildingDTO dto) {
        Long buildingID = dto.getId();
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(dto);
        if (buildingID != null){
            rentAreaRepository.deleteByBuilding_id(buildingID);
            buildingEntity.setUsers(userRepository.findByBuildings_Id(buildingID));
        }
        buildingRepsitory.save(buildingEntity);
    }
    @Override
    @Transactional
    public Map<String, String> getDistricts() {
        Map<String,String> districts = new HashMap<>();
        for (DistrictsEnum item : DistrictsEnum.values()) {
            districts.put(item.toString(),item.getDistrictValue());
        }
        return districts;
    }
    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String,String> types = new HashMap<>();
        for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
            types.put(item.toString(),item.getBuildingTypeValue());
        }
        return types;
    }
    @Override
    @Transactional
    public void delete(Long[] ids) {
        if (ids != null){
            buildingRepsitory.deleteByIdIn(ids);
        }
    }
    private String getArentares(Long id) {
        List<RentAreaEntity> entities = rentAreaRepository.findByBuilding_id(id);
        List<String> rentaras = new ArrayList<>();
        for (RentAreaEntity item: entities) {
            rentaras.add(item.getValue().toString());
        }
        return String.join(",",rentaras);
    }
}
