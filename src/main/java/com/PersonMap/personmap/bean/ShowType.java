package com.PersonMap.personmap.bean;

public class ShowType {

    private int id;
    private String name;
    private Integer size;
    private String colorHex;
    private Integer markID;

    public ShowType() {
    }

    public ShowType(int id, String name, Integer size, String colorHex, Integer markID) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.colorHex = colorHex;
        this.markID = markID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Integer getMarkID() {
        return markID;
    }

    public void setMarkID(Integer markID) {
        this.markID = markID;
    }
}
