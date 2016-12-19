package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
@Entity
@Table(name = "WORKSHIFT")
public class Workshift implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "ENTER_TIME")
    @Temporal(TemporalType.TIME)
    private Date enterTime;

    @Column(name = "EXIT_TIME")
    @Temporal(TemporalType.TIME)
    private Date exitTime;

    public Workshift() {
    }

    public Workshift(long id) {
        this.id = id;
    }

    public Workshift(Date enterTime, Date exitTime) {
        this.enterTime = enterTime;
        this.exitTime = exitTime;
    }

    public Workshift(long id, Date enterTime, Date exitTime) {
        this.id = id;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
