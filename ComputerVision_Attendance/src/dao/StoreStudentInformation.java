package dao;

import db_connection.DB_Connection;
import models.Student;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreStudentInformation {

    DB_Connection dbConnectionClass = new DB_Connection();

    public void insertStudentInformation(Student student) {
        dbConnectionClass.connectDatabase();
        if (dbConnectionClass.ifTableExists("student")) {
            System.out.println("table exists");
            insertStudent(student);
        } else {
            System.out.println("table not exists");
            dbConnectionClass.createTable("student");
            insertStudent(student);
        }
        dbConnectionClass.disconnectDatabase();
    }

    private void insertStudent(Student student) {
        try {
            PreparedStatement preparedStatement = dbConnectionClass.dbConnection.
                    prepareStatement("INSERT INTO student (id,first_name,last_name,student_class,class_section) values (?,?,?,?,?)");
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getFirst_name());
            preparedStatement.setString(3, student.getLast_name());
            preparedStatement.setInt(4, student.getStudent_class());
            preparedStatement.setString(5, student.getClass_section());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
