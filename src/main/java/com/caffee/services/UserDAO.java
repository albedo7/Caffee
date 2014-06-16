package com.caffee.services;

import com.caffee.dao.beans.Customer;
import com.caffee.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


public class UserDAO extends AbstractDAO <Customer> {

    public UserDAO() {
        clazz = Customer.class;
    }

    public Customer getUserByEmail(String email) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(clazz);
        Customer result = (Customer) criteria.add(Restrictions.eq("email", email))
                .uniqueResult();
        session.close();
        return result;
    }
}
