package com.caffee.services;

import com.caffee.dao.beans.DAOEntity;
import com.caffee.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AbstractDAO <E extends DAOEntity> {
    private Class clazz;

    public AbstractDAO(Class clazz) {
        this.clazz = clazz;
    }

    public synchronized E getById(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        E result = (E)session.get(clazz, id);
        session.close();
        return result;
    }

    public synchronized boolean saveBean(E bean) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(bean);
        tx.commit();
        session.close();
        return true;
    }

    public synchronized boolean deleteBean(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(session.get(clazz, id));
        tx.commit();
        session.close();
        return true;
    }
}