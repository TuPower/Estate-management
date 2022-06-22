package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.request.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchResquestDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.ITransactionService;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "CustomerControllerOfAdmin")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IUserService userService;

    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/admin/customer-list")
    public ModelAndView customerList(@ModelAttribute(name = "customerModel") CustomerSearchResquestDTO dto){
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("staffMaps",userService.getStaffMaps());
        mav.addObject("customerModel");
        mav.addObject("customers",customerService.find(dto));
        return mav;
    }

    @GetMapping("/admin/customer-edit")
    public ModelAndView customerEdit(@ModelAttribute(name = "modelUpdate")CustomerDTO dto){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("modelUpdate");
        return mav;
    }

    @GetMapping("/admin/customer-update-{id}")
    public ModelAndView customerEdit(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("transaction",transactionService.findByCustomerID(id));
        mav.addObject("customerModel",customerService.findOneById(id));
        mav.addObject("transactionTypes",transactionService.getTransactionType());
        return mav;
    }
}
