package com.codegym.controller;

import com.codegym.model.Student;
import com.codegym.repository.IStudentRepository;
import com.codegym.repository.StudentRepository;
import com.codegym.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    public ModelAndView index() {
        List<Student> studentList = studentService.findAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("studentList", studentList);
        return modelAndView;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "/create";
    }

    @PostMapping("/save")
    public String save(Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "/update";
    }

    @PostMapping("/update")
    public String update(Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable long id, RedirectAttributes redirect) {
        studentService.remove(id);
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/students";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "/view";
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String search) {
        List<Student> studentList = studentService.findByName(search);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("studentList", studentList);
        return modelAndView;
    }
}
