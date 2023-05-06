package com.PersonMap.personmap.bean;

public class Point {

    private int id;
    private String category;
    private String name;
    private String tips;
    private Double longitude;
    private Double latitude;

    public Point() {
    }

    public Point(int id, String category, String name, String tips, Double longitude, Double latitude) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.tips = tips;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Point(String category, String name, String tips, Double longitude, Double latitude) {
        this.category = category;
        this.name = name;
        this.tips = tips;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
