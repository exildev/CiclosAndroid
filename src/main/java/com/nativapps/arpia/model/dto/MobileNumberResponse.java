package com.nativapps.arpia.model.dto;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi> @version 1.0
 */
public class MobileNumberResponse extends MobileNumberData {

    private Long id;

    public MobileNumberResponse() {
    }

    public MobileNumberResponse(Long id, Integer number, Long messengerId,
            Long operationId) {
        super(number, messengerId, operationId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
