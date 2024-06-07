package com.example.annotionjpa.model;

import com.example.annotionjpa.dto.StudentDto;
import com.example.annotionjpa.exception.Not_Found;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentJpaExecutor;
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody StudentDto student) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentJpaExecutor.add(student));
        } catch (Not_Found e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok().body(studentJpaExecutor.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(studentJpaExecutor.findById(id));

    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudentsByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok().body(studentJpaExecutor.searchKeyword(keyword));

    }
}
