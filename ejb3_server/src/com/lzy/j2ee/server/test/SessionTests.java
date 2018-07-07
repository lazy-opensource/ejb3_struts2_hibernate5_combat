package com.lzy.j2ee.server.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by lzy on 2017/8/27.
 */
public class SessionTests {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("update HelloHibernate set name =:name where id=:id");
        query.setParameter("name", "testSession");
        query.setParameter("id", 5L);
        query.executeUpdate();
        tx.commit();

        session.close();
        sessionFactory.close();
    }
}
