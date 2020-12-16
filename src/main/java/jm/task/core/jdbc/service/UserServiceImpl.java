package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl dao = new UserDaoJDBCImpl();
    UserDaoHibernateImpl daoHib = new UserDaoHibernateImpl();


    public void createUsersTable() {
//        dao.createUsersTable();
        daoHib.createUsersTable();
    }

    public void dropUsersTable() {
//        dao.dropUsersTable();
        daoHib.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
//        dao.saveUser(name, lastName, age);
        daoHib.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
//        dao.removeUserById(id);
        daoHib.removeUserById(id);
    }

    public List<User> getAllUsers() {
//        return dao.getAllUsers();
        return daoHib.getAllUsers();
    }

    public void cleanUsersTable() {
//        dao.cleanUsersTable();
        daoHib.cleanUsersTable();
    }
}
