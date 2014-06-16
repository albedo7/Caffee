package com.caffee.dao.beans;

import com.caffee.dao.DAOEntity;
import com.caffee.utils.CryptoUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

@Table(name = "credit_card", schema = "", catalog = "caffe")
@Entity
public class CreditCard extends DAOEntity {
    private long id;
    private String numHash;
    @DateTimeFormat(pattern="dd-MM-yyyy")
    @NotNull
    @Future
    private Date expDate;
    private CreditCardType creditCardType;
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
    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
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

    public void crypt() {
        this.setSalt(CryptoUtils.doSalt());
        this.setNumHash(CryptoUtils.crypt(this.getNumHash(), this.getSalt()));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        if (id != that.id) return false;
        Calendar thisCal = new GregorianCalendar();
        Calendar thatCal = new GregorianCalendar();
        thisCal.setTime(expDate);
        thatCal.setTime(that.expDate);
        if (thisCal.get(Calendar.YEAR) != thatCal.get(Calendar.YEAR) ||
            thisCal.get(Calendar.MONTH) != thatCal.get(Calendar.MONTH) ||
            thisCal.get(Calendar.DAY_OF_MONTH) != thatCal.get(Calendar.DAY_OF_MONTH)) return false;
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
    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardTypeByTypeId) {
        this.creditCardType = creditCardTypeByTypeId;
    }

    private Collection<Customer> customersesById;

    @OneToMany(mappedBy = "creditCard")
    public Collection<Customer> getCustomersesById() {
        return customersesById;
    }

    public void setCustomersesById(Collection<Customer> customersesById) {
        this.customersesById = customersesById;
    }
}
