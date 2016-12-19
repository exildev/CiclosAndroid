package com.nativapps.arpia.model.dto;

import java.util.Calendar;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class BonusData {
    
    private Integer countFreeService;

    private Integer countFreeServiceUsed;

    private String reason;
    
    private Calendar expiryDate;

    public Integer getCountFreeService() {
        return countFreeService;
    }

    public void setCountFreeService(Integer countFreeService) {
        this.countFreeService = countFreeService;
    }

    public Integer getCountFreeServiceUsed() {
        return countFreeServiceUsed;
    }

    public void setCountFreeServiceUsed(Integer countFreeServiceUsed) {
        this.countFreeServiceUsed = countFreeServiceUsed;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }
}
