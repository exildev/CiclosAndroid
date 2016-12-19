package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "CREDIT_INFO")
public class CreditInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BILL")
    private boolean bill;

    @Column(name = "PAYMENT_CHECKOUT")
    private boolean paymentCheckout;

    @Column(name = "RETEFUENTE")
    private boolean retefuente;

    @Column(name = "RETEICA")
    private float reteica;

    @Column(name = "AUTHORIZED_CREDIT")
    private boolean authorizedCredit;
    
    @OneToOne
    @JoinColumn(name = "PERSON_IN_CHARGE")
    private Person personInCharge;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isBill() {
        return bill;
    }

    public void setBill(boolean bill) {
        this.bill = bill;
    }

    public boolean isPaymentCheckout() {
        return paymentCheckout;
    }

    public void setPaymentCheckout(boolean paymentCheckout) {
        this.paymentCheckout = paymentCheckout;
    }

    public boolean isRetefuente() {
        return retefuente;
    }

    public void setRetefuente(boolean retefuente) {
        this.retefuente = retefuente;
    }

    public float getReteica() {
        return reteica;
    }

    public void setReteica(float reteica) {
        this.reteica = reteica;
    }

    public boolean isAuthorizedCredit() {
        return authorizedCredit;
    }

    public void setAuthorizedCredit(boolean authorizedCredit) {
        this.authorizedCredit = authorizedCredit;
    }

    public Person getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(Person personInCharge) {
        this.personInCharge = personInCharge;
    }
}
