package com.zsh.trial_demo.converter;

import com.zsh.trial_demo.dao.Student;
import com.zsh.trial_demo.dto.StudentDTO;

public class StudentConverter {

    //将DAO从数据库中拿到的student对象，
    //转换成我们返回给前端的studentDTO对象（序列化）
    public static StudentDTO converterStudent(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;
    }

    //将前端拿到的studentDTO对象，
    //转换成我们想要写入数据库的student对象（反序列化）
    public static Student converterStudent(StudentDTO studentDTO){
        Student student=new Student();
        student.setId(studentDTO.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        return student;
    }
}
