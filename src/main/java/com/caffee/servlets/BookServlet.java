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
import javax.annotation.Resource;

import java.util.Map;

@Controller
@SessionAttributes({"mealList", "customer"})
@RequestMapping(value = {"/book", "/resources/image"})
public class BookServlet {
    Logger log = Logger.getLogger(BookServlet.class);
    @Resource
    private AbstractDAO<Meal> meals;

    @RequestMapping (method = RequestMethod.GET)
    public String bookView(Map<String, Object> model) {
        if (model.get("customer") == null) {
            model.put("customer", new Customer());
        }
        if (model.get("mealList") == null) {
            model.put("mealList", meals.getAllBeans());
        }
        return "book";
    }

    @RequestMapping (method = RequestMethod.GET, value = "/resources/image/{id}")
    public void getImage(@PathVariable("id") long mealId, Map<String, Object> model,
                         HttpServletResponse response) {
        byte[] picture = meals.getById(mealId).getPicture();
        response.setContentType("image/gif");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(picture);
            outputStream.flush();
        } catch (IOException e) {
            log.error(e);
        }
    }
}
