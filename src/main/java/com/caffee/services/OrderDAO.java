package com.caffee.services;

import com.caffee.dao.beans.Customer;
import com.caffee.dao.beans.Order;
import com.caffee.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class OrderDAO extends AbstractDAO<Order> {

    public OrderDAO() {
        clazz = Order.class;
    }

    public synchronized Order getCurrentUsersOrder(Customer customer) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(clazz);
        Order result = (Order) criteria.add(Restrictions.eq("customersByCustomerId", customer)).uniqueResult();
        session.close();
        return result;
    }
}
