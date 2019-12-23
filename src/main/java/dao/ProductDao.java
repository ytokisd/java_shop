package dao;


import model.Product;

import java.util.List;

public interface ProductDao {
    // todo add CRUD operations for Product class
    List<Product> getAllProducts();
    Product getProduct(long id);
}