package com.kgt.university.controller;

import com.kgt.university.services.UniversityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private UniversityService universityService;

    public IndexController(UniversityService universityService) {
        this.universityService = universityService;
    }
    @RequestMapping({"","/","index"})
    public String getIndex(){


        return "index";
    }
}
