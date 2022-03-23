package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.model.OrderProduct;
import siit.repo.OrderProductDao;
import siit.model.Product;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class OrderProductService {
    @Autowired
    OrderProductDao orderProductsDao;
    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;


    public void updateProductInOrder(Integer customerId, Integer orderId, OrderProduct orderProduct) {
        if ((orderProduct.getProduct().getId() == null) || (orderProduct.getQuantity() == null)){
            throw new ValidationException("Please input a valid product/quantity!");
        } else {
        if (isProductInOrder(customerId, orderId, orderProduct)) {
            orderProductsDao.updateProductQuantity(orderId, orderProduct);
        } else {
            orderProductsDao.addProductInOrder(orderId, orderProduct);
        }
    }
    }

    private boolean isProductInOrder(Integer customerId, Integer orderId, OrderProduct orderProduct) {
        List<OrderProduct> orderProductsDb = getAllBy(customerId, orderId);
        for (OrderProduct op : orderProductsDb) {
            if (op.getProductId().equals(orderProduct.getProduct().getId())) {
                return true;
            }
        }
        return false;
    }

    public List<OrderProduct> getAllBy(Integer customerId, Integer orderId) {
        List<OrderProduct> orderProducts = orderProductsDao.getAllBy(customerId, orderId);
        for (OrderProduct orderProduct : orderProducts) {
            Product product = productService.getBy(orderProduct.getProductId());
            orderProduct.setProduct(product);
        }
        return orderProducts;
    }

    public void deleteProductInOrder(Integer customerId, Integer orderId, Integer orderProductId) {
        List<OrderProduct> orderProductsDB = getAllBy(customerId, orderId);
        for (OrderProduct orderProduct : orderProductsDB) {
            if (orderProduct.getId() == orderProductId) {
                orderProductsDao.deleteProductInOrder(orderId, orderProductId);
            }
        }
    }


}
