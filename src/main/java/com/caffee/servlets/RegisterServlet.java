package com.caffee.servlets;

import com.caffee.dao.beans.CreditCard;
import com.caffee.dao.beans.CreditCardType;
import com.caffee.dao.beans.Customer;
import com.caffee.services.AbstractDAO;
import com.caffee.utils.CryptoUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping (value = "/register")
    public class RegisterServlet {
    private AbstractDAO<CreditCardType> cardTypesDAO = new AbstractDAO<>(CreditCardType.class);
    private AbstractDAO<Customer> customerDAO = new AbstractDAO<>(Customer.class);
    private AbstractDAO<CreditCard> creditCardDAO = new AbstractDAO<>(CreditCard.class);

    /*@InitBinder
    public void binder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    Date ts = new Date(new SimpleDateFormat("dd-MM-yyyy").parse(value).getTime());
                    setValue(ts);
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });
    }*/

    @RequestMapping (method = RequestMethod.GET)
    public String viewRegistration(Map<String, Object> model) {
        Customer userForm = new Customer();
        model.put("userForm", userForm);
        model.put("cardList", cardTypesDAO.getAllBeans());
        return "register";
    }

    @RequestMapping (method = RequestMethod.POST)
    public String processRegistration(@Valid @ModelAttribute("userForm") Customer user, BindingResult result,
                                      Map<String, Object> model) {
        if (result.hasErrors()) {
            return "register";
        }
        user.setSalt(CryptoUtils.doSalt());
        user.setPwdHash(CryptoUtils.crypt(user.getPwdHash(), user.getSalt()));
        CreditCard creditCard = user.getCreditCard();
        creditCard.setSalt(CryptoUtils.doSalt());
        creditCard.setNumHash(CryptoUtils.crypt(creditCard.getNumHash(), creditCard.getSalt()));
        creditCardDAO.saveBean(creditCard);
        customerDAO.saveBean(user);
        return "successRegister";
    }
}

