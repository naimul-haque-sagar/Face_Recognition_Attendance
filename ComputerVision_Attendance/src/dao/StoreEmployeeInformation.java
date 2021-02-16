package dao;

import db_connection.DB_Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Employee;
import models.Student;

public class StoreEmployeeInformation {
    DB_Connection dbConnectionClass = new DB_Connection();

    public void insertEmployeeInformation(Employee employee) {
        dbConnectionClass.connectDatabase();
        if (dbConnectionClass.ifTableExists("employee")) {
            System.out.println("table exists");
            insertEmployee(employee);
        } else {
            System.out.println("table not exists");
            dbConnectionClass.createTable("employee");
            insertEmployee(employee);
        }
        dbConnectionClass.disconnectDatabase();
    }

    private void insertEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = dbConnectionClass.dbConnection.
                    prepareStatement("INSERT INTO employee (id,first_name,last_name) values (?,?,?)");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirst_name());
            preparedStatement.setString(3, employee.getLast_name());       
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}