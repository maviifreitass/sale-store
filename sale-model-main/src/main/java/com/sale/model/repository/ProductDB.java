/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sale.model.repository;

import com.sale.model.entity.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class ProductDB implements Serializable {

    private static ProductDB instance;
    private List<Product> productList;

    private ProductDB() {
        productList = new ArrayList<>();
        Product p1 = new Product(1L, "Produto 1", 7.90);
        Product p2 = new Product(2L, "Produto 2", 9.90);
        productList.add(p1);
        productList.add(p2);
    }

    public static ProductDB getInstance() {
        if (instance == null) {
            instance = new ProductDB();
        }
        return instance;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
