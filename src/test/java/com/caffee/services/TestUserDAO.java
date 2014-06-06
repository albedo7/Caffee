package com.caffee.services;

import com.caffee.dao.beans.CreditCard;
import com.caffee.dao.beans.CreditCardType;
import com.caffee.dao.beans.Customer;
import com.caffee.utils.CryptoUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.AssertJUnit.*;

public class TestUserDAO {
    private static final String CC_NUMBER = "4111111111111111";
    private static final String PASSWORD = "SoMePa12@";
    private UserDAO sut;
    private CreditCardType cardType;
    private AbstractDAO<CreditCardType> creditCardType;
    private AbstractDAO<CreditCard> creditCard;
    private CreditCard card;
    private Customer customer;

    @BeforeClass
    public void setUp() {
        cardType = new CreditCardType();
        cardType.setType("Visa");
        card = new CreditCard();
        card.setCreditCardType(cardType);
        card.setExpDate(new Date(System.currentTimeMillis() + 100_000_000L));
        card.setSalt(CryptoUtils.doSalt());
        card.setNumHash(CryptoUtils.crypt(CC_NUMBER, card.getSalt()));
        customer = new Customer();
        customer.setName("Yuriy");
        customer.setLastName("Kostikoff");
        customer.setEmail("kostikoff@rambler.ru");
        customer.setCreditCard(card);
        customer.setPwdHash("later");
        customer.setSalt(CryptoUtils.doSalt());
        customer.setPwdHash(CryptoUtils.crypt(PASSWORD, customer.getSalt()));
        creditCardType = new AbstractDAO<>(CreditCardType.class);
        creditCard = new AbstractDAO<>(CreditCard.class);
        creditCardType.saveBean(cardType);
        assertTrue(creditCard.saveBean(card));
        sut = new UserDAO();
        sut.saveBean(customer);
    }

    @AfterClass
    public void destroy() {
        sut.deleteBean(customer.getId());
        creditCard.deleteBean(card.getId());
        creditCardType.deleteBean(cardType.getId());
    }

    @Test
    public void testGetUserByEmail() {
        assertEquals(customer, sut.getUserByEmail("kostikoff@rambler.ru"));
        assertNotSame(customer,sut.getUserByEmail("kostikoff@rambler"));
    }
}
