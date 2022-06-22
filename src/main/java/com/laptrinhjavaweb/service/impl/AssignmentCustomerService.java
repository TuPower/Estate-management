package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.request.AssignmentResquestDTO;
import com.laptrinhjavaweb.dto.response.AssignmentResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentCustomerService implements IAssignmentCustomerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<AssignmentResponse> findStaffManageByCustomerID(Long id) {
        List<AssignmentResponse> result = new ArrayList<>();
        List<UserEntity> users = userRepository.findByRoles_Code("USER");
        List<UserEntity> assgmentStaffs = userRepository.findByCustomers_Id(id);
        List<Long> listID = assgmentStaffs.stream()
                .map(item -> item.getId()).collect(Collectors.toList());
        for (UserEntity item : users) {
            AssignmentResponse asses = userConverter.convertToAssDto(item);
            if(listID.contains(item.getId())){
                asses.setChecked(SystemConstant.CHECKED);
            }else {
                asses.setChecked("");
            }
            result.add(asses);
        }
        return result;
    }

    @Override
    public void assignCustomerToStaff(AssignmentResquestDTO input) {
        List<UserEntity> userEntities = userRepository.findByIdIn(input.getStaffs());
        CustomerEntity customerEntity = customerRepository.findOne(input.getId());
        customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
    }
}
