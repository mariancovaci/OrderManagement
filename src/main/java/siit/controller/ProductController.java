package siit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import siit.model.Product;
import siit.service.ProductService;

import java.util.*;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/api/products")
    public List<Product> getProductsBySearchTerm(@RequestParam(name = "term") String searchString) {
        return productService.getBySearchTerm(searchString);
    }
}
