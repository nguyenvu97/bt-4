package com.example.annotionjpa;

import com.example.annotionjpa.annotation.Impl.JpaExecutorImpl;
import com.example.annotionjpa.annotation.Impl.NonNullImpl;
import com.example.annotionjpa.dbConnet.CreateTableEntity;
import com.example.annotionjpa.dbConnet.DatabaseHelper;
import com.example.annotionjpa.model.Student;
import com.example.annotionjpa.model.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AnnotationJpaApplication {

    public static void main(String[] args) {

        SpringApplication.run(AnnotationJpaApplication.class, args);

    }

}
