package com.nativapps.arpia.model.dto;

import java.util.Calendar;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 2.0
 */
public class InventoryResponse extends InventoryData {

    private Long id;

    private Integer countUsed;

    private Calendar registerDate;

    private Calendar updatedDate;

    public InventoryResponse() {
    }

    public InventoryResponse(Long id, Integer countUsed, Calendar registerDate,
            Calendar updatedDate, String name, Integer count) {
        super(name, count);
        this.id = id;
        this.countUsed = countUsed;
        this.registerDate = registerDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountUsed() {
        return countUsed;
    }

    public void setCountUsed(Integer countUsed) {
        this.countUsed = countUsed;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }

}
