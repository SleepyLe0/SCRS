package System;

import java.sql.*;
import java.util.*;

public class CourseManager {

    public void addCourse(String courseId,String courseName, String courseDescription) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String insertCourseData = "INSERT INTO courses VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCourseData);
            preparedStatement.setString(1,courseId);
            preparedStatement.setString(2,courseName);
            preparedStatement.setString(3,courseDescription);
            preparedStatement.executeUpdate();
            System.out.println("Add Course Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        databaseConnector.disconnect();
    }

    public List<Course> getAllCourses() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String selectAllCourseData = "SELECT * FROM courses";
        List listOfCourses = new ArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllCourseData);
            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setCourseDescription(resultSet.getString(3));
                listOfCourses.add(course);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnector.disconnect();

        return listOfCourses;
    }

    public void getCourse(String courseId) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String selectData = "SELECT * FROM courses WHERE course_id LIKE ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectData);
            preparedStatement.setString(1,courseId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            int order = 1;
            while (resultSet.next()) {
                System.out.print((order++) + ". " + resultSet.getString(1) + " ");
                System.out.println(resultSet.getString(2));
                System.out.println("Description : " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnector.disconnect();
    }
}
