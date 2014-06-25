package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;

import javax.persistence.*;

@Table(name = "order_meals", schema = "", catalog = "caffe")
@Entity
public class OrderMeals extends DAOEntity {

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMeals that = (OrderMeals) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    private Order orderByOrderId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    private Meal mealsByMealsId;

    @ManyToOne
    @JoinColumn(name = "MEALS_ID", referencedColumnName = "ID", nullable = false)
    public Meal getMealsByMealsId() {
        return mealsByMealsId;
    }

    public void setMealsByMealsId(Meal mealsByMealsId) {
        this.mealsByMealsId = mealsByMealsId;
    }
}
