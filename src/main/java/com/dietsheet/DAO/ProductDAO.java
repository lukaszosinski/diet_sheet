package com.dietsheet.DAO;

import com.dietsheet.model.Product;
import org.springframework.stereotype.Component;

@Component("productDAO")
public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO() {
        setClazz(Product.class);
    }
}
