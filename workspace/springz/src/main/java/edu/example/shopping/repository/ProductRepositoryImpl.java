package edu.example.shopping.repository;

import edu.example.shopping.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Product selectById(String id) {
        Product result = jdbcTemplate.queryForObject(
                " SELECT * FROM product WHERE id = ?",
                new DataClassRowMapper<>(Product.class), id);
        return result;
    }

    @Override
    public List<Product> selectAll() {
        List<Product> result = jdbcTemplate.query(
                " SELECT * FROM product ORDER BY id",
                new DataClassRowMapper<>(Product.class));
        return result;
    }

    @Override
    public boolean update(Product product) {
        int result = jdbcTemplate.update("""
                        UPDATE product  SET name = ?, price = ?, quantity = ?
                        WHERE id = ? """,
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getId() );
        return result == 1;
    }
}
