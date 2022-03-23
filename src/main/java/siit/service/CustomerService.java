package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.repo.CustomerDao;
import siit.model.Customer;
import siit.model.Order;
import siit.model.OrderProduct;
import siit.model.Product;

import javax.validation.ValidationException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private ProductService productService;

    public void update(Customer customer) {
//        phone number Validation -> phone sa contina doar cifre, un anumit numar de caractere
        if (validateCustomerEntry(customer)) {
            customerDao.update(customer);
        }
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getBy(int id) {
        Customer customer = customerDao.getBy(id); // fara orders
        customer.setOrders(orderService.getBy(id));
        return customer;
    }

    public Double getCustomerInvoice(Integer id) throws ValidationException {

        Double invoiceSum = 0d;

            List<Order> orderList = orderService.getBy(id); // List of orders by customer id
            if (validateOrders(orderList)) {

                for (Order order : orderList) {
                    List<OrderProduct> orderProductList = orderProductService.getAllBy(id, order.getId()); // Refactor code to remove customer id
                    for (OrderProduct orderProduct : orderProductList) {
                        Product product = productService.getBy(orderProduct.getProductId());
                        invoiceSum += orderProduct.getQuantity() * product.getPrice();
                    }
                }
            }

        return invoiceSum;
    }

    private boolean validateOrders(List<Order> orderList) {
        if (orderList.size() == 0) {
            throw new ValidationException("Customer doesnt have any orders");
        } else {
            return true;
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

        Pattern pattern = Pattern.compile(patterns);
            Matcher matcher = pattern.matcher(phoneNumber);
            return matcher.matches();
    }

    private boolean isEmailValid(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public void addCustomer(Customer customer) {

        if (validateCustomerEntry(customer)){
            customerDao.add(customer);
        }

    }

    private boolean validateCustomerEntry(Customer customer) {
        if (customer.getName().equals("")) {
            throw new ValidationException("Customer name not valid!");
        } else if (!isPhoneNumberValid(customer.getPhone())){
            throw new ValidationException("Customer phone not valid!");
        } else if (!isEmailValid(customer.getEmail())){
            throw new ValidationException("Customer e-mail not valid!");
        } else {
            return true;
        }
    }

    public void deleteBy(Integer id) {
        customerDao.deleteBy(id);
    }
}
