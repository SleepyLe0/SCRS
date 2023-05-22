package System;

import java.sql.*;

public class DatabaseConnector {

    public Connection connect() {
        String url = "jdbc:mysql://localhost:3306/scrs";
        String username = "root";
        String password = "mysql@sit";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public void disconnect() {
        try {
            connect().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
