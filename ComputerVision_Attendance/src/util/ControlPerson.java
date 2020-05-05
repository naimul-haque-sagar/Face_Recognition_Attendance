
package util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControlPerson {
    ConnectDatabase cd=new ConnectDatabase();
    public void insert(ModelPerson mp){
        try {
            cd.connect();
            PreparedStatement ps=cd.con.prepareStatement("INSERT INTO person (id,first_name,last_name,dob,office) values (?,?,?,?,?)");
            ps.setInt(1,mp.getId());
            ps.setString(2,mp.getFirst_name());
            ps.setString(3, mp.getLast_name());
            ps.setString(4, mp.getDob());
            ps.setString(5, mp.getOffice());
            ps.executeUpdate();
            System.out.println(""+mp.getFirst_name());
            cd.disconnect();
        } catch (SQLException e) {
            System.out.println("Error...."+e);
        }
    }
}
