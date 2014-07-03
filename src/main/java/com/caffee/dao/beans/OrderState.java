package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;
import javax.persistence.*;

@Table(name = "ORDER_STATES", schema = "", catalog = "caffe")
@Entity
public class OrderState extends DAOEntity {
    private String description;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPTION", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderState that = (OrderState) o;
        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description!= null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (int) id;
    }
}
