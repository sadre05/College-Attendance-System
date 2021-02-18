/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBaseActivity;

import database.connection.MySQL_Coonection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mdsad
 */
public class CurdOprationAgainstDatabase {

    public static int selectDataFromDatabase(String selectQuery, String wantDataName) throws SQLException {
        System.err.println("query" + selectQuery);
        int currentCount = 0;
        Connection connect = MySQL_Coonection.ConnectDb();
        PreparedStatement ps = connect.prepareStatement(selectQuery);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            currentCount = rs.getInt(wantDataName);
        }
       
        return currentCount;
    }

}
