package com.laptrinhjavaweb.controller.admin;

import com.laptrinhjavaweb.dto.request.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchResquestDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchResquestDTO dto ){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch");
        mav.addObject("buildings",buildingService.find(dto));
        mav.addObject("staffMaps",userService.getStaffMaps());
        mav.addObject("buildingTypes" ,buildingService.getBuildingTypes());
        mav.addObject("districts" ,buildingService.getDistricts());
        return mav;
    }
    @GetMapping("/admin/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("modelUpdate") BuildingDTO dto){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("modelUpdate");
        mav.addObject("buildingTypes" ,buildingService.getBuildingTypes());
        mav.addObject("districts" ,buildingService.getDistricts());
        return mav;
    }
    @GetMapping("/admin/building-update-{id}")
    public ModelAndView buildingEdit(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("modelUpdate",buildingService.findByID(id));
        mav.addObject("buildingTypes" ,buildingService.getBuildingTypes());
        mav.addObject("districts" ,buildingService.getDistricts());
        return mav;
    }
}
