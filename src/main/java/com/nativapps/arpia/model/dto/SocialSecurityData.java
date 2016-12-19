package com.nativapps.arpia.model.dto;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class SocialSecurityData {

    protected String nameEPS;

    protected String nameARL;

    protected String pensionEntity;

    public SocialSecurityData() {
    }

    public SocialSecurityData(String nameEPS, String nameARL,
            String pensionEntity) {
        this.nameEPS = nameEPS;
        this.nameARL = nameARL;
        this.pensionEntity = pensionEntity;
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
