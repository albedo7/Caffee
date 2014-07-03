package com.caffee.servlets;

import com.caffee.dao.beans.Customer;
import com.caffee.dao.beans.Meal;
import com.caffee.dao.beans.Order;
import com.caffee.services.AbstractDAO;
import com.caffee.services.OrderServices;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@SessionAttributes({"mealList", "customer", "order"})
public class BookServlet {
    Logger log = Logger.getLogger(BookServlet.class);
    @Resource
    private AbstractDAO<Meal> mealsDAO;
    @Autowired
    private OrderServices orderServices;
    @Autowired
    Order order;

    @RequestMapping (method = RequestMethod.GET, value = "/book")
    public String bookView(Map<String, Object> model) {
        if (model.get("customer") == null) {
            model.put("customer", new Customer());
        }
        if (model.get("mealList") == null) {
            model.put("mealList", mealsDAO.getAllBeans());
        }
        return "book";
    }

    @RequestMapping (method = RequestMethod.GET, value = "/book/image", params = "id")
    public void getImage(@RequestParam(value = "id") long id, HttpServletResponse response) {
        response.setContentType("image/gif");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(mealsDAO.getById(id).getPicture());
            outputStream.flush();
            log.info("Picture with id = " + id + " have been read");
        } catch (IOException e) {
            log.error(e);
        }
    }

    @RequestMapping (method=RequestMethod.POST, value = "/book/add", params = "id")
    public String addToOrder(@RequestParam(value="id") long id, Map<String, Object> model) {
        Customer customer = (Customer) model.get("customer");
        if (customer == null || customer.getId() == 0){
            return "book";
        }
        if ((order = (Order)model.get("order")) == null) {
            order= orderServices.createOrder(customer);
        }
        Meal meal = mealsDAO.getById(id);
        orderServices.addMealToOrder(order, meal);
        model.put("order", order);
        return "book";
    }
}
