package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.request.AssignmentResquestDTO;
import com.laptrinhjavaweb.dto.response.AssignmentResponse;

import java.util.List;

public interface IAssignmentCustomerService {
    List<AssignmentResponse> findStaffManageByCustomerID(Long id);
    void assignCustomerToStaff(AssignmentResquestDTO input);
}
