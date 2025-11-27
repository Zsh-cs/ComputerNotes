package com.zsh.trial_demo.controller;

import com.zsh.trial_demo.Response;
import com.zsh.trial_demo.dao.Student;
import com.zsh.trial_demo.dto.StudentDTO;
import com.zsh.trial_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public Response<StudentDTO> getStudentById(@PathVariable long id){
        return Response.newSuccess(studentService.getStudentById(id));
    }

    @PostMapping("/student")
    public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO){
        return Response.newSuccess(studentService.addNewStudent(studentDTO));
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudentById(@PathVariable long id){
        studentService.deleteStudentById(id);
    }

    @PutMapping("/student/{id}")
    public Response<StudentDTO> updateStudentById
            (@PathVariable long id,@RequestParam(required = false)String name,
             @RequestParam(required = false)String email){
        return Response.newSuccess(studentService.updateStudentById(id,name,email));
    }
}
