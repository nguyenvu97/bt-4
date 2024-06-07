package com.example.annotionjpa.model;

import com.example.annotionjpa.annotation.Entity;
import com.example.annotionjpa.annotation.GeneratedValue;
import com.example.annotionjpa.annotation.GenerationType;
import com.example.annotionjpa.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(tableName = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SERIAL)
    public int id;
    public String studentName;
    public int age;
    public String phone;
}
