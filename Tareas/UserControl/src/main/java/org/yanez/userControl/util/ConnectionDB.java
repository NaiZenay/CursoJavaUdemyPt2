package org.yanez.userControl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static String url="jdbc:mysql://localhost:3306/user_control";
    private static String user="root";
    private static String password="queso";

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
