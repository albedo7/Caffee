package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "meal_type", schema = "", catalog = "caffe")
@Entity
public class MealType extends DAOEntity{
    private String type;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "TYPE", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealType that = (MealType) o;
        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    private Collection<Meal> mealsesById;

    @OneToMany(mappedBy = "mealTypeByMealTypeId")
    public Collection<Meal> getMealsesById() {
        return mealsesById;
    }

    public void setMealsesById(Collection<Meal> mealsesById) {
        this.mealsesById = mealsesById;
    }
}
