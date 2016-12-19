package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "IDENTIFICATION", length = 15, unique = true)
    private String identification;

    @Column(name = "`NAME`", length = 60, nullable = false)
    private String name;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "GENDER")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Address> addresses;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Email> emails;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Phone> phones;

    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Calendar birthday;

    public Person() {
        this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public Person(String identification, String name, String lastName,
            Gender gender) {
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.emails = new ArrayList<>();
        this.phones = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public Person(String identification, String name, String lastName,
            Gender gender, Calendar birthday) {
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(Address address) {
        if (addresses == null)
            addresses = new ArrayList<>();

        if (address != null) {
            this.addresses.add(address);

            if (address.getPerson() != this)
                address.setPerson(this);
        }
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void addEmail(Email email) {
        if (emails == null)
            emails = new ArrayList<>();

        if (email != null) {
            this.emails.add(email);

            if (email.getOwner() != this)
                email.setOwner(this);
        }
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        if (phones == null)
            phones = new ArrayList<>();

        if (phone != null) {
            this.phones.add(phone);

            if (phone.getOwner() != this)
                phone.setOwner(this);
        }
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

}
