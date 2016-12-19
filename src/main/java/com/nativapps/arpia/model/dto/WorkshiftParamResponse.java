package com.nativapps.arpia.model.dto;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.0
 */
public class WorkshiftParamResponse extends WorkshiftParamData {

    private Long id;

    private WorkshiftResponse workDay;

    private WorkshiftResponse festiveDay;

    public WorkshiftParamResponse() {
    }

    public WorkshiftParamResponse(Long id, WorkshiftResponse workDay,
            WorkshiftResponse festiveDay, Integer breakDay) {
        super(breakDay);
        this.id = id;
        this.workDay = workDay;
        this.festiveDay = festiveDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WorkshiftResponse getWorkDay() {
        return workDay;
    }

    public void setWorkDay(WorkshiftResponse workDay) {
        this.workDay = workDay;
    }

    public WorkshiftResponse getFestiveDay() {
        return festiveDay;
    }

    public void setFestiveDay(WorkshiftResponse festiveDay) {
        this.festiveDay = festiveDay;
    }

}
