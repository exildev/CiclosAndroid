package com.nativapps.arpia.model.dto;

import java.util.Date;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class WorkshiftData {

    protected Date enterTime;

    protected Date exitTime;

    public WorkshiftData() {
    }

    public WorkshiftData(Date enterTime, Date exitTime) {
        this.enterTime = enterTime;
        this.exitTime = exitTime;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }

}
