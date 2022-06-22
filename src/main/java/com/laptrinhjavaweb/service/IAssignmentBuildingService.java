package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.AssignmentResquestDTO;
import com.laptrinhjavaweb.dto.response.AssignmentResponse;

import java.util.List;

public interface IAssignmentBuildingService {
    List<AssignmentResponse> findStaffManageByBuildingId(Long id);
    void assignBuildingToStaff(AssignmentResquestDTO input);
}
