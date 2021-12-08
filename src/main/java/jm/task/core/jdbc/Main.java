package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.Test;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        User user1 = new User("Dima","Dimov",(byte)20);
        User user2 = new User("Andrei","Andreev",(byte)18);
        User user3 = new User("Valera","Valerov",(byte)11);
        UserService dao = new UserServiceImpl();
        dao.createUsersTable();
        dao.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        dao.removeUserById(1L);
        dao.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        dao.saveUser(user3.getName(),user3.getLastName(),user3.getAge());
        dao.getAllUsers().stream().forEach(System.out::println);
        dao.cleanUsersTable();
        dao.dropUsersTable();
        dao.removeUserById(2);
    }
}
