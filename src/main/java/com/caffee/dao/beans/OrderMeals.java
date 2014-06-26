package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;

import javax.persistence.*;

@Table(name = "order_meals", schema = "", catalog = "caffe")
@Entity
public class OrderMeals extends DAOEntity {

    private Order order;
    private Meal meal;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderByOrderId) {
        this.order = orderByOrderId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEALS_ID", referencedColumnName = "ID", nullable = false)
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal mealsByMealsId) {
        this.meal = mealsByMealsId;
    }
}
