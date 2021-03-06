package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Table(name = "orders", schema = "", catalog = "caffe")
@Entity
public class Order extends DAOEntity {
    private Collection<OrderMeals> orderMealsesById;
    private Customer customersByCustomerId;
    private BigDecimal summ;
    private OrderState orderState;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "SUMM", nullable = false, insertable = true, updatable = true, length = 10, precision = 2)
    @Basic
    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        if (id != that.id) return false;
        if (summ != null ? !summ.equals(that.summ) : that.summ != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID", nullable = false)
    public Customer getCustomersByCustomerId() {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(Customer customersByCustomerId) {
        this.customersByCustomerId = customersByCustomerId;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Collection<OrderMeals> getOrderMealsesById() {
        return orderMealsesById;
    }

    public void setOrderMealsesById(Collection<OrderMeals> orderMealsesById) {
        this.orderMealsesById = orderMealsesById;
    }


    @JoinColumn(name = "STATE", referencedColumnName = "ID", nullable = false)
    @ManyToOne
    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }
}
