package com.nativapps.arpia.model.dto;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class CreditInfoData {

    protected Boolean bill;

    protected Boolean paymentCheckout;

    protected Boolean retefuente;

    protected Float reteica;

    protected Boolean authorizedCredit;

    protected Long personInCharge;

    public Boolean getBill() {
        return bill;
    }

    public void setBill(Boolean bill) {
        this.bill = bill;
    }

    public Boolean getPaymentCheckout() {
        return paymentCheckout;
    }

    public void setPaymentCheckout(Boolean paymentCheckout) {
        this.paymentCheckout = paymentCheckout;
    }

    public Boolean getRetefuente() {
        return retefuente;
    }

    public void setRetefuente(Boolean retefuente) {
        this.retefuente = retefuente;
    }

    public Float getReteica() {
        return reteica;
    }

    public void setReteica(Float reteica) {
        this.reteica = reteica;
    }

    public Boolean getAuthorizedCredit() {
        return authorizedCredit;
    }

    public void setAuthorizedCredit(Boolean authorizedCredit) {
        this.authorizedCredit = authorizedCredit;
    }

    public Long getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(Long personInCharge) {
        this.personInCharge = personInCharge;
    }

}
