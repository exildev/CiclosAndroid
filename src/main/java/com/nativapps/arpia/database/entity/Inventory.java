package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pachecho <ryctabo@gmail.com>
 *
 * @version 1.0.1
 */
@Entity
@Table(name = "INVENTORY")
@NamedQueries({
    @NamedQuery(name = "inventory.findByName",
            query = "select i from Inventory i where i.name = :name")
})
public class Inventory implements Serializable {

    @Id
    @Column(name = "ID")
    @Access(AccessType.PROPERTY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false, length = 25, unique = true)
    private String name;

    @Column(name = "COUNT", nullable = false)
    private int count;

    @Column(name = "COUNT_USED", nullable = false)
    private int countUsed;

    @Column(name = "REGISTER_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar registerDate;

    @Column(name = "UPDATE_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updateDate;

    public Inventory() {
    }

    public Inventory(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Inventory(long id, String name, int count, int countUsed,
            Calendar registerDate, Calendar updateDate) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.countUsed = countUsed;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountUsed() {
        return countUsed;
    }

    public void setCountUsed(int countUsed) {
        this.countUsed = countUsed;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

}
