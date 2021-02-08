package dao;

import db_connection.ConnectDatabase;
import models.Student;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StoreStudentInformation {
    ConnectDatabase cd=new ConnectDatabase();
    public void insert(Student mp){
        try {
            cd.connect();
            PreparedStatement ps=
                    cd.con.prepareStatement("INSERT INTO person (id,first_name,last_name,dob,office) values (?,?,?,?,?)");
            ps.setInt(1,mp.getId());
            ps.setString(2,mp.getFirst_name());
            ps.setString(3, mp.getLast_name());
            ps.setInt(4, mp.getStudent_class());
            ps.setString(5, mp.getClass_section());
            ps.executeUpdate();
            System.out.println(""+mp.getFirst_name());
            cd.disconnect();
        } catch (SQLException e) {
            System.out.println("Error occured ...."+e);
        }
    }
}
