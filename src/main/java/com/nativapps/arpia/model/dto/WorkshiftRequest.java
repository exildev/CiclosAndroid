package com.nativapps.arpia.model.dto;

import java.util.Date;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class WorkshiftRequest extends WorkshiftData {

    public WorkshiftRequest() {
    }

    public WorkshiftRequest(Date enterTime, Date exitTime) {
        super(enterTime, exitTime);
    }

}
