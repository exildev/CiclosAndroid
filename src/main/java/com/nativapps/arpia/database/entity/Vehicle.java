package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
@Entity
@Table(name = "VEHICLE")
@NamedQueries({
    @NamedQuery(name = "vehicle.getAllByMessengerId",
            query = "SELECT a FROM Vehicle a "
            + "WHERE a.messenger.id = :messengerId"),
    @NamedQuery(name = "vehicle.findAll",
            query = "SELECT a FROM Vehicle a ")
})
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MESSENGER_ID", nullable = false)
    private Messenger messenger;

    @Column(name = "OWNERSHIP", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleOwnership ownership;

    @Column(name = "`CONDITION`", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleCondition condition;

    @Column(name = "TRADEMARK", length = 20, nullable = false)
    private String trademark;

    @Column(name = "MODEL", length = 10, nullable = false)
    private String model;

    @Column(name = "COLOR", length = 10)
    private String color;

    @Column(name = "LICENCE_PLATE", unique = true, length = 10, nullable = false)
    private String licensePlate;

    @Column(name = "SOAT_EXPIRATION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar soatExpiration;

    @Column(name = "TECNOMECANICA_EXPIRATION", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar tecnomecanicaExpiration;

    public Vehicle() {
    }

    public Vehicle(VehicleOwnership ownership, VehicleCondition condition,
            String trademark, String model, String color, String licensePlate,
            Calendar soatExpiration, Calendar tecnomecanicaExpiration) {
        this.ownership = ownership;
        this.condition = condition;
        this.trademark = trademark;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.soatExpiration = soatExpiration;
        this.tecnomecanicaExpiration = tecnomecanicaExpiration;
    }

    public Vehicle(long id, VehicleOwnership ownership,
            VehicleCondition condition, String trademark, String model,
            String color, String licensePlate, Calendar soatExpiration,
            Calendar tecnomecanicaExpiration) {
        this.id = id;
        this.ownership = ownership;
        this.condition = condition;
        this.trademark = trademark;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.soatExpiration = soatExpiration;
        this.tecnomecanicaExpiration = tecnomecanicaExpiration;
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

    public VehicleOwnership getOwnership() {
        return ownership;
    }

    public void setOwnership(VehicleOwnership ownership) {
        this.ownership = ownership;
    }

    public VehicleCondition getCondition() {
        return condition;
    }

    public void setCondition(VehicleCondition condition) {
        this.condition = condition;
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

    public Calendar getSoatExpiration() {
        return soatExpiration;
    }

    public void setSoatExpiration(Calendar soatExpiration) {
        this.soatExpiration = soatExpiration;
    }

    public Calendar getTecnomecanicaExpiration() {
        return tecnomecanicaExpiration;
    }

    public void setTecnomecanicaExpiration(Calendar tecnomecanicaExpiration) {
        this.tecnomecanicaExpiration = tecnomecanicaExpiration;
    }

}
