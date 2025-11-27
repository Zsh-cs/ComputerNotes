package com.zsh.trial_demo.service;

import com.zsh.trial_demo.dao.Student;
import com.zsh.trial_demo.dto.StudentDTO;

public interface StudentService {

    StudentDTO getStudentById(long id);

    Long addNewStudent(StudentDTO studentDTO);

    void deleteStudentById(long id);

    StudentDTO updateStudentById(long id, String name, String email);
}
