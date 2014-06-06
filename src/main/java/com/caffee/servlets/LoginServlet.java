package com.caffee.servlets;

import com.caffee.dao.beans.Customer;
import com.caffee.services.UserDAO;
import com.caffee.utils.CryptoUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value = {"/login", "/logout"})
public class LoginServlet {
    private UserDAO userDAO = new UserDAO();

    @RequestMapping (method = RequestMethod.GET)
    public String loginView(Map<String, Object> model) {
        model.put("customer", new Customer());
        return "login";
    }

    @RequestMapping (method = RequestMethod.POST, params = "login")
    public String processLogin(@ModelAttribute("customer") Customer user,
                                      Map<String, Object> model) {
        Customer customer = userDAO.getUserByEmail(user.getEmail());
        if (customer != null) {
            customer.setPwdHash(CryptoUtils.decrypt(customer.getPwdHash(), customer.getSalt()));
            if (user.getPwdHash().equals(customer.getPwdHash())) {
                model.put("customer", customer);
            }
        }
        return "/login";
    }

    @RequestMapping (method = RequestMethod.POST, params = "logout")
    public String processLogout(Map<String, Object> model) {
        model.put("customer", new Customer());
        return "/login";
    }
}
