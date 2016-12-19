package com.nativapps.arpia.model.dto;

import java.util.Calendar;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class BonusResponse extends BonusData {
    
    private Long id;
    
    private Boolean available;
    
    private Integer countFreeServiceAvailable;
    
    private Long authorizedBy;
    
    private Calendar registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getCountFreeServiceAvailable() {
        return countFreeServiceAvailable;
    }

    public void setCountFreeServiceAvailable(Integer countFreeServiceAvailable) {
        this.countFreeServiceAvailable = countFreeServiceAvailable;
    }

    public Long getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(Long authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public Calendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Calendar registerDate) {
        this.registerDate = registerDate;
    }     
}

