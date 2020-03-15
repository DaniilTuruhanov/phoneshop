package com.es.core.data;

import com.es.core.model.Color;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

public class PhoneData {
    private Long id;

    private String model;

    private BigDecimal price;

    private BigDecimal displaySizeInches;

    private Integer weightGr;

    private BigDecimal lengthMm;

    private BigDecimal widthMm;

    private String deviceType;

    private String os;

    private Set<Color> colors = Collections.EMPTY_SET;

    private String displayResolution;

    private Integer pixelDensity;

    private String displayTechnology;

    private BigDecimal backCameraMegapixels;

    private BigDecimal frontCameraMegapixels;

    private Integer batteryCapacityMah;

    private BigDecimal talkTimeHours;

    private BigDecimal standByTimeHours;

    private String bluetooth;

    private String imageUrl;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getWeightGr() {
        return weightGr;
    }

    public void setWeightGr(Integer weightGr) {
        this.weightGr = weightGr;
    }

    public BigDecimal getLengthMm() {
        return lengthMm;
    }

    public void setLengthMm(BigDecimal lengthMm) {
        this.lengthMm = lengthMm;
    }

    public BigDecimal getWidthMm() {
        return widthMm;
    }

    public void setWidthMm(BigDecimal widthMm) {
        this.widthMm = widthMm;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public String getDisplayResolution() {
        return displayResolution;
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution = displayResolution;
    }

    public Integer getPixelDensity() {
        return pixelDensity;
    }

    public void setPixelDensity(Integer pixelDensity) {
        this.pixelDensity = pixelDensity;
    }

    public String getDisplayTechnology() {
        return displayTechnology;
    }

    public void setDisplayTechnology(String displayTechnology) {
        this.displayTechnology = displayTechnology;
    }

    public BigDecimal getBackCameraMegapixels() {
        return backCameraMegapixels;
    }

    public void setBackCameraMegapixels(BigDecimal backCameraMegapixels) {
        this.backCameraMegapixels = backCameraMegapixels;
    }

    public BigDecimal getFrontCameraMegapixels() {
        return frontCameraMegapixels;
    }

    public void setFrontCameraMegapixels(BigDecimal frontCameraMegapixels) {
        this.frontCameraMegapixels = frontCameraMegapixels;
    }

    public Integer getBatteryCapacityMah() {
        return batteryCapacityMah;
    }

    public void setBatteryCapacityMah(Integer batteryCapacityMah) {
        this.batteryCapacityMah = batteryCapacityMah;
    }

    public BigDecimal getTalkTimeHours() {
        return talkTimeHours;
    }

    public void setTalkTimeHours(BigDecimal talkTimeHours) {
        this.talkTimeHours = talkTimeHours;
    }

    public BigDecimal getStandByTimeHours() {
        return standByTimeHours;
    }

    public void setStandByTimeHours(BigDecimal standByTimeHours) {
        this.standByTimeHours = standByTimeHours;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}