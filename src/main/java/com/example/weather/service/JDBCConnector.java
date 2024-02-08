package com.example.weather.service;

import com.example.weather.dao.CoordinatesDAO;
import org.springframework.stereotype.Service;

import java.sql.*;
@Service
public class JDBCConnector {
    CoordinatesDAO coordinates = new CoordinatesDAO();
    public Connection JDBCConnect() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weather",
                "root", "qwerty" );
        return connection;
    }

    public void getCoordinatesCity(String name) throws SQLException {
        if (name == null) {
            name = "Москва";
        }
        PreparedStatement preparedStatement = JDBCConnect().prepareStatement("SELECT Column_4, Column_5 FROM table_name where Column_1 = '" + name + "'");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            coordinates.setName(name);
            coordinates.setWidth(resultSet.getString("Column_4"));
            coordinates.setLongitude(resultSet.getString("Column_5"));
        }
        preparedStatement.close();
        resultSet.close();
        JDBCConnect().close();
    }
}
