package com.caffee.servlets;

//import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping({"index.jsp"})
public class caffeeServlet {
    @RequestMapping (method = RequestMethod.GET)
    public String showHomePage(Map<String, Object> model) {
        model.put("home", null);
        return "caffee";
    }
}

