package siit.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query("SELECT * FROM CUSTOMERS", this::extractCustomer);

    }

    public Customer getBy(int customerId) {
        String sql = "SELECT * FROM CUSTOMERS WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::extractCustomer, customerId);
    }

    private Customer extractCustomer(ResultSet rs, int rowNumb) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        return new Customer(id, name, phone,email);
    }

    public void update(Customer customer) {
        String sql = "UPDATE CUSTOMERS SET name=?, phone=?,email = ? WHERE id =?";
        jdbcTemplate.update(sql, customer.getName(), customer.getPhone(),customer.getEmail(), customer.getId());
    }

    public void add(Customer customer) {
        String sql = "INSERT INTO CUSTOMERS (NAME, PHONE, EMAIL, BIRTHDAY)" +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,customer.getName(),customer.getPhone(),customer.getEmail(),customer.getBirthday());
    }

    public void deleteBy(Integer id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
