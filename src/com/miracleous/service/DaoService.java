package com.miracleous.service;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {
    List<T> getAllData() throws SQLException, ClassNotFoundException;

    T getById(Object id);
}
