package siit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Customer;
import siit.model.Order;
import siit.service.CustomerService;
import siit.service.OrderService;

@Controller
@RequestMapping("")
public class OrderController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{customerId}/orders")
    public ModelAndView renderOrderPage(@PathVariable Integer customerId) {
        ModelAndView mav = new ModelAndView("customer-orders");
        Customer dbCustomer = customerService.getBy(customerId);
        mav.addObject("customer", dbCustomer);

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/customers/{customerId}/orders/add")
    public ModelAndView renderCustomerOrderAddPage(@PathVariable Integer customerId) {
        ModelAndView mav = new ModelAndView("customer-order-add");
        Customer customer = customerService.getBy(customerId);
        mav.addObject("customer", customer);
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/customers/{customerId}/orders/add")
    public ModelAndView addOrderV1(@ModelAttribute Order order) {
        ModelAndView modelAndView = new ModelAndView("customer-order-add");
        Order dbOrder = orderService.getByNumber(order.getNumber());
        if (dbOrder == null) {
            orderService.add(order);
            modelAndView.setViewName("redirect:/customers/" + order.getCustomerId() + "/orders");
        } else {
            Customer customer = new Customer();
            customer.setId(order.getCustomerId());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "The order [ " + order.getNumber() + " ] already exists!!");
        }

        return modelAndView;
    }

    public ModelAndView addOrderV2(@ModelAttribute Order order) {
        ModelAndView modelAndView = new ModelAndView("customer-order-add");

        if (orderService.getByNumberV2(order.getNumber())) {
            orderService.add(order);
            modelAndView.setViewName("redirect:/customers/" + order.getCustomerId() + "/orders");
        } else {
            Customer customer = new Customer();
            customer.setId(order.getCustomerId());
            modelAndView.addObject("customer", customer);
            modelAndView.addObject("error", "Something was wrong... :)");
        }

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/customers/{cId}/orders/{oId}")
    @ResponseBody
    public Order getOrderForOrderEdit(@PathVariable("cId") Integer customerId, @PathVariable("oId") Integer orderId){
        return orderService.getBy(customerId, orderId);
    }

    @RequestMapping(method = RequestMethod.GET,path = "/customers/{cId}/orders/{oId}/delete")
    public ModelAndView deleteOrder(@PathVariable("cId") Integer customerId, @PathVariable("oId") Integer orderId){
        ModelAndView modelAndView = new ModelAndView("customer-orders");
        orderService.deleteOrder(customerId,orderId);
        modelAndView.setViewName("redirect:/customers/" + customerId + "/orders");
        return modelAndView;

    }

}
