package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.2.0
 */
@Entity
@Table(name = "RELIABILITY")
@NamedQueries({
    @NamedQuery(name = "reliability.findByMessengerId",
            query = "select r from Reliability r where r.messenger.id = :id")
})
public class Reliability implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "reliability")
    private Messenger messenger;

    @Column(name = "CONFIDENCE")
    private int confidence;

    @Column(name = "SPEED")
    @Enumerated(EnumType.STRING)
    private Speed speed;

    @Column(name = "MANAGEMENT")
    @Enumerated(EnumType.STRING)
    private Management management;

    @Column(name = "MIN_NUMBER_BASE")
    private int minNumberBase;

    @Column(name = "MIN_PRODUCTION")
    private int minProduction;

    @Column(name = "MIN_SERVICE")
    private int minService;

    @Column(name = "DAILY_SAVING")
    private long dailySaving;

    public Reliability() {
    }

    public Reliability(int confidence, Speed speed, Management management,
            int minNumberBase, int minProduction, int minService,
            long dailySaving) {
        this.confidence = confidence;
        this.speed = speed;
        this.management = management;
        this.minNumberBase = minNumberBase;
        this.minProduction = minProduction;
        this.minService = minService;
        this.dailySaving = dailySaving;
    }

    public Reliability(long id, int confidence, Speed speed,
            Management management, int minNumberBase, int minProduction,
            int minService, long dailySaving) {
        this.id = id;
        this.confidence = confidence;
        this.speed = speed;
        this.management = management;
        this.minNumberBase = minNumberBase;
        this.minProduction = minProduction;
        this.minService = minService;
        this.dailySaving = dailySaving;
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

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Management getManagement() {
        return management;
    }

    public void setManagement(Management management) {
        this.management = management;
    }

    public int getMinNumberBase() {
        return minNumberBase;
    }

    public void setMinNumberBase(int minNumberBase) {
        this.minNumberBase = minNumberBase;
    }

    public int getMinProduction() {
        return minProduction;
    }

    public void setMinProduction(int minProduction) {
        this.minProduction = minProduction;
    }

    public int getMinService() {
        return minService;
    }

    public void setMinService(int minService) {
        this.minService = minService;
    }

    public long getDailySaving() {
        return dailySaving;
    }

    public void setDailySaving(long dailySaving) {
        this.dailySaving = dailySaving;
    }
}
