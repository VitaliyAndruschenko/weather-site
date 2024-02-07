package com.example.weather.service;

import java.sql.*;

public class JDBCConnector {
    public Connection JDBCConnect() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weather",
                "root", "qwerty" );
        return connection;
    }

    public String[] getCoordinatesCity(String name) throws SQLException {
        String[] coordinates = new String[1];
        Statement statement = JDBCConnect().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT Column_4, Column_5 FROM table_name where Column_1 = " + name);
        coordinates[0] = resultSet.getString("Column_4");
        coordinates[1] = resultSet.getString("Column_ 5");
        resultSet.close();
        statement.close();
        JDBCConnect().close();
        return coordinates;
    }
}
