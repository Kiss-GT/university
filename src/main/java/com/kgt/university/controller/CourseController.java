package com.kgt.university.controller;

import com.kgt.university.commands.CourseCommand;
import com.kgt.university.commands.ProfessorCommand;
import com.kgt.university.commands.StudentCommand;
import com.kgt.university.repositories.CourseRepo;
import com.kgt.university.services.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class CourseController {

    private UniversityService universityService;

    public CourseController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @RequestMapping("/courses")
    public String getCourses(Model model){
        model.addAttribute("courses", universityService.getCourses());
        return "courses";
    }
    @GetMapping
    @RequestMapping("professor/{profId}/course/{id}/update")
    public String updateProfessorCourse(@PathVariable String profId,
                                         @PathVariable String id,Model model){
        model.addAttribute("course",universityService.findByProfessorAndCourseId(Long.valueOf(profId),
                Long.valueOf(id)));
        return "courseform";
    }
    @RequestMapping("course/{professorId}/new")
    public String newCourse(@PathVariable String professorId, Model model){
        ProfessorCommand professorCommand=universityService.findProfessorCommandById(Long.valueOf(professorId));
        CourseCommand courseCommand=new CourseCommand();
        courseCommand.setProfessorId(Long.valueOf(professorId));
        model.addAttribute("course",courseCommand);
        return "courseform";
    }
    @PostMapping("professor/{profId}/coursenew")
    public String saveOrUpdate(@ModelAttribute CourseCommand command){
        CourseCommand savedCommand=universityService.saveCourseCommand(command);
        log.debug("saved receipe id:" + savedCommand.getProfessorId());
        log.debug("saved ingredient id:" + savedCommand.getId());
        System.out.println("Saving "+command.getName());
        return "redirect:/professordetail/{profId}";
    }

}
