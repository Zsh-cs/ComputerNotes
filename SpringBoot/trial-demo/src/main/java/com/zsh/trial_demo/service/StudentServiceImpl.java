package com.zsh.trial_demo.service;

import com.zsh.trial_demo.converter.StudentConverter;
import com.zsh.trial_demo.dao.Student;
import com.zsh.trial_demo.dao.StudentRepository;
import com.zsh.trial_demo.dto.StudentDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.beans.Transient;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        return StudentConverter.converterStudent(student);
    }

    @Override
    public Long addNewStudent(StudentDTO studentDTO) {
        //先检查新增学生的email是否已被他人占用
        List<Student> studentList=studentRepository.findByEmail(studentDTO.getEmail());
        if(!CollectionUtils.isEmpty(studentList)){
            throw new IllegalStateException("email:"+studentDTO.getEmail()+" has been taken.");
        }
        Student student=studentRepository.save(StudentConverter.converterStudent(studentDTO));
        return student.getId();
    }

    @Override
    public void deleteStudentById(long id) {
        //先检查该id是否存在
        studentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("id:"+id+" doesn't exist!"));
        studentRepository.deleteById(id);
    }

    @Transactional//若更新失败，则事务回滚
    @Override
    public StudentDTO updateStudentById(long id, String name, String email) {
        //先检查该id是否存在
        Student studentInDB=studentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("id:"+id+" doesn't exist!"));

        //如果传进来的name不为空且与数据库中的name不相同，我们才更新数据库中的name
        if(StringUtils.hasLength(name) && !studentInDB.getName().equals(name)){
            studentInDB.setName(name);
        }

        //如果传进来的email不为空且与数据库中的email不相同，我们才更新数据库中的email
        if(StringUtils.hasLength(email) && !studentInDB.getEmail().equals(email)){
            studentInDB.setEmail(email);
        }

        Student student=studentRepository.save(studentInDB);
        return StudentConverter.converterStudent(student);
    }


}
