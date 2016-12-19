package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1.1
 */
@Entity
@Table(name = "WORKSHIFT_PARAM")
@NamedQueries({
    @NamedQuery(name = "workshiftParam.findByMessengerId",
            query = "select w from WorkshiftParam w where w.messenger.id = :id")
})
public class WorkshiftParam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "workshiftParam")
    private Messenger messenger;

    @OneToOne
    @JoinColumn(name = "FESTIVE_DAY_ID")
    private Workshift festiveDay;

    @OneToOne
    @JoinColumn(name = "WORK_DAY_ID")
    private Workshift workDay;

    @Column(name = "BREAK_DAY")
    private int breakDay;

    public WorkshiftParam() {
    }

    public WorkshiftParam(Workshift festiveDay, Workshift workDay,
            int breakDay) {
        this.festiveDay = festiveDay;
        this.workDay = workDay;
        this.breakDay = breakDay;
    }

    public WorkshiftParam(long id, Workshift festiveDay, Workshift workDay,
            int breakDay) {
        this.id = id;
        this.festiveDay = festiveDay;
        this.workDay = workDay;
        this.breakDay = breakDay;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public Workshift getFestiveDay() {
        return festiveDay;
    }

    public void setFestiveDay(Workshift festiveDay) {
        this.festiveDay = festiveDay;
    }

    public Workshift getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Workshift workDay) {
        this.workDay = workDay;
    }

    public int getBreakDay() {
        return breakDay;
    }

    public void setBreakDay(int breakDay) {
        this.breakDay = breakDay;
    }

}
