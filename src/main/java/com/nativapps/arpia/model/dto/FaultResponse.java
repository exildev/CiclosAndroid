package com.nativapps.arpia.model.dto;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public class FaultResponse extends FaultData {

    private Long id;

    public FaultResponse() {
    }

    public FaultResponse(Long id, String description) {
        this.description = description;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
