package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "EMAIL_ADDRESS")
@NamedQueries({
    @NamedQuery(name = "email.findAllByPersonId",
            query = "SELECT e FROM Email e WHERE e.owner.id = :personId"),
    @NamedQuery(name = "email.findByAddress",
            query = "SELECT e FROM Email e WHERE e.address = :address")
})
public class Email implements Serializable {

    @Id
    @Column(name = "EMAIL_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ADDRESS", length = 50, nullable = false, updatable = false,
            unique = true)
    private String address;

    @Column(name = "CONFIRMED")
    private boolean confirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Person owner;

    public Email() {
    }

    public Email(String address) {
        this.address = address;
    }

    public Email(long id, String address, boolean confirmed, Person owner) {
        this.id = id;
        this.address = address;
        this.confirmed = confirmed;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (!person.getEmails().contains(this))
            person.addEmail(this);
    }

}
