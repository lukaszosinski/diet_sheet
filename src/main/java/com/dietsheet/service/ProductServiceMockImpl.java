package com.dietsheet.service;

import com.dietsheet.model.Nutrients;
import com.dietsheet.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;



public class ProductServiceMockImpl implements Service<Product> {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Product> products;

    static {
        products = populateDummyProducts();
    }


    @Override
    public Product findById(long id) {
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    @Override
    public Product findByName(String name) {
        for(Product product : products){
            if(product.getName().equalsIgnoreCase(name)){
                return product;
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        product.setId(counter.incrementAndGet());
        products.add(product);
    }

    @Override
    public void update(Product product) {
        int index = products.indexOf(product);
        products.set(index, product);
    }

    @Override
    public void deleteById(long id) {
        products.removeIf(product -> product.getId() == id);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void deleteAll() {
        products.clear();
    }

    @Override
    public boolean isExist(Product product) {
        return findByName(product.getName())!=null;
    }

    private static List<Product> populateDummyProducts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product1", new Nutrients(50, 50, 50 ,50), 50));
        products.add(new Product( "Product2", new Nutrients(50, 50, 50 ,50), 50));
        products.add(new Product( "Product3", new Nutrients( 50, 50, 50 ,50), 50));
        return products;
    }

}

