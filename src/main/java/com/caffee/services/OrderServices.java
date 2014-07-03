package com.caffee.services;

import com.caffee.dao.beans.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;

public class OrderServices {
    @Resource
    private OrderDAO orders;
    @Resource
    private AbstractDAO<OrderMeals> orderMeals;
    @Autowired
    private OrderMeals orderMeal;
    @Autowired
    private Order order;

    public boolean addMealToOrder(Order order, Meal meal) {
        orderMeal.setOrder(order);
        orderMeal.setMeal(meal);
        orderMeals.saveBean(orderMeal);
        order.setSumm(new BigDecimal(order.getSumm().doubleValue() + meal.getPrice().doubleValue()));
        orders.updateBean(order);
        return true;
    }

    public Order createOrder(Customer customer) {
        if (customer == null || customer.getId() == 0) {
            return null;
        }
        order.setSumm(new BigDecimal(0));
        order.setCustomersByCustomerId(customer);
        OrderState orderState = new OrderState();
        orderState.setId(1);
        order.setOrderState(orderState);
        orders.saveBean(order);
        return order;
    }

    public Order getCurrentUsersOrder(Customer customer) {
        return orders.getCurrentUsersOrder(customer);
    }
}
