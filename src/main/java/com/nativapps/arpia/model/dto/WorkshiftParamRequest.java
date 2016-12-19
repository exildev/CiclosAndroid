package com.nativapps.arpia.model.dto;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public class WorkshiftParamRequest extends WorkshiftParamData {

    private Long workDay;

    private Long festiveDay;

    public WorkshiftParamRequest() {
    }

    public WorkshiftParamRequest(Long workDay, Long festiveDay, Integer breakDay) {
        super(breakDay);
        this.workDay = workDay;
        this.festiveDay = festiveDay;
    }

    public Long getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Long workDay) {
        this.workDay = workDay;
    }

    public Long getFestiveDay() {
        return festiveDay;
    }

    public void setFestiveDay(Long festiveDay) {
        this.festiveDay = festiveDay;
    }

}
