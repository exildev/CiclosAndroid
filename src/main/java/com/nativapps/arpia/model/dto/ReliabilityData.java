package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Management;
import com.nativapps.arpia.database.entity.Speed;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 1.1.0
 */
public class ReliabilityData {

    protected Integer confidence;

    protected Speed speed;

    protected Management management;

    protected Integer minNumberBase;

    protected Integer minProduction;

    protected Integer minService;

    protected Long dailySaving;

    public ReliabilityData() {
    }

    public ReliabilityData(Integer confidence, Speed speed,
            Management management, Integer minNumberBase, Integer minProduction,
            Integer minService, Long dailySaving) {
        this.confidence = confidence;
        this.speed = speed;
        this.management = management;
        this.minNumberBase = minNumberBase;
        this.minProduction = minProduction;
        this.minService = minService;
        this.dailySaving = dailySaving;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
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

    public Integer getMinNumberBase() {
        return minNumberBase;
    }

    public void setMinNumberBase(Integer minNumberBase) {
        this.minNumberBase = minNumberBase;
    }

    public Integer getMinProduction() {
        return minProduction;
    }

    public void setMinProduction(Integer minProduction) {
        this.minProduction = minProduction;
    }

    public Integer getMinService() {
        return minService;
    }

    public void setMinService(Integer minService) {
        this.minService = minService;
    }

    public Long getDailySaving() {
        return dailySaving;
    }

    public void setDailySaving(Long dailySaving) {
        this.dailySaving = dailySaving;
    }

}
