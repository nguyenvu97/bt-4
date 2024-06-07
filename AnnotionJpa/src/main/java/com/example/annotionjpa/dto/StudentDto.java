package com.example.annotionjpa.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {
    public int id;
    public String studentName;
    public int age;
    public String phone;
}
