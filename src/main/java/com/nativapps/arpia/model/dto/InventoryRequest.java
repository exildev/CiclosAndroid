package com.nativapps.arpia.model.dto;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 2.0
 */
public class InventoryRequest extends InventoryData {

    public InventoryRequest() {
    }

    public InventoryRequest(String name, Integer count) {
        super(name, count);
    }

}
