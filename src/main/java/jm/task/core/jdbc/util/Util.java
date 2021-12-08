package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        return new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
    }
}
