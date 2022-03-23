package siit.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.OrderProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderProductDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderProduct> getAllBy(Integer customerId, Integer orderId) {
        String sql = "SELECT * FROM orders_products op WHERE op.order_id = ?";
        return jdbcTemplate.query(sql, this::extractOrderProduct, orderId);
    }

    private OrderProduct extractOrderProduct(ResultSet rs, int rowNumb) throws SQLException {
        Integer id = rs.getInt("id");
        Integer orderId = rs.getInt("order_id");
        Integer productId = rs.getInt("product_id");
        Double quantity = rs.getDouble("quantity");
        return new OrderProduct(id, orderId, productId, quantity);
    }

    public void updateProductQuantity(Integer orderId,OrderProduct op) {
        String sql = "UPDATE orders_products SET quantity = quantity +  ? WHERE order_id = ? AND product_id = ?";
        jdbcTemplate.update(sql,op.getQuantity(),orderId,op.getProduct().getId());
    }

    public void addProductInOrder(Integer orderId, OrderProduct orderProduct) {
//        insert into orders_products (order_id,product_id,quantity) values (7,4,5)
        String sql = "INSERT INTO orders_products (order_id, product_id, quantity) VALUES (?,?,?)";
        jdbcTemplate.update(sql,orderId,orderProduct.getProduct().getId(),orderProduct.getQuantity());
    }

    public void deleteProductInOrder(Integer orderId, Integer orderProductId) {
//        delete from orders_products where order_id = 7 and product_id = 4
        String sql = "DELETE FROM orders_products WHERE order_id = ? AND id = ?";
        jdbcTemplate.update(sql,orderId,orderProductId);
    }

    public OrderProduct getBy(Integer orderId,Integer productId){
        String sql = "SELECT * FROM orders_products WHERE order_id = ? AND product_id = ?";
        return jdbcTemplate.queryForObject(sql,this::extractOrderProduct,orderId,productId);
    }

    public void update(OrderProduct orderProduct) {
        String sql = "UPDATE orders_products SET quantity = ? WHERE orders_products.id = ?";
        jdbcTemplate.update(sql, orderProduct.getQuantity(), orderProduct.getId());
    }

}
