package com.nativapps.arpia.model.dto;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0-SNAPSHOT
 */
public class StockData {

    private int size;

    private String name;

    public StockData() {
    }

    public StockData(Integer size, String name) {
        this.size = size;
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
