package com.caffee.servlets;

import com.caffee.dao.beans.CreditCardType;
import com.caffee.dao.beans.Customer;
import com.caffee.services.AbstractDAO;
import com.caffee.utils.CryptoUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping (value = "/register")
public class caffeeServlet {
    private AbstractDAO<CreditCardType> cardTypes= new AbstractDAO<>(CreditCardType.class);

    @RequestMapping (method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        Customer userForm = new Customer();
        model.put("userForm", userForm);
        model.put("cardList", cardTypes.getAllBeans());
        return "register";
    }

    @RequestMapping (method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") Customer user,
                                      Map<String, Object> model) {
        user.setSalt(CryptoUtils.doSalt());
        user.setPwdHash(CryptoUtils.crypt(user.getPwdHash(), user.getSalt()));
        return "successRegister";
    }
}

