package com.kgt.university.controller;

import com.kgt.university.commands.ProfessorCommand;
import com.kgt.university.services.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class ProfessorController {
    private UniversityService universityService;

    public ProfessorController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @RequestMapping("/professors")
    public String getProfessors(Model model){
        model.addAttribute("professors", universityService.getProfessors());
        return "professors";
    }
    @RequestMapping("professordetail/{profId}")
    public String viewProfessor(Model model, @PathVariable String profId){
        model.addAttribute("professor", universityService.findProfessorById(Long.valueOf(profId)));
        return "professordetail";
    }
    @RequestMapping("/newsprofessorform")
    public String addProfessor(Model model){
        model.addAttribute("professor",new ProfessorCommand());
        return "professorform";
    }
    @RequestMapping("professordetail/{profIdd}/update")
    public String updateProfessor(Model model, @PathVariable String profIdd){
        model.addAttribute("professor", universityService.findProfessorById(Long.valueOf(profIdd)));
        return "professorform";
    }
    @PostMapping("professor")
    public String sOrU(@ModelAttribute ProfessorCommand command){
        ProfessorCommand savedCommand=universityService.saveProfessorCommand(command);
        System.out.println(savedCommand.getCourses());
        return "redirect:/professors";
    }
    @GetMapping("professordetail/{profIdd}/delete")
    public String deleteProfessorById(@PathVariable String profIdd){
        log.debug("Deleting id: " + profIdd);
        universityService.deleteProfessorById(Long.valueOf(profIdd));
        System.out.println("Deleting"+profIdd);
        return "redirect:/professors";
    }

}
