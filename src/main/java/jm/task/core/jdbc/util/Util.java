package jm.task.core.jdbc.util;

//import com.sun.tools.doclint.Env;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



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

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testbase?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "port40");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Exception from getSessionFactory()" + e);
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }



}
