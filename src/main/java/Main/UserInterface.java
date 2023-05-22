package Main;

import java.sql.SQLException;
import java.util.Scanner;
import System.*;

public class UserInterface {

    public static void main(String[] args) {
        int optionInput = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            int studentIDInput = 0;
            String studentFirstNameInput = "";
            String studentLastNameInput = "";
            String studentEmailInput = "";
            String courseIDInput = "";
            String courseNameInput = "";
            String courseDescriptionInput = "";
            StudentManager studentManager = new StudentManager();
            CourseManager courseManager = new CourseManager();
            RegistrationManager registrationManager = new RegistrationManager();
            System.out.println("Welcome to Student Course Registration System");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. View Student by ID");
            System.out.println("4. Add Course");
            System.out.println("5. View All Courses");
            System.out.println("6. View Course by ID");
            System.out.println("7. Register Course");
            System.out.println("8. View Course by Student");
            System.out.println("9. View Student in Course");
            System.out.println("10. Exit");
            System.out.print("Please select option : ");
            optionInput = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            switch (optionInput) {
                case 1 :
                    //Add Student
                    System.out.print("Enter student firstname : ");
                    studentFirstNameInput = scanner.nextLine();
                    System.out.print("Enter student lastname : ");
                    studentLastNameInput = scanner.nextLine();
                    System.out.print("Enter student email : ");
                    studentEmailInput = scanner.nextLine();
                    System.out.println();
                    studentManager.addStudent(studentFirstNameInput,studentLastNameInput,studentEmailInput);
                    break;
                case 2 :
                    //View All Students
                    System.out.println("List of All Students");
                    for (Student student : studentManager.getAllStudents()) {
                        System.out.println("=========================================");
                        System.out.println("Student ID : " + student.getStudentId() + " ");
                        System.out.println("Name : " + student.getFirstName() + " " + student.getLastName());
                        System.out.println("Email : " + student.getEmail());
                    }
                    break;
                case 3 :
                    //View Student by ID
                    System.out.print("Enter student ID : ");
                    studentIDInput = scanner.nextInt();
                    System.out.println();
                    studentManager.getStudent(studentIDInput);
                    break;
                case 4 :
                    //Add Course
                    System.out.print("Enter course ID : ");
                    courseIDInput = scanner.nextLine();
                    System.out.print("Enter course name : ");
                    courseNameInput = scanner.nextLine();
                    System.out.print("Enter course description : ");
                    courseDescriptionInput = scanner.nextLine();
                    System.out.println();
                    courseManager.addCourse(courseIDInput,courseNameInput,courseDescriptionInput);
                    break;
                case 5 :
                    //View All Courses
                    System.out.println("List of All Courses");
                    int order = 1;
                    for (Course course : courseManager.getAllCourses()) {
                        System.out.println("=========================================");
                        System.out.print((order++) + ". " + course.getCourseId() + " ");
                        System.out.println(course.getCourseName());
                        System.out.println("Description : " + course.getCourseDescription());
                    }
                    break;
                case 6 :
                    //View Course by ID
                    System.out.print("Enter course ID : ");
                    courseIDInput = scanner.nextLine();
                    System.out.println();
                    courseManager.getCourse(courseIDInput);
                    break;
                case 7 :
                    //Register Course
                    System.out.print("Enter student ID : ");
                    studentIDInput = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter course ID : ");
                    courseIDInput = scanner.nextLine();
                    System.out.println();
                    registrationManager.registerStudentForCourse(studentIDInput,courseIDInput);
                    break;
                case 8 :
                    //View Course by Student
                    System.out.print("Enter student ID : ");
                    studentIDInput = scanner.nextInt();
                    System.out.println();
                    System.out.println("List of All Courses by Student ID");
                    registrationManager.getCoursesForStudent(studentIDInput);
                    break;
                case 9 :
                    //View Student in Course
                    System.out.print("Enter course ID : ");
                    courseIDInput = scanner.nextLine();
                    System.out.println();
                    System.out.println("List of All Students in Course ID");
                    registrationManager.getStudentsForCourse(courseIDInput);
                    break;
                case 10 :
                    //Exit
                    System.out.println("Exit from system");
                    break;
                default :
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println();
        } while (optionInput != 10);
    }
}
