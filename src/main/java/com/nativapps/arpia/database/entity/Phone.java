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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "PHONE")
@NamedQueries({
    @NamedQuery(name = "phone.findAllByPersonId",
            query = "SELECT p FROM Phone p WHERE p.owner.id = :personId"),
    @NamedQuery(name = "phone.findByNumber",
            query = "SELECT p FROM Phone p WHERE p.number = :number")
})
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TAG", nullable = false, length = 10)
    private String tag;

    @Column(name = "PHONE_NUMER", nullable = false, length = 15, unique = true)
    private String number;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Person owner;

    @Column(name = "CONFIRMED")
    private boolean confirmed;

    public Phone() {
    }

    public Phone(String tag, String number, PhoneType type) {
        this.tag = tag;
        this.number = number;
        this.type = type;
    }

    public Phone(long id, String tag, String number, PhoneType type,
            boolean confirmed) {
        this.id = id;
        this.tag = tag;
        this.number = number;
        this.type = type;
        this.confirmed = confirmed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person person) {
        this.owner = person;

        if (!person.getPhones().contains(this))
            person.addPhone(this);
    }

}
