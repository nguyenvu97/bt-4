package com.example.annotionjpa.model;

import com.example.annotionjpa.Mapper.Impl.StudentDtoImpl;
import com.example.annotionjpa.Mapper.StudentMapper;
import com.example.annotionjpa.annotation.NonNull;
import com.example.annotionjpa.dto.StudentDto;
import com.example.annotionjpa.exception.Not_Found;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentJpaAccessService studentJpaAccessService;

    public StudentDto add(StudentDto student){
        if (student.getAge() <= 0 || student.getStudentName().isEmpty()){
            throw new Not_Found("error input information");
        }
        StudentDtoImpl studentDto = new StudentDtoImpl();
        Student  student1=studentDto.dtoToEntity(student);
        studentJpaAccessService.add(student1);
        return student;
    }

    public List<StudentDto> getAll(){

        StudentDtoImpl studentDto = new StudentDtoImpl();
        List<Student> students = studentJpaAccessService.findAll();
        return students.stream().map(studentDto::entityDto).collect(Collectors.toList());
    }
    public StudentDto findById(Integer id){
        if (id <0 ){
            throw new Not_Found("chuyen data vao di ");
        }
        StudentDtoImpl studentDto = new StudentDtoImpl();
        Student student1 = studentJpaAccessService.findById(id).orElseThrow(()-> new Not_Found("student Not found"));
        return studentDto.entityDto(student1);
    }


    public List<StudentDto>searchKeyword( String keyword){
        if (keyword == null || keyword.isEmpty() ){
            throw new Not_Found("chuyen data vao di ");
        }
        StudentDtoImpl studentDto = new StudentDtoImpl();
        List<Student> students = studentJpaAccessService.search(keyword);
        return students.stream().map(studentDto::entityDto).collect(Collectors.toList());
    }
}
