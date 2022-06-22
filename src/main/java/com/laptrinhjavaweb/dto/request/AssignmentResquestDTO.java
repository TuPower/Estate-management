package com.laptrinhjavaweb.dto.request;

import java.util.List;

public class AssignmentResquestDTO {
    private Long id;
    private List<Long> staffs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getStaffs() {
        return staffs;
    }

    public void setStaffid(List<Long> staffid) {
        this.staffs = staffs;
    }
}
