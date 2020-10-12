package com.kgt.university.controller;

import com.kgt.university.commands.StudentCommand;
import com.kgt.university.services.UniversityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;


@Slf4j
@Controller
public class StudentController {
    private UniversityService universityService;

    public StudentController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @RequestMapping("/students")
    public String getStudents(Model model){
        model.addAttribute("students", universityService.getStudents());
        return "students";
    }
    @RequestMapping("/newstudentform")
    public String addStudents(Model model){
        model.addAttribute("student",new StudentCommand());
        return "studentform";
    }
    @PostMapping
    @RequestMapping("student")
    public String sOrU(@ModelAttribute StudentCommand command){
        StudentCommand savedCommand=universityService.saveStudentCommand(command);


        return "redirect:/students";
    }
    @RequestMapping("studentdetail/{id}/update")
    public String updateStudent(@PathVariable String id, Model model, StudentCommand command){
        model.addAttribute("student", universityService.findStudentCommandById(Long.valueOf(id)));
        model.addAttribute("studentcourses", universityService.getCourses());
       return "studentdetail";
    }
    @GetMapping("studentdetail/{id}/delete")
    public String deleteById(@PathVariable String id){
       log.debug("Deleting id: " + id);
        universityService.deleteStudentById(Long.valueOf(id));
        System.out.println("Deleting"+id);
        return "redirect:/students";
    }
    @GetMapping("studentdetail/{studentId}/new")
    public String newCourseToStudent(@PathVariable String studentId, Model model){
        model.addAttribute("student", universityService.findStudentCommandById(Long.valueOf(studentId)));
        StudentCommand studentCommand=universityService.findStudentCommandById(Long.valueOf(studentId));
        System.out.println(universityService.getCourses());
        return "studentdetail";
    }
    @PostMapping("/student/{studentId}/save")
    public String saveCourseToStudent(@ModelAttribute StudentCommand command){
        StudentCommand savedCommand=universityService.saveStudentCommand(command);
       return "redirect:/studentdetail/{studentId}/update";
    }


}
