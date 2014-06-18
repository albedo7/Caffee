package com.caffee.services;

import com.caffee.dao.DAOEntity;
import com.caffee.utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstractDAO <E extends DAOEntity> implements DAOService<E> {
    protected Class<E> clazz;
    protected AbstractDAO(){}

    public AbstractDAO(Class<E> clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    @Override
    public synchronized E getById(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        E result = (E)session.get(clazz, id);
        session.close();
        return result;
    }

    @Override
    public synchronized boolean saveBean(E bean) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(bean);
            tx.commit();
        } catch (HibernateException e) {
            session.close();
            return false;
        }
        session.close();
        return true;
    }

    @Override
    public synchronized boolean deleteBean(long id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(session.get(clazz, id));
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public synchronized List<E> getAllBeans() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<E> result= session.createCriteria(clazz).list();
        session.close();
        return result;
    }
}
