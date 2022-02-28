package webshop;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long insertProduct(String productName, int price, int stock) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> SqlUtil.createStatement(con, Statement.RETURN_GENERATED_KEYS,
                "INSERT INTO products (product_name, price, stock) VALUES (?, ?, ?)",
                productName, price, stock), keyHolder);
        return SqlUtil.getKey(keyHolder);
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", this::createProduct, id);
    }

    public void updateProductStock(long id, int reduceBy) {
        jdbcTemplate.update("UPDATE products SET stock = (stock - ?) WHERE id = ?", reduceBy, id);
    }

    private Product createProduct(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("product_name"),
                rs.getInt("price"),
                rs.getInt("stock")
        );
    }
}
