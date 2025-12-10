package com.miracleous.service;

import com.miracleous.entity.Department;
import com.miracleous.entity.Student;
import com.miracleous.util.MySQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements DaoService<Student> {
    @Override
    public List<Student> getAllData() throws SQLException, ClassNotFoundException {
        String query = "SELECT s.id, s.name, address, male, department_id, d.name AS department_name FROM student s JOIN department d ON d.id = s.department_id";
        List<Student> students = new ArrayList<>();
        try (Connection connection = MySQLUtil.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Department department = new Department.Builder()
                                .id(resultSet.getString("department_id"))
                                .name(resultSet.getString("department_name"))
                                .build();
                        Student student = new Student.Builder()
                                .id(resultSet.getString("id"))
                                .name(resultSet.getString("name"))
                                .address(resultSet.getString("address"))
                                .male(resultSet.getBoolean("male"))
                                .department(department)
                                .build();
                        students.add(student);
                    }
                }
            }
        }
        return students;
    }

    @Override
    public Student getById(Object id) {
        return null;
    }
}
