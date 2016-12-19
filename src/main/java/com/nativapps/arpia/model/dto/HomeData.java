package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.HomeOwnership;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 2.0
 */
public class HomeData {

    private Integer stratum;

    private Integer sonsNumber;

    private HomeOwnership homeownership;

    private Float monthlyExpenses;

    public HomeData() {
    }

    public HomeData(Integer stratum, Integer sonsNumber,
            HomeOwnership homeownership, Float monthlyExpenses) {
        this.stratum = stratum;
        this.sonsNumber = sonsNumber;
        this.homeownership = homeownership;
        this.monthlyExpenses = monthlyExpenses;
    }

    public Integer getStratum() {
        return stratum;
    }

    public void setStratum(Integer stratum) {
        this.stratum = stratum;
    }

    public Integer getSonsNumber() {
        return sonsNumber;
    }

    public void setSonsNumber(Integer sonsNumber) {
        this.sonsNumber = sonsNumber;
    }

    public HomeOwnership getHomeownership() {
        return homeownership;
    }

    public void setHomeownership(HomeOwnership homeownership) {
        this.homeownership = homeownership;
    }

    public Float getMonthlyExpenses() {
        return monthlyExpenses;
    }

    public void setMonthlyExpenses(Float monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }
}
