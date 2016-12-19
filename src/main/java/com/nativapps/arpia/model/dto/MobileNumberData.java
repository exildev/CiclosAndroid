package com.nativapps.arpia.model.dto;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class MobileNumberData {

    protected Integer number;
    protected Long messengerId;
    protected Long operationId;

    public MobileNumberData() {
    }

    public MobileNumberData(Integer number, Long messengerId,
            Long operationId) {
        this.number = number;
        this.messengerId = messengerId;
        this.operationId = operationId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getMessengerId() {
        return messengerId;
    }

    public void setMessengerId(Long messengerId) {
        this.messengerId = messengerId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

}
