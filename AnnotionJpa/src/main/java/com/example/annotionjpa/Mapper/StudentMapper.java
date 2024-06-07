package com.example.annotionjpa.Mapper;

import com.example.annotionjpa.dto.StudentDto;
import com.example.annotionjpa.model.Student;

public interface StudentMapper {
    Student dtoToEntity (StudentDto studentDto);
    StudentDto entityDto (Student student);
}
