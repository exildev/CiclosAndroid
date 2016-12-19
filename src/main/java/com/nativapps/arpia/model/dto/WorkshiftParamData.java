package com.nativapps.arpia.model.dto;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public class WorkshiftParamData {

    protected Integer breakDay;

    public WorkshiftParamData() {
    }

    public WorkshiftParamData(Integer breakDay) {
        this.breakDay = breakDay;
    }

    public Integer getBreakDay() {
        return breakDay;
    }

    public void setBreakDay(Integer breakDay) {
        this.breakDay = breakDay;
    }

}
