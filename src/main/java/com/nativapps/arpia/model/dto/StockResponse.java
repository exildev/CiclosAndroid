package com.nativapps.arpia.model.dto;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public class StockResponse extends StockData {

    private Long id;

    public StockResponse() {
    }

    public StockResponse(Long id, Integer size, String name) {
        super(size, name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
