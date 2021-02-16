package db_connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Student_Attendance_Operation {

    DB_Connection db_connection = new DB_Connection();

    public void storeStudentAttendance(int id) {
        db_connection.connectDatabase();
        try {
            String sql = "SELECT * FROM student WHERE id =" + id;
            db_connection.executesql(sql);
            if (db_connection.resultSet.next()) {
                storeAttendance(id, db_connection.resultSet.getString("first_name"),
                        db_connection.resultSet.getString("last_name"), db_connection.resultSet.getString("class_section"), db_connection.resultSet.getInt("student_class"));
            }
        } catch (Exception e) {
        }
        db_connection.disconnectDatabase();
    }

    private void storeAttendance(int id, String first_name, String last_name, String class_section, int student_class) {
        if (db_connection.ifTableExists("student_attendance")) {
            try {
                String sql = "SELECT * FROM student_attendance WHERE id =" + id;
                db_connection.executesql(sql);
                if (!db_connection.resultSet.next()) {
                    store(id, first_name, last_name, class_section, student_class);
                }
            } catch (Exception e) {
            }
        } else {
            db_connection.createTable("student_attendance");
            store(id, first_name, last_name, class_section, student_class);
        }
    }

    private void store(int id, String first_name, String last_name, String class_section, int student_class) {
        try {
            PreparedStatement preparedStatement = db_connection.dbConnection.
                    prepareStatement("INSERT INTO student_attendance (id,first_name,last_name,student_class,class_section) values (?,?,?,?,?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.setInt(4, student_class);
            preparedStatement.setString(5, class_section);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
