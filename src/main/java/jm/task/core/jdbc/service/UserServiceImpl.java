package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserServiceImpl implements UserService {
    List<User> list = new LinkedList<>();
    SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.createSQLQuery("create table if not exists users (id bigint primary key auto_increment,name varchar(30) not null,lastName varchar(30) not null,age int not null)").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.createSQLQuery("drop table if exists users").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        User user = new User(name,lastName,age);
        session.save(user);
        list.add(user);
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        User user = session.get(User.class,id);
        session.remove(user);
        list.remove(id);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
       return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.createSQLQuery("delete from users").executeUpdate();
        list.clear();
        session.getTransaction().commit();
    }
}
