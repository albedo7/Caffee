package com.caffee.services;

import com.caffee.dao.beans.CreditCard;
import com.caffee.dao.beans.CreditCardType;
import com.caffee.dao.beans.Customer;
import com.caffee.utils.CryptoUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Timestamp;

import static org.testng.AssertJUnit.*;

public class AbstractDAOTestCustomers {
    private static final String CC_NUMBER = "4111111111111111";
    private static final String PASSWORD = "SoMePa12@";
    private CreditCardType cardType;
    private AbstractDAO<CreditCardType> creditCardTypeSut;
    private AbstractDAO<CreditCard> creditCardSut;
    private AbstractDAO<Customer> customerSut;
    private CreditCard creditCard;
    private Customer customer;

    @BeforeTest
    public void setUp() {
        cardType = new CreditCardType();
        cardType.setType("Visa");
        creditCard = new CreditCard();
        creditCard.setCreditCardTypeByTypeId(cardType);
        creditCard.setExpDate(new Timestamp(System.currentTimeMillis() + 100_000_000L));
        creditCard.setSalt(CryptoUtils.doSalt());
        creditCard.setNumHash(CryptoUtils.crypt(CC_NUMBER, creditCard.getSalt()));
        creditCardTypeSut = new AbstractDAO<>(CreditCardType.class);
        creditCardSut = new AbstractDAO<>(CreditCard.class);
        customerSut = new AbstractDAO<>(Customer.class);
        customer = new Customer();
        customer.setName("Yuriy");
        customer.setLastName("Kostikoff");
        customer.setEmail("kostikoff@rambler.ru");
        customer.setCreditCardByCreditCardId(creditCard);
        customer.setPwdHash("later");
        customer.setSalt(CryptoUtils.doSalt());
        customer.setPwdHash(CryptoUtils.crypt(PASSWORD, customer.getSalt()));
    }

    @Test(priority = 1001)
    public void testAbstractDAOSaveWithCreditCardType(){
        assertTrue(creditCardTypeSut.saveBean(cardType));
    }

    @Test(priority = 1002)
    public void testAbstractDAOGetWithCreditCardType(){
        CreditCardType custom = creditCardTypeSut.getById(cardType.getId());
        assertNotNull(custom);
        assertEquals(custom, cardType);
    }

    @Test(priority = 1003)
    public void testAbstractDAOSaveWithCreditCard(){
        assertTrue(creditCardSut.saveBean(creditCard));
    }

    @Test(priority = 1004)
    public void testAbstractDAOGetWithCreditCard(){
        CreditCard custom = creditCardSut.getById(creditCard.getId());
        assertNotNull(custom);
        assertEquals(custom, creditCard);
    }

    @Test(priority = 1005)
    public void testAbstractDAOSaveWithCustomer(){
        assertTrue(customerSut.saveBean(customer));
    }

    @Test(priority = 1006)
    public void testAbstractDAOGetWithCustomer(){
        Customer custom = customerSut.getById(customer.getId());
        assertNotNull(custom);
        assertEquals(custom, customer);
    }

    @Test(priority = 1007)
    public void testAbstractDAODeleteWithCustomer(){
                assertTrue(customerSut.deleteBean(customer.getId()));
    }

    @Test(priority = 1008)
    public void testAbstractDAODeleteWithCreditCard(){
        assertTrue(creditCardSut.deleteBean(creditCard.getId()));
    }

    @Test(priority = 1009)
    public void testAbstractDAODeleteWithCreditCardType(){
        assertTrue(creditCardTypeSut.deleteBean(cardType.getId()));
    }

}
