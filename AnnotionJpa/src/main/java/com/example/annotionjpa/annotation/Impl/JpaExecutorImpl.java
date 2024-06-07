package com.example.annotionjpa.annotation.Impl;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.annotionjpa.annotation.Entity;
import com.example.annotionjpa.annotation.GeneratedValue;
import com.example.annotionjpa.annotation.GenerationType;
import com.example.annotionjpa.annotation.Id;

import com.example.annotionjpa.dbConnet.ConnectionPool;
import com.example.annotionjpa.model.Student;

public class JpaExecutorImpl<T> {
    private Class<T> clazz;
    private String  className;
    public String tableName;
    Field[] fields;
    public JpaExecutorImpl(Class<T> clazz) throws SQLException {
        this.clazz = clazz;
        this.className = clazz.getSimpleName();
        this.tableName = clazz.getAnnotation(Entity.class) != null ?
                clazz.getAnnotation(Entity.class).tableName()
                : className;
        this.fields = clazz.getDeclaredFields();
        System.err.println(this.className);
        System.err.println(this.tableName);
        System.err.println(this.fields);
    }

    public void crate(Class<T> clazz) throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        if (con == null) {
            System.err.println("Connection is null. Cannot proceed with table creation.");
            return;
        }
        this.tableName = clazz.getAnnotation(Entity.class) != null ?
                clazz.getAnnotation(Entity.class).tableName()
                : clazz.getSimpleName(); // Sử dụng tên lớp nếu không có Annotation @Entity

        String sql = "CREATE TABLE " + tableName + " (";
        for (Field field : clazz.getDeclaredFields()) {
            String fieldName = field.getName();
            String fieldType = getSQLType(field.getType().getSimpleName());
            sql += fieldName + " " + fieldType;

            if (field.isAnnotationPresent(GeneratedValue.class)) {
                sql = sql.replace("INT", "SERIAL");

                System.out.println( sql);
            }
            if (field.isAnnotationPresent(Id.class)) {
                sql += " PRIMARY KEY";
            }
            sql += ", ";
        }

        sql = sql.substring(0, sql.length() - 2); // Xóa dấu phẩy và khoảng trắng cuối cùng
        sql += ")";
        System.out.println("SQL query: " + sql);

        if (!checkTable(tableName, con)) {
            Statement statement = con.createStatement();
            statement.execute(sql);
            System.out.println("Table created successfully.");
        } else {
            System.out.println("Table already exists.");
        }
    }
    public Boolean checkTable(String tableName, Connection con) throws SQLException {
        DatabaseMetaData metaData = con.getMetaData();
        ResultSet resultSet = metaData.getTables(null,null,tableName,null);
        return resultSet.next();
    }
    private String getSQLType(String javaType) {
        switch (javaType) {
            case "String":
                return "VARCHAR(255)";
            case "int":
            case "Integer":
                return "INT";
            case "double":
            case "Double":
                return "DOUBLE";
            case "float":
            case "Float":
                return "FLOAT";
            case "long":
            case "Long":
                return "LONG";

            default:
                return "VARCHAR(255)";
        }
    }





}
