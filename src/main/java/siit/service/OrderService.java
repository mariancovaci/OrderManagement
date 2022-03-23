package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.repo.OrderDao;
import siit.model.Order;
import siit.model.OrderProduct;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderProductService orderProductService;

    public List<Order> getBy(Integer customerId) {
        List<Order> orders = orderDao.getAllBy(customerId);
        for (Order order: orders){
            List<OrderProduct> orderProducts = orderProductService.getAllBy(customerId, order.getId());
            order.setOrderProducts(orderProducts);
        }

        return orders;
    }

    public Order getBy(Integer customerId, Integer orderId) {
        List<Order> orders = getBy(customerId);
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        throw new RuntimeException("Order with id = " + orderId + " does not exist!");
    }

    public Order getByNumber(String number) {
        return orderDao.getByNumber(number);
    }

    public boolean getByNumberV2(String number) {
        Boolean result = false;
        try {
            orderDao.getByNumberV2(number);
        } catch (Exception e) {
            result = true;
        }
        return result;
    }

    public void add(Order order) {
        order.setPlaced(LocalDateTime.now());
        orderDao.add(order);
    }


    public void deleteOrder(Integer customerId, Integer orderId) {
        orderDao.deleteOrder(customerId,orderId);
    }
}
