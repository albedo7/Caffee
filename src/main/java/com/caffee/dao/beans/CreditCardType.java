package com.caffee.dao.beans;

import javax.persistence.*;
import java.util.Collection;

@Table(name = "credit_card_type", schema = "", catalog = "caffe")
@Entity
public class CreditCardType extends DAOEntity{
    private String type;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "TYPE", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
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

        CreditCardType that = (CreditCardType) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    private Collection<CreditCard> creditCardsById;

    @OneToMany(mappedBy = "creditCardTypeByTypeId", fetch = FetchType.EAGER)
    public Collection<CreditCard> getCreditCardsById() {
        return creditCardsById;
    }

    public void setCreditCardsById(Collection<CreditCard> creditCardsById) {
        this.creditCardsById = creditCardsById;
    }

    @Override
    public String toString() {
        return "CreditCardType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", creditCardsById=" + creditCardsById +
                '}';
    }
}
