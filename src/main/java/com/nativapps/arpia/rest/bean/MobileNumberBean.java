package com.nativapps.arpia.rest.bean;

import javax.ws.rs.QueryParam;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class MobileNumberBean {

    @QueryParam("quantity")
    private Integer quantity;

    @QueryParam("operation")
    private Long operationId;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

}
