package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

@Table(name = "meals", schema = "", catalog = "caffe")
@Entity
public class Meal extends DAOEntity {
    private BigDecimal price;
    private String name;
    private byte[] picture;
    private String description;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "PRICE", nullable = false, insertable = true, updatable = true, length = 10, precision = 2)
    @Basic
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "PICTURE", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    @Basic
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Column(name = "DESCRIPTION", nullable = false, insertable = true, updatable = true, length = 256, precision = 0)
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
        Meal that = (Meal) o;
        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!Arrays.equals(picture, that.picture)) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        long result = id;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (picture != null ? Arrays.hashCode(picture) : 0);
        return (int) result;
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

    private Collection<OrderMeals> orderMealsesById;

    @OneToMany(mappedBy = "mealsByMealsId")
    public Collection<OrderMeals> getOrderMealsesById() {
        return orderMealsesById;
    }

    public void setOrderMealsesById(Collection<OrderMeals> orderMealsesById) {
        this.orderMealsesById = orderMealsesById;
    }
}
