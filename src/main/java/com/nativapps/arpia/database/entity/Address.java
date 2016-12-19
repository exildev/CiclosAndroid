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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "ADDRESS")
@NamedQueries({
    @NamedQuery(name = "address.findAllByPersonId",
            query = "SELECT a FROM Address a WHERE a.person.id = :personId"),
    @NamedQuery(name = "address.findAllByEstablishmentId",
            query = "SELECT a FROM Address a WHERE "
            + "a.establishment.id = :establishmentId"),
    @NamedQuery(name = "address.findByPersonId",
            query = "SELECT a FROM Address a WHERE a.id = :id "
            + "AND a.person.id = :personId"),
    @NamedQuery(name = "address.findByEstablishmentId",
            query = "SELECT a FROM Address a WHERE a.id = :id AND "
            + "a.establishment.id = :establishmentId")
})
public class Address implements Serializable {

    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TAG", nullable = false, length = 10)
    private String tag;

    @Column(name = "RESIDENTIAL_ADDRESS", nullable = false, length = 150)
    private String residentialAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
    private Person person;
    
    @OneToOne
    @JoinColumn(name = "NEIGHBORHOOD")
    private Neighborhood neighborhood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTABLISHMENT_ID")
    private Establishment establishment;

    public Address() {
    }

    public Address(String tag, String residentialAddress, 
            Neighborhood neighborhood) {
        this.tag = tag;
        this.residentialAddress = residentialAddress;
        this.neighborhood = neighborhood;
    }

    public Address(long id, String tag, String residentialAddress,
            Neighborhood neighborhood) {
        this.id = id;
        this.tag = tag;
        this.residentialAddress = residentialAddress;
        this.neighborhood = neighborhood;
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

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;

        if (!person.getAddresses().contains(this)) {
            person.addAddress(this);
        }
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;

        if (!establishment.getAddresses().contains(this))
            establishment.addAddress(this);
    }

}
