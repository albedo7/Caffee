package com.caffee.dao.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "order_meals", schema = "", catalog = "caffe")
@Entity
public class OrderMealsEntity {
    private int id;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMealsEntity that = (OrderMealsEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    private OrderEntity orderByOrderId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
    public OrderEntity getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(OrderEntity orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    private MealsEntity mealsByMealsId;

    @ManyToOne
    @JoinColumn(name = "MEALS_ID", referencedColumnName = "ID", nullable = false)
    public MealsEntity getMealsByMealsId() {
        return mealsByMealsId;
    }

    public void setMealsByMealsId(MealsEntity mealsByMealsId) {
        this.mealsByMealsId = mealsByMealsId;
    }
}
