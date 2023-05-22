package System;

import java.sql.*;
import java.util.*;

public class RegistrationManager {

    public void registerStudentForCourse(int studentId, String courseId) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String insertRegisterData = "INSERT INTO registration (student_id,course_id) VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertRegisterData);
            preparedStatement.setInt(1,studentId);
            preparedStatement.setString(2,courseId);
            preparedStatement.executeUpdate();
            System.out.println("Register Student for Course Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnector.disconnect();
    }

    public void getCoursesForStudent(int studentId) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String selectAllCourseForStudentData = "SELECT * FROM courses C INNER JOIN registration R ON C.course_id = R.course_id WHERE R.student_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllCourseForStudentData);
            preparedStatement.setInt(1,studentId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            int order = 1;
            while (resultSet.next()) {
                System.out.println("=========================================");
                System.out.print((order++) + ". " + resultSet.getString(1) + " ");
                System.out.println(resultSet.getString(2));
                System.out.println("Description : " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnector.disconnect();
    }

    public void getStudentsForCourse(String courseId) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String selectAllStudentForCourseData = "SELECT * FROM students S INNER JOIN registration R ON S.student_id = R.student_id WHERE R.course_id LIKE ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllStudentForCourseData);
            preparedStatement.setString(1,courseId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("=========================================");
                System.out.println("Student ID : " + resultSet.getInt(1));
                System.out.println("Name : " + resultSet.getString(2) + " " + resultSet.getString(3));
                System.out.println("Email : " + resultSet.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnector.disconnect();
    }
}
