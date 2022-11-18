package com.example.StudentManagementSystem1.controller;

import com.example.StudentManagementSystem1.models.Students;
import com.example.StudentManagementSystem1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/student/redirect")
    public  String addForm(Model model){
        Students students = new Students();
        model.addAttribute("addForm",students);
        return "/Create_students";
    }

    @PostMapping(value = "/student/save")
    public String saveStudent(@ModelAttribute("student") Students students){
        studentService.saveStudent(students);
        return "redirect:/";
    }

    @GetMapping(value = "/student/edit/{id}")
    public String editStudentForm(@PathVariable Long id,Model model){
        model.addAttribute("Students",studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping(value = "/student/{id}")
    public String updateStudents(@PathVariable Long id,
                                 @ModelAttribute("Students")Students students,
                                 Model model){

//      get Student from database by id
        Students existingStudent = studentService.getStudentById(id);

        existingStudent.setId(id);
        existingStudent.setFirstName(students.getFirstName());
        existingStudent.setLastName(students.getLastName());
        existingStudent.setAddress(students.getAddress());
        existingStudent.setEmail(students.getEmail());

//      save Updated Student object
        studentService.updateStudent(existingStudent);
        model.addAttribute(existingStudent);

        return "redirect:/";

    }

//      handle method to handledelete student required

    @GetMapping(value = "/student/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentService.deleteStudentById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findpaginated(@PathVariable(value = "pageNo") int pageNo,Model model){
        int pageSize = 3;

        Page<Students> page = studentService.findpaginated(pageNo,pageSize);
        List<Students> list_ = page.getContent();

        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("list_",list_);

        return "StudentPage";
    }

    @GetMapping("/")
    public String ListStudents(Model model){
        return findpaginated(1,model);
    }

}
