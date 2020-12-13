package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbname = "testbase";
        String userName = "root";
        String password = "port40";
        return getMysqlConnection(hostName, dbname, userName, password);
    }

    public static Connection getMysqlConnection(String hostName, String dbName,
                                                String userName, String password)
            throws SQLException, ClassNotFoundException {
        registerDriver();
        String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?" +
                "useSSL=false&" +
                "useUnicode=true&" +
                "characterEncoding=UTF-8&" +
                "zeroDateTimeBehavior=CONVERT_TO_NULL&" +
                "serverTimezone=GMT";
        Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
        return conn;
    }

    public static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("Driver load success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
