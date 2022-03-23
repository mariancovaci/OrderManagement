package siit.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Product getBy(Integer productId) {
        String sql = "SELECT * FROM products p WHERE id =?";
        return jdbcTemplate.queryForObject(sql, this::extractProduct, productId);
    }


    private Product extractProduct(ResultSet rs, int rowNumb) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Double weight = rs.getDouble("weight");
        Double price = rs.getDouble("price");
        return new Product(id, name, weight, price);
    }

    public List<Product> getLike(String searchString) {
        String sql = "SELECT * FROM products WHERE LOWER(name) LIKE ?";
        String finalSearchString = searchString.toLowerCase() + "%";
        return jdbcTemplate.query(sql, this::extractProduct, finalSearchString);
    }

    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, this::extractProduct);
    }
}
