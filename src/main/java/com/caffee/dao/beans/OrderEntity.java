package com.caffee.dao.beans;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Table(name = "orders", schema = "", catalog = "caffe")
@Entity
public class OrderEntity {
    private int id;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (summ != null ? !summ.equals(that.summ) : that.summ != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (summ != null ? summ.hashCode() : 0);
        return result;
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

    private Collection<OrderMealsEntity> orderMealsesById;

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderMealsEntity> getOrderMealsesById() {
        return orderMealsesById;
    }

    public void setOrderMealsesById(Collection<OrderMealsEntity> orderMealsesById) {
        this.orderMealsesById = orderMealsesById;
    }
}
