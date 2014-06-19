package com.caffee.servlets;

import com.caffee.dao.beans.Customer;
import com.caffee.dao.beans.Meal;
import com.caffee.services.AbstractDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"mealList", "customer"})
@RequestMapping(value = {"/book"})
public class BookServlet {
    @Resource
    private AbstractDAO<Meal> meals;

    @RequestMapping (method = RequestMethod.GET)
    public String bookView(Map<String, Object> model) {
        if (model.get("customer") == null) {
            model.put("customer", new Customer());
        }
        if (model.get("mealList") == null) {
            List<Meal> mealList = meals.getAllBeans();
            model.put("mealList", mealList);
        }
        return "book";
    }
}
