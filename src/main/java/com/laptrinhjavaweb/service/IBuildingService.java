package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchResquestDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    BuildingDTO findByID(Long Id);
    List<BuildingResponseDTO> find(BuildingSearchResquestDTO buildingDTO);
    void save(BuildingDTO dtoRequest);
    Map<String,String> getDistricts();
    Map<String, String> getBuildingTypes();
    void delete(Long[] ids);
}
