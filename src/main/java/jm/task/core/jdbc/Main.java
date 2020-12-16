package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserDaoJDBCImpl dao1 = new UserDaoJDBCImpl();
        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();

        dao.createUsersTable();

        List<User> listUsers = new ArrayList<>();
        listUsers.add(new User("Jimi", "Hendrix", (byte)27));
        listUsers.add(new User("Jim", "Morrison", (byte)27));
        listUsers.add(new User("Jenis", "Joplin", (byte)27));
        listUsers.add(new User("Curt", "Cobain", (byte)27));

        for (User user : listUsers) {
            String name = user.getName();
            String lastName = user.getLastName();
            dao.saveUser(name, lastName, user.getAge());
            System.out.printf("User с именем - %s %s, был добавлен в базу данных\n", name, lastName);
        }
        for (User user : listUsers) {
            String name = user.getName();
            String lastName = user.getLastName();
            dao.saveUser(name, lastName, user.getAge());
            System.out.printf("User с именем - %s %s, был добавлен в базу данных\n", name, lastName);
        }

        List<User> listUsers2 =  dao.getAllUsers();
        listUsers2.forEach(System.out::println);

        dao.cleanUsersTable();
        dao.dropUsersTable();


    }



}
