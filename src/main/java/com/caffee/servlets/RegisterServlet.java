package com.caffee.servlets;

import com.caffee.dao.beans.CreditCard;
import com.caffee.dao.beans.CreditCardType;
import com.caffee.dao.beans.Customer;
import com.caffee.services.DAOService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("customer")
@RequestMapping (value = "/register")
    public class RegisterServlet {
    @Resource
    private DAOService<CreditCardType> cardTypesDAO;
    @Resource
    private DAOService<Customer> customerDAO;
    @Resource
    private DAOService<CreditCard> creditCardDAO;


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
        model.put("userForm", new Customer());
        model.put("cardList", cardTypesDAO.getAllBeans());
        return "register";
    }

    @RequestMapping (method = RequestMethod.POST)
    public String processRegistration(@Valid @ModelAttribute("userForm") Customer user, BindingResult result,
                                      Map<String, Object> model) {
        if (result.hasErrors()) {
            return "register";
        }
        user.crypt();
        CreditCard creditCard = user.getCreditCard();
        creditCard.crypt();
        if (creditCardDAO.saveBean(creditCard)) {
            if (customerDAO.saveBean(user)) {
                user.decrypt();
                creditCard.decrypt();
                model.put("customer", user);
                return "successRegister";
            }
        }
        /*ToDo put errors in form!!!*/
        creditCardDAO.deleteBean(creditCard.getId()); //if user havn't been saved, but his credit card have been saved we have to remove this credit card
        model.put("cardList", cardTypesDAO.getAllBeans());
        user.decrypt();
        creditCard.decrypt();
        return "register";
    }
}

