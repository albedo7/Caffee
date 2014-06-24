package com.caffee.servlets;

import com.caffee.dao.beans.Customer;
import com.caffee.services.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.Map;

@Controller
@SessionAttributes("customer")
@RequestMapping(value = {"/login", "/logout"})
public class LoginServlet {
    @Autowired
    private UserDAO userDAO;

    @RequestMapping (method = RequestMethod.GET)
    public String loginView(Map<String, Object> model) {
        if (model.get("customer") == null) {
            model.put("customer", new Customer());
        }
        return "login";
    }

    @RequestMapping (method = RequestMethod.POST, params = "login")
    public String processLogin(@ModelAttribute("customer") Customer user,
                                      Map<String, Object> model) {
        Customer customer = userDAO.getUserByEmail(user.getEmail());
        if (customer != null) {
            customer.decrypt();
            if (user.getPwdHash().equals(customer.getPwdHash())) {
                customer.getCreditCard().decrypt();
                    model.put("customer", customer);
            }
        }
        return "main";
    }

    @RequestMapping (method = RequestMethod.POST, params = "logout")
    public String processLogout(Map<String, Object> model) {
        model.put("customer",new Customer());
        return "main";
    }
}
