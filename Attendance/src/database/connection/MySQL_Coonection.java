/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.connection;

import attendance.Constants;
import attendance.Getter_Setter;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import propertiesVariables.GetPropertiesValues;
/*
 *@author mdsad
 */
public class MySQL_Coonection {

    static Connection conn = null;
    static ObservableList<Getter_Setter> list = FXCollections.observableArrayList();
    
    public static Connection ConnectDb() {
        try {
            Properties varFile=GetPropertiesValues.getPropertiesInstance("Resources/Variables.properties");
           
            Class.forName(varFile.get("MysqlDb.driver").toString());
            Connection conn = (Connection) DriverManager.getConnection(varFile.get("MysqlDb.url").toString(), 
                    varFile.get("MysqlDb.userName").toString(), varFile.get("MysqlDb.password").toString());
            //JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    public static ObservableList<Getter_Setter> getDataStudent() throws SQLException {
        Connection conn = ConnectDb();
        System.out.println("passed query: "+Constants.query);
        PreparedStatement ps = conn.prepareStatement(Constants.query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int roll_number = rs.getInt("roll_number");
            String student_name = rs.getString("student_name");
            String gender = rs.getString("gender");
            String semester = rs.getString("semester");
            String department = rs.getString("department");
            String keys_dept_year_sem_sec = rs.getString("keys_dept_year_sem_sec");

            Getter_Setter k = new Getter_Setter(roll_number, student_name, gender, department,keys_dept_year_sem_sec);
            list.add(k);

        }
        return list;

    }

}
