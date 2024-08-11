package com.sale.model.entity.dao;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ProductList {

    @JsonProperty("products")
    private List<ProductDAO> products;

    // Getters e Setters
    public List<ProductDAO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDAO> products) {
        this.products = products;
    }
}