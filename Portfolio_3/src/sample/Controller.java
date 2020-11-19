package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import java.sql.*;


public class Controller {
    public TableView studentTable;
    public TableColumn IDColumn;
    public TableColumn nameColumn;
    public TableColumn hometownColumn;
    public Label newStudentTitle;
    public Button addStudentButton;
    public Label nameLabel;
    public TextField nameTextfield;
    public Label homeTownLabel;
    public TextField hometownTextfield;
    public Label studentIDLabel;
    public TextField studentIDTextfield;
    public ComboBox selectStudentComB;
    public TableView courseTable;
    public TableColumn courseNameColumn;
    public TableColumn teacherColumn;
    public TableColumn averageColumn;
    public ComboBox chooseStudentComB;
    public ComboBox chooseCourseComB;
    public Button addToCourseButton;
    public ComboBox selectCourseComB;
    public ComboBox selectTeacherComB;
    public TableColumn studentGradeColumn;
    public TableColumn studentCourseColumn;
    DataConnection dataConnection;

    private ObservableList<Students> getAllStudents() throws SQLException {
        ObservableList<Students> students = FXCollections.observableArrayList();

        try {
            Connection c = DriverManager.getConnection(dataConnection.url, "root", "admin");
            Statement st = c.createStatement();
            String sql = "SELECT Student.studentID, Student.studentName, Student.cityName, " +
                    "Grade.courseName, Grade.grade " +
                    "FROM Student " +
                    "LEFT JOIN Grade " +
                    "ON Student.studentID = Grade.studentID;";
            ResultSet rs = st.executeQuery(sql);


            while (rs.next()) {
                Students student = new Students();
                student.setID(rs.getString("studentID"));
                student.setFullName(rs.getString("studentName"));
                student.setHomeTown(rs.getString("cityName"));
                student.setCourse(rs.getString("courseName"));
                student.setGrade(rs.getString("grade"));
                students.add(student);
            }

        } catch (Exception e) {
            System.out.println("getALLStudents()");
            e.printStackTrace();
        }

        return students;
    }

    private ObservableList<Courses> getAllCourses() throws SQLException {
        ObservableList<Courses> courses = FXCollections.observableArrayList();

        try {
            Connection c2 = DriverManager.getConnection(dataConnection.url, "root", "admin");
            Statement st2 = c2.createStatement();
            String sql2 = "SELECT * FROM Course;";
            ResultSet rs2 = st2.executeQuery(sql2);


            while (rs2.next()) {
                Courses course = new Courses();
                course.setCourseName(rs2.getString("courseName"));
                course.setTeacher(rs2.getString("teacher"));
                courses.add(course);
            }

        } catch (Exception e) {
            System.out.println("getALLCourses()");
            e.printStackTrace();
        }

        return courses;
    }

    public void initialize() throws SQLException {
        String url = "jdbc:sqlite:C:\\Users\\Micool\\Documents\\GitHub\\Portfolio_3\\Portfolio_3\\Portfolio3_database";

        try {
            dataConnection = new DataConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(" We did not make this exam");
        }
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        hometownColumn.setCellValueFactory(new PropertyValueFactory<>("homeTown"));
        studentCourseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        studentGradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        studentTable.setItems(getAllStudents());

        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        courseTable.setItems(getAllCourses());
    }
}