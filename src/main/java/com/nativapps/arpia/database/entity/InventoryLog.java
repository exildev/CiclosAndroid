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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "INVENTORY_HISTORY")
public class InventoryLog implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INVENTORY_ID", nullable = false)
    private Inventory inventory;

    @Column(name = "TYPE", updatable = false)
    @Enumerated(EnumType.ORDINAL)
    private LogType type;

    @Column(name = "COUNT", updatable = false)
    private int count;

    @Column(name = "REASON", updatable = false, length = 200)
    private String reason;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGISTER_DATE", updatable = false)
    private Calendar registerDate;

    public InventoryLog() {
        this.registerDate = Calendar.getInstance();
    }

    public InventoryLog(Inventory inventory, LogType type, int count,
            String reason) {
        this.inventory = inventory;
        this.type = type;
        this.count = count;
        this.reason = reason;
        this.registerDate = Calendar.getInstance();
    }

    public InventoryLog(long id, Inventory inventory, LogType type, int count,
            String reason, Calendar registerDate) {
        this.id = id;
        this.inventory = inventory;
        this.type = type;
        this.count = count;
        this.reason = reason;
        this.registerDate = registerDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LogType getType() {
        return type;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

}
