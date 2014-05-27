package com.caffee.dao.beans;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Table(name = "credit_card", schema = "", catalog = "caffe")
@Entity
public class CreditCard extends DAOEntity{
    private String numHash;
    private Timestamp expDate;
    private CreditCardType creditCardTypeByTypeId;
    private String salt;

    @Column(name = "ID", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NUM_HASH", nullable = false, insertable = true, updatable = true, length = 256, precision = 0)
    @Basic
    public String getNumHash() {
        return numHash;
    }

    public void setNumHash(String numHash) {
        this.numHash = numHash;
    }

    @Column(name = "EXP_DATE", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    @Basic
    public Timestamp getExpDate() {
        return expDate;
    }

    public void setExpDate(Timestamp expDate) {
        this.expDate = expDate;
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
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        if (id != that.id) return false;
        if (Math.abs(expDate.getTime() - that.expDate.getTime()) > 10000) return false;
        if (!numHash.equals(that.numHash)) return false;
        if (!salt.equals(that.salt)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (numHash != null ? numHash.hashCode() : 0);
        result = 31 * result + (expDate != null ? expDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID", nullable = false)
    public CreditCardType getCreditCardTypeByTypeId() {
        return creditCardTypeByTypeId;
    }

    public void setCreditCardTypeByTypeId(CreditCardType creditCardTypeByTypeId) {
        this.creditCardTypeByTypeId = creditCardTypeByTypeId;
    }

    private Collection<Customer> customersesById;

    @OneToMany(mappedBy = "creditCardByCreditCardId")
    public Collection<Customer> getCustomersesById() {
        return customersesById;
    }

    public void setCustomersesById(Collection<Customer> customersesById) {
        this.customersesById = customersesById;
    }
}
