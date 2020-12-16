package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        String query = "CREATE TABLE IF NOT EXISTS users" +
                "(id bigint, name text, lastName text, age tinyint);";
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            transaction = session.getTransaction();
            session.createSQLQuery(query);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String query = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            transaction = session.getTransaction();
            session.createSQLQuery(query);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;

        // auto close session object
        try (Session session = Util.getSessionFactory().openSession() ) {
            // start the transaction
//            transaction = session.beginTransaction();
            transaction = session.getTransaction();

            // save student object
            session.save(user);

            // commit transction
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        User user = new User(name, lastName, age);
//        Transaction transaction = null;
//
//        // auto close session object
//        Session session = Util.getSessionFactory().openSession();
//        // start the transaction
//        transaction = session.beginTransaction();
//
//        // save student object
//        session.save(user);
//
//        // commit transction
//        transaction.commit();
//
//    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
            transaction = session.getTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> result = null;
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.getTransaction();
            transaction = session.beginTransaction();
            result = (List<User>) session.createQuery("select u from User u", User.class).list();
//            result = (List<User>) session.createQuery("from User").list();
//            result = (List<User>) session.createQuery("SELECT a.id, a.name, a.lastName, a.age FROM User a", User.class).list();
////            result = session.createCriteria(User.class).list();
//            result = session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
//            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
//            transaction = session.getTransaction();
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
