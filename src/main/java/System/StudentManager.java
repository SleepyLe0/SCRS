package System;

import java.sql.*;
import java.util.*;

public class StudentManager {

    public void addStudent(String firstName, String lastName, String email) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String insertStudentData = "INSERT INTO students (first_name,last_name,email) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertStudentData);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,email);
            preparedStatement.executeUpdate();
            System.out.println("Add Student Successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnector.disconnect();
    }

    public List<Student> getAllStudents() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String selectAllStudentData = "SELECT * FROM students";
        List listOfStudents = new ArrayList();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectAllStudentData);
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt(1));
                student.setFirstName(resultSet.getString(2));
                student.setLastName(resultSet.getString(3));
                student.setEmail(resultSet.getString(4));
                listOfStudents.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        databaseConnector.disconnect();

        return listOfStudents;
    }

    public void getStudent(int studentId) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection connection = databaseConnector.connect();
        String selectStudentData = "SELECT * FROM students WHERE student_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectStudentData);
            preparedStatement.setInt(1,studentId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
