package com.caffee.servlets;

import com.caffee.dao.beans.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@RequestMapping(value = "/order")
@SessionAttributes("order")
public class OrderServlet {

    @RequestMapping(method = RequestMethod.GET, value = "/order")
    public String orderView(Map<String, Object> model) {
        if (model.get("order") == null) {
            model.put("order", new Order());
        }
        return "order";
    }
}
