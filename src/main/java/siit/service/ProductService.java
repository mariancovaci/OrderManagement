package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.repo.ProductDao;
import siit.model.Product;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product getBy(Integer productId) {
        return productDao.getBy(productId);
    }

    public List<Product> getBySearchTerm(String searchString) {
        return productDao.getLike(searchString);
    }
}
