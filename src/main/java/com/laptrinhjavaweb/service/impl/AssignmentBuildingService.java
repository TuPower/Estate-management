package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.request.AssignmentResquestDTO;
import com.laptrinhjavaweb.dto.response.AssignmentResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IAssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignmentBuildingService implements IAssignmentBuildingService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public List<AssignmentResponse> findStaffManageByBuildingId(Long id) {
        List<AssignmentResponse> result = new ArrayList<>();
        List<UserEntity> users = userRepository.findByRoles_Code("USER");
        List<UserEntity> assgmentStaffs = userRepository.findByBuildings_Id(id);
        List<Long> listID = new ArrayList<>();
        for(UserEntity item : assgmentStaffs) {
            listID.add(item.getId());
        }
        for (UserEntity item : users) {
            AssignmentResponse asses = userConverter.convertToAssDto(item);
            if(listID.contains(item.getId())){
                asses.setChecked(SystemConstant.CHECKED);
            }else
                asses.setChecked("");
            result.add(asses);
        }
        return result;
    }

    @Override
    @Transactional
    public void assignBuildingToStaff(AssignmentResquestDTO input) {
        List<UserEntity> userEntities = userRepository.findByIdIn(input.getStaffs());
        BuildingEntity buildingEntity = buildingRepository.findOne(input.getId());
        buildingEntity.setUsers(userEntities);
        buildingRepository.save(buildingEntity);
    }
}
