package com.caffee.dao.beans;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@Table(name = "meals", schema = "", catalog = "caffe")
@Entity
public class MealsEntity {
    private int id;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private BigDecimal price;

    @Column(name = "PRICE", nullable = false, insertable = true, updatable = true, length = 10, precision = 2)
    @Basic
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private String name;

    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private byte[] picture;

    @Column(name = "PICTURE", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    @Basic
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealsEntity that = (MealsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!Arrays.equals(picture, that.picture)) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (picture != null ? Arrays.hashCode(picture) : 0);
        return result;
    }

    private MealType mealTypeByMealTypeId;

    @ManyToOne
    @JoinColumn(name = "MEAL_TYPE_ID", referencedColumnName = "ID", nullable = false)
    public MealType getMealTypeByMealTypeId() {
        return mealTypeByMealTypeId;
    }

    public void setMealTypeByMealTypeId(MealType mealTypeByMealTypeId) {
        this.mealTypeByMealTypeId = mealTypeByMealTypeId;
    }

    private Collection<OrderMealsEntity> orderMealsesById;

    @OneToMany(mappedBy = "mealsByMealsId")
    public Collection<OrderMealsEntity> getOrderMealsesById() {
        return orderMealsesById;
    }

    public void setOrderMealsesById(Collection<OrderMealsEntity> orderMealsesById) {
        this.orderMealsesById = orderMealsesById;
    }
}
