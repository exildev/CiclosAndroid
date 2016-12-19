package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 2.0.1
 */
@Entity
@Table(name = "CURRICULUM_VITAE")
public class CurriculumVitae implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SOCIAL_SECURITY_ID")
    private SocialSecurity socialSecurity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOME_ID")
    private Home home;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.PERSIST)
    private List<Reference> references;

    @Column(name = "CIVIL_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private CivilStatus civilStatus;

    @Column(name = "BIRTH_CITY", length = 50, nullable = false)
    private String birthCity;

    @Column(name = "ACADEMIC_LEVEL", nullable = false)
    @Enumerated(EnumType.STRING)
    private AcademicLevel academicLevel;

    @Column(name = "MILITARY_CARD", length = 15)
    private String militaryCard;

    @Column(name = "DISTRICT", length = 70)
    private String district;

    public CurriculumVitae() {
    }

    public CurriculumVitae(SocialSecurity socialSecurity, Home home,
            List<Reference> references, CivilStatus civilStatus,
            String birthCity, AcademicLevel academicLevel, String militaryCard,
            String district) {
        this.socialSecurity = socialSecurity;
        this.home = home;
        this.references = references;
        this.civilStatus = civilStatus;
        this.birthCity = birthCity;
        this.academicLevel = academicLevel;
        this.militaryCard = militaryCard;
        this.district = district;
    }

    public CurriculumVitae(long id, SocialSecurity socialSecurity, Home home,
            List<Reference> references, CivilStatus civilStatus,
            String birthCity, AcademicLevel academicLevel, String militaryCard,
            String district) {
        this.id = id;
        this.socialSecurity = socialSecurity;
        this.home = home;
        this.references = references;
        this.civilStatus = civilStatus;
        this.birthCity = birthCity;
        this.academicLevel = academicLevel;
        this.militaryCard = militaryCard;
        this.district = district;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SocialSecurity getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(SocialSecurity socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
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
