package com.es.core.data;

import com.es.core.model.Color;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class PhonePlpData {
    private Long id;

    private String brand;

    private String model;

    private BigDecimal price;

    private BigDecimal displaySizeInches;

    private Set<Color> colors = Collections.EMPTY_SET;

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDisplaySizeInches() {
        return displaySizeInches;
    }

    public void setDisplaySizeInches(BigDecimal displaySizeInches) {
        this.displaySizeInches = displaySizeInches;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
