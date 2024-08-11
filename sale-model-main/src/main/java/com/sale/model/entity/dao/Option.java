package com.sale.model.entity.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Option {

    @JsonProperty("Size")
    private String size;

    @JsonProperty("Color")
    private String color;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
