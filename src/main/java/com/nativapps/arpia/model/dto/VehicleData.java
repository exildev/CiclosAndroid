package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.VehicleOwnership;
import com.nativapps.arpia.database.entity.VehicleCondition;
import java.util.Calendar;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
public class VehicleData {

    protected VehicleOwnership ownership;

    protected VehicleCondition vehicleCondition;

    protected String trademark;

    protected String model;

    protected String color;

    protected String licensePlate;

    protected Calendar tecnomecanicaExpiration;

    protected Calendar soatExpiration;

    public VehicleData() {
    }

    public VehicleData(VehicleOwnership ownership,
            VehicleCondition vehicleCondition, String trademark, String model,
            String color, String licensePlate, Calendar tecnomecanicaExpiration,
            Calendar soatExpiration) {
        this.ownership = ownership;
        this.vehicleCondition = vehicleCondition;
        this.trademark = trademark;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.tecnomecanicaExpiration = tecnomecanicaExpiration;
        this.soatExpiration = soatExpiration;
    }

    public VehicleOwnership getOwnership() {
        return ownership;
    }

    public void setOwnership(VehicleOwnership ownership) {
        this.ownership = ownership;
    }

    public VehicleCondition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleCondition vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Calendar getTecnomecanicaExpiration() {
        return tecnomecanicaExpiration;
    }

    public void setTecnomecanicaExpiration(Calendar tecnomecanicaExpiration) {
        this.tecnomecanicaExpiration = tecnomecanicaExpiration;
    }

    public Calendar getSoatExpiration() {
        return soatExpiration;
    }

    public void setSoatExpiration(Calendar soatExpiration) {
        this.soatExpiration = soatExpiration;
    }

}
