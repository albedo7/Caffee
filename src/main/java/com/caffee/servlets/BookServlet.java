package com.caffee.servlets;

import com.caffee.dao.beans.Customer;
import com.caffee.dao.beans.Meal;
import com.caffee.dao.beans.Order;
import com.caffee.dao.beans.OrderMeals;
import com.caffee.services.AbstractDAO;
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
import java.math.BigDecimal;
import java.util.Map;

@Controller
@SessionAttributes({"mealList", "customer", "order"})
public class BookServlet {
    Logger log = Logger.getLogger(BookServlet.class);
    @Resource
    private AbstractDAO<Meal> meals;
    @Resource
    private AbstractDAO<Order> orders;
    @Resource
    private AbstractDAO<OrderMeals> orderMeals;
    @Autowired
    private Order order;
    @Autowired
    private OrderMeals orderMeal;

    @RequestMapping (method = RequestMethod.GET, value = "/book")
    public String bookView(Map<String, Object> model) {
        if (model.get("customer") == null) {
            model.put("customer", new Customer());
        }
        return "book";
    }

    @RequestMapping (method = RequestMethod.GET, value = "/book/image", params = "id")
    public void getImage(@RequestParam(value = "id") long id, HttpServletResponse response) {
        response.setContentType("image/gif");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(meals.getById(id).getPicture());
            outputStream.flush();
            log.info("Picture with id = " + id + " have been read");
        } catch (IOException e) {
            log.error(e);
        }
    }

    @RequestMapping (method=RequestMethod.GET, value = "/book/add", params = "id")
    public String addToOrder(@RequestParam(value="id") long id, Map<String, Object> model) {
        if (model.get("order") == null) {
            model.put("order", order);
        }
        Customer customer = (Customer) model.get("customer");
        if (customer == null || customer.getId() == 0){
            return "book";
        }
        order.setCustomersByCustomerId(customer);
        if (order.getSumm() != null) {
            order.setSumm(new BigDecimal(order.getSumm().doubleValue()).add(new BigDecimal(meals.getById(id).getPrice().doubleValue())));
        } else {
            order.setSumm(new BigDecimal(meals.getById(id).getPrice().doubleValue()));
        }
        if (orders.getById(order.getId()) == null) {
            orders.saveBean(order);
        }
        orderMeal.setOrderByOrderId(order);
        orderMeal.setMealsByMealsId(meals.getById(id));
        orderMeals.saveBean(orderMeal);
        return "book";
    }
}
