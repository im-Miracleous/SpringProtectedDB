package com.miracleous.service;

import com.miracleous.entity.Department;
import com.miracleous.util.MySQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements DaoService<Department> {
    @Override
    public List<Department> getAllData() throws SQLException, ClassNotFoundException {
        String query = "SELECT id, name FROM department";
        List<Department> departments = new ArrayList<>();
        try (Connection connection = MySQLUtil.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Department department = new Department.Builder()
                                .id(resultSet.getString("id"))
                                .name(resultSet.getString("name"))
                                .build();
                        departments.add(department);
                    }
                }
            }
        }
        return departments;
    }

    @Override
    public Department getById(Object id) {
        return null;
    }
}
