package main;

import accounts.User;
import dao.UsersDAO;
import dao.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import org.hibernate.service.ServiceRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.util.HashMap;
import java.util.Map;

public class DatabaseService {

    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "validate";

    private final SessionFactory sessionFactory;

    public DatabaseService(){
        Configuration configuration= getConfiguration();
        sessionFactory= createSessionFactory(configuration);
    }

    private static Configuration getConfiguration(){
        Configuration configuration= new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/bdnar");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }


    /*
    public static SessionFactory createSessionFactory() {
        // Hibernate 5.4 SessionFactory example without XML
        Map<String, Object> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
        settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
        settings.put("hibernate.connection.url",
                "jdbc:mysql://localhost/hibernate_examples");
        settings.put("hibernate.connection.username", "root");
        settings.put("hibernate.connection.password", "password");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        // metadataSources.addAnnotatedClass(Player.class);
        Metadata metadata = metadataSources.buildMetadata();

        // here we build the SessionFactory (Hibernate 5.4)
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        //Session session = sessionFactory.getCurrentSession();
        return sessionFactory;
    }*/



    public UsersDataSet getUser(String email) throws RuntimeException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UsersDataSet dataSet = dao.getByEmail(email);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }



    public void addUser(User user) throws RuntimeException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDataSet dataSet=new UsersDataSet(user);
            UsersDAO dao=new UsersDAO(session);
            dao.insertUser(dataSet);
            transaction.commit();
            session.close();

        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }


    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();

        configuration.addAnnotatedClass(UsersDataSet.class);
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }


}
