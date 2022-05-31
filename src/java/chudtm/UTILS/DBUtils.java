/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.UTILS;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ohmin
 */
public class DBUtils {
    public static Connection makeConnection() throws Exception {
        Connection cn = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        Class.forName(driver);
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=PlantShop;user=sa;password=12345;";
        cn = DriverManager.getConnection(connectionUrl);
        return cn;
    }
}
