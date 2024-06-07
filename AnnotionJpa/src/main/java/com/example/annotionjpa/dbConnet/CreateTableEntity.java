package com.example.annotionjpa.dbConnet;


import com.example.annotionjpa.annotation.Impl.JpaExecutorImpl;
import com.example.annotionjpa.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class CreateTableEntity {
    @Bean
    public void create(){
        try {
            JpaExecutorImpl<Student> jpaExecutor = new JpaExecutorImpl<>(Student.class);
            jpaExecutor.crate(Student.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
