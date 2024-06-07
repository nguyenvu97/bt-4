package com.example.annotionjpa.config;

import com.example.annotionjpa.dbConnet.ConnectionPool;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {
    @Bean
    public ConnectionPool connectionPool(){
        return  new ConnectionPool();
    }
}
