package com.caffee.dao.beans;


import com.caffee.dao.DAOEntity;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;


@Table(name = "customers", schema = "", catalog = "caffe")
@Entity
@Component
@Scope("session")
public class Customer extends DAOEntity {
    @NotNull @Size(min = 2, max = 20)
    private String name;
    @NotNull @Size(min = 2, max = 20)
    private String lastName;
    @NotNull @NotEmpty @Email
    private String email;
    @NotNull @NotEmpty
    private String pwdHash;
    private String salt;
    @Valid
    private CreditCard creditCard;
    private Collection<OrderEntity> ordersById;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NAME", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Column(name = "LAST_NAME", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PWD_HASH", nullable = false, insertable = true, updatable = true, length = 256, precision = 0)
    @Basic
    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    @Column(name="SALT", nullable =  false, insertable = true, updatable = true, length = 45, precision = 0)
    @Basic
    public String getSalt(){
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer that = (Customer) o;
        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (pwdHash != null ? !pwdHash.equals(that.pwdHash) : that.pwdHash != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pwdHash != null ? pwdHash.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "CREDIT_CARD_ID", referencedColumnName = "ID", nullable = false)
    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCardByCreditCardId) {
        this.creditCard = creditCardByCreditCardId;
    }

    @OneToMany(mappedBy = "customersByCustomerId")
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
