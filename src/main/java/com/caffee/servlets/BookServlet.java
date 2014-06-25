package com.caffee.servlets;

import com.caffee.dao.beans.Customer;
import com.caffee.dao.beans.Meal;
import com.caffee.services.AbstractDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;




import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


import java.util.Map;

@Controller
@SessionAttributes({"mealList", "customer"})
//@RequestMapping(value = {"/book", "/book/image"})
public class BookServlet {
    Logger log = Logger.getLogger(BookServlet.class);
    @Resource
    private AbstractDAO<Meal> meals;

    @RequestMapping (method = RequestMethod.GET, value = "/book")
    public String bookView(Map<String, Object> model) {
        if (model.get("customer") == null) {
            model.put("customer", new Customer());
        }
        if (model.get("mealList") == null) {
            model.put("mealList", meals.getAllBeans());
        }
        return "book";
    }

    @RequestMapping (method = RequestMethod.GET, value = "/book/image", params = "id")
    public void getImage(@RequestParam(value = "id") long id, HttpServletResponse response) {
        response.setContentType("image/gif");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(meals.getById(id).getPicture());
            outputStream.flush();
        } catch (IOException e) {
            log.error(e);
        }
    }
}
