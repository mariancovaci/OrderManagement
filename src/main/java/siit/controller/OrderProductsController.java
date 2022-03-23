package siit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siit.model.OrderProduct;
import siit.service.OrderProductService;

import java.util.List;

@RestController
public class OrderProductsController {

    @Autowired
    OrderProductService orderProductService;

    @GetMapping("/api/customers/{cId}/orders/{oId}/products")
    public List<OrderProduct> getAllOrderProductsBy(@PathVariable("cId") Integer customerId, @PathVariable("oId") Integer orderId) {
        return orderProductService.getAllBy(customerId, orderId);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/api/customers/{cId}/orders/{oId}/products")
    public void addProductInOrder(@RequestBody OrderProduct orderProduct, @PathVariable Integer cId, @PathVariable Integer oId) {
        orderProductService.updateProductInOrder(cId, oId, orderProduct);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/customers/{cId}/orders/{oId}/products/{opId}")
    public void deleteProductInOrder(@PathVariable Integer cId, @PathVariable Integer oId, @PathVariable Integer opId) {
        orderProductService.deleteProductInOrder(cId, oId, opId);
    }

}
