package com.example.annotionjpa.Mapper.Impl;

import com.example.annotionjpa.Mapper.StudentMapper;
import com.example.annotionjpa.dto.StudentDto;
import com.example.annotionjpa.model.Student;
import org.springframework.beans.BeanUtils;

public class StudentDtoImpl implements StudentMapper {
    @Override
    public Student dtoToEntity(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto,student);
        return student;
    }

    @Override
    public StudentDto entityDto(Student student) {
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student,studentDto);
        return studentDto;
    }
}
