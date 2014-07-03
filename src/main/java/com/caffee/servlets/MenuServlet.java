package com.caffee.servlets;

import com.caffee.dao.beans.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@RequestMapping(value = "/menu")
@SessionAttributes("customer")
public class MenuServlet {

    @RequestMapping (method = RequestMethod.GET)
    public String menuView(Map<String, Object> model) {
        if (model.get("customer") == null) {
            model.put("customer", new Customer());
        }
        return "menu";
    }
}
