package db_connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee_Attendance_Operation {

    DB_Connection db_connection = new DB_Connection();

    public void storeEmployeeAttendance(int id) {
        db_connection.connectDatabase();
        try {
            String sql = "SELECT * FROM employee WHERE id =" + id;
            db_connection.executesql(sql);
            if (db_connection.resultSet.next()) {
                storeAttendance(id, db_connection.resultSet.getString("first_name"),
                        db_connection.resultSet.getString("last_name"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        db_connection.disconnectDatabase();
    }

    private void storeAttendance(int id, String first_name, String last_name) {
        if (db_connection.ifTableExists("employee_attendance")) {
            System.out.println("table exists");
            try {
                String sql = "SELECT * FROM employee_attendance WHERE id =" + id;
                db_connection.executesql(sql);
                if (!db_connection.resultSet.next()) {
                    store(id, first_name, last_name);
                }
            } catch (Exception e) {
            }
        } else {
            System.out.println("table not exists table creating");
            db_connection.createTable("employee_attendance");
            store(id, first_name, last_name);
        }
    }

    private void store(int id, String first_name, String last_name) {
        try {
            PreparedStatement preparedStatement = db_connection.dbConnection.
                    prepareStatement("INSERT INTO employee_attendance (id,first_name,last_name) values (?,?,?)");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, first_name);
            preparedStatement.setString(3, last_name);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
