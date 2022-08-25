package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try ( Session session = Util.getConnectionHibernate().openSession())
        {
             transaction = session.beginTransaction();
             session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                     "(id BIGINT PRIMARY KEY AUTO_INCREMENT, nameC VARCHAR(255), lastNameC VARCHAR(255), ageC INT)")
                     .executeUpdate();
             transaction.commit();
             session.close();
             System.out.println("Table Created Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try ( Session session = Util.getConnectionHibernate().openSession())
        {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
            session.close();
            System.out.println("Table Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try ( Session session = Util.getConnectionHibernate().openSession())
        {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
            System.out.println("Information Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try ( Session session = Util.getConnectionHibernate().openSession())
        {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            session.close();
            System.out.println("Information Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try ( Session session = Util.getConnectionHibernate().openSession())
        {
            list = session.createQuery("From User").list();
            session.close();
            System.out.println("Information Added To List Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try ( Session session = Util.getConnectionHibernate().openSession())
        {
            transaction = session.beginTransaction();
            List<User> list = session.createQuery("From User").list();
            for (Object o : list) {
                session.delete(o);
            }
            transaction.commit();
            session.close();
            System.out.println("Data Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
