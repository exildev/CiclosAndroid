package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.AcademicLevel;
import com.nativapps.arpia.database.entity.CivilStatus;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 2.0
 */
public class CurriculumVitaeData {

    protected SocialSecurityData socialSecurity;

    protected HomeData home;

    protected CivilStatus civilStatus;

    protected String birthCity;

    protected AcademicLevel academicLevel;

    protected String militaryCard;

    protected String district;

    public CurriculumVitaeData() {
    }

    public CurriculumVitaeData(SocialSecurityData socialSecurity, HomeData home,
            CivilStatus civilStatus, String birthCity,
            AcademicLevel academicLevel, String militaryCard, String district) {
        this.socialSecurity = socialSecurity;
        this.home = home;
        this.civilStatus = civilStatus;
        this.birthCity = birthCity;
        this.academicLevel = academicLevel;
        this.militaryCard = militaryCard;
        this.district = district;
    }

    public SocialSecurityData getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(SocialSecurityData socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public HomeData getHome() {
        return home;
    }

    public void setHome(HomeData home) {
        this.home = home;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public AcademicLevel getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(AcademicLevel academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getMilitaryCard() {
        return militaryCard;
    }

    public void setMilitaryCard(String militaryCard) {
        this.militaryCard = militaryCard;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

}
