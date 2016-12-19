package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.1
 */
@Entity
@Table(name = "HOME")
public class Home implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "STRATUM", nullable = false)
    private int stratum;

    @Column(name = "SONS_NUMBER", nullable = false)
    private int sonsNumber;

    @Column(name = "HOMEOWNERSHIP", nullable = false)
    private HomeOwnership homeownership;

    @Column(name = "MONTHLY_EXPENSES", nullable = false)
    private float monthlyExpenses;

    public Home() {
    }

    public Home(int stratum, int sonsNumber, HomeOwnership homeownership,
            float monthlyExpenses) {
        this.stratum = stratum;
        this.sonsNumber = sonsNumber;
        this.homeownership = homeownership;
        this.monthlyExpenses = monthlyExpenses;
    }

    public Home(long id, int stratum, int sonsNumber,
            HomeOwnership homeownership, float monthlyExpenses) {
        this.id = id;
        this.stratum = stratum;
        this.sonsNumber = sonsNumber;
        this.homeownership = homeownership;
        this.monthlyExpenses = monthlyExpenses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStratum() {
        return stratum;
    }

    public void setStratum(int stratum) {
        this.stratum = stratum;
    }

    public int getSonsNumber() {
        return sonsNumber;
    }

    public void setSonsNumber(int sonsNumber) {
        this.sonsNumber = sonsNumber;
    }

    public HomeOwnership getHomeownership() {
        return homeownership;
    }

    public void setHomeownership(HomeOwnership homeownership) {
        this.homeownership = homeownership;
    }

    public float getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(float monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

}
