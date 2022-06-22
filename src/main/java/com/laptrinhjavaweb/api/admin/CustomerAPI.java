package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.request.AssignmentResquestDTO;
import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.request.ListDeleteResquestDTO;
import com.laptrinhjavaweb.dto.request.TransactionDTOResquest;
import com.laptrinhjavaweb.dto.response.AssignmentResponse;
import com.laptrinhjavaweb.service.IAssignmentCustomerService;
import com.laptrinhjavaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAssignmentCustomerService assignmentCustomerService;

    @PutMapping()
    public void addCustomer(@RequestBody CustomerDTO dto){
        customerService.save(dto);
    }

    @PutMapping("/transaction")
    public void addTransaction(@RequestBody(required = false) TransactionDTOResquest dto){
        customerService.addTransaction(dto);
    }

    @PostMapping("/{id}")
    public void updateCustomer(@RequestBody CustomerDTO dto){
        customerService.save(dto);
    }

    @DeleteMapping("/delete")
    public void deleteCustomer(@RequestBody ListDeleteResquestDTO dto){
        customerService.delete(dto.getIds());
    }


    @GetMapping("/{id}/staffs")
    public List<AssignmentResponse> loadStaff(@PathVariable Long id){
        return assignmentCustomerService.findStaffManageByCustomerID(id);
    }

    @PostMapping("/assignCustomer")
    public void assignBuilding(@RequestBody AssignmentResquestDTO dto){
        assignmentCustomerService.assignCustomerToStaff(dto);
    }
}
