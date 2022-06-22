package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.AssignmentResquestDTO;
import com.laptrinhjavaweb.dto.request.ListDeleteResquestDTO;
import com.laptrinhjavaweb.dto.request.BuildingDTO;
import com.laptrinhjavaweb.dto.response.AssignmentResponse;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;
    @Autowired
    private IAssignmentBuildingService assignmentBuildingService;



    @PutMapping
    public void addBuilding(@RequestBody(required = false) BuildingDTO dto){
        buildingService.save(dto);
    }

    @PostMapping("/{id}")
    public void updateBuilding(@RequestBody(required = false) BuildingDTO dto){
        buildingService.save(dto);
    }

    @DeleteMapping("/delete")
    public void deleteBuilding(@RequestBody ListDeleteResquestDTO dto){
        buildingService.delete(dto.getIds());
    }

    @GetMapping("/{id}/staffs")
    public List<AssignmentResponse> loadStaff(@PathVariable Long id){
        return assignmentBuildingService.findStaffManageByBuildingId(id);
    }
    @PostMapping("/assignBuilding")
    public void assignBuilding(@RequestBody AssignmentResquestDTO dto){
        assignmentBuildingService.assignBuildingToStaff(dto);
    }
}
