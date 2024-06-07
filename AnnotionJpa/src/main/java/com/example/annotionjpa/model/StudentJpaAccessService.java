package com.example.annotionjpa.model;


import com.example.annotionjpa.annotation.Impl.JpaExecutor;
import com.example.annotionjpa.dbConnet.ConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class StudentJpaAccessService implements JpaExecutor<Student> {

    @Autowired
    public ConnectionPool connectionPool;

    @Override
    public Student add(Student student) {
        String sql = "INSERT INTO student (studentname, age, phone) VALUES (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setDouble(2, student.getAge());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.executeUpdate();
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Student> findAll() {
        var sql = """
                select * from student limit 1000 offset 0
                """;
        List<Student> students = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql
            )){
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String studentName = resultSet.getString("studentName");
                String phone = resultSet.getString("phone");
                int age = resultSet.getInt("age");
               Student   student = new Student( id,studentName,age,phone);
               students.add(student);
            }
            return students;
        }catch (SQLException e){
            e.getStackTrace();
            return null;
        }

    }

    @Override
    public Optional<Student> findById(Number id) {

        String sql = "SELECT * FROM student where id = ? " ;
        Student student = null;
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql
            )){
            preparedStatement.setInt(1, (Integer) id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String studentName = resultSet.getString("studentName");
                String phone = resultSet.getString("phone");
                int age = resultSet.getInt("age");
                 student = new Student((Integer) id,studentName,age,phone);
            }
            return Optional.of(student);

        }catch (SQLException e){
            e.getStackTrace();
            return null;
        }

    }

    @Override
    public List<Student> search(String keyword) {

        String sql = " select * from student where studentName like ? ";
        List<Student> students = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql
            )){
            preparedStatement.setString(1,"%"+ keyword +"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("id");
                String studentName = resultSet.getString("studentName");
                String phone = resultSet.getString("phone");
                int age = resultSet.getInt("age");
                Student   student = new Student( id,studentName,age,phone);
                students.add(student);
            }
            return students;
        }catch (SQLException e){
            e.getStackTrace();
            return null;
        }

    }


}
