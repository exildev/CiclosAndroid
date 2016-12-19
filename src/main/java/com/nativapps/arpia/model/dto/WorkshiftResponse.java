package com.nativapps.arpia.model.dto;

import java.util.Date;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class WorkshiftResponse extends WorkshiftData {

    private Long id;

    public WorkshiftResponse() {
    }

    public WorkshiftResponse(Long id, Date enterTime, Date exitTime) {
        super(enterTime, exitTime);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
