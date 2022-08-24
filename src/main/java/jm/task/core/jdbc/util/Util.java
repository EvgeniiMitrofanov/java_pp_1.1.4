package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String url = "jdbc:mysql://localhost:3306/mysql";
    private static String password = "кщще";
    private static String user = "root";
    private static SessionFactory sessionFactory = null;
    public static SessionFactory getConnectionHibernate() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.url", url)
                    .setProperty("hibernate.connection.username", user)
                    .setProperty("hibernate.connection.password", password)
                    .addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
