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
@Table(name = "SOCIAL_SECURITY")
public class SocialSecurity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME_EPS", length = 100, nullable = false)
    private String nameEPS;

    @Column(name = "NAME_ARL", length = 100, nullable = false)
    private String nameARL;

    @Column(name = "PENSION_ENTITY", length = 100, nullable = false)
    private String pensionEntity;

    public SocialSecurity() {
    }

    public SocialSecurity(String nameEPS, String nameARL,
            String pensionEntity) {
        this.nameEPS = nameEPS;
        this.nameARL = nameARL;
        this.pensionEntity = pensionEntity;
    }

    public SocialSecurity(long id, String nameEPS, String nameARL,
            String pensionEntity) {
        this.id = id;
        this.nameEPS = nameEPS;
        this.nameARL = nameARL;
        this.pensionEntity = pensionEntity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameEPS() {
        return nameEPS;
    }

    public void setNameEPS(String nameEPS) {
        this.nameEPS = nameEPS;
    }

    public String getNameARL() {
        return nameARL;
    }

    public void setNameARL(String nameARL) {
        this.nameARL = nameARL;
    }

    public String getPensionEntity() {
        return pensionEntity;
    }

    public void setPensionEntity(String pensionEntity) {
        this.pensionEntity = pensionEntity;
    }

}
