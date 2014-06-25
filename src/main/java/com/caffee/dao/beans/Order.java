package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Table(name = "orders", schema = "", catalog = "caffe")
@Entity
public class Order extends DAOEntity{

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private BigDecimal summ;

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
        long result = id;
        result = 31 * result + (summ != null ? summ.hashCode() : 0);
        return (int) result;
    }

    private Customer customersByCustomerId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID", nullable = false)
    public Customer getCustomersByCustomerId() {
        return customersByCustomerId;
    }

    public void setCustomersByCustomerId(Customer customersByCustomerId) {
        this.customersByCustomerId = customersByCustomerId;
    }

    private Collection<OrderMeals> orderMealsesById;

    /*@OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderMeals> getOrderMealsesById() {
        return orderMealsesById;
    }

    public void setOrderMealsesById(Collection<OrderMeals> orderMealsesById) {
        this.orderMealsesById = orderMealsesById;
    }*/
}
