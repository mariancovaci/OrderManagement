package siit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Customer;
import siit.service.CustomerService;

import javax.validation.ValidationException;

@Controller
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderCustomerList() {
        ModelAndView mav = new ModelAndView("customer-list");
        mav.addObject("customers", customerService.getAllCustomers());
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public ModelAndView renderCustomerAdd() {
        ModelAndView mav = new ModelAndView("customer-add");
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public ModelAndView performCustomerAdd(@ModelAttribute Customer customer){
        ModelAndView mav = new ModelAndView("customer-add");
        try {
            customerService.addCustomer(customer);
            mav.setViewName("redirect:/customers");
            } catch (ValidationException e){
            mav.addObject("error",e.getMessage());
            }
        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{customer_id}/edit")
    public ModelAndView renderCustomerEdit(@PathVariable("customer_id") int id) {
        ModelAndView mav = new ModelAndView("customer-edit");
        mav.addObject("customer", customerService.getBy(id));
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public ModelAndView performCustomerEdit(@ModelAttribute Customer customer) {
        ModelAndView mav = new ModelAndView("customer-edit");
        try {
            customerService.update(customer);
            mav.setViewName("redirect:/customers");
        } catch (ValidationException e){
            mav.addObject("error",e.getMessage());
        }

        return mav;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/invoice")
    @ResponseBody
    public String getCustomerInvoice(@PathVariable Integer id) {

        try {
            return customerService.getCustomerInvoice(id).toString();
        } catch (ValidationException ex) {
            return ex.getMessage();
        } catch (DataAccessException de){
            return "Customer not valid!";
        }

    }

    @RequestMapping(method = RequestMethod.GET,path = "/{id}/delete")
    public ModelAndView deleteCustomer(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView();
        customerService.deleteBy(id);
        mav.setViewName("redirect:/customers");
        return mav;
    }

}
